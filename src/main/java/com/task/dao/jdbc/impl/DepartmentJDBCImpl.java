package com.task.dao.jdbc.impl;

import com.task.dao.jdbc.DepartmentDAO;
import com.task.exeptions.DataProcessingException;
import com.task.jdbc.Util;
import com.task.model.Department;
import com.task.model.Employee;

import java.io.Serializable;
import java.sql.*;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static com.task.dao.jdbc.impl.EmployeeJDBCImpl.EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE;

/**
 * @author Mikhail Terletskyi
 * @Create 10/27/2020
 * @Extends of {@link Util} class.
 * Implementation of {@link Serializable} interface.
 */

public class DepartmentJDBCImpl extends Util implements DepartmentDAO {

    private static final String CREATE_DEPARTMENT_QUERY = "INSERT INTO test_task_db.department(title) VALUES (?)";

    private static final String GET_DEPARTMENT_QUERY = "SELECT department_id, title FROM test_task_db.department WHERE department_id = ?";

    private static final String GET_ALL_DEPARTMENTS_QUERY = "SELECT * FROM test_task_db.department";

    private static final String UPDATE_DEPARTMENT_QUERY = "UPDATE test_task_db.department SET title = ? WHERE department_id = ?";

    private static final String DELETE_DEPARTMENT_QUERY = "DELETE FROM test_task_db.department WHERE department_id = ?";


    private static final String GET_ALL_EMPLOYEES_FOR_DEPARTMENT_QUERY = "SELECT * FROM test_task_db.department_employees WHERE department_id = ?";

    private static final String CREATE_EMPLOYEE_FOR_DEPARTMENT_QUERY = "INSERT INTO test_task_db.department_employees (department_id, employee_id) VALUES (? , ?)";

    private static final String DELETE_ALL_EMPLOYEES_FOR_DEPARTMENT_QUERY = "DELETE FROM test_task_db.department_employees WHERE department_id = ?";

    private static final String DELETE_EMPLOYEE_FOR_DEPARTMENT_QUERY = "DELETE FROM test_task_db.department_employees WHERE department_id = ? AND employee_id = ?";


    public static final String EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE = "Could not get department by ID from database. ";

    public static final String DEPARTMENT_WITH_THIS_TITLE_IS_ALREADY_EXISTS = "Department with this title is already exists ";

    public static final String TITLE_LENGTH_LESS_THAN_2_CHARACTERS_OR_BEYOND_50_CHARACTERS = "Title length less 2 characters or beyond 50 characters";


    private Connection connection = getConnection();

    private EmployeeJDBCImpl employeeJDBC = new EmployeeJDBCImpl();
    /**
     * DEPARTMENT OPERATIONS BLOCK
     */

    @Override
    public void create(Department element) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DEPARTMENT_QUERY)) {
            preparedStatement.setString(1, element.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Department> getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Department department = new Department();
        try {
            preparedStatement = connection.prepareStatement(GET_DEPARTMENT_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department.setId(resultSet.getLong("department_id"));
                department.setTitle(resultSet.getString("title"));
                Set<Employee> allEmployeesOfDepartment = getAllEmployeesOfDepartment(department.getId());
                department.setDepartmentEmployees(allEmployeesOfDepartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(department);
    }

    @Override
    public Set<Department> getAll() throws SQLException {
        Set<Department> departments = new TreeSet<>((o1, o2) -> (int) (o1.getId() - o2.getId()));
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_DEPARTMENTS_QUERY);
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getLong("department_id"));
                department.setTitle(resultSet.getString("title"));
                Set<Employee> allEmployeesOfDepartment = getAllEmployeesOfDepartment(department.getId());
                department.setDepartmentEmployees(allEmployeesOfDepartment);
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public void update(Department department) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT_QUERY)) {
            preparedStatement.setString(1, department.getTitle());
            preparedStatement.setLong(2, department.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pay Attention, employees are not removed from database, you can find them after void delete().
     * This method delete department and remove records from department_employees table
     */
    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_EMPLOYEES_FOR_DEPARTMENT_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * EMPLOYEES OPERATIONS BLOCK
     */

    @Override
    public void createEmployee(Long department_id, Employee employee) throws SQLException {
        employeeJDBC.create(employee);
        Employee employeeForDepartment = employeeJDBC.getAll()
                .stream()
                .filter(employee1 -> employee.getEmail().equals(employee1.getEmail()))
                .findFirst()
                .orElseThrow(() -> new DataProcessingException("Could not find employee by email from database. ",
                        new NullPointerException()));
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EMPLOYEE_FOR_DEPARTMENT_QUERY);
            preparedStatement.setLong(1, department_id);
            preparedStatement.setLong(2, employeeForDepartment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Long department_id, Employee employee) throws SQLException {
        employeeJDBC.update(employee);
    }

    @Override
    public void deleteEmployee(Long department_id, Long employee_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_FOR_DEPARTMENT_QUERY);
            preparedStatement.setLong(1, department_id);
            preparedStatement.setLong(2, employee_id);
            preparedStatement.executeUpdate();

            new EmployeeJDBCImpl().delete(employee_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Employee> getAllEmployeesOfDepartment(Long department_id) {
        Set<Employee> employees = new TreeSet<>((o1, o2) -> (int) (o1.getId() - o2.getId()));
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES_FOR_DEPARTMENT_QUERY);
            preparedStatement.setLong(1, department_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new EmployeeJDBCImpl().getById(resultSet.getLong("employee_id")).
                        orElseThrow(() ->
                                new DataProcessingException(EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE,
                                        new NullPointerException())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
