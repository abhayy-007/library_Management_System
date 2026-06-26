package com.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.library.dao.AdminDAO;
import com.library.model.AdminModel;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");

        if (id == null || name == null || password == null || contact == null ||
            id.isEmpty() || name.isEmpty() || password.isEmpty() || contact.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        AdminModel admin = new AdminModel(id, name, password, contact);
        AdminDAO adminDAO = new AdminDAO();
        boolean inserted = adminDAO.insertAdmin(admin);

        if (inserted) {
            response.sendRedirect("adminLogin.jsp?registered=true");
        } else {
            request.setAttribute("error", "Registration failed. ID may already exist.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
