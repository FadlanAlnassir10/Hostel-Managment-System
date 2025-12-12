<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Your Booking</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=|Roboto+Sans:400,700|Playfair+Display:400,700">
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
  /* Table Styling */
  .custom-table {
    width: 100%;
    margin-bottom: 1rem;
    color: #212529;
    border-collapse: collapse;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
  }
  .custom-table th,
  .custom-table td {
    padding: 12px;
    text-align: center;
    vertical-align: middle;
    border: 1px solid #dee2e6;
  }
  .custom-table thead th {
    vertical-align: bottom;
    border-bottom: 2px solid #dee2e6;
    background-color: #371D10; /* Brown background */
    color: white; /* White text */
    font-weight: 600;
  }
  .custom-table tbody tr:nth-of-type(odd) {
    background-color: rgba(0, 0, 0, 0.05);
  }
  .custom-table tbody tr:hover {
    background-color: rgba(0, 0, 0, 0.075);
  }
  .custom-table-bordered {
    border: 1px solid #dee2e6;
  }
  .custom-table-bordered th,
  .custom-table-bordered td {
    border: 1px solid #dee2e6;
  }
  .custom-table-hover tbody tr:hover {
    background-color: rgba(0, 0, 0, 0.075);
  }
  .custom-table-shadow {
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
  }
  .custom-table-responsive {
    display: block;
    width: 100%;
    overflow-x: auto;
    -webkit-overflow-scrolling: touch;
  }
</style>
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
              <span></span>
              <span></span>
              <span></span>
            </div>
            <div class="site-navbar js-site-navbar">
              <nav role="navigation">
                <div class="container">
                  <div class="row full-height align-items-center">
                    <div class="col-md-6 mx-auto">
                      <ul class="list-unstyled menu">
                        <li class="active"><a href="Homepage.jsp">Home</a></li>
                        <c:choose>
                          <c:when test="${not empty sessionScope.customerId}">
                            <li><a href="ViewRoomTypeController?customerId=${sessionScope.customerId}">Rooms</a></li>
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
                            <li><a href="ViewYourBookingController">Your Booking</a></li>
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

    <section class="site-hero inner-page overlay" style="background-image: url(images/hero_2.jpg);" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade">
            <h1 class="heading mb-3">Your Booking</h1>
            <span class="custom-caption text-uppercase text-white d-block mb-3">"Your booking details."</span>
          </div>
        </div>
      </div>
      <a class="mouse smoothscroll" href="#next">
        <div class="mouse-icon">
          <span class="mouse-wheel"></span>
        </div>
      </a>
    </section>

    <section class="section bg-light">
      <div class="container">
        <div class="row justify-content-center text-center mb-5">
          <div class="col-md-7">
            <h2 class="heading" data-aos="fade">Booking Details</h2>
          </div>
        </div>
        <div class="custom-table-responsive">
          <table class="custom-table custom-table-bordered custom-table-hover custom-table-shadow">
            <thead>
              <tr>
                <th>Booking ID</th>
                <th>Booking Date</th>
                <th>Booking Distribute Key</th>
                <th>Booking Return Key</th>
                <th>Room ID</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="booking" items="${bookings}">
                <tr>
                  <td>${booking.bookingId}</td>
                  <td>${booking.bookingDate}</td>
                  <td>${booking.bookingDistributeKey}</td>
                  <td>${booking.bookingReturnKey}</td>
                  <td>${booking.roomId}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <c:if test="${empty bookings}">
          <p>No bookings found.</p>
        </c:if>
      </div>
    </section>

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