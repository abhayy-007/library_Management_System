<%@page import="java.util.List" %>
<%@page import="org.bson.Document" %>
<%@page import="com.library.dao.RentDAO" %>
<html>
<head>
    <title>Manage Extensions</title>
    <link rel="stylesheet" href="project-7/Renting-Books/Rent-Books/style.css" />
    <style>
        .back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}
        .btn-app{background:#4caf50;color:#fff;border:none;padding:6px 12px;border-radius:4px;cursor:pointer}
        .btn-rej{background:#f44336;color:#fff;border:none;padding:6px 12px;border-radius:4px;cursor:pointer}
    </style>
</head>
<body>
    <div class="container">
        <h1>Pending Extension Requests</h1>
        <% 
            RentDAO dao = new RentDAO();
            List<Document> requests = dao.getPendingExtensions();
            if (requests != null && !requests.isEmpty()) {
        %>
        <table border="1" style="width:100%">
            <thead>
                <tr>
                    <th>Rent ID</th>
                    <th>Book ID</th>
                    <th>User ID</th>
                    <th>Current Due Date</th>
                    <th>Request Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (Document r : requests) { %>
                <tr>
                    <td><%= r.getObjectId("_id").toString() %></td>
                    <td><%= r.getString("Book_ID") %></td>
                    <td><%= r.getString("User_ID") %></td>
                    <td><%= r.getString("Due_Date") %></td>
                    <td><%= r.getString("Extension_Request_Date") %></td>
                    <td>
                        <form action="ExtensionActionServlet" method="post" style="display:inline">
                            <input type="hidden" name="rentID" value="<%= r.getObjectId("_id").toString() %>" />
                            <input type="hidden" name="action" value="approve" />
                            <button type="submit" class="btn-app">Approve</button>
                        </form>
                        <form action="ExtensionActionServlet" method="post" style="display:inline">
                            <input type="hidden" name="rentID" value="<%= r.getObjectId("_id").toString() %>" />
                            <input type="hidden" name="action" value="reject" />
                            <button type="submit" class="btn-rej">Reject</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } else { %>
            <p>No pending extension requests.</p>
        <% } %>
        <a href="staffDashboard.jsp" class="back-btn">Back to Staff Dashboard</a>
    </div>
</body>
</html>
