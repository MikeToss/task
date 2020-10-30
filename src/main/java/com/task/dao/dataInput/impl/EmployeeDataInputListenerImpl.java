package com.task.dao.dataInput.impl;

import com.task.dao.dataInput.EmployeeDataInputListenerDAO;
import com.task.jdbc.Util;
import com.task.model.dataInput.EmployeeDataInputListener;

import java.sql.*;

/**
 * @author Mikhail Terletskyi
 * @Create 10/29/2020
 * @Extends of {@link Util} class.
 * Implementation of {@link EmployeeDataInputListenerDAO} interface.
 */

public class EmployeeDataInputListenerImpl extends Util implements EmployeeDataInputListenerDAO {

    private static final String CREATE_EMPLOYEE_DATA_INPUT_LISTENER_QUERY =
            "INSERT INTO test_task_db.employee_validation_attempts (first_name, last_name, email, salary_per_hour, date_of_birth, is_validation_failed, reason_of_failed_validation, controller_of_listener, date_of_input, time_of_input ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private Connection connection = getConnection();

    @Override
    public void add(EmployeeDataInputListener employeeDataInputListener) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EMPLOYEE_DATA_INPUT_LISTENER_QUERY)) {
            preparedStatement.setString(1, employeeDataInputListener.getFirstName());
            preparedStatement.setString(2, employeeDataInputListener.getLastName());
            preparedStatement.setString(3, employeeDataInputListener.getEmail());
            preparedStatement.setString(4, employeeDataInputListener.getSalaryPerHour());
            preparedStatement.setString(5, employeeDataInputListener.getDateOfBirth());
            preparedStatement.setBoolean(6, employeeDataInputListener.isValidationFailed());
            preparedStatement.setString(7, employeeDataInputListener.getReasonOfFailedValidation());
            preparedStatement.setString(8, employeeDataInputListener.getControllerOFListener());
            preparedStatement.setDate(9, Date.valueOf(employeeDataInputListener.getDateOfInput()));
            preparedStatement.setTime(10, Time.valueOf(employeeDataInputListener.getTimeOfInput()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}