package com.task.dao.dataInput.impl;

import com.task.dao.dataInput.DepartmentDataInputListenerDAO;
import com.task.jdbc.Util;
import com.task.model.dataInput.DepartmentDataInputListener;

import java.sql.*;

/**
 * @author Mikhail Terletskyi
 * @Create 10/28/2020
 * @Extends of {@link Util} class.
 * Implementation of {@link DepartmentDataInputListenerDAO} interface.
 */

public class DepartmentDataInputListenerImpl extends Util implements DepartmentDataInputListenerDAO {

    private static final String CREATE_DEPARTMENT_DATA_INPUT_LISTENER_QUERY =
            "INSERT INTO test_task_db.department_validation_attempts (department_title, is_validation_failed, reason_of_failed_validation, controller_of_listener, date_of_input,time_of_input) VALUES (?, ?, ?, ?, ?, ?)";

    private Connection connection = getConnection();

    @Override
    public void add(DepartmentDataInputListener departmentDataInputListener) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DEPARTMENT_DATA_INPUT_LISTENER_QUERY)) {
            preparedStatement.setString(1, departmentDataInputListener.getDepartmentTitle());
            preparedStatement.setBoolean(2, departmentDataInputListener.isValidationFailed());
            preparedStatement.setString(3, departmentDataInputListener.getReasonOfFailedValidation());
            preparedStatement.setString(4, departmentDataInputListener.getControllerOFListener());
            preparedStatement.setDate(5, Date.valueOf(departmentDataInputListener.getDateOfInput()));
            preparedStatement.setTime(6, Time.valueOf(departmentDataInputListener.getTimeOfInput()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}