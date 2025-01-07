package com.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.library.dao.BooksDAO;
import com.library.model.BooksModel;

@WebServlet("/FetchBooksServlet")
public class FetchBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Set the content type to HTML
        response.setContentType("text/html");

        // Create an instance of BooksDAO to fetch the list of all books
        BooksDAO booksDAO = new BooksDAO();

        // Fetch all books from the database
        List<BooksModel> booksList = booksDAO.selectAllUsers();

        // Set the booksList as an attribute in the request object
        request.setAttribute("booksList", booksList);

        // Forward the request to the JSP page to display the books
        request.getRequestDispatcher("viewAllBooks.jsp").forward(request, response);
    }
}
