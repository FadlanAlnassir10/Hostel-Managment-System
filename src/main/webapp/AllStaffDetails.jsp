<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
      /* Custom styles for table background */
      .table-container {
        background-color: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      }
      .action-button {
        float: right;
      }
    </style>
  </head>
  <body>

    <header class="site-header js-site-header">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="col-6 col-lg-4 site-logo" data-aos="fade"><a href="AdminHomepage.html">HostelMate Admin</a></div>
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

    <!-- Main Section -->
    <section class="site-hero overlay" style="background-image: url(images/img1.jpg);" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade-up">
            <h1 class="heading">Staff Details</h1>
            <div class="row mt-5">
              <div class="col-md-12">
                <div class="table-container" style="margin-left: 20px;">
                  <div class="d-flex justify-content-between align-items-center">
                    <h2 class="text-center w-100">Staff Information</h2>
                    <a href="RegisterStaff.jsp" class="btn btn-success action-button">Add Staff</a>
                  </div>
                  <table class="table table-bordered table-striped mt-4">
                    <thead class="thead-dark">
                      <tr>
                        <th scope="col">Staff ID</th>
                        <th scope="col">Staff Name</th>
                        <th scope="col">Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:if test="${not empty staffList}">
                        <c:forEach var="staff" items="${staffList}">
                          <tr>
                            <td>${staff.staffId}</td>
                            <td>${staff.staffName}</td>
                            <td>
                              <a href="ViewUpdateStaffController?staffId=${staff.staffId}" class="btn btn-primary btn-sm">Edit</a>
                              <a href="DeleteStaffController?staffId=${staff.staffId}" class="btn btn-danger btn-sm" onclick="return confirmDeletion()">Delete</a>
                            </td>
                          </tr>
                        </c:forEach>
                      </c:if>
                      <c:if test="${empty staffList}">
                        <tr>
                          <td colspan="3" class="text-center">No staff records found.</td>
                        </tr>
                      </c:if>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Message Alert using JavaScript -->
    <script>
      window.onload = function() {
        // Check for the message or error from the server-side
        var message = '<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>';
        var error = '<%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>';
        
        if (message) {
          alert(message);
        }
        if (error) {
          alert(error);
        }
      }

      // Function to confirm deletion before proceeding
      function confirmDeletion() {
        return confirm("Are you sure you want to delete this staff member?");
      }
      
      // Display alert based on status
      const status = "${status}";
      if (status === "success") {
          alert("Staff details updated successfully!");
      } else if (status === "failure") {
          alert("Failed to update staff details. Please try again.");
      }
    </script>

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
