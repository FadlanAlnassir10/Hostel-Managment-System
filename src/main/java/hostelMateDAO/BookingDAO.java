package hostelMateDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import hostelMateConnection.ConnectionManager;
import hostelMateModel.Booking;

public class BookingDAO {
    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static String sql;

    // Method to add a new booking record
    public static void addBooking(Booking booking) {
        try {
            con = ConnectionManager.getConnection();

            sql = "INSERT INTO booking (BOOKING_ID, BOOKING_DATE, BOOKING_DISTRIBUTE_KEY, BOOKING_RETURN_KEY, CUST_ID, ROOM_ID, PAYMENT_ID, TOTAL_PAYMENT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, booking.getBookingId());
            ps.setDate(2, booking.getBookingDate());
            ps.setDate(3, booking.getBookingDistributeKey());
            ps.setDate(4, booking.getBookingReturnKey());
            ps.setString(5, booking.getCustId());
            ps.setString(6, booking.getRoomId());
            ps.setString(7, booking.getPaymentId());
            ps.setDouble(8, booking.getTotalPayment());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to fetch a booking record by ID
    public static Booking getBooking(String bookingId) {
        Booking booking = null;

        try {
            con = ConnectionManager.getConnection();

            sql = "SELECT * FROM booking WHERE BOOKING_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, bookingId);

            rs = ps.executeQuery();

            if (rs.next()) {
                booking = new Booking();
                booking.setBookingId(rs.getString("BOOKING_ID"));
                booking.setBookingDate(rs.getDate("BOOKING_DATE"));
                booking.setBookingDistributeKey(rs.getDate("BOOKING_DISTRIBUTE_KEY"));
                booking.setBookingReturnKey(rs.getDate("BOOKING_RETURN_KEY"));
                booking.setCustId(rs.getString("CUST_ID"));
                booking.setRoomId(rs.getString("ROOM_ID"));
                booking.setPaymentId(rs.getString("PAYMENT_ID"));
                booking.setTotalPayment(rs.getDouble("TOTAL_PAYMENT"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return booking;
    }
    
 // Method to fetch bookings by customer ID
    public static List<Booking> getBookingsByCustomerId(String custId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM booking WHERE CUST_ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, custId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getString("BOOKING_ID"));
                    booking.setBookingDate(rs.getDate("BOOKING_DATE"));
                    booking.setBookingDistributeKey(rs.getDate("BOOKING_DISTRIBUTE_KEY"));
                    booking.setBookingReturnKey(rs.getDate("BOOKING_RETURN_KEY"));
                    booking.setCustId(rs.getString("CUST_ID"));
                    booking.setRoomId(rs.getString("ROOM_ID"));
                    booking.setPaymentId(rs.getString("PAYMENT_ID"));
                    booking.setTotalPayment(rs.getDouble("TOTAL_PAYMENT"));

                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Method to update a booking record
    public static void updateBooking(Booking booking) {
        try {
            con = ConnectionManager.getConnection();

            sql = "UPDATE booking SET BOOKING_DATE = ?, BOOKING_DISTRIBUTE_KEY = ?, BOOKING_RETURN_KEY = ?, CUST_ID = ?, ROOM_ID = ?, PAYMENT_ID = ?, TOTAL_PAYMENT = ? WHERE BOOKING_ID = ?";
            ps = con.prepareStatement(sql);

            ps.setDate(1, booking.getBookingDate());
            ps.setDate(2, booking.getBookingDistributeKey());
            ps.setDate(3, booking.getBookingReturnKey());
            ps.setString(4, booking.getCustId());
            ps.setString(5, booking.getRoomId());
            ps.setString(6, booking.getPaymentId());
            ps.setDouble(7, booking.getTotalPayment());
            ps.setString(8, booking.getBookingId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update booking: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to fetch all booking records
    public static List<Booking> getAllBookings() {
        List<Booking> bookingList = new ArrayList<>();

        try {
            con = ConnectionManager.getConnection();

            sql = "SELECT * FROM booking";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getString("BOOKING_ID"));
                booking.setBookingDate(rs.getDate("BOOKING_DATE"));
                booking.setBookingDistributeKey(rs.getDate("BOOKING_DISTRIBUTE_KEY"));
                booking.setBookingReturnKey(rs.getDate("BOOKING_RETURN_KEY"));
                booking.setCustId(rs.getString("CUST_ID"));
                booking.setRoomId(rs.getString("ROOM_ID"));
                booking.setPaymentId(rs.getString("PAYMENT_ID"));
                booking.setTotalPayment(rs.getDouble("TOTAL_PAYMENT"));

                bookingList.add(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookingList;
    }

    // New method to check if a room is in use
    public static boolean isRoomInUse(String roomId) throws SQLException {
        boolean isInUse = false;

        String query = "SELECT COUNT(*) FROM booking WHERE ROOM_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isInUse = (count > 0); // If count > 0, the room is in use
                }
            }
        }
        return isInUse;
    }

    public static List<Booking> getBookingsBySearchCriteria(String search) {
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()) { // Using your connection manager
            String query = "SELECT * FROM booking WHERE BOOKING_ID LIKE ?"; // Query to search by booking ID
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + search + "%"); // Use LIKE for partial matches
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getString("BOOKING_ID")); // Assuming it's a String
                    booking.setBookingDate(rs.getDate("BOOKING_DATE"));
                    booking.setBookingDistributeKey(rs.getDate("BOOKING_DISTRIBUTE_KEY"));
                    booking.setBookingReturnKey(rs.getDate("BOOKING_RETURN_KEY"));
                    booking.setCustId(rs.getString("CUST_ID")); // Assuming it's a String
                    booking.setRoomId(rs.getString("ROOM_ID")); // Assuming it's a String
                    booking.setPaymentId(rs.getString("PAYMENT_ID"));
                    booking.setTotalPayment(rs.getDouble("TOTAL_PAYMENT"));

                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }


}