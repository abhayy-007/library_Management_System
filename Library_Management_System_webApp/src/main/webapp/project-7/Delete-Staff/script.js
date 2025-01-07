document
  .getElementById("deleteStaffForm")
  .addEventListener("submit", function (event) {
    const staffID = document.getElementById("staffID").value.trim();

    // Basic validation to check if the Staff ID is provided
    if (!staffID) {
      event.preventDefault(); // Prevent form submission
      alert("Please enter a Staff ID to delete.");
    }
  });
