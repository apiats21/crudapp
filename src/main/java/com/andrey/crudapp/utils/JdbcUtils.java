package com.andrey.crudapp.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    private static Properties properties = new Properties();
    private static Connection connection = null;


    public static Connection getConnectionToDb() {
        String filePath = "C:/Users/Home/Desktop/Crud_app/src/main/resources/application.properties";
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
            Class.forName(properties.getProperty("jdbc.driverClassName"));
            return connection = DriverManager.getConnection(properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
