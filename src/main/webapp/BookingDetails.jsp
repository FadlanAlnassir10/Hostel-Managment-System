<!--<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking Invoice</title>
</head>
<body>
    <h2>Booking Invoice</h2>

    <p><strong>Booking ID:</strong> ${bookingId}</p> <!-- Added booking ID
    <p><strong>Customer Name:</strong> ${customerName}</p>
    <p><strong>Email:</strong> ${customerEmail}</p>
    <p><strong>Contact Number:</strong> ${customerPhone}</p>
    <p><strong>Customer ID:</strong> ${customerId}</p>
    <p><strong>Room Name:</strong> ${roomName}</p>
    <p><strong>Booking Date:</strong> ${bookingDate}</p>
    <p><strong>Booking Distribute Key:</strong> ${distributeKeyDate}</p>
    <p><strong>Total Payment:</strong> ${totalPayment}</p>
    <p><strong>Room Price:</strong> ${roomPrice}</p>
    <p><strong>Payment Type:</strong> ${paymentType}</p>
    <p><strong>Booking Return Key:</strong> ${returnKeyDate}</p>

</body>
</html>-->

<%@ page import="java.util.Date" %>
<%@ page import="hostelMateModel.Booking" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Booking Information</title>
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
      .info-container {
        background-color: #d8c5a2;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        max-width: 600px;
        margin: 50px auto;
      }
      .info-container h2 {
        text-align: center;
        margin-bottom: 20px;
        font-weight: bold;
      }
      .info-container p {
        margin: 5px 0;
        font-size: 16px;
      }
      .info-container .label {
        font-weight: bold;
      }
    </style>
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
                         
                                <li><a href="StaffHomepage.jsp?staffId=${sessionScope.staffId}">Home</a></li>
                            
                           
                                <li><a href="ViewStaffProfileController?staffId=${sessionScope.staffId}">Staff Profile</a></li>
                            
                                <li><a href="ViewSearchBookingController?customerId=${sessionScope.staffId}">Search Booking</a></li>
                            
                              
                            
                             
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

    <section class="site-hero overlay" style="background-image: url(images/frontImg2.jpg)" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade-up">
            <h1 class="heading">Booking Details</h1>
            <span class="custom-caption text-uppercase text-white d-block mb-3">
              "All your booking details are here."
            </span>
          </div>
        </div>
      </div>
      <a class="mouse smoothscroll" href="#next">
        <div class="mouse-icon">
          <span class="mouse-wheel"></span>
        </div>
      </a>
    </section>

    <section id="next">
      <div class="info-container">
        <h2>Booking Details</h2>
        <p><span class="label">Booking ID:</span> ${bookingId}</p>
        <p><span class="label">Billed To:</span></p>
        <p>&emsp;<span class="label">Customer ID:</span> ${customerId}</p>
        <p>&emsp;<span class="label">Customer Name:</span> ${customerName}</p>
        <p>&emsp;<span class="label">Email:</span> ${customerEmail}</p>
        <p>&emsp;<span class="label">Contact Number:</span> ${customerPhone}</p>
        <p><span class="label">Description:</span></p>
        <p>&emsp;<span class="label">Room Name:</span> ${roomName}</p>
        <p>&emsp;<span class="label">Room Price:</span> ${roomPrice}</p>
        <p>&emsp;<span class="label">Booking Date:</span> ${bookingDate}</p>
        <p>&emsp;<span class="label">Booking Distribute Key:</span> ${distributeKeyDate}</p>
        <p>&emsp;<span class="label">Booking Return Key:</span> ${returnKeyDate}</p>
        <p>&emsp;<span class="label">Total Payment:</span> ${totalPayment}</p>
        <p>&emsp;<span class="label">Payment Type:</span> ${paymentMethod}</p>
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

