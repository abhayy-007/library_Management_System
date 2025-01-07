document
  .getElementById("addStaffForm")
  .addEventListener("submit", function (event) {
    const staffID = document.getElementById("staffID").value.trim();
    const name = document.getElementById("name").value.trim();
    const password = document.getElementById("password").value.trim();
    const contact = document.getElementById("contact").value.trim();

    // Basic validation to check if all fields are filled
    if (!staffID || !name || !password || !contact) {
      event.preventDefault(); // Prevent form submission
      alert("Please fill in all fields.");
    }

    // Additional validation can be added here (e.g., for valid contact or password format)
  });
