package hostelMateDAO;

import hostelMateConnection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    // Existing method to validate login
    public static String validateLogin(String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();

            // Check for Customer (no domain restriction)
            String sql = "SELECT 'customer' AS role FROM customer WHERE CUST_EMAIL = ? AND CUST_PASSWORD = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "customer";
            }

            // Check for Staff (email must end with @staff.com)
            if (email.endsWith("@staff.com")) {
                sql = "SELECT 'staff' AS role FROM staff WHERE STAFF_EMAIL = ? AND STAFF_PASSWORD = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return "staff";
                }
            }

            // Check for Admin (email must end with @admin.com)
            if (email.endsWith("@admin.com")) {
                sql = "SELECT 'admin' AS role FROM admin WHERE ADMIN_EMAIL = ? AND ADMIN_PASSWORD = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return "admin";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null; // Return null if no match found
    }

    // New method to get the user ID based on email
    public static String getUserIdByEmail(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userId = null;  // Changed to String

        try {
            con = ConnectionManager.getConnection();

            // Check for Customer
            String sql = "SELECT CUST_ID FROM customer WHERE CUST_EMAIL = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getString("CUST_ID");  // Changed to getString for String type
                return userId; // Return customer ID
            }

            // Check for Staff
            if (email.endsWith("@staff.com")) {
                sql = "SELECT STAFF_ID FROM staff WHERE STAFF_EMAIL = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getString("STAFF_ID");  // Changed to getString for String type
                    return userId; // Return staff ID
                }
            }

            // Check for Admin
            if (email.endsWith("@admin.com")) {
                sql = "SELECT ADMIN_ID FROM admin WHERE ADMIN_EMAIL = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getString("ADMIN_ID");  // Changed to getString for String type
                    return userId; // Return admin ID
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null; // Return null if user ID not found
    }
    
    public static boolean isEmailExists(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();

            // Check in all three tables (customer, staff, admin)
            String sql = "SELECT 1 FROM customer WHERE CUST_EMAIL = ? " +
                         "UNION SELECT 1 FROM staff WHERE STAFF_EMAIL = ? " +
                         "UNION SELECT 1 FROM admin WHERE ADMIN_EMAIL = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, email);
            ps.setString(3, email);
            rs = ps.executeQuery();

            return rs.next(); // Return true if a match is found
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false; // Return false if no match is found
    }
}
