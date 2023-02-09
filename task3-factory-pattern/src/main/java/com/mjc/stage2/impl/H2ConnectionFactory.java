package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

    private final static String JDBC_DRIVER;
    private final static String DB_URL;
    private final static String USER;
    private final static String PASSWORD;

    static {

        Properties connectionProperties = new Properties();

        try {
            connectionProperties.load(
                    H2ConnectionFactory.class
                            .getClassLoader()
                            .getResourceAsStream("h2database.properties")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JDBC_DRIVER = connectionProperties.getProperty("jdbc_driver");
        DB_URL = connectionProperties.getProperty("db_url");
        USER = connectionProperties.getProperty("user");
        PASSWORD = connectionProperties.getProperty("password");

    }

    static {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Connection createConnection() {

        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}