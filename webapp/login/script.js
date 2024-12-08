document.addEventListener("DOMContentLoaded", function () {
  const loginTab = document.getElementById("login-tab");
  const signupTab = document.getElementById("signup-tab");
  const loginForm = document.getElementById("login-form");
  const signupForm = document.getElementById("signup-form");
  const toSignupLink = document.getElementById("to-signup");
  const toLoginLink = document.getElementById("to-login");
  const errorMessage = document.getElementById("error-message");
  const errorText = document.getElementById("error-text");

  // Switch between login and signup forms
  loginTab.addEventListener("click", () => {
    loginForm.style.display = "block";
    signupForm.style.display = "none";
    loginTab.classList.add("active");
    signupTab.classList.remove("active");
  });

  signupTab.addEventListener("click", () => {
    loginForm.style.display = "none";
    signupForm.style.display = "block";
    signupTab.classList.add("active");
    loginTab.classList.remove("active");
  });

  toSignupLink.addEventListener("click", () => {
    loginForm.style.display = "none";
    signupForm.style.display = "block";
    signupTab.classList.add("active");
    loginTab.classList.remove("active");
  });

  toLoginLink.addEventListener("click", () => {
    loginForm.style.display = "block";
    signupForm.style.display = "none";
    loginTab.classList.add("active");
    signupTab.classList.remove("active");
  });

  // Handle form validation and submission
  loginForm.addEventListener("submit", function (e) {
    e.preventDefault();

    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;

    if (!validateEmail(email) || password.length < 6) {
      showErrorMessage("Invalid email or password.");
    } else {
      alert("Login successful!");
      // Redirect or further processing
    }
  });

  signupForm.addEventListener("submit", function (e) {
    e.preventDefault();

    const name = document.getElementById("signup-name").value;
    const email = document.getElementById("signup-email").value;
    const password = document.getElementById("signup-password").value;
    const confirmPassword = document.getElementById("confirm-password").value;

    if (password !== confirmPassword) {
      showErrorMessage("Passwords do not match.");
    } else if (!validateEmail(email) || password.length < 6) {
      showErrorMessage("Invalid email or password.");
    } else {
      alert("Signup successful!");
      // Redirect or further processing
    }
  });

  function validateEmail(email) {
    const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return re.test(email);
  }

  function showErrorMessage(message) {
    errorText.textContent = message;
    errorMessage.style.display = "block";
    setTimeout(() => {
      errorMessage.style.display = "none";
    }, 3000);
  }
});
