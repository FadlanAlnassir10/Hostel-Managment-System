<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Room</title>
    
    <!-- Linking Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Custom Styles -->
    <style>
        .container {
            margin-top: 50px;
        }
        .form-container {
            padding: 20px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
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
        .error-message {
            color: red;
            font-size: 0.9rem;
            display: none;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 form-container">
                <h2 class="text-center mb-4">Add New Room</h2>
                
              <!-- Display Alert Message -->
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                %>
                    <script>
                        alert("<%= message %>");
                    </script>
                <%
                    }
                %>

                <!-- Add Room Form -->
                <form id="roomForm" action="AddRoomController" method="POST" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="roomId" class="form-label">Room ID</label>
                        <input type="text" id="roomId" name="roomId" class="form-control" placeholder="Enter Room ID" required>
                    </div>

                    <div class="mb-3">
                        <label for="roomName" class="form-label">Room Name</label>
                        <input type="text" id="roomName" name="roomName" class="form-control" placeholder="Enter Room Name" required>
                    </div>

                    <div class="mb-3">
                        <label for="roomPrice" class="form-label">Room Price</label>
                        <input type="number" id="roomPrice" name="roomPrice" class="form-control" placeholder="Enter Room Price" step="0.01" required>
                    </div>

                    <div class="mb-3">
                        <label for="roomDesc" class="form-label">Room Description</label>
                        <textarea id="roomDesc" name="roomDesc" class="form-control" placeholder="Enter Room Description" rows="3" required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="roomImage" class="form-label">Upload Room Image</label>
                        <input type="file" id="roomImage" name="roomImage" class="form-control">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-custom btn-lg">Add Room</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Linking Bootstrap and jQuery JS -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>
