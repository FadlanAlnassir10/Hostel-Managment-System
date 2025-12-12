package hostelMateDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import hostelMateConnection.ConnectionManager;
import hostelMateModel.Customer;

public class CustomerDAO {

    // Method to add a new customer
    public static boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (CUST_ID, CUST_NAME, CUST_NO_PHONE, CUST_IC_NUMBER, CUST_EMAIL, CUST_PASSWORD, CUST_IMAGE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getCustId());
            ps.setString(2, customer.getCustName());
            ps.setString(3, customer.getCustNoPhone());
            ps.setString(4, customer.getCustIcNumber());
            ps.setString(5, customer.getCustEmail());
            ps.setString(6, customer.getCustPassword());
            if (customer.getCustImage() != null) {
                ps.setString(7, customer.getCustImage());
            } else {
                ps.setNull(7, Types.VARCHAR); // If image is null, set NULL
            }

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a customer by ID
    public static boolean deleteCustomer(String custId) {
        String sql = "DELETE FROM customer WHERE CUST_ID = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, custId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to fetch a customer by ID
    public static Customer getCustomer(String custId) {
        String sql = "SELECT * FROM customer WHERE CUST_ID = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, custId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustId(rs.getString("CUST_ID"));
                customer.setCustName(rs.getString("CUST_NAME"));
                customer.setCustNoPhone(rs.getString("CUST_NO_PHONE"));
                customer.setCustIcNumber(rs.getString("CUST_IC_NUMBER"));
                customer.setCustEmail(rs.getString("CUST_EMAIL"));
                customer.setCustPassword(rs.getString("CUST_PASSWORD"));
                customer.setCustImage(rs.getString("CUST_IMAGE")); // Return the image path/base64 string

                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Method to fetch all customers
    public static List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        List<Customer> customerList = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustId(rs.getString("CUST_ID"));
                customer.setCustName(rs.getString("CUST_NAME"));
                customer.setCustNoPhone(rs.getString("CUST_NO_PHONE"));
                customer.setCustIcNumber(rs.getString("CUST_IC_NUMBER"));
                customer.setCustEmail(rs.getString("CUST_EMAIL"));
                customer.setCustPassword(rs.getString("CUST_PASSWORD"));
                customer.setCustImage(rs.getString("CUST_IMAGE")); // Return the image path/base64 string
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    // Method to update a customer record
    public static boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET CUST_NAME = ?, CUST_NO_PHONE = ?, CUST_IC_NUMBER = ?, CUST_EMAIL = ?, CUST_PASSWORD = ?, CUST_IMAGE = ? WHERE CUST_ID = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getCustName());
            ps.setString(2, customer.getCustNoPhone());
            ps.setString(3, customer.getCustIcNumber());
            ps.setString(4, customer.getCustEmail());
            ps.setString(5, customer.getCustPassword());
            if (customer.getCustImage() != null) {
                ps.setString(6, customer.getCustImage());
            } else {
                ps.setNull(6, Types.VARCHAR); // If image is null, set NULL
            }
            ps.setString(7, customer.getCustId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Method to check if an email already exists in the database
    public static boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM customer WHERE CUST_EMAIL = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 // Method to check if IC Number exists
    public static boolean isIcNumberExists(String icNumber) {
        String sql = "SELECT COUNT(*) FROM customer WHERE CUST_IC_NUMBER = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, icNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, IC Number exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
