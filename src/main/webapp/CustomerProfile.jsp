<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Profile</title>

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
    <style>
        /* Optional CSS Class for Profile Image */
        .profile-img-small {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>
    
    <script>
    function validatePhoneNumber() {
        const phoneInput = document.getElementById('custPhone');
        const phoneValue = phoneInput.value.trim();

        // Check if phone number contains at least 10 numeric characters
        if (!/^\d{10,}$/.test(phoneValue)) {
            alert('Phone number must contain at least 10 numeric characters.');
            return false; // Prevent form submission
        }
        return true; // Allow form submission
    }
</script>
</head>

<body>

<header class="site-header js-site-header">
    <div class="container-fluid">
        <div class="row align-items-center">
            <div class="col-6 col-lg-4 site-logo" data-aos="fade">
                <a href="Homepage.jsp">HostelMate</a>
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
                                        <li class="active"><a href="Homepage.jsp">Home</a></li>
                                        <!-- Room Types Link -->
							                 <c:choose>
							                   <c:when test="${not empty sessionScope.customerId}">
							                     <li><a href="ViewRoomTypeController?customerId=${sessionScope.customerId}">Rooms</a></li> <!-- Profile link with customerId -->
							                   </c:when>
							                   <c:otherwise>
							                     <li><a href="Login.jsp">Login</a></li>
							                   </c:otherwise>
							                 </c:choose>
	                                        <c:choose>
                                            <c:when test="${not empty sessionScope.customerId}">
                                                <li><a href="ViewCustProfileController?customerId=${sessionScope.customerId}">Profile</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="Login.jsp">Login</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.customerId}">
                                                <li><a href="ViewBookingController?customerId=${sessionScope.customerId}">Your Booking</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="Login.jsp">Login</a></li>
                                            </c:otherwise>
                                        </c:choose>
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

<!-- Customer Profile Section -->
<section class="site-hero inner-page overlay" style="background-image: url(images/slider-5.jpg); background-size: cover;" data-stellar-background-ratio="0.5">
    <div class="container" style="min-height: 100vh; display: flex; flex-direction: column; justify-content: center;">
        <div class="row site-hero-inner justify-content-center align-items-center">
            <div class="col-md-10 text-center" data-aos="fade">
                <h1 class="heading mb-3">Customer Profile</h1>
                <span class="custom-caption text-uppercase text-white d-block mb-3">"Need to update your profile? Scroll down!"</span>
            </div>
        </div>
    </div>
</section>

<!-- Alert for Update Success or Error -->
<c:choose>
    <c:when test="${param.update == 'success'}">
        <script>
            alert('Your profile has been successfully updated!');
        </script>
    </c:when>
    <c:when test="${param.update == 'error'}">
        <div class="alert alert-danger" role="alert">
            An error occurred while updating your profile. Please try again.
        </div>
    </c:when>
</c:choose>

<!-- Check if customer data is available -->
<c:if test="${not empty customer}">
    <!-- Form Container -->
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <div class="bg-white p-4 rounded" style="width: 400px;">
            <h2 class="text-center">Customer Profile</h2>

            <!-- Display Profile Picture -->
            <div class="mb-3 text-center">
                <c:if test="${not empty customer.custImage}">
                    <img src="data:image/jpeg;base64,${customer.custImage}" alt="Customer Image" class="profile-img-small">
                </c:if>
                <c:if test="${empty customer.custImage}">
                    <img src="images/default-profile.png" alt="Customer Image" class="profile-img-small">
                </c:if>
            </div>

            <!-- Update Customer Profile Form -->
            <form action="UpdateCustProfileController" method="post" enctype="multipart/form-data" onsubmit="return validatePhoneNumber();" >
                <input type="hidden" name="sessionId" value="${sessionId}">

                <!-- Full Name (Read-Only) -->
                <div class="form-group">
                    <label for="custName">Full Name</label>
                    <input type="text" id="custName" name="custName" class="form-control" value="${customer.custName}" readonly>
                </div>

                <!-- IC Number (Read-Only) -->
                <div class="form-group">
                    <label for="custIcNumber">IC Number</label>
                    <input type="text" id="custIcNumber" name="custIcNumber" class="form-control" value="${customer.custIcNumber}" readonly>
                </div>

                <!-- Email (Read-Only) -->
                <div class="form-group">
                    <label for="custEmail">Email</label>
                    <input type="email" id="custEmail" name="custEmail" class="form-control" value="${customer.custEmail}" readonly>
                </div>

                <!-- Phone Number (Editable) -->
                <div class="form-group">
                    <label for="custPhone">Phone Number</label>
                    <input type="text" id="custPhone" name="custPhone" class="form-control" value="${customer.custNoPhone}">
                </div>

                <!-- Profile Picture (Optional) -->
                <div class="mb-3">
                    <label for="custImage" class="form-label">Upload New Profile Picture (Optional)</label>
                    <input type="file" id="custImage" name="custImage" class="form-control">
                    <small class="form-text text-muted">If you don't want to change the picture, leave it blank.</small>
                </div>

                <!-- Form Buttons -->
                <div class="form-group text-center">
                    <button type="button" class="btn btn-danger" onclick="window.location.href='Homepage.jsp'">Cancel</button>
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>
            </form>
        </div>
    </div>
</c:if>

<!-- Message for Missing Customer Data -->
<c:if test="${empty customer}">
    <p>No customer found. Please log in to view your profile.</p>
</c:if>


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
