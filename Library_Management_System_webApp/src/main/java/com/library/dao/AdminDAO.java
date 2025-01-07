package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.model.AdminModel;

public class AdminDAO {

    private String jdbcURL = "jdbc:mysql://localhost:30006/lmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Akash#19122017**";

    private static final String AUTHENTICATE_USER_SQL = "SELECT * FROM Admin WHERE User_ID = ? AND Name = ?;";

    public AdminDAO() {
        // Empty constructor
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public boolean authenticate(AdminModel admin) {
        boolean isAuthenticated = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE_USER_SQL)) {

            preparedStatement.setString(1, admin.getUser_ID());
            preparedStatement.setString(2, admin.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            isAuthenticated = resultSet.next(); // If a record is found, authentication is successful.

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }
}
