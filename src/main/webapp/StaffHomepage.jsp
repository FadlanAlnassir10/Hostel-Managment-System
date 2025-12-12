<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>HostelMate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/style.css">
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
                                        
                                                <li><a href="Login.jsp">Logout</a></li>
                                            
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

    <section class="site-hero overlay" style="background-image: url(images/staffhomepage3.jpg);" data-stellar-background-ratio="0.5">
        <div class="container">
            <div class="row site-hero-inner justify-content-center align-items-center">
                <div class="col-md-10 text-center" data-aos="fade-up">
                    <span class="custom-caption text-uppercase text-white d-block mb-3">
                        "Together, let's create the best experience for our valued hostel guests."
                    </span>
                    <h1 class="heading">We're thrilled to have you join our team!</h1>
                </div>
            </div>
        </div>
    </section>
   
    <!-- Scripts -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
