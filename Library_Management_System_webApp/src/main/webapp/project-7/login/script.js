// Basic form validation before submitting
document.querySelector("form").addEventListener("submit", function (event) {
  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();

  if (username === "" || password === "") {
    event.preventDefault(); // Prevent form submission
    alert("Please fill in both the ID and Name fields.");
  }
});
