<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Staff</title>
    
    <!-- Linking Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Custom Styles -->
    <style>
        .container {
            margin-top: 50px;
        }
        .form-container {
            padding: 20px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .btn-custom {
            background: linear-gradient(45deg, #ff8c00, #ffa500);
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 40px;
            cursor: pointer;
            font-size: 1.25rem;
            font-weight: bold;
            transition: all 0.3s ease;
        }
        .btn-custom:hover {
            background: linear-gradient(45deg, #ffa500, #ff8c00);
            transform: scale(1.05);
        }
        .error-message {
            color: red;
            font-size: 0.9rem;
            display: none;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 form-container">
                <h2 class="text-center mb-4">Register Staff</h2>

                <!-- Registration Form -->
                <form id="staffForm" action="RegisterStaffController" method="POST" enctype="multipart/form-data" onsubmit="return validateForm()">
                    <div class="mb-3">
                        <label for="staffName" class="form-label">Name</label>
                        <input type="text" id="staffName" name="staffName" class="form-control" placeholder="Enter Full Name" required>
                    </div>

                    <div class="mb-3">
                        <label for="ic-number" class="form-label">IC Number</label>
                        <input type="text" id="icNumber" name="icNumber" class="form-control" placeholder="Enter IC Number" required>
                    </div>

                    <div class="mb-3">
                        <label for="phone-number" class="form-label">Phone Number</label>
                        <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="Enter Phone Number" required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email Address</label>
                        <input type="email" id="email" name="email" class="form-control" placeholder="Example: staffemail@staff.com" required>
                        <span id="emailError" class="error-message">Invalid email format. Please use an email ending with <strong>@staff.com</strong>.</span>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Enter Password" required>
                    </div>

                    <div class="mb-3">
                        <label for="profile-picture" class="form-label">Upload Profile Picture</label>
                        <input type="file" id="profilePicture" name="profilePicture" class="form-control">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-custom btn-lg">Register Staff</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Linking Bootstrap and jQuery JS -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- JavaScript for Form Validation -->
   <script>
    function validateForm() {
        const emailInput = document.getElementById("email");
        const emailValue = emailInput.value.trim();
        const icNumber = document.getElementById("icNumber").value.trim();
        const phoneNumber = document.getElementById("phoneNumber").value.trim();

        // Email validation
        if (!emailValue.endsWith("@staff.com")) {
            alert("Invalid email format. Please use an email ending with '@staff.com'.");
            emailInput.focus();
            return false; // Prevent form submission
        }

        // IC Number validation: must be exactly 12 numeric characters
        if (!/^\d{12}$/.test(icNumber)) {
            alert("IC Number must be exactly 12 numeric characters.");
            document.getElementById("icNumber").focus();
            return false; // Prevent form submission
        }

        // Phone Number validation: must be at least 10 numeric characters
        if (!/^\d{10,}$/.test(phoneNumber)) {
            alert("Phone Number must be at least 10 numeric characters.");
            document.getElementById("phoneNumber").focus();
            return false; // Prevent form submission
        }

        // If all validations pass
        return true;
    }
</script>


</body>
</html>
