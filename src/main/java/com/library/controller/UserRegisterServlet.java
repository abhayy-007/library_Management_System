package com.library.controller;

import com.library.dao.UserDAO;
import com.library.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegisterUserServlet")
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("registerUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");

        if (userID == null || name == null || password == null || contact == null ||
            userID.isEmpty() || name.isEmpty() || password.isEmpty() || contact.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
            return;
        }

        UserDAO dao = new UserDAO();
        boolean inserted = dao.insertUser(new UserModel(userID, name, password, contact));

        if (inserted) {
            response.sendRedirect("userLogin.jsp?registered=true");
        } else {
            request.setAttribute("error", "Registration failed. User ID may already exist.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
        }
    }
}
