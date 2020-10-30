package com.task.controllers.departament;

import com.task.dao.jdbc.DepartmentDAO;
import com.task.dao.jdbc.impl.DepartmentJDBCImpl;
import com.task.model.Department;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author Mikhail Terletskyi
 * @Create 10/27/2020
 * @Extends of {@link HttpServlet} class.
 */

public class DepartmentsController extends HttpServlet {

    private DepartmentDAO departmentJDBC = new DepartmentJDBCImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Set<Department> departments = departmentJDBC.getAll();
            departments.addAll(departmentJDBC.getAll());
            req.setAttribute("departments", departments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/views/department/departments.jsp").forward(req, resp);
    }
}