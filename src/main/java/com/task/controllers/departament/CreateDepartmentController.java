package com.task.controllers.departament;

import com.task.dao.dataInput.DepartmentDataInputListenerDAO;
import com.task.dao.dataInput.impl.DepartmentDataInputListenerImpl;
import com.task.dao.jdbc.DepartmentDAO;
import com.task.dao.jdbc.impl.DepartmentJDBCImpl;
import com.task.model.Department;
import com.task.model.dataInput.DepartmentDataInputListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.task.core.Helper.CREATE_CONTROLLER;

/**
 * @author Mikhail Terletskyi
 * @Create 10/27/2020
 * @Extends of {@link HttpServlet} class.
 */

public class CreateDepartmentController extends HttpServlet {

    private DepartmentDataInputListenerDAO departmentDataInputListener = new DepartmentDataInputListenerImpl();
    private DepartmentDAO departmentJDBC = new DepartmentJDBCImpl();
    private static final String URL = "/WEB-INF/views/department/create_department.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title").trim();
        try {
            boolean isDepartmentAlreadyExists = departmentJDBC.getAll()
                    .stream()
                    .anyMatch(department -> title.trim().equalsIgnoreCase(department.getTitle()));
            boolean isTitleLengthInvalid = !(title.length() >= 2 && title.length() <= 50);

            DepartmentValidation.validate(req, resp, isDepartmentAlreadyExists, isTitleLengthInvalid,
                    departmentDataInputListener, title, URL, CREATE_CONTROLLER);

            if (!isDepartmentAlreadyExists && !isTitleLengthInvalid) {
                departmentDataInputListener.add(new DepartmentDataInputListener(title, false,
                        "No reasons", CREATE_CONTROLLER, LocalDate.now(), LocalTime.now()));
                departmentJDBC.create(new Department(title));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/departments");
    }
}
