package com.library.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.library.util.JwtUtil;
import io.jsonwebtoken.Claims;

import java.io.IOException;

@WebFilter(urlPatterns = {"*.jsp"})
public class JwtFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Exclude public pages
        if (path.endsWith("adminLogin.jsp") || path.endsWith("register.jsp") || path.endsWith("index.jsp") || path.endsWith("userLogin.jsp") || path.endsWith("registerUser.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = req.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            try {
                Claims claims = JwtUtil.validateToken(token);
                // Can also extract role from claims and restrict pages based on role if needed.
                req.setAttribute("userRole", claims.get("role", String.class));
                req.setAttribute("userId", claims.getSubject());
                chain.doFilter(request, response);
            } catch (Exception e) {
                // Invalid token
                res.sendRedirect(req.getContextPath() + "/userLogin.jsp");
            }
        } else {
            // No token found
            res.sendRedirect(req.getContextPath() + "/userLogin.jsp");
        }
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
