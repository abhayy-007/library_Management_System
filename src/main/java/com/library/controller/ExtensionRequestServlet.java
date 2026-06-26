package com.library.controller;

import com.library.dao.RentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ExtensionRequestServlet")
public class ExtensionRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rentID = request.getParameter("rentID");
        if (rentID != null && !rentID.isEmpty()) {
            new RentDAO().requestExtension(rentID);
        }
        response.sendRedirect("UserDashboardServlet");
    }
}
