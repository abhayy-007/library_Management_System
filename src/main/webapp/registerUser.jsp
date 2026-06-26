<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User Registration</title>
    <link rel="stylesheet" href="project-7/login/style.css" />
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
</head>
<body>
    <div class="container">
        <h1>User Registration</h1>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="RegisterUserServlet" method="post">
            <label for="userID">User ID:</label>
            <input type="text" name="userID" id="userID" required />

            <label for="name">Name:</label>
            <input type="text" name="name" id="name" required />

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required />

            <label for="contact">Contact:</label>
            <input type="text" name="contact" id="contact" required />

            <button type="submit">Register</button>
            <a href="userLogin.jsp" class="back-btn">Already have an account? Login</a>
        </form>
    </div>
</body>
</html>
