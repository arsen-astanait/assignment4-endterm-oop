package com.example.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/warehouse_db2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "basik228";

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
