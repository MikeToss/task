package com.task.controllers.employee;

import com.task.core.Helper;
import com.task.dao.dataInput.EmployeeDataInputListenerDAO;
import com.task.dao.dataInput.impl.EmployeeDataInputListenerImpl;
import com.task.dao.jdbc.DepartmentDAO;
import com.task.dao.jdbc.EmployeeDAO;
import com.task.dao.jdbc.impl.DepartmentJDBCImpl;
import com.task.dao.jdbc.impl.EmployeeJDBCImpl;
import com.task.exeptions.DataProcessingException;
import com.task.model.Department;
import com.task.model.Employee;
import com.task.model.dataInput.EmployeeDataInputListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static com.task.controllers.departament.ViewDepartmentEmployeesController.DEPARTMENT_ID;
import static com.task.core.Helper.UPDATE_CONTROLLER;
import static com.task.dao.jdbc.impl.DepartmentJDBCImpl.EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE;
import static com.task.dao.jdbc.impl.EmployeeJDBCImpl.EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE;

/**
 * @author Mikhail Terletskyi
 * @Create 10/28/2020
 * @Extends of {@link HttpServlet} class.
 */

public class UpdateEmployeeController extends HttpServlet {
    private EmployeeDataInputListenerDAO employeeDataInputListener = new EmployeeDataInputListenerImpl();
    private DepartmentDAO departmentJDBC = new DepartmentJDBCImpl();
    private EmployeeDAO employeeJDBC = new EmployeeJDBCImpl();
    private static final String URL = "/WEB-INF/views/employee/update_employee.jsp";
    private static Long EMPLOYEE_ID;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        EMPLOYEE_ID = Long.parseLong(id);
        Employee employee = null;
        Department currentDepartment = null;
        try {
            currentDepartment = departmentJDBC.getById(DEPARTMENT_ID)
                    .orElseThrow(() ->
                            new DataProcessingException(EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE,
                                    new SQLException()));
            employee = employeeJDBC.getById(EMPLOYEE_ID)
                    .orElseThrow(() ->
                            new DataProcessingException(EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE,
                                    new SQLException()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert employee != null;
        req.setAttribute("firstName", employee.getFirstName());
        req.setAttribute("lastName", employee.getLastName());
        req.setAttribute("email", employee.getEmail());
        req.setAttribute("salaryPerHour", employee.getSalaryPerHour());
        req.setAttribute("dateOfBirth", employee.getDateOfBirth());

        req.setAttribute("employeeID", EMPLOYEE_ID);
        req.setAttribute("departmentID", currentDepartment.getId());
        req.setAttribute("departmentTitle", currentDepartment.getTitle());
        req.getRequestDispatcher(URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isEmployeeWithSimilarEmailIsAlreadyExists = false;
        boolean isFirstNameLengthInvalid = false;
        boolean isLastNameLengthInvalid = false;
        boolean isEmail = true;
        boolean isSalary;
        boolean isAnAdult = false;

        String firstName = req.getParameter("firstName").trim();
        String lastName = req.getParameter("lastName").trim();
        String email = req.getParameter("email").trim();
        String salaryPerHour = req.getParameter("salaryPerHour").trim();
        String dateOfBirth = req.getParameter("dateOfBirth");

        try {
            Employee employee = employeeJDBC.getById(EMPLOYEE_ID).orElseThrow(() ->
                    new DataProcessingException(EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE,
                            new SQLException()));

            if (!email.isEmpty()) {
                for (Employee currentEmployee : employeeJDBC.getAll()) {
                    isEmployeeWithSimilarEmailIsAlreadyExists = email.trim().equalsIgnoreCase(currentEmployee.getEmail());
                    if (isEmployeeWithSimilarEmailIsAlreadyExists) break;
                }
            }

            if (!firstName.isEmpty()) {
                isFirstNameLengthInvalid = !(firstName.length() >= 2 && firstName.length() <= 30
                        && !Helper.stringHasDigits(firstName));
            }
            if (!lastName.isEmpty()) {
                isLastNameLengthInvalid = !(lastName.length() >= 2 && lastName.length() <= 30
                        && !Helper.stringHasDigits(lastName));
            }

            if (!email.isEmpty()) isEmail = Helper.isEmail(email);

            if (salaryPerHour.isEmpty()) isSalary = true;
            else isSalary = !Helper.stringHasLetters(salaryPerHour);

            if (!dateOfBirth.isEmpty())
                isAnAdult = Helper.getTimeLength(LocalDate.parse(dateOfBirth), ChronoUnit.YEARS) >= 18;

            EmployeeValidation.validate(req, resp, employeeDataInputListener, URL, firstName, lastName, email,
                    salaryPerHour, dateOfBirth, isEmployeeWithSimilarEmailIsAlreadyExists, isFirstNameLengthInvalid,
                    isLastNameLengthInvalid, isSalary, isEmail, isAnAdult, UPDATE_CONTROLLER);

            if (!isEmployeeWithSimilarEmailIsAlreadyExists && !isFirstNameLengthInvalid && !isLastNameLengthInvalid &&
                    isEmail && isSalary && isAnAdult) {

                employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email,
                        salaryPerHour, dateOfBirth, false, "No reasons",
                        UPDATE_CONTROLLER, LocalDate.now(), LocalTime.now()));
                req.getRequestDispatcher(URL).forward(req, resp);

                if (!firstName.isEmpty()) employee.setFirstName(firstName);
                if (!lastName.isEmpty()) employee.setLastName(lastName);
                if (!email.isEmpty()) employee.setEmail(email);
                BigDecimal salary;
                if (salaryPerHour.length() == 0) salary = BigDecimal.valueOf(0L);
                else salary = BigDecimal.valueOf(Double.parseDouble(salaryPerHour));
                employee.setSalaryPerHour(salary);
                if (!dateOfBirth.isEmpty()) employee.setDateOfBirth(LocalDate.parse(dateOfBirth));

                departmentJDBC.updateEmployee(DEPARTMENT_ID, employee);
            }
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view_employee?id=" + DEPARTMENT_ID);
    }
}