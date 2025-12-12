package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hostelMateDAO.RoomDAO;
import hostelMateModel.Room;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/RoomDetailsController")
public class RoomDetailsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RoomDetailsController() {
        super();
    }

    // Fetch room data and forward to JSP
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Room> roomList = RoomDAO.getAllRooms();

            // Set the room list as a request attribute
            request.setAttribute("roomList", roomList);

            // Forward the request to the JSP page for rendering
            request.getRequestDispatcher("/RoomDetails.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            response.getWriter().append("Error fetching room details: ").append(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
