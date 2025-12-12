package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import hostelMateDAO.RoomDAO;
import hostelMateModel.Room;

@WebServlet("/ViewRoomTypeController")
public class ViewRoomTypeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewRoomTypeController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch the list of rooms from the database
            List<Room> roomList = RoomDAO.getAllRoomDetails();

            // Set the roomList as a request attribute
            request.setAttribute("roomList", roomList);

            // Forward the request to the JSP page
            request.getRequestDispatcher("roomType.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch room data.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}