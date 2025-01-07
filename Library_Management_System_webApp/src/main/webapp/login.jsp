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
      <form action="LoginServlet" method="post">
        <label for="role">Role:</label>
        <select name="role" id="role" required>
          <option value="admin">Admin</option>
          <option value="staff">Staff</option>
        </select>

        <label for="username">ID:</label>
        <input type="text" name="username" id="username" required />

        <label for="password">Name:</label>
        <input type="text" name="password" id="password" required />

        <button type="submit">Login</button>
      </form>
    </div>

    <script src="project-7/login/script.js"></script>
  </body>
</html>
