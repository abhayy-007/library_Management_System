package com.library.controller;

import com.library.dao.RentDAO;
import com.library.model.RentModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RentBookServlet")
public class RentBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        String borrowerName = request.getParameter("borrowerName");
        String borrowerContact = request.getParameter("borrowerContact");

        RentModel rent = new RentModel(bookID, borrowerName, borrowerContact);
        RentDAO rentDAO = new RentDAO();

        boolean success = rentDAO.rentBook(rent);

        if (success) {
            response.sendRedirect("rentSuccess.jsp");
        } else {
            request.setAttribute("message",
                    "Unable to rent the book. Either the book ID is invalid or no copies are available.");
            request.getRequestDispatcher("rentBook.jsp").forward(request, response);
        }
    }
}
