<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Booking Form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="author" content="" />

    <!-- External CSS -->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Roboto+Sans:400,700|Playfair+Display:400,700">
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
  <body>
    <!-- Header -->
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
                        <!-- Home Link -->
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

                        <!-- Profile or Login Link (depending on session) -->
                        <c:choose>
                          <c:when test="${not empty sessionScope.customerId}">
                            <li><a href="ViewCustProfileController?customerId=${sessionScope.customerId}">Profile</a></li> <!-- Profile link with customerId -->
                          </c:when>
                          <c:otherwise>
                            <li><a href="Login.jsp">Login</a></li>
                          </c:otherwise>
                        </c:choose>

                        <!-- Booking Link (depending on session) -->
                     	<c:choose>
                          <c:when test="${not empty sessionScope.customerId}">
                            <li><a href="ViewYourBookingController?customerId=${sessionScope.customerId}">Your Booking</a></li>
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
      </div>
    </header>

    <!-- Booking Form Section -->
    <section class="site-hero inner-page overlay" style="background-image: url(images/img1.jpg)" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade">
            <h1 class="heading mb-3">Booking Form</h1>
             <span class="custom-caption text-uppercase text-white d-block  mb-3">"Let's book your room!"</span>
            </div>
          </div>
      </div>
  </section>

            <!-- Booking Form -->
            <!-- Booking Form -->
<form class="booking-form mt-4 p-4" action="BookingController" method="POST" 
      style="background: rgba(255, 255, 255, 0.8); border-radius: 8px; padding: 2rem; max-width: 900px; margin: auto;">
  
  <!-- Hidden Input for Customer ID -->
  <input type="hidden" name="cust_id" value="<%= session.getAttribute("userId") %>">
  
  <!-- Room Type Section -->
  <div class="mb-4">
    <label for="room-id" class="form-label d-block text-center">Select Room Type</label>
    <select id="room-id" name="room_id" class="form-control" required>
      <option value="" disabled selected>Select a room</option>
      <c:forEach var="room" items="${rooms}">
        <option value="${room.roomId}">${room.roomName}</option>
      </c:forEach>
    </select>
  </div>
  
  <!-- Booking Dates Section -->
  <div class="row">
    <!-- Booking Date -->
    <div class="col-md-4 mb-3">
      <label for="booking-date" class="form-label text-center d-block">Booking Date</label>
      <input type="date" id="booking-date" name="booking_date" class="form-control text-center" required>
    </div>
    
    <!-- Distribute Key Date -->
    <div class="col-md-4 mb-3">
      <label for="distribute-key-date" class="form-label text-center d-block">Distribute Key Date</label>
      <input type="date" id="distribute-key-date" name="distribute_key_date" class="form-control text-center" required>
    </div>
    
    <!-- Return Key Date -->
    <div class="col-md-4 mb-3">
      <label for="return-key-date" class="form-label text-center d-block">Return Key Date</label>
      <input type="date" id="return-key-date" name="return_key_date" class="form-control text-center" required>
    </div>
  </div>

  <!-- Payment Method Section -->
  <div class="text-center my-4">
    <label class="form-label d-block mb-3">Payment Method</label>
    <c:forEach var="payment" items="${paymentTypes}">
      <div class="form-check form-check-inline">
        <input type="radio" id="payment-${payment.paymentId}" name="payment_method" 
               value="${payment.paymentId}" class="form-check-input" required>
        <label for="payment-${payment.paymentId}" class="form-check-label">${payment.paymentType}</label>
      </div>
    </c:forEach>
  </div>

  <!-- Submit Button -->
  <div class="text-center">
    <button type="submit" class="btn btn-warning text-white">Book Now</button>
  </div>
</form>

            <!-- End Booking Form -->

            <!-- User Information Display -->
            <div class="mt-4">
              <%
                String userId = (String) request.getSession(false).getAttribute("userId");
                String role = (String) request.getSession(false).getAttribute("role");
              %>

              <% if (userId != null) { %>
                <p>Welcome, User ID: <%= userId %> - Role: <%= role %></p>
              <% } else { %>
                <p>Welcome, Guest! Please log in to make a booking.</p>
              <% } %>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- External JavaScript -->
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
