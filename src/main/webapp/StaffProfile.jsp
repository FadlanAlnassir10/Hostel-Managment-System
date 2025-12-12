<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Staff Profile</title>
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
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <header class="site-header js-site-header">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="col-6 col-lg-4 site-logo" data-aos="fade"><a href="StaffHomepage.jsp?staffId=${sessionScope.staffId}">HostelMate Staff</a></div>
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
                         <c:choose>
                            <c:when test="${not empty sessionScope.staffId}">
                                <li><a href="ViewSearchBookingController?customerId=${sessionScope.staffId}">Search Booking</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="Login.jsp">Search Booking</a></li>
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

    <section class="site-hero inner-page" 
             style="background-image: url(images/slider-1.jpg); 
                    background-size: cover; 
                    background-position: center; 
                    background-repeat: no-repeat; 
                    height: 100vh; 
                    width: 100vw;" 
             data-stellar-background-ratio="0.5">
      <div class="container" style="min-height: 100vh; display: flex; flex-direction: column; justify-content: center;">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade">
            <div style="margin-top: 0%;">
              <h1 class="heading mb-3">Staff Profile</h1>
            </div>
            <div style="display: flex; justify-content: center; align-items: center; background: rgba(255, 255, 255, 0.8); padding: 20px; border-radius: 10px;">
              <div style="margin-right: 20px; width: 400px;">
                <h2>Profile</h2>
                <p><strong>Name:</strong> ${staff.staffName}</p>
                <p><strong>IC Number:</strong> ${staff.staffIcNumber}</p>
                <p><strong>Email:</strong> ${staff.staffEmail}</p>
                <p><strong>Phone:</strong> ${staff.staffNoPhone}</p>
              </div>

              <div style="text-align: center; width: 200px;">
                <h3>Profile Picture</h3>
                <div class="profile-picture" id="profilePreview">
                  <img id="previewImg" src="data:image/jpeg;base64,${staff.staffImage}" alt="Profile Picture" style="border-radius: 50%; width: 100%; height: 100%; object-fit: cover;">
                </div>
              </div>
            </div>
          </div>
        </div>
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
