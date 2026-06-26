<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register</title>
    <link rel="stylesheet" href="project-7/login/style.css" />
  </head>

  <body>
    <div class="container">
      <h1>Registration</h1>
      <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
      <% } %>
      <form action="register" method="post">
        <label for="role">Role:</label>
        <select name="role" id="role" required>
          <option value="admin">Admin</option>
        </select>

        <label for="id">ID:</label>
        <input type="text" name="id" id="id" required />

        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required />

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />

        <label for="contact">Contact:</label>
        <input type="text" name="contact" id="contact" required />

        <button type="submit">Register</button>
      </form>
      <p style="text-align: center; margin-top: 1rem;">
        Already have an account? <a href="adminLogin.jsp">Login</a>
      </p>
    </div>
  </body>
</html>
