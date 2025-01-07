// Adding basic form validation and interactivity for "Rent a Book" page
document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const bookIDInput = document.getElementById("bookID");
  const borrowerNameInput = document.getElementById("borrowerName");
  const borrowerContactInput = document.getElementById("borrowerContact");

  // Function to validate phone number input (basic example)
  function isValidPhoneNumber(phone) {
    const phonePattern = /^[0-9]{10}$/; // Assumes a 10-digit phone number
    return phonePattern.test(phone);
  }

  // Add an event listener to validate form inputs on submission
  form.addEventListener("submit", (e) => {
    if (!isValidPhoneNumber(borrowerContactInput.value)) {
      e.preventDefault(); // Prevent form submission
      alert("Please enter a valid 10-digit phone number for Borrower Contact.");
    }
  });
});
