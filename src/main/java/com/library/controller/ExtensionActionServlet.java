package com.library.controller;

import com.library.dao.RentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ExtensionActionServlet")
public class ExtensionActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rentID = request.getParameter("rentID");
        String action = request.getParameter("action");

        if (rentID != null && action != null) {
            new RentDAO().handleExtension(rentID, action);
        }
        response.sendRedirect("manageExtensions.jsp");
    }
}
