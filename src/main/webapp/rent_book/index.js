// Handle Rent Book Form Submission
document
  .getElementById("rentBookForm")
  .addEventListener("submit", function (e) {
    e.preventDefault();

    const book = document.getElementById("bookSelect").value;
    const userName = document.getElementById("userName").value;
    const userEmail = document.getElementById("userEmail").value;

    // Show Confirmation Popup
    const confirmationMessage = `Thank you, ${userName}! You have successfully rented "${book}". A confirmation email will be sent to ${userEmail}.`;
    document.getElementById("confirmationMessage").innerText =
      confirmationMessage;
    document.getElementById("confirmationPopup").style.display = "flex";
  });

// Close Popup
document.querySelector(".close-popup").addEventListener("click", function () {
  document.getElementById("confirmationPopup").style.display = "none";
});
