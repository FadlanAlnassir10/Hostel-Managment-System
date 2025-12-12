package hostelMateController;

import hostelMateDAO.BookingDAO;
import hostelMateDAO.CustomerDAO;
import hostelMateModel.Booking;
import hostelMateModel.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ViewSearchBookingController")
public class ViewSearchBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewSearchBookingController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");

        List<Booking> bookings;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            bookings = BookingDAO.getBookingsBySearchCriteria(searchQuery);  // Assuming this method filters bookings by bookingId or other criteria
        } else {
            bookings = BookingDAO.getAllBookings();  // Get all bookings if no search term is provided
        }

        if (bookings != null && !bookings.isEmpty()) {
            // Create a map to store customers by their custId
            Map<String, Customer> customerMap = new HashMap<>();
            for (Booking booking : bookings) {
                Customer customer = CustomerDAO.getCustomer(booking.getCustId());  // Get customer by customer ID
                customerMap.put(customer.getCustId(), customer);  // Store customer by their ID in the map
            }

            // Set bookings and customerMap as attributes in the request
            request.setAttribute("bookings", bookings);
            request.setAttribute("customerMap", customerMap);

            // Forward to JSP
            request.getRequestDispatcher("SearchBooking.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "No bookings found.");
            request.getRequestDispatcher("SearchBooking.jsp").forward(request, response); // Forward to same page
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);  // Handle POST requests by forwarding to doGet (for search functionality)
    }
}
