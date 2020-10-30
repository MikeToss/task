package com.task.dao;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 */

public interface GenericDAO<T, K> {

    void create(T element) throws SQLException;

    Optional<T> getById(K id) throws SQLException;

    Set<T> getAll() throws SQLException;

    void update(T element) throws SQLException;

    void delete(K id) throws SQLException;
}