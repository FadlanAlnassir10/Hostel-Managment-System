package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Base64;

import hostelMateDAO.RoomDAO;
import hostelMateModel.Room;

@WebServlet("/UpdateRoomController")
@MultipartConfig  // Annotation to enable file upload handling
public class UpdateRoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateRoomController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Retrieve form data
        String roomId = request.getParameter("roomId");
        String roomName = request.getParameter("roomName");
        double roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
        String roomDesc = request.getParameter("roomDesc");

        try {

            // Retrieve the existing room record from the database
            Room existingRoom = RoomDAO.getRoomById(roomId);

            // Handle file upload for room image
            Part roomImagePart = request.getPart("roomImage");
            String roomImageBase64 = null;

            if (roomImagePart != null && roomImagePart.getSize() > 0) {
                // Convert uploaded image to Base64
                byte[] imageBytes = roomImagePart.getInputStream().readAllBytes();
                roomImageBase64 = Base64.getEncoder().encodeToString(imageBytes);
            } else {
                // If no new image is uploaded, retain the old one
                roomImageBase64 = existingRoom != null ? existingRoom.getRoomImage() : null;
            }

            // Create a Room object with the updated data
            Room updatedRoom = new Room();
            updatedRoom.setRoomId(roomId);
            updatedRoom.setRoomName(roomName);
            updatedRoom.setRoomPrice(roomPrice);
            updatedRoom.setRoomDesc(roomDesc);
            updatedRoom.setRoomImage(roomImageBase64);  // Set the image (Base64 string)

            // Update the room record in the database
            boolean isUpdated = RoomDAO.updateRoom(updatedRoom);

            // Check if the update was successful
            if (isUpdated) {
                // Set success message and forward the request to ViewUpdateRoomController or another JSP/Servlet
                request.setAttribute("message", "Room updated successfully.");
                request.getRequestDispatcher("RoomDetailsController").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update room.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }
}
