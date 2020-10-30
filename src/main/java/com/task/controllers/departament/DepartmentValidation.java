package com.task.controllers.departament;

import com.task.dao.dataInput.DepartmentDataInputListenerDAO;
import com.task.model.dataInput.DepartmentDataInputListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.task.dao.jdbc.impl.DepartmentJDBCImpl.DEPARTMENT_WITH_THIS_TITLE_IS_ALREADY_EXISTS;
import static com.task.dao.jdbc.impl.DepartmentJDBCImpl.TITLE_LENGTH_LESS_THAN_2_CHARACTERS_OR_BEYOND_50_CHARACTERS;

/**
 * @author Mikhail Terletskyi
 * @Create 10/29/2020
 */

class DepartmentValidation {
    static void validate(HttpServletRequest req, HttpServletResponse resp,
                         boolean isDepartmentAlreadyExists, boolean isTitleLengthInvalid,
                         DepartmentDataInputListenerDAO departmentDataInputListener, String title,
                         String URL, String controller) throws IOException {
        try {
            if (isDepartmentAlreadyExists) {
                req.setAttribute("departmentAlreadyExistsMessage", DEPARTMENT_WITH_THIS_TITLE_IS_ALREADY_EXISTS);
                departmentDataInputListener.add(new DepartmentDataInputListener(title, true,
                        DEPARTMENT_WITH_THIS_TITLE_IS_ALREADY_EXISTS, controller, LocalDate.now(), LocalTime.now()));
                req.getRequestDispatcher(URL).forward(req, resp);
            }

            if (isTitleLengthInvalid) {
                req.setAttribute("isTitleLengthInvalid", TITLE_LENGTH_LESS_THAN_2_CHARACTERS_OR_BEYOND_50_CHARACTERS);
                departmentDataInputListener.add(new DepartmentDataInputListener(title, true,
                        TITLE_LENGTH_LESS_THAN_2_CHARACTERS_OR_BEYOND_50_CHARACTERS, controller, LocalDate.now(),
                        LocalTime.now()));
                req.getRequestDispatcher(URL).forward(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}