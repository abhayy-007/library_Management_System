<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Delete Staff</title>
    <link rel="stylesheet" href="project-7/Delete-Staff/style.css" />
  </head>

  <body>
    <div class="container">
      <h1>Delete Staff</h1>
      <form action="AdminManageStaffs" method="post" id="deleteStaffForm">
        <input type="hidden" name="action" value="delete" />

        <label for="staffID">Staff ID:</label>
        <input type="text" name="staffID" id="staffID" required />

        <button type="submit">Delete Staff</button>
      </form>
    </div>

    <script src="project-7/Delete-Staff/script.js"></script>
  </body>
</html>
