<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Room</title>
    
    <!-- Bootstrap CSS -->
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
    <!-- Custom Styles -->
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden; /* Ensures no scrolling and the background fits perfectly */
        }

        .site-hero {
            background-image: url('images/background2.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            height: 100%;
            width: 100%;
            padding: 0;
        }

        .container {
            margin-top: 0px;
        }

        .form-container {
            padding: 20px;
            background: rgba(255, 255, 255, 0.9); /* Slight transparency for better readability */
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .btn-custom {
            background: linear-gradient(45deg, #ff8c00, #ffa500);
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 40px;
            cursor: pointer;
            font-size: 1.25rem;
            font-weight: bold;
            transition: all 0.3s ease;
        }

        .btn-custom:hover {
            background: linear-gradient(45deg, #ffa500, #ff8c00);
            transform: scale(1.05);
        }

        .profile-img {
            max-width: 150px;
            max-height: 150px;
            border-radius: 50%;
            margin-bottom: 20px;
        }
    </style>
<body>

<header class="site-header js-site-header">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="col-6 col-lg-4 site-logo" data-aos="fade">
            <a href="AdminHomepage.html">HostelMate Admin</a>
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
    
     <script type="text/javascript">
    <c:if test="${not empty message}">
        alert("${message}");
    </c:if>
</script>
    

<section class="site-hero">
    <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
            <div class="col-md-10 text-center" data-aos="fade">
                <h1 class="heading mb-3">Room Details</h1>
            

                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-8 form-container">
                            <h2 class="text-center mb-4">Update Room</h2>

                            <!-- Display Current Room Picture -->
                            <div class="mb-3 text-center">
                                <c:if test="${not empty room.roomImage}">
                                    <!-- Display the current room picture using Base64 encoding -->
                                    <img src="data:image/jpeg;base64,${room.roomImage}" alt="Room Image" class="profile-img">
                                </c:if>
                                <c:if test="${empty room.roomImage}">
                                    <!-- Default placeholder if no picture exists -->
                                    <img src="images/default-room.png" alt="Default Room Picture" class="profile-img">
                                </c:if>
                            </div>

                            <!-- Update Room Form -->
                            <form action="UpdateRoomController" method="POST" enctype="multipart/form-data">
                                <!-- Room ID (hidden) -->
                                <input type="hidden" name="roomId" value="${room.roomId}" required>
                                
                                <!-- Room Name (read-only) -->
                                <div class="mb-3">
                                    <label for="roomName" class="form-label">Room Name</label>
                                    <input type="text" id="roomName" name="roomName" class="form-control" value="${room.roomName}" require>
                                </div>

                                <!-- Room Price (read-only) -->
                                <div class="mb-3">
                                    <label for="roomPrice" class="form-label">Room Price</label>
                                    <input type="text" id="roomPrice" name="roomPrice" class="form-control" value="${room.roomPrice}" require>
                                </div>

                                <!-- Room Description (editable) -->
                                <div class="mb-3">
                                    <label for="roomDesc" class="form-label">Room Description</label>
                                    <input type="text" id="roomDesc" name="roomDesc" class="form-control" value="${room.roomDesc}" required>
                                </div>

                                <!-- Room Picture (optional) -->
                                <div class="mb-3">
                                    <label for="roomImage" class="form-label">Upload New Room Picture (Optional)</label>
                                    <input type="file" id="roomImage" name="roomImage" class="form-control" accept="image/*">
                                    <small class="form-text text-muted">If you don't want to change the picture, leave it blank.</small>
                                </div>

                                <!-- Submit Button -->
                                <div class="text-center">
                                    <button type="submit" class="btn btn-custom btn-lg">Update Room</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
    </div>
</section>

<!-- Bootstrap and jQuery JS -->
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
