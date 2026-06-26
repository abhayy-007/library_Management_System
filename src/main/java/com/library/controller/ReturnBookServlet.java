package com.library.controller;

import com.library.dao.RentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rentID = request.getParameter("rentID");
        if (rentID == null || rentID.isEmpty()) {
            request.setAttribute("message", "Rent ID is required.");
            request.getRequestDispatcher("returnBook.jsp").forward(request, response);
            return;
        }

        RentDAO dao = new RentDAO();
        boolean success = dao.returnBook(rentID);

        if (success) {
            request.setAttribute("message", "Book returned successfully!");
        } else {
            request.setAttribute("message", "Failed to return book. Invalid Rent ID.");
        }
        request.getRequestDispatcher("returnBook.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RentDAO dao = new RentDAO();
        request.setAttribute("rentals", dao.getAllRentals());
        request.getRequestDispatcher("returnBook.jsp").forward(request, response);
    }
}
