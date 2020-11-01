package com.task.controllers.employee;

import com.task.dao.dataInput.EmployeeDataInputListenerDAO;
import com.task.model.dataInput.EmployeeDataInputListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.task.dao.jdbc.impl.EmployeeJDBCImpl.*;

/**
 * @author Mikhail Terletskyi
 * @Create 10/29/2020
 */

class EmployeeValidation {
    static void validate(HttpServletRequest req, HttpServletResponse resp,
                         EmployeeDataInputListenerDAO employeeDataInputListener,
                         String URL, String firstName, String lastName, String email, String salaryPerHour,
                         String dateOfBirth, boolean isEmployeeWithSimilarEmailIsAlreadyExists,
                         boolean isFirstNameLengthInvalid, boolean isLastNameLengthInvalid,
                         boolean isSalary, boolean isEmail, boolean isAnAdult, String controllerOFListener) throws ServletException, IOException {
        if (isFirstNameLengthInvalid) {
            req.setAttribute("firstNameLengthBeyond30CharactersOrHasDigitsMessage",
                    FIRST_NAME_LENGTH_LESS_2_CHARACTERS_OR_BEYOND_30_CHARACTERS_OR_HAS_DIGITS);
            employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email,
                    salaryPerHour, dateOfBirth, true,
                    FIRST_NAME_LENGTH_LESS_2_CHARACTERS_OR_BEYOND_30_CHARACTERS_OR_HAS_DIGITS, controllerOFListener,
                    LocalDate.now(), LocalTime.now()));
            req.getRequestDispatcher(URL).forward(req, resp);
        }
        if (isLastNameLengthInvalid) {
            req.setAttribute("lastNameLengthBeyond30CharactersOrHasDigitsMessage",
                    LAST_NAME_LENGTH_LESS_2_CHARACTERS_OR_BEYOND_30_CHARACTERS_OR_HAS_DIGITS);
            employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email,
                    salaryPerHour, dateOfBirth, true,
                    LAST_NAME_LENGTH_LESS_2_CHARACTERS_OR_BEYOND_30_CHARACTERS_OR_HAS_DIGITS, controllerOFListener,
                    LocalDate.now(), LocalTime.now()));
            req.getRequestDispatcher(URL).forward(req, resp);
        }
        if (!isEmail) {
            req.setAttribute("invalidEmailMessage", INVALID_EMAIL);
            employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email, salaryPerHour,
                    dateOfBirth, true, INVALID_EMAIL, controllerOFListener, LocalDate.now(),
                    LocalTime.now()));
            req.getRequestDispatcher(URL).forward(req, resp);
        }
        if (isEmployeeWithSimilarEmailIsAlreadyExists) {
            req.setAttribute("employeeWithSimilarEmailIsAlreadyExistsMessage",
                    EMPLOYEE_WITH_SIMILAR_EMAIL_IS_ALREADY_EXISTS);
            employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email, salaryPerHour,
                    dateOfBirth, true, EMPLOYEE_WITH_SIMILAR_EMAIL_IS_ALREADY_EXISTS,
                    controllerOFListener, LocalDate.now(), LocalTime.now()));
            req.getRequestDispatcher(URL).forward(req, resp);
        }
        if (!isSalary) {
            req.setAttribute("onlyNumericValuesAreAllowedInSalaryPerHourField", ONLY_NUMERIC_VALUES_ARE_ALLOWED_IN_SALARY_PER_HOUR_FIELD);
            employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email,
                    salaryPerHour, dateOfBirth, true,
                    ONLY_NUMERIC_VALUES_ARE_ALLOWED_IN_SALARY_PER_HOUR_FIELD, controllerOFListener, LocalDate.now(),
                    LocalTime.now()));
            req.getRequestDispatcher(URL).forward(req, resp);
        }
        if (!isAnAdult) {
            req.setAttribute("employeeIsNotAdult", EMPLOYEE_IS_NOT_ADULT);
            employeeDataInputListener.add(new EmployeeDataInputListener(firstName, lastName, email,
                    salaryPerHour, dateOfBirth, true, EMPLOYEE_IS_NOT_ADULT, controllerOFListener,
                    LocalDate.now(), LocalTime.now()));
            req.getRequestDispatcher(URL).forward(req, resp);
        }
    }
}