<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Staff</title>
    <link rel="stylesheet" href="project-7/Add-Staff/style.css" />
  </head>

  <body>
    <div class="container">
      <h1>Add New Staff</h1>
      <form action="AdminManageStaffs" method="post" id="addStaffForm">
        <input type="hidden" name="action" value="add" />

        <label for="staffID">Staff ID:</label>
        <input type="text" name="staffID" id="staffID" required />

        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required />

        <label for="password">Password:</label>
        <input type="text" name="password" id="password" required />

        <label for="contact">Contact:</label>
        <input type="text" name="contact" id="contact" required />

        <button type="submit">Add Staff</button>
      </form>
    </div>

    <script src="project-7/Add-Staff/script.js"></script>
  </body>
</html>
