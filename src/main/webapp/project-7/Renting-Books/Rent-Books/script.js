document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const contactInput = document.getElementById("borrowerContact");
  const errorDiv = document.createElement("div");
  errorDiv.style.cssText = "color:#e74c3c;font-size:14px;margin-top:4px;";
  contactInput.parentNode.insertBefore(errorDiv, contactInput.nextSibling);

  form.addEventListener("submit", (e) => {
    if (!/^[0-9]{10}$/.test(contactInput.value)) {
      e.preventDefault();
      errorDiv.textContent = "Enter a valid 10-digit phone number.";
    } else {
      errorDiv.textContent = "";
    }
  });
});
