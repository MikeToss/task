package com.task.dao.jdbc;

import com.task.dao.GenericDAO;
import com.task.model.Department;
import com.task.model.Employee;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * @Extends of {@link GenericDAO} interface.
 */

public interface DepartmentDAO extends GenericDAO<Department, Long> {

    void createEmployee(Long department_id, Employee employee) throws SQLException;

    void updateEmployee(Long department_id, Employee employee) throws SQLException;

    void deleteEmployee(Long department_id, Long employee_id) throws SQLException;

    Set<Employee> getAllEmployeesOfDepartment(Long department_id);
}