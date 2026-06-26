<%@page import="java.util.List" %>
<%@page import="com.library.model.UserModel" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rent a Book</title>
    <link rel="stylesheet" href="project-7/Renting-Books/Rent-Books/style.css" />
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
</head>
<body>
    <div class="container">
        <h1>Rent a Book</h1>
        <% if (request.getAttribute("message") != null) { %>
            <p style="color: red; font-weight: bold;"><%= request.getAttribute("message") %></p>
        <% } %>
        <form action="RentBookServlet" method="post">
            <label for="bookID">Book ID:</label>
            <input type="text" id="bookID" name="bookID" required />

            <label for="userID">User:</label>
            <select id="userID" name="userID" required>
                <option value="">-- Select User --</option>
                <% List<UserModel> users = (List<UserModel>) request.getAttribute("users");
                   if (users != null) {
                       for (UserModel u : users) { %>
                <option value="<%= u.getUserID() %>"><%= u.getUserID() %> - <%= u.getName() %></option>
                <%     }
                   } %>
            </select>

            <input type="submit" value="Rent Book" />
            <a href="staffDashboard.jsp" class="back-btn">Back to Staff Dashboard</a>
        </form>
    </div>
</body>
</html>
