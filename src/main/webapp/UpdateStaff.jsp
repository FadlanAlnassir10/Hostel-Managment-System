<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Staff</title>
    
    <!-- Include Bootstrap CSS for styling -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/aos.css">
    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">
    <link rel="stylesheet" href="css/fancybox.min.css">
    <link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
    
      <!-- Custom Styles -->
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden; /* Ensures no scrolling and the background fits perfectly */
        }

        .site-hero {
            background-image: url('images/background2.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            height: 100%;
            width: 100%;
            padding: 0;
        }

        .container {
            margin-top: 0px;
        }

        .form-container {
            padding: 20px;
            background: rgba(255, 255, 255, 0.9); /* Slight transparency for better readability */
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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

        .profile-img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>
<body>


<header class="site-header js-site-header">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="col-6 col-lg-4 site-logo" data-aos="fade">
            <a href="AdminHomepage.html">HostelMate Admin</a>
          </div>
          <div class="col-6 col-lg-8">
            <div class="site-menu-toggle js-site-menu-toggle" data-aos="fade">
              <span></span><span></span><span></span>
            </div>
            <div class="site-navbar js-site-navbar">
              <nav role="navigation">
                <div class="container">
                  <div class="row full-height align-items-center">
                    <div class="col-md-6 mx-auto">
                      <ul class="list-unstyled menu">
                        <li class="active"><a href="AdminHomepage.html">Home</a></li>
                        <li><a href="AllStaffDetailsController">Staff Management</a></li>
                        <li><a href="RoomDetailsController">Room Management</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </nav>
            </div>
            </div>
            </div>
          </div>
    </header>
    
<header class="site-header js-site-header">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="col-6 col-lg-4 site-logo" data-aos="fade">
            <a href="AdminHomepage.jsp">HostelMate Admin</a>
          </div>
          <div class="col-6 col-lg-8">
            <div class="site-menu-toggle js-site-menu-toggle" data-aos="fade">
              <span></span><span></span><span></span>
            </div>
            <div class="site-navbar js-site-navbar">
              <nav role="navigation">
                <div class="container">
                  <div class="row full-height align-items-center">
                    <div class="col-md-6 mx-auto">
                      <ul class="list-unstyled menu">
                        <li class="active"><a href="AdminHomepage.html">Home</a></li>
                        <li><a href="AllStaffDetailsController">Staff Management</a></li>
                        <li><a href="RoomDetailsController">Room Management</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </nav>
            </div>
            </div>
            </div>
          </div>
    </header>

<section class="site-hero">
    <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
            <div class="col-md-10 text-center" data-aos="fade">
                <h1 class="heading mb-3">Staff Details</h1>

                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-8 form-container">
                            <h2 class="text-center mb-4">Update Staff Details</h2>

                            <!-- Display Current Profile Picture -->
                            <div class="mb-3 text-center">
                                <c:if test="${not empty staff.staffImage}">
                                    <!-- Display the current profile picture using Base64 -->
                                    <img src="data:image/jpeg;base64,${staff.staffImage}" alt="Profile Picture" class="profile-img">
                                </c:if>
                                <c:if test="${empty staff.staffImage}">
                                    <!-- Default placeholder if no picture exists -->
                                    <img src="images/default-profile.png" alt="Default Profile Picture" class="profile-img">
                                </c:if>
                            </div>

                            <!-- Update Form -->
                            <form action="UpdateStaffController" method="POST" enctype="multipart/form-data">
                                <!-- Staff ID (hidden) -->
                                <input type="hidden" name="staffId" value="${staff.staffId}" required>
                                
                                <!-- Staff Name (read-only) -->
                                <div class="mb-3">
                                    <label for="staffName" class="form-label">Name</label>
                                    <input type="text" id="staffName" name="staffName" class="form-control" value="${staff.staffName}" readonly>
                                </div>

                                <!-- IC Number (read-only) -->
                                <div class="mb-3">
                                    <label for="icNumber" class="form-label">IC Number</label>
                                    <input type="text" id="icNumber" name="icNumber" class="form-control" value="${staff.staffIcNumber}" readonly>
                                </div>

                                <!-- Phone Number (editable) -->
                                <div class="mb-3">
                                    <label for="phoneNumber" class="form-label">Phone Number</label>
                                    <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${staff.staffNoPhone}" required>
                                </div>

                                <!-- Email Address (editable) -->
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email Address</label>
                                    <input type="email" id="email" name="email" class="form-control" value="${staff.staffEmail}" required>
                                </div>

                                <!-- Profile Picture (optional) -->
                                <div class="mb-3">
                                    <label for="staffImage" class="form-label">Upload New Profile Picture (Optional)</label>
                                    <input type="file" id="staffImage" name="staffImage" class="form-control">
                                    <small class="form-text text-muted">If you don't want to change the picture, leave it blank.</small>
                                </div>

                                <!-- Submit Button -->
                                <div class="text-center">
                                    <button type="submit" class="btn btn-custom btn-lg">Update Details</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const form = document.querySelector("form");
        const emailInput = document.getElementById("email");
        const phoneInput = document.getElementById("phoneNumber");

        form.addEventListener("submit", function (event) {
            // Validate email
            const emailValue = emailInput.value.trim();
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(emailValue)) {
                alert("Please enter a valid email address.");
                emailInput.focus();
                event.preventDefault(); // Prevent form submission
                return;
            }

            // Ensure email ends with '@staff.com'
            if (!emailValue.endsWith("@staff.com")) {
                alert("Email must end with '@staff.com'.");
                emailInput.focus();
                event.preventDefault(); // Prevent form submission
                return;
            }

            // Validate phone number
            const phoneValue = phoneInput.value.trim();
            const phoneRegex = /^\d{10,15}$/; // Allows 10-15 digits
            if (!phoneRegex.test(phoneValue)) {
                alert("Please enter a valid phone number (10-15 digits).");
                phoneInput.focus();
                event.preventDefault(); // Prevent form submission
                return;
            }
        });
    });
</script>


    <!-- Bootstrap and jQuery JS -->
    <!-- Include JavaScript Libraries -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery-migrate-3.0.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.stellar.min.js"></script>
<script src="js/jquery.fancybox.min.js"></script>
<script src="js/aos.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/jquery.timepicker.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
