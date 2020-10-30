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
import static com.task.core.Helper.CREATE_CONTROLLER;
import static com.task.dao.jdbc.impl.DepartmentJDBCImpl.EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * @Extends of {@link HttpServlet} class.
 */

public class CreateEmployeeController extends HttpServlet {

    private EmployeeDataInputListenerDAO employeeDataInputListener = new EmployeeDataInputListenerImpl();
    private DepartmentDAO departmentJDBC = new DepartmentJDBCImpl();
    private EmployeeDAO employeeJDBC = new EmployeeJDBCImpl();
    private static final String URL = "/WEB-INF/views/employee/create_employee.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentTitle = null;
        try {
            Department currentDepartment = departmentJDBC.getById(DEPARTMENT_ID).
                    orElseThrow(() -> new DataProcessingException(EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE,
                            new SQLException()));
            departmentTitle = currentDepartment.getTitle();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("departmentID", DEPARTMENT_ID);
        req.setAttribute("departmentTitle", departmentTitle);
        req.getRequestDispatcher(URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isEmployeeWithSimilarEmailIsAlreadyExists = false;
        boolean isSalary;
        boolean isAnAdult = false;

        String firstName = req.getParameter("firstName").trim();
        String lastName = req.getParameter("lastName").trim();
        String email = req.getParameter("email").trim();
        String salaryPerHour = req.getParameter("salaryPerHour");
        String dateOfBirth = req.getParameter("dateOfBirth");

        try {
            for (Employee employee : employeeJDBC.getAll()) {
                isEmployeeWithSimilarEmailIsAlreadyExists = email.trim().equalsIgnoreCase(employee.getEmail());
                if (isEmployeeWithSimilarEmailIsAlreadyExists) break;
            }

            boolean isFirstNameLengthInvalid = !(firstName.length() >= 2 && firstName.length() <= 30 && !Helper.stringHasDigits(firstName));
            boolean isLastNameLengthInvalid = !(lastName.length() >= 2 && lastName.length() <= 30 && !Helper.stringHasDigits(lastName));
            boolean isEmail = Helper.isEmail(email);

            if (salaryPerHour.isEmpty()) isSalary = true;
            else isSalary = !Helper.stringHasLetters(salaryPerHour);

            if(!dateOfBirth.isEmpty()) isAnAdult = Helper.getTimeLength(LocalDate.parse(dateOfBirth), ChronoUnit.YEARS) >= 18;

            EmployeeValidation.validate(req, resp, employeeDataInputListener, URL, firstName, lastName, email,
                    salaryPerHour, dateOfBirth, isEmployeeWithSimilarEmailIsAlreadyExists, isFirstNameLengthInvalid,
                    isLastNameLengthInvalid, isSalary, isEmail, isAnAdult, CREATE_CONTROLLER);

            if (!isEmployeeWithSimilarEmailIsAlreadyExists && !isFirstNameLengthInvalid && !isLastNameLengthInvalid &&
                    isEmail && isSalary && isAnAdult) {
                employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email,
                        salaryPerHour, dateOfBirth,
                        false, "No reasons", CREATE_CONTROLLER,
                        LocalDate.now(), LocalTime.now()));
                req.getRequestDispatcher(URL).forward(req, resp);

                BigDecimal salary;
                if (salaryPerHour.length() == 0) salary = BigDecimal.valueOf(0L);
                else salary = BigDecimal.valueOf(Double.parseDouble(salaryPerHour));
                departmentJDBC.createEmployee(DEPARTMENT_ID, new Employee(firstName, lastName, email, salary,
                        LocalDate.parse(dateOfBirth)));
            }
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view_employee?id=" + DEPARTMENT_ID);
    }
}