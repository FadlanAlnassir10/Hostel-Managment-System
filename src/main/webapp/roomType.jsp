<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Room Type</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="author" content="" />
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

    <!-- Theme Style -->
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    
    <header class="site-header js-site-header">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="col-6 col-lg-4 site-logo" data-aos="fade"><a href="Homepage.jsp">HostelMate</a></div>
          <div class="col-6 col-lg-8">


            <div class="site-menu-toggle js-site-menu-toggle"  data-aos="fade">
              <span></span>
              <span></span>
              <span></span>
            </div>
            <!-- END menu-toggle -->

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
    <!-- END head -->

    <section class="site-hero inner-page overlay" style="background-image: url(images/roombackground.jpg)" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade">
            <h1 class="heading mb-3">Room Type</h1>
            <span class="custom-caption text-uppercase text-white d-block  mb-3">"We offer three types of rooms, each with its own unique features. Feel free to choose the one that best suits your comfort."</span>
          </div>
        </div>
      </div>

      <a class="mouse smoothscroll" href="#next">
        <div class="mouse-icon">
          <span class="mouse-wheel"></span>
        </div>
      </a>
    </section>
    <!-- END section -->

  

    <!-- Loop Section of room  -->
<section class="section bg-light">
  <div class="container">
    <div class="row justify-content-center text-center mb-5">
      <div class="col-md-7">
        <h2 class="heading" data-aos="fade">Explore Your New Space</h2>
        <p data-aos="fade">"Your new home away from home, near your university. A space to study, socialize, and succeed. Comfortable rooms, a supportive community, and everything you need to make the most of your student journey."</p>
      </div>
    </div>

    <c:forEach var="room" items="${roomList}" varStatus="loop">
      <div class="site-block-half d-block d-lg-flex bg-white" data-aos="fade" data-aos-delay="${loop.index * 100}">
        <!-- Fix: Add data:image/jpeg;base64, to the Base64 image string -->
        <a href="#" class="image d-block bg-image-2 ${loop.index % 2 == 1 ? 'order-2' : ''}" 
           style="background-image: url('data:image/jpeg;base64,${room.roomImage}');"></a>
        <div class="text ${loop.index % 2 == 1 ? 'order-1' : ''}">
          <span class="d-block mb-4"><span class="display-4 text-primary">RM ${room.roomPrice}</span> <span class="text-uppercase letter-spacing-2">/ per day</span></span>
          <h2 class="mb-4">${room.roomId} - ${room.roomName}</h2>
          <p>${room.roomDesc}</p>
          <p>
            <c:choose>
              <c:when test="${not empty sessionScope.customerId}">
                <a href="ViewBookingController?customerId=${sessionScope.customerId}" class="btn btn-primary text-white">Book Now</a>
              </c:when>
              <c:otherwise>
                <a href="Login.jsp" class="btn btn-primary text-white">Book Now</a>
              </c:otherwise>
            </c:choose>
          </p>
        </div>
      </div>
    </c:forEach>
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