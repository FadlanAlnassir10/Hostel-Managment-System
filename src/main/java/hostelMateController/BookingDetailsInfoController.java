package hostelMateController;

import hostelMateDAO.BookingDAO;
import hostelMateDAO.CustomerDAO;
import hostelMateDAO.PaymentDAO;
import hostelMateDAO.RoomDAO;
import hostelMateModel.Booking;
import hostelMateModel.Customer;
import hostelMateModel.Payment;
import hostelMateModel.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/BookingDetailsInfoController")
public class BookingDetailsInfoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookingDetailsInfoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the booking ID from the request
        String bookingId = request.getParameter("bookingId");

        try {
            // Fetch the booking using the BookingDAO
            Booking booking = BookingDAO.getBooking(bookingId);
            if (booking == null) {
                request.setAttribute("message", "Booking not found.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                return;
            }

            // Fetch customer details using the CustomerDAO
            Customer customer = CustomerDAO.getCustomer(booking.getCustId());
            if (customer == null) {
                request.setAttribute("message", "Customer not found.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                return;
            }

            // Fetch room details using the RoomDAO
            Room room = RoomDAO.getRoomById(booking.getRoomId());
            if (room == null) {
                request.setAttribute("message", "Room not found.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                return;
            }

            // Fetch payment details using the PaymentDAO
            Payment payment = PaymentDAO.getPaymentById(booking.getPaymentId());
            if (payment == null) {
                request.setAttribute("message", "Payment details not found.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                return;
            }

            // Set the booking, customer, room, and payment details as request attributes
            request.setAttribute("bookingId", booking.getBookingId());
            request.setAttribute("customerName", customer.getCustName());
            request.setAttribute("customerEmail", customer.getCustEmail());
            request.setAttribute("customerPhone", customer.getCustNoPhone());
            request.setAttribute("roomName", room.getRoomName());
            request.setAttribute("roomPrice", room.getRoomPrice());
            request.setAttribute("bookingDate", booking.getBookingDate());
            request.setAttribute("distributeKeyDate", booking.getBookingDistributeKey());
            request.setAttribute("returnKeyDate", booking.getBookingReturnKey());
            request.setAttribute("totalPayment", booking.getTotalPayment());
            request.setAttribute("paymentMethod", payment.getPaymentType()); // Add payment type here

            // Forward to the BookingDetails page to display the details
            request.getRequestDispatcher("BookingDetails.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while processing your request.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests if necessary (for example, updating booking details)
        doGet(request, response);
    }
}