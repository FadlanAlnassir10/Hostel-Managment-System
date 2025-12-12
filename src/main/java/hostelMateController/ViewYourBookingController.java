package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import hostelMateDAO.BookingDAO;
import hostelMateModel.Booking;

@WebServlet("/ViewYourBookingController")
public class ViewYourBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewYourBookingController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the customer ID from the session
        String custId = (String) request.getSession().getAttribute("customerId");

        if (custId != null) {
            // Fetch bookings for the logged-in customer
            List<Booking> bookings = BookingDAO.getBookingsByCustomerId(custId);

            // Set the bookings as a request attribute
            request.setAttribute("bookings", bookings);

            // Forward the request to the JSP page
            request.getRequestDispatcher("YourBooking.jsp").forward(request, response);
        } else {
            // If no customer ID in session, redirect to login page
            response.sendRedirect("Login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}