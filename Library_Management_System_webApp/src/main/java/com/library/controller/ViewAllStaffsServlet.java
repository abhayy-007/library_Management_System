package com.library.controller;

import com.library.dao.StaffsDAO;
import com.library.model.StaffsModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewAllStaffsServlet")
public class ViewAllStaffsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StaffsDAO staffsDAO;

    public ViewAllStaffsServlet() {
        this.staffsDAO = new StaffsDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all staffs from the database
        List<StaffsModel> staffs = staffsDAO.selectAllUsers();

        // Set the list of staffs as a request attribute
        request.setAttribute("staffs", staffs);

        // Forward the request to the JSP page for rendering
        request.getRequestDispatcher("view-AllStaffs.jsp").forward(request, response);
    }
}
