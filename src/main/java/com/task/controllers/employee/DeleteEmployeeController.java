package com.task.controllers.employee;

import com.task.dao.jdbc.DepartmentDAO;
import com.task.dao.jdbc.impl.DepartmentJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.task.controllers.departament.ViewDepartmentEmployeesController.DEPARTMENT_ID;

/**
 * @author Mikhail Terletskyi
 * @Create 10/27/2020
 * @Extends of {@link HttpServlet} class.
 */

public class DeleteEmployeeController extends HttpServlet {

    private DepartmentDAO departmentJDBC = new DepartmentJDBCImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            departmentJDBC.deleteEmployee(DEPARTMENT_ID, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("DEPARTMENT_ID", DEPARTMENT_ID);
        req.getRequestDispatcher("/WEB-INF/views/employee/delete_employee.jsp").forward(req, resp);
    }
}