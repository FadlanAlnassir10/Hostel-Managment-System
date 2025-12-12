package hostelMateController;

import java.io.IOException;
import java.util.List;

import hostelMateDAO.RoomDAO;
import hostelMateModel.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/AddRoomController")
@MultipartConfig
public class AddRoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String roomId = request.getParameter("roomId");
        String roomName = request.getParameter("roomName");
        String roomPrice = request.getParameter("roomPrice");
        String roomDesc = request.getParameter("roomDesc");
        Part roomImage = request.getPart("roomImage");

        try {
        	
        	 // Validate uniqueness of room ID and room name
            boolean isUnique = RoomDAO.isRoomUnique(roomId, roomName);
            if (!isUnique) {
                // Send error message if the room ID or name is not unique
                request.setAttribute("message", "Room ID or Room Name already exists. Please use unique values.");
                request.getRequestDispatcher("AddRoom.jsp").forward(request, response);
                return;
            }
            
            
            // Create a new Room object
            Room room = new Room();
            room.setRoomId(roomId);  // Directly use the provided Room ID
            room.setRoomName(roomName);
            room.setRoomPrice(Double.parseDouble(roomPrice));
            room.setRoomDesc(roomDesc);

            // Convert room image to Base64 (if provided)
            if (roomImage != null && roomImage.getSize() > 0) {
                byte[] imageBytes = roomImage.getInputStream().readAllBytes();
                String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
                room.setRoomImage(base64Image);
            } else {
                room.setRoomImage(null); // No image provided
            }

            // Insert room into the database using RoomDAO
            RoomDAO.addRoom(room);

            // Fetch the updated list of rooms after addition
            List<Room> roomList = RoomDAO.getAllRooms();

            // Set the updated room list and success message
            request.setAttribute("roomList", roomList);
            request.setAttribute("message", "New room added successfully with Room ID: " + roomId);

        } catch (Exception e) {
            e.printStackTrace();
            // Set error message if something goes wrong
            request.setAttribute("message", "Error adding room.");
        }

        // Forward to AllRoomDetails.jsp to display the list of rooms and the message
        request.getRequestDispatcher("RoomDetails.jsp").forward(request, response);
    }
}
