package com.task.controllers.departament;

import com.task.dao.jdbc.DepartmentDAO;
import com.task.dao.jdbc.impl.DepartmentJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Mikhail Terletskyi
 * @Create 10/27/2020
 * @Extends of {@link HttpServlet} class.
 */

public class DeleteDepartmentController extends HttpServlet {

    private DepartmentDAO departmentJDBC = new DepartmentJDBCImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            departmentJDBC.delete(Long.parseLong(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("id", id);
        req.getRequestDispatcher("/WEB-INF/views/department/delete_department.jsp").forward(req, resp);
    }
}