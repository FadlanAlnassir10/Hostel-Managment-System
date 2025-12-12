package hostelMateDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import hostelMateConnection.ConnectionManager;
import hostelMateModel.Payment;

public class PaymentDAO {

    // Method to get all payment IDs and payment types for the dropdown
    public List<Payment> getAllPayments() throws SQLException {
        String query = "SELECT PAYMENT_ID, PAYMENT_TYPE FROM payment"; // Only fetch necessary columns
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getString("PAYMENT_ID"));
                payment.setPaymentType(resultSet.getString("PAYMENT_TYPE")); // Changed to getString() for paymentType
                payments.add(payment);
            }
        }
        return payments;
    }

    // Existing method to add a new payment
    public void addPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payment (PAYMENT_ID, PAYMENT_TYPE) VALUES (?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, payment.getPaymentId());
            statement.setString(2, payment.getPaymentType());  // Changed to setString() for paymentType
            statement.executeUpdate();
        }
    }

    // Existing method to get a payment by ID
    public static Payment getPaymentById(String paymentId) throws SQLException {
        String query = "SELECT * FROM payment WHERE PAYMENT_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, paymentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentId(resultSet.getString("PAYMENT_ID"));
                    payment.setPaymentType(resultSet.getString("PAYMENT_TYPE"));  // Changed to getString() for paymentType
                    return payment;
                }
            }
        }
        return null;
    }

    // Existing method to update a payment
    public void updatePayment(Payment payment) throws SQLException {
        String query = "UPDATE payment SET PAYMENT_TYPE = ? WHERE PAYMENT_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, payment.getPaymentType());  // Changed to setString() for paymentType
            statement.setString(2, payment.getPaymentId());
            statement.executeUpdate();
        }
    }

    // Existing method to delete a payment by ID
    public void deletePayment(String paymentId) throws SQLException {
        String query = "DELETE FROM payment WHERE PAYMENT_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, paymentId);
            statement.executeUpdate();
        }
    }
}