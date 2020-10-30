package com.task.controllers.departament;

import com.task.dao.jdbc.DepartmentDAO;
import com.task.dao.jdbc.impl.DepartmentJDBCImpl;
import com.task.exeptions.DataProcessingException;
import com.task.model.Department;
import com.task.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import static com.task.dao.jdbc.impl.DepartmentJDBCImpl.EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE;

/**
 * @author Mikhail Terletskyi
 * @Create 10/27/2020
 * @Extends of {@link HttpServlet} class.
 */

public class ViewDepartmentEmployeesController extends HttpServlet {

    private DepartmentDAO departmentJDBC = new DepartmentJDBCImpl();
    public static Long DEPARTMENT_ID = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");

            Department department = departmentJDBC.getById(Long.parseLong(id)).orElseThrow(() ->
                    new DataProcessingException(EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE, new SQLException()));
            String departTitle = department.getTitle();
            DEPARTMENT_ID = department.getId();

            Set<Employee> departmentEmployees = departmentJDBC.getById(Long.parseLong(id)).get().getDepartmentEmployees();

            req.setAttribute("departmentEmployees", departmentEmployees);
            req.setAttribute("departTitle", departTitle);
            req.setAttribute("id", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/views/department/view_department_employees.jsp").forward(req, resp);
    }
}