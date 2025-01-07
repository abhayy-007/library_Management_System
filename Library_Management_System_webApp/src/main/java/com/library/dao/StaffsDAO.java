package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.model.StaffsModel;

public class StaffsDAO {

    private String jdbcURL = "jdbc:mysql://localhost:30006/lmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Akash#19122017**";

    private static final String AUTHENTICATE_USER_SQL = "SELECT * FROM Staff WHERE Staff_ID = ? AND Name = ?;";
    private static final String INSERT_STAFF_SQL = "INSERT INTO Staff (Staff_ID, Name, Password, Contact) VALUES (?, ?, ?, ?);";
    private static final String DELETE_STAFF_SQL = "DELETE FROM Staff WHERE Staff_ID = ?;";

    public StaffsDAO() {
        // Empty constructor
    } public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public boolean authenticate(StaffsModel staff) {
        boolean isAuthenticated = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE_USER_SQL)) {

            preparedStatement.setString(1, staff.getStaff_ID());
            preparedStatement.setString(2, staff.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            isAuthenticated = resultSet.next(); // If a record is found, authentication is successful.

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }

    public boolean insertStaff(StaffsModel staff) {
        boolean isAdded = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)) {

            preparedStatement.setString(1, staff.getStaff_ID());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, staff.getPassword());
            preparedStatement.setString(4, staff.getContact());

            isAdded = preparedStatement.executeUpdate() > 0; // Returns true if the record was inserted.

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAdded;
    }


    public boolean deleteStaff(String staffID) {
        boolean isDeleted = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF_SQL)) {

            preparedStatement.setString(1, staffID);

            isDeleted = preparedStatement.executeUpdate() > 0; // Returns true if the record was deleted.

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }
    public List<StaffsModel> selectAllUsers() {
        List<StaffsModel> staffs = new ArrayList<StaffsModel>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM Staff";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String staffID = resultSet.getString("Staff_ID");
                String name = resultSet.getString("Name");
                String password = resultSet.getString("Password");
                String contact = resultSet.getString("Contact");

                staffs.add(new StaffsModel(staffID, name, password , contact));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffs;
    }
}
