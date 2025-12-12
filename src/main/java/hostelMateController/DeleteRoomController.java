package hostelMateController;

import hostelMateDAO.RoomDAO;
import hostelMateDAO.BookingDAO;  // Assuming a BookingDAO to check if the room is in use
import hostelMateModel.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class DeleteRoomController
 */
@WebServlet("/DeleteRoomController")
public class DeleteRoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public DeleteRoomController() {
        super();
    }

    /**
     * Handles the GET request to delete a room by its ID.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the room ID from the URL parameter
        String roomId = request.getParameter("roomId");

        if (roomId == null || roomId.isEmpty()) {
            // Respond with an error if the room ID is not provided
            response.getWriter().println("Error: Room ID is required.");
            return;
        }

        try {
            // Check if the room is in use (i.e., associated with a booking or active service)
            boolean isRoomInUse = BookingDAO.isRoomInUse(roomId);  // Assuming this method checks if the room has any bookings

            if (isRoomInUse) {
                // If the room is in use, show an error message
                request.setAttribute("message", "Cannot delete room with ID " + roomId + " as it is currently in use.");
                request.getRequestDispatcher("RoomDetailsController").forward(request, response);
                return;
            }

            // Call the DAO method to delete the room
            RoomDAO.deleteRoom(roomId);  // Delete the room from the database

            // Set a success message
            request.setAttribute("message", "Room with ID " + roomId + " has been deleted successfully.");

            // Fetch the updated list of rooms after deletion
            List<Room> roomList = RoomDAO.getAllRooms();  // Fetch the updated room list

            // Set the updated room list as a request attribute
            request.setAttribute("roomList", roomList);

            // Forward to the JSP page to display the updated room list and message
            request.getRequestDispatcher("RoomDetails.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();

            // Set an error message
            request.setAttribute("error", "Error occurred while deleting room: " + e.getMessage());

            // Forward to the JSP page to display the error message
            request.getRequestDispatcher("RoomDetails.jsp").forward(request, response);
        }
    }
}
