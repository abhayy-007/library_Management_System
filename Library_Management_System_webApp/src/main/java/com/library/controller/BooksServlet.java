package com.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.library.dao.BooksDAO;
import com.library.model.BooksModel;

@WebServlet("/booksServlet")
public class BooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action"); // e.g., "insert", "delete", "updateCopies"
        BooksDAO booksDAO = new BooksDAO();

        try {
            if ("insert".equalsIgnoreCase(action)) {
                // Insert a new book
                String bookID = request.getParameter("bookID");
                String Category = request.getParameter("category");
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                int copies = Integer.parseInt(request.getParameter("copies"));

                BooksModel book = new BooksModel(bookID, Category, title, author, copies);
                booksDAO.insertBook(book);

                response.getWriter().write("Book inserted successfully!");
            } else if ("delete".equalsIgnoreCase(action)) {
                // Delete a book
                String bookID = request.getParameter("bookID");

                boolean isDeleted = booksDAO.deleteBook(bookID);
                if (isDeleted) {
                    response.getWriter().write("Book deleted successfully!");
                } else {
                    response.getWriter().write("Failed to delete book. Book ID not found.");
                }
            } else if ("updateCopies".equalsIgnoreCase(action)) {
                // Update the number of copies available
                String bookID = request.getParameter("bookID");
                int newCopies = Integer.parseInt(request.getParameter("copies"));

                boolean isUpdated = booksDAO.updateCopies(bookID, newCopies);
                if (isUpdated) {
                    response.getWriter().write("Book copies updated successfully!");
                } else {
                    response.getWriter().write("Failed to update book copies. Book ID not found.");
                }
            } else {
                response.getWriter().write("Invalid action specified.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}
