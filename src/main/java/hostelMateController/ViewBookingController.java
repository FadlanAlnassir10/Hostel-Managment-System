package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import hostelMateDAO.RoomDAO;
import hostelMateDAO.PaymentDAO;
import hostelMateModel.Room;
import hostelMateModel.Payment;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewBookingController")  // Updated URL pattern
public class ViewBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewBookingController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Check for session and userId
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect("Login.jsp");
                return;
            }
            String userId = (String) session.getAttribute("userId");

            // Set userId for the JSP
            request.setAttribute("userId", userId);

            PaymentDAO paymentDAO = new PaymentDAO();
            List<Room> rooms = RoomDAO.getAllRooms();
            List<Payment> paymentTypes = paymentDAO.getAllPayments();

            // Check if rooms or payment types are empty or null and set error messages
            if (rooms == null || rooms.isEmpty()) {
                request.setAttribute("errorMessage", "No rooms available.");
            }
            if (paymentTypes == null || paymentTypes.isEmpty()) {
                request.setAttribute("errorMessage", "No payment methods available.");
            }

            // Pass the data to the JSP
            request.setAttribute("rooms", rooms);
            request.setAttribute("paymentTypes", paymentTypes);

            // Forward to the booking JSP
            request.getRequestDispatcher("Booking.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();  // Log error details
            request.setAttribute("errorMessage", "An error occurred while retrieving booking information. Please try again later.");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle form submissions if needed
    }
}
