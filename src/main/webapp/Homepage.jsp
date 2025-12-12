<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>HostelMate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="author" content="" />

    <!-- External Stylesheets -->
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

    <!-- Theme Style -->
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
  
    <!-- Header Section -->
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

            <!-- Navigation Menu -->
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
                        
                        <!-- Room Types Link -->
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

    <!-- Hero Section -->
    <section class="site-hero overlay" style="background-image: url(images/background.jpg)" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade-up">
            <span class="custom-caption text-uppercase text-white d-block mb-3">Welcome To 5 <span class="fa fa-star text-primary"></span> Hostel</span>
            <h1 class="heading">A Best Place To Stay</h1>
          </div>
        </div>
      </div>
      <a class="mouse smoothscroll" href="#next">
        <div class="mouse-icon">
          <span class="mouse-wheel"></span>
        </div>
      </a>
    </section>

    <!-- Welcome Section -->
    <section class="py-5 bg-light">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-12 col-lg-7 ml-auto order-lg-2 position-relative mb-5" data-aos="fade-up">
            <figure class="img-absolute">
              <img src="images/food-1.jpg" alt="Image" class="img-fluid">
            </figure>
            <img src="images/img_1.jpg" alt="Image" class="img-fluid rounded">
          </div>
          <div class="col-md-12 col-lg-4 order-lg-1" data-aos="fade-up">
            <h2 class="heading">Welcome!</h2>
            <p class="mb-4">Looking for a luxury but affordable hostel? HostelMate is the answer! Stay at our student-friendly hostel, where comfort meets convenience, and enjoy affordable rates, a vibrant community, and a great location near your campus!</p>
          </div>
        </div>
      </div>
    </section>

    <!-- Rooms & Suites Section -->
    <section class="section">
      <div class="container">
        <div class="row justify-content-center text-center mb-5">
          <div class="col-md-7">
            <h2 class="heading" data-aos="fade-up">Rooms &amp; Suites</h2>
            <p data-aos="fade-up" data-aos-delay="100">HostelMate offers three amazing room choices to suit your needs: cozy basic rooms, spacious classic rooms, and magnificent premium rooms—all designed for comfort and affordability!</p>
          </div>
        </div>
        <div class="row">
          <!-- Basic Room -->
          <div class="col-md-6 col-lg-4" data-aos="fade-up">
            <a href="#" class="room">
              <figure class="img-wrap">
                <img src="images/img_1.jpg" alt="Room Image" class="img-fluid mb-3">
              </figure>
              <div class="p-3 text-center room-info">
                <h2>R010 - Basic Room</h2>
                <span class="text-uppercase letter-spacing-1">RM40 / per day</span>
              </div>
            </a>
          </div>

          <!-- Classic Room -->
          <div class="col-md-6 col-lg-4" data-aos="fade-up">
            <a href="#" class="room">
              <figure class="img-wrap">
                <img src="images/img_2.jpg" alt="Room Image" class="img-fluid mb-3">
              </figure>
              <div class="p-3 text-center room-info">
                <h2>R020 - Classic Room</h2>
                <span class="text-uppercase letter-spacing-1">RM80 / per day</span>
              </div>
            </a>
          </div>

          <!-- Premium Room -->
          <div class="col-md-6 col-lg-4" data-aos="fade-up">
            <a href="#" class="room">
              <figure class="img-wrap">
                <img src="images/img_3.jpg" alt="Room Image" class="img-fluid mb-3">
              </figure>
              <div class="p-3 text-center room-info">
                <h2>R030 - Premium Room</h2>
                <span class="text-uppercase letter-spacing-1">RM150 / per day</span>
              </div>
            </a>
          </div>
        </div>
      </div>
    </section>
   
    <section class="section slider-section bg-light">
      <div class="container">
        <div class="row justify-content-center text-center mb-5">
          <div class="col-md-7">
            <h2 class="heading" data-aos="fade-up">Photos</h2>
            <p data-aos="fade-up" data-aos-delay="100">Here are the pictures provided as references for the available rooms at HostelMate—take a closer look at your future cozy stay! Choose the room that fits your style and make yourself at home.</p>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="home-slider major-caousel owl-carousel mb-5" data-aos="fade-up" data-aos-delay="200">
              <div class="slider-item">
                <a href="images/slider-1.jpg" data-fancybox="images"><img src="images/slider-1.jpg" alt="Image placeholder" class="img-fluid"></a>
              </div>
              <div class="slider-item">
                <a href="images/slider-2.jpg" data-fancybox="images"><img src="images/slider-2.jpg" alt="Image placeholder" class="img-fluid"></a>
              </div>
              <div class="slider-item">
                <a href="images/slider-3.jpg" data-fancybox="images"><img src="images/slider-3.jpg" alt="Image placeholder" class="img-fluid"></a>
              </div>
              <div class="slider-item">
                <a href="images/slider-4.jpg" data-fancybox="images"><img src="images/slider-4.jpg" alt="Image placeholder" class="img-fluid"></a>
              </div>
              <div class="slider-item">
                <a href="images/slider-5.jpg" data-fancybox="images"><img src="images/slider-5.jpg" alt="Image placeholder" class="img-fluid"></a>
              </div>
              <div class="slider-item">
                <a href="images/slider-6.jpg" data-fancybox="images"><img src="images/slider-6.jpg" alt="Image placeholder" class="img-fluid"></a>
              </div>
              <div class="slider-item">
                <a href="images/slider-7.jpg" data-fancybox="images"><img src="images/slider-7.jpg" alt="Image placeholder" class="img-fluid"></a>
              </div>
            </div>
            <!-- END slider -->
          </div>
        
        </div>
      </div>
    </section>
    <!-- END section -->
    
    <!-- END section -->
    <section class="section testimonial-section">
      <div class="container">
        <div class="row justify-content-center text-center mb-5">
          <div class="col-md-7">
            <h2 class="heading" data-aos="fade-up">People Says</h2>
          </div>
        </div>
        <div class="row">
          <div class="js-carousel-2 owl-carousel mb-5" data-aos="fade-up" data-aos-delay="200">
            
            <div class="testimonial text-center slider-item">
              <div class="author-image mb-3">
                <img src="images/person_1.jpg" alt="Image placeholder" class="rounded-circle mx-auto">
              </div>
              <blockquote>

                <p>&ldquo;HostelMate is the ultimate go-to website for students! If you're looking for a room near your campus, this platform makes hostel booking easy, fast, and reliable—highly recommended by students everywhere!&rdquo;</p>
              </blockquote>
              <p><em>&mdash; Jean Smith</em></p>
            </div> 

            <div class="testimonial text-center slider-item">
              <div class="author-image mb-3">
                <img src="images/person_2.jpg" alt="Image placeholder" class="rounded-circle mx-auto">
              </div>
              <blockquote>
                <p>&ldquo;HostelMate is the perfect platform for students to book hostels near their campus. With its seamless interface and reliable listings, it’s highly recommended for a stress-free booking experience!&rdquo;</p>
              </blockquote>
              <p><em>&mdash; John Doe</em></p>
            </div>

            <div class="testimonial text-center slider-item">
              <div class="author-image mb-3">
                <img src="images/person_3.jpg" alt="Image placeholder" class="rounded-circle mx-auto">
              </div>
              <blockquote>

                <p>&ldquo;Looking for a room near campus? HostelMate’s got your back with quick bookings, friendly service, and a whole lot of student-approved vibes—highly recommended for your next stay!&rdquo;</p>
              </blockquote>
              <p><em>&mdash; John Black</em></p>
            </div>


            <div class="testimonial text-center slider-item">
              <div class="author-image mb-3">
                <img src="images/person_1.jpg" alt="Image placeholder" class="rounded-circle mx-auto">
              </div>
              <blockquote>

                <p>&ldquo;HostelMate is the ultimate solution for students searching for a room near their campus—easy to use, reliable, and absolutely hassle-free. 5 stars for students looking for the best hostel booking experience!&rdquo;</p>
              </blockquote>
              <p><em>&mdash; Sam Smith</em></p>
            </div> 

            <div class="testimonial text-center slider-item">
              <div class="author-image mb-3">
                <img src="images/person_2.jpg" alt="Image placeholder" class="rounded-circle mx-auto">
              </div>
              <blockquote>
                <p>&ldquo;Need a place close to campus? HostelMate offers a fast, reliable booking experience—definitely worth trying!&rdquo;</p>
              </blockquote>
              <p><em>&mdash; Luke Doe</em></p>
            </div>

            <div class="testimonial text-center slider-item">
              <div class="author-image mb-3">
                <img src="images/person_3.jpg" alt="Image placeholder" class="rounded-circle mx-auto">
              </div>
              <blockquote>

                <p>&ldquo;HostelMate simplifies finding rooms near campus, making it the top choice for students in need of accommodation!&rdquo;</p>
              </blockquote>
              <p><em>&mdash; Nick Gah</em></p>
            </div>

          </div>
            <!-- END slider -->
        </div>

      </div>
    </section>

    <section class="section bg-image overlay" style="background-image: url('images/background.jpg');">
        <div class="container" >
          <div class="row align-items-center">
            <div class="col-12 col-md-6 text-center mb-4 mb-md-0 text-md-left" data-aos="fade-up">
              <h2 class="text-white font-weight-bold">A Best Place To Stay. Reserve Now!</h2>
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
