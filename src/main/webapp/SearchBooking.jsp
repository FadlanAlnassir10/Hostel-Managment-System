<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Search Booking</title>
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
    <script type="text/javascript">
        // Function to handle the search by booking ID
        function searchBooking() {
            var searchValue = document.getElementById("searchBox").value.trim();
            if (searchValue !== "") {
                window.location.href = "ViewSearchBookingController?search=" + encodeURIComponent(searchValue);
            } else {
                alert("Please enter a booking ID to search.");
            }
        }

        // Function to handle the reset action and show all bookings
        function resetSearch() {
            window.location.href = "ViewSearchBookingController";
        }
    </script>
  </head>
  <body>
    <header class="site-header js-site-header">
    <div class="container-fluid">
        <div class="row align-items-center">
            <div class="col-6 col-lg-4 site-logo" data-aos="fade">
                <a href="StaffHomepage.jsp?staffId=${sessionScope.staffId}">HostelMate Staff</a>
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
                                         <!-- Dynamic Profile Link -->
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.staffId}">
                                                <li><a href="StaffHomepage.jsp?staffId=${sessionScope.staffId}">Home</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="Login.jsp">Login</a></li>
                                            </c:otherwise>
                                        </c:choose>                   
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.staffId}">
                                                <li><a href="ViewStaffProfileController?staffId=${sessionScope.staffId}">Staff Profile</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="Login.jsp">Login</a></li>
                                            </c:otherwise>
                                        </c:choose>                   
                                        <!-- Dynamic Booking Link -->
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.staffId}">
                                                <li><a href="ViewSearchBookingController?customerId=${seessionScope.staffId}">Search Booking</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="Login.jsp">Search Booking</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                        <!-- Logout Link -->
                                       
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
            <h1 class="heading mb-3">Search Booking</h1>
            <span class="custom-caption text-uppercase text-white d-block mb-3">"All customers' booking details are here"</span>
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
            <h2 class="heading" data-aos="fade">Customer Booking Details</h2>
          </div>
        </div>
        <form onsubmit="event.preventDefault(); searchBooking();">
          <div class="search-container mb-4">
            <input type="text" id="searchBox" name="search" placeholder="Enter Booking ID" style="padding: 10px; width: 70%; margin-right: 10px;">
            <button type="submit" class="btn btn-primary">Search</button>
            <button type="button" class="btn btn-secondary" onclick="resetSearch();">Reset</button>
          </div>
        </form>
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>Booking ID</th>
              <th>Customer Name</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="booking" items="${bookings}">
              <tr>
                <td>${booking.bookingId}</td>
                <td>
                  <c:if test="${not empty customerMap[booking.custId]}">
                    ${customerMap[booking.custId].custName}
                  </c:if>
                </td>
                <td>
                  <a href="BookingDetailsInfoController?bookingId=${booking.bookingId}" class="btn btn-info">Details</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <c:if test="${not empty message}">
          <p>${message}</p>
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
