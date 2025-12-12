package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import hostelMateDAO.RoomDAO;
import hostelMateModel.Room;

/**
 * Servlet implementation class ViewUpdateRoomController
 */
@WebServlet("/ViewUpdateRoomController")
public class ViewUpdateRoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUpdateRoomController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String roomId = request.getParameter("roomId");
        String message = request.getParameter("message");

        if (roomId == null || roomId.trim().isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().write("<h1>Error: roomId is missing or empty.</h1>");
            return;
        }

        try {
            // Fetch room details using DAO
            Room room = RoomDAO.getRoomById(roomId);  // Changed to RoomDAO

            if (room != null && room.getRoomId() != null) {
                // Set the room object as a request attribute
                request.setAttribute("room", room);
                request.setAttribute("message", message);

                // Forward to UpdateRoom.jsp (Assuming this JSP handles updating room details)
                request.getRequestDispatcher("UpdateRoom.jsp").forward(request, response);
            } else {
                response.setContentType("text/html");
                response.getWriter().write("<h1>Error: No room found for roomId: " + roomId + "</h1>");
            }
        } catch (Exception e) {
            response.setContentType("text/html");
            response.getWriter().write("<h1>Error: An exception occurred while fetching room details.</h1>");
            response.getWriter().write("<p>" + e.getMessage() + "</p>");
        }
    }    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
