<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User Login</title>
    <link rel="stylesheet" href="project-7/login/style.css" />
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
</head>
<body>
    <div class="container">
        <h1>User Login</h1>
        <% if ("true".equals(request.getParameter("registered"))) { %>
            <p style="color: green;">Registration successful! Please login.</p>
        <% } %>
        <% String error = request.getParameter("error"); if (error != null && !error.isEmpty()) { %>
            <p style="color: red;"><%= error %></p>
        <% } %>
        <form action="UserLoginServlet" method="post">
            <label for="userID">User ID:</label>
            <input type="text" name="userID" id="userID" required />

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required />

            <button type="submit">Login</button>
            <a href="registerUser.jsp" class="back-btn">Don't have an account? Register</a>
            <a href="adminLogin.jsp" class="back-btn" style="background:#455a64">Admin / Staff Login</a>
            <a href="register.jsp" class="back-btn" style="background:#455a64">Admin Registration</a>
        </form>
    </div>
</body>
</html>
