package com.task.dao.jdbc.impl;

import com.task.dao.jdbc.EmployeeDAO;
import com.task.jdbc.Util;
import com.task.model.Employee;

import java.io.Serializable;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * @Extends of {@link Util} class.
 * Implementation of {@link Serializable} interface.
 */

public class EmployeeJDBCImpl extends Util implements EmployeeDAO {

    private static final String CREATE_EMPLOYEE_QUERY = "INSERT INTO test_task_db.employee (first_name, last_name, email, salary_per_hour, date_of_birth) VALUES (?, ?, ?, ?, ?)";

    private static final String GET_EMPLOYEE_QUERY = "SELECT employee_id, first_name, last_name, email, salary_per_hour, date_of_birth FROM test_task_db.employee WHERE employee_id = ?";

    private static final String GET_ALL_EMPLOYEES_QUERY = "SELECT * FROM test_task_db.employee";

    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE test_task_db.employee SET first_name = ?, last_name = ?, email = ?, salary_per_hour = ?, date_of_birth = ? WHERE employee_id = ?";

    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM test_task_db.employee WHERE employee_id = ?";


    public static final String EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE = "Could not get employee by ID from database. ";

    public static final String EMPLOYEE_WITH_SIMILAR_EMAIL_IS_ALREADY_EXISTS = "Employee with similar email is already exists";

    public static final String INVALID_EMAIL = "Invalid email ";

    public static final String FIRST_NAME_LENGTH_LESS_2_CHARACTERS_OR_BEYOND_30_CHARACTERS_OR_HAS_DIGITS = "First name length less 2 characters or beyond 30 characters or has digits";

    public static final String LAST_NAME_LENGTH_LESS_2_CHARACTERS_OR_BEYOND_30_CHARACTERS_OR_HAS_DIGITS = "Last name length less 2 characters or beyond 30 characters or has digits";

    public static final String ONLY_NUMERIC_VALUES_ARE_ALLOWED_IN_SALARY_PER_HOUR_FIELD = "Only numeric values are allowed in Salary Per Hour field";

    public static final String EMPLOYEE_IS_NOT_ADULT = "Employee Is Not Adult";

    private Connection connection = getConnection();


    @Override
    public void create(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EMPLOYEE_QUERY)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setBigDecimal(4, employee.getSalaryPerHour());
            preparedStatement.setDate(5, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> getById(Long id) throws SQLException {
        Employee employee = new Employee();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_QUERY);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setSalaryPerHour(resultSet.getBigDecimal("salary_per_hour"));
                employee.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(employee);
    }

    @Override
    public Set<Employee> getAll() {
        Set<Employee> employees = new TreeSet<>((o1, o2) -> (int) (o1.getId() - o2.getId()));
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_EMPLOYEES_QUERY);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setSalaryPerHour(resultSet.getBigDecimal("salary_per_hour"));
                employee.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setBigDecimal(4, employee.getSalaryPerHour());
            preparedStatement.setDate(5, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setLong(6, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pat attention you can't delete employee if his ID contains in department_employees table that will throw SQLIntegrityConstraintViolationException
     * Use deleteEmployee() in DepartmentJDBCImpl before this method
     */

    @Override
    public void delete(Long employee_id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
            preparedStatement.setLong(1, employee_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}