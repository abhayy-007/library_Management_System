package com.library.dao;

import com.library.model.RentModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:30006/lmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Akash#19122017**";

    private static final String INSERT_RENT_SQL = "INSERT INTO Rent (Book_ID, Borrower_Name, Borrower_Contact) VALUES (?, ?, ?);";
    private static final String UPDATE_BOOK_COPIES_SQL = "UPDATE Books SET Copies = Copies - 1 WHERE Book_ID = ? AND Copies > 0;";

    public RentDAO() {
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

    public boolean rentBook(RentModel rent) {
        boolean success = false;

        try (Connection connection = getConnection()) {
            // Decrement book copies
            PreparedStatement updateStmt = connection.prepareStatement(UPDATE_BOOK_COPIES_SQL);
            updateStmt.setString(1, rent.getBookID());
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Insert rental record
                PreparedStatement insertStmt = connection.prepareStatement(INSERT_RENT_SQL);
                insertStmt.setString(1, rent.getBookID());
                insertStmt.setString(2, rent.getBorrowerName());
                insertStmt.setString(3, rent.getBorrowerContact());
                insertStmt.executeUpdate();

                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
