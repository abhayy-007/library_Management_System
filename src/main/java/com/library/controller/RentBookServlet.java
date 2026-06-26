package com.library.controller;

import com.library.dao.RentDAO;
import com.library.dao.UserDAO;
import com.library.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/RentBookServlet")
public class RentBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<UserModel> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("rentBook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        String userID = request.getParameter("userID");

        if (bookID == null || userID == null || bookID.isEmpty() || userID.isEmpty()) {
            request.setAttribute("message", "Book ID and User are required.");
            request.getRequestDispatcher("rentBook.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        UserModel user = userDAO.getUserById(userID);
        if (user == null) {
            request.setAttribute("message", "User not found.");
            request.getRequestDispatcher("rentBook.jsp").forward(request, response);
            return;
        }

        RentDAO rentDAO = new RentDAO();
        boolean success = rentDAO.rentBook(bookID, userID, user.getName(), user.getContact());

        if (success) {
            request.setAttribute("bookID", bookID);
            request.setAttribute("userName", user.getName());
            request.setAttribute("dueDate", LocalDate.now().plusDays(14).format(DateTimeFormatter.ISO_LOCAL_DATE));
            request.getRequestDispatcher("sucessPage.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Unable to rent the book. Either the book ID is invalid or no copies are available.");
            request.getRequestDispatcher("rentBook.jsp").forward(request, response);
        }
    }
}
