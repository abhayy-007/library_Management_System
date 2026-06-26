<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Login</title>
  <link rel="stylesheet" href="project-7/login/style.css" />
</head>

<body>
  <div class="container">
    <h1>Login</h1>
    <% if ("true".equals(request.getParameter("registered"))) { %>
      <p style="color: green;">Registration successful! Please login.</p>
      <% } %>
    <% String error = request.getParameter("error"); if (error != null && !error.isEmpty()) { %>
      <p style="color: red;"><%= error %></p>
    <% } %>
        <form action="LoginServlet" method="post">
          <label for="role">Role:</label>
          <select name="userType" id="role" required>
            <option value="admin">Admin</option>
            <option value="staff">Staff</option>
          </select>

          <label for="username">ID:</label>
          <input type="text" name="id" id="username" required />

          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required />

          <button type="submit">Login</button>
        </form>
        <p style="text-align: center; margin-top: 1rem;">
          Don't have an account? <a href="register.jsp">Register here</a>
        </p>
  </div>

  <script src="project-7/login/script.js"></script>
</body>

</html>