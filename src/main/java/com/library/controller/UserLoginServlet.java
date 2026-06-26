package com.library.controller;

import com.library.dao.UserDAO;
import com.library.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        if (dao.authenticate(userID, password)) {
            String token = JwtUtil.generateToken(userID, "user");
            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true);
            response.addCookie(jwtCookie);
            response.sendRedirect("UserDashboardServlet");
        } else {
            response.sendRedirect("userLogin.jsp?error=Invalid+User+ID+or+Password");
        }
    }
}
