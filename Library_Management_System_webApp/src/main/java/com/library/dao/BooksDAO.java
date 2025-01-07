package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.model.BooksModel;

public class BooksDAO {

    private String jdbcURL = "jdbc:mysql://localhost:30006/lmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Akash#19122017**";

    private static final String INSERT_BOOK_SQL = "INSERT INTO Books (Book_ID, Category, Name, Author, Copies) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM Books WHERE Book_ID = ?;";
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM Books;";
    private static final String DELETE_BOOK_SQL = "DELETE FROM Books WHERE Book_ID = ?;";
    private static final String UPDATE_COPIES_SQL = "UPDATE Books SET Copies = ? WHERE Book_ID = ?;";

    public BooksDAO() {
        super();
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

    /**
     * Insert a new book into the database.
     */
    public void insertBook(BooksModel book) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {

            preparedStatement.setString(1, book.getBook_ID());
            preparedStatement.setString(2, book.getCategory());
            preparedStatement.setString(3, book.getName());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setInt(5, book.getCopies());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BooksModel selectBookByID(String bookID) {
        BooksModel book = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {

            preparedStatement.setString(1, bookID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String category = resultSet.getString("Category");
                String name = resultSet.getString("Name");
                String author = resultSet.getString("Author");
                int copies = resultSet.getInt("Copies");

                book = new BooksModel(bookID, category, name, author, copies);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public List<BooksModel> selectAllBooks() {
        List<BooksModel> books = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String bookID = resultSet.getString("Book_ID");
                String category = resultSet.getString("Category");
                String name = resultSet.getString("Name");
                String author = resultSet.getString("Author");
                int copies = resultSet.getInt("Copies");

                books.add(new BooksModel(bookID, category, name, author, copies));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public boolean deleteBook(String bookID) {
        boolean rowDeleted = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_SQL)) {

            preparedStatement.setString(1, bookID);
            rowDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowDeleted;
    }

    public boolean updateCopies(String bookID, int newCopies) {
        boolean rowUpdated = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COPIES_SQL)) {

            preparedStatement.setInt(1, newCopies);
            preparedStatement.setString(2, bookID);
            rowUpdated = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowUpdated;
    }
    public List<BooksModel> selectAllUsers(){
        List<BooksModel> books = new ArrayList<BooksModel>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String bookID = resultSet.getString("Book_ID");
                String category = resultSet.getString("Category");
                String name = resultSet.getString("Name");
                String author = resultSet.getString("Author");
                int copies = resultSet.getInt("Copies");

                books.add(new BooksModel(bookID, category, name, author, copies));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
