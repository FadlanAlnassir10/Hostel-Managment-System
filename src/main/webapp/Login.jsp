<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Welcome to HostelMate!</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Remove popup and overlay styles */
        /* .popup, .overlay {
            display: none;
        } */
    </style>
</head>
<body>
    <div class="container">
        <input type="checkbox" id="flip">
        <div class="cover">
            <div class="front">
                <img src="images/frontImg2.jpg" alt="">
                <div class="text">
                    <span class="text-1">Every new friend is a <br> new adventure</span>
                    <span class="text-2">Let's get connected</span>
                </div>
            </div>
            <div class="back">
                <img class="backImg" src="images/backImg.jpg" alt="">
                <div class="text">
                    <span class="text-1">Complete miles of journey <br> with one step</span>
                    <span class="text-2">Let's get started</span>
                </div>
            </div>
        </div>
        <div class="forms">
            <div class="form-content">
                <!-- Login Form -->
                <div class="login-form">
                    <div class="title">Login</div>
                    <form action="LoginController" method="POST">
                        <div class="input-boxes">
                            <div class="input-box">
                                <i class="fas fa-envelope"></i>
                                <input type="text" name="email" id="loginEmail" placeholder="Enter your email" required>
                            </div>
                            <div class="input-box">
                                <i class="fas fa-lock"></i>
                                <input type="password" name="password" id="password" placeholder="Enter your password" required>
                            </div>
                            <div class="button input-box">
                                <input type="submit" value="Login">
                            </div>
                            <div class="text sign-up-text">
                                Don't have an account? <label for="flip">Sign up now</label>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- Signup Form -->
                <div class="signup-form">
                    <div class="title">Signup</div>
                    <form id="signupForm" action="RegisterCustController" method="POST">
                        <div class="input-boxes">
                            <!-- Name Field -->
                            <div class="input-box">
                                <i class="fas fa-user"></i>
                                <input type="text" name="name" id="name" placeholder="Enter your name" required>
                            </div>

                            <!-- IC Number Field -->
                            <div class="input-box">
                                <i class="fas fa-id-card"></i>
                                <input type="text" name="icNumber" id="icNumber" placeholder="Enter your IC number" required>
                            </div>

                            <!-- Email Field -->
                            <div class="input-box">
                                <i class="fas fa-envelope"></i>
                                <input type="text" name="email" id="email" placeholder="Enter your email" class="input-field" required>
                            </div>

                            <!-- Phone Number Field -->
                            <div class="input-box">
                                <i class="fas fa-phone"></i>
                                <input type="text" name="phone" id="phone" placeholder="Enter your phone number" required>
                            </div>

                            <!-- Password Field -->
                            <div class="input-box">
                                <i class="fas fa-lock"></i>
                                <input type="password" name="password" id="password" placeholder="Enter your password" required>
                            </div>

                            <!-- Submit Button -->
                            <div class="button input-box">
                                <input type="submit" value="Submit">
                            </div>

                            <div class="text sign-up-text">
                                Already have an account? <label for="flip">Login now</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- JavaScript to handle form validation and alert -->
   <script>
    // Function to show error messages
    function showErrorMessage(message) {
        if (message) {
            alert(message);  // Show the alert with the error message
        }
    }

    // Function to validate email format
    function validateEmail(email) {
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        return emailPattern.test(email);
    }

    // Add form submission listener
    document.getElementById("signupForm").addEventListener("submit", function (event) {
        var email = document.getElementById("email").value.trim();
        var icNumber = document.getElementById("icNumber").value.trim();
        var phone = document.getElementById("phone").value.trim();

        // Validate Email
        if (!validateEmail(email)) {
            event.preventDefault();
            showErrorMessage("Please enter a valid email address.");
            document.getElementById("email").style.border = "2px solid red";
            return;
        }

        // Validate IC Number (Numeric and 12 digits)
        if (!/^\d{12}$/.test(icNumber)) {
            event.preventDefault();
            showErrorMessage("IC Number must be 12 numeric digits.");
            document.getElementById("icNumber").style.border = "2px solid red";
            return;
        }

        // Validate Phone Number (Numeric and 10–12 digits as an example)
        if (!/^\d{10,12}$/.test(phone)) {
            event.preventDefault();
            showErrorMessage("Phone number must be between 10 to 12 numeric digits.");
            document.getElementById("phone").style.border = "2px solid red";
            return;
        }
    });

    // Fetch error messages if exists
    <%
        String loginError = (String) request.getAttribute("loginError");
        String signupError = (String) request.getAttribute("signupError");
        if (loginError != null) {
    %>
        showErrorMessage("<%= loginError %>");
    <%
        }
        if (signupError != null) {
    %>
        showErrorMessage("<%= signupError %>");
    <%
        }
    %>
</script>


</body>
</html>
