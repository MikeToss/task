package com.task.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 */

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_task_db";

    // TODO pay attention at USERNAME and PASSWORD you you need to set your values here
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection success");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Connection error");
        }
        return connection;
    }
}