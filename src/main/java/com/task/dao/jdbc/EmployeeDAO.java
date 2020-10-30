package com.task.dao.jdbc;

import com.task.dao.GenericDAO;
import com.task.model.Employee;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * @Extends of {@link GenericDAO} interface.
 */

public interface EmployeeDAO extends GenericDAO<Employee, Long> {

}