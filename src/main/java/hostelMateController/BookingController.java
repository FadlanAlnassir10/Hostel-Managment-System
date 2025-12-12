package hostelMateController;

import hostelMateDAO.CustomerDAO;
import hostelMateDAO.RoomDAO;
import hostelMateDAO.BookingDAO;
import hostelMateModel.Customer;
import hostelMateModel.Room;
import hostelMateModel.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookingController() {
        super();
    }

    // Process the booking form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String custId = request.getParameter("cust_id");
        String roomId = request.getParameter("room_id");
        String bookingDate = request.getParameter("booking_date");
        String distributeKeyDate = request.getParameter("distribute_key_date");
        String returnKeyDate = request.getParameter("return_key_date");
        String paymentMethod = request.getParameter("payment_method");

        // Debugging: Print initial values
        System.out.println("Customer ID: " + custId);
        System.out.println("Room ID: " + roomId);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Distribute Key Date: " + distributeKeyDate);
        System.out.println("Return Key Date: " + returnKeyDate);
        System.out.println("Payment Method: " + paymentMethod);

        // Fetch customer details using the CustomerDAO
		Customer customer = CustomerDAO.getCustomer(custId);
		if (customer != null) {
		    System.out.println("Customer Details:");
		    System.out.println("Customer ID: " + customer.getCustId());
		    System.out.println("Customer Name: " + customer.getCustName());
		    System.out.println("Customer Email: " + customer.getCustEmail());
		    System.out.println("Customer Phone: " + customer.getCustNoPhone());
		} else {
		    System.out.println("Error: Customer not found for ID: " + custId);
		    return; // Exit early if the customer doesn't exist
		}

		// Fetch room details using the RoomDAO
		Room room = RoomDAO.getRoomById(roomId);
		if (room != null) {
		    System.out.println("Room Details:");
		    System.out.println("Room ID: " + room.getRoomId());
		    System.out.println("Room Name: " + room.getRoomName());
		    System.out.println("Room Price Per Day: " + room.getRoomPrice());
		} else {
		    System.out.println("Error: Room not found for ID: " + roomId);
		    return; // Exit early if the room doesn't exist
		}

		// Parse and calculate the number of days between dates
		LocalDate distributeDate = LocalDate.parse(distributeKeyDate);
		LocalDate returnDate = LocalDate.parse(returnKeyDate);
		long daysBetween = ChronoUnit.DAYS.between(distributeDate, returnDate);

		// Debugging: Print calculated days
		System.out.println("Days Between: " + daysBetween);

		if (daysBetween <= 0) {
		    System.out.println("Error: Return date must be after the distribute key date.");
		    return; // Exit early if dates are invalid
		}

		// Calculate the total price
		double totalPrice = room.getRoomPrice() * daysBetween;

		// Debugging: Print calculated total price
		System.out.println("Total Price for the Stay: " + totalPrice);

		// Convert LocalDate to java.sql.Date for database insertion
		Date bookingSqlDate = Date.valueOf(bookingDate);  // For bookingDate
		Date distributeKeySqlDate = Date.valueOf(distributeDate);  // For distributeKeyDate
		Date returnKeySqlDate = Date.valueOf(returnDate);  // For returnKeyDate

		// Generate a new booking ID
		String bookingId = generateBookingId();

		// Create a booking object and set its attributes
		Booking booking = new Booking();
		booking.setBookingId(bookingId);
		booking.setBookingDate(bookingSqlDate);
		booking.setBookingDistributeKey(distributeKeySqlDate);
		booking.setBookingReturnKey(returnKeySqlDate);
		booking.setCustId(custId);
		booking.setRoomId(roomId);
		booking.setPaymentId(paymentMethod); // Assuming payment method is passed as a string
		booking.setTotalPayment(totalPrice);

		// Insert the booking into the database
		BookingDAO.addBooking(booking);
		System.out.println("Booking successfully inserted into the database.");

		// Set booking and customer details as request attributes for the JSP
		request.setAttribute("customerName", customer.getCustName());
		request.setAttribute("customerEmail", customer.getCustEmail());
		request.setAttribute("customerPhone", customer.getCustNoPhone());
		request.setAttribute("customerId", customer.getCustId());
		request.setAttribute("roomName", room.getRoomName());
		request.setAttribute("bookingDate", bookingSqlDate);
		request.setAttribute("distributeKeyDate", distributeKeySqlDate);
		request.setAttribute("returnKeyDate", returnKeySqlDate);
		request.setAttribute("totalPayment", totalPrice);
		request.setAttribute("roomPrice", room.getRoomPrice());
		request.setAttribute("paymentType", paymentMethod);
		request.setAttribute("bookingId", bookingId);

		// Forward to the invoice JSP
		request.getRequestDispatcher("BookingInvoice.jsp").forward(request, response);
    }

    // Method to generate a new booking ID
    private String generateBookingId() {
        String prefix = "B"; // Prefix for booking IDs
        int nextId = 100; // Default starting ID

        try {
            var allBookings = BookingDAO.getAllBookings(); // Fetch all bookings from the database
            if (!allBookings.isEmpty()) {
                // Get the last booking ID and extract the numeric part
                String lastBookingId = allBookings.get(allBookings.size() - 1).getBookingId();
                int lastNumericPart = Integer.parseInt(lastBookingId.substring(1)); // Remove 'B' prefix and parse the number
                nextId = lastNumericPart + 1; // Increment the ID for the next booking
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error if any
        }

        return prefix + nextId; // Return the generated booking ID
    }
}
