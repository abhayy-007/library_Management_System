package com.library.controller;

import com.library.dao.BooksDAO;
import com.library.dao.RentDAO;
import com.library.model.BooksModel;
import com.library.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserDashboardServlet")
public class UserDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("jwt".equals(c.getName())) {
                    try {
                        Claims claims = JwtUtil.validateToken(c.getValue());
                        userID = claims.getSubject();
                    } catch (Exception e) {
                        response.sendRedirect("userLogin.jsp");
                        return;
                    }
                    break;
                }
            }
        }
        if (userID == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }
        request.setAttribute("userId", userID);

        RentDAO rentDAO = new RentDAO();
        BooksDAO booksDAO = new BooksDAO();

        List<Document> rentals = rentDAO.getRentalsByUser(userID);
        List<BooksModel> books = booksDAO.selectAllBooks();

        for (Document rental : rentals) {
            String bookName = rentDAO.getBookName(rental.getString("Book_ID"));
            rental.append("Book_Name", bookName);
        }

        request.setAttribute("rentals", rentals);
        request.setAttribute("books", books);
        request.getRequestDispatcher("userDashboard.jsp").forward(request, response);
    }
}
