package hostelMateDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import hostelMateConnection.ConnectionManager;
import hostelMateModel.Staff;

public class StaffDAO {
    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static String sql;

    // Method to insert a new staff record into the database
    public static void addStaff(Staff staff) {
        try {
            con = ConnectionManager.getConnection();

            sql = "INSERT INTO staff (STAFF_ID, STAFF_NAME, STAFF_IC_NUMBER, STAFF_NO_PHONE, STAFF_EMAIL, STAFF_PASSWORD, STAFF_IMAGE) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, staff.getStaffId());
            ps.setString(2, staff.getStaffName());
            ps.setString(3, staff.getStaffIcNumber());
            ps.setString(4, staff.getStaffNoPhone());
            ps.setString(5, staff.getStaffEmail());
            ps.setString(6, staff.getStaffPassword());

            // Converting Base64 string to BLOB for storing image
            byte[] imageBytes = java.util.Base64.getDecoder().decode(staff.getStaffImage());
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            ps.setBinaryStream(7, inputStream, imageBytes.length);

            ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a staff record by ID
    public static void deleteStaff(String staffId) {
        try {
            con = ConnectionManager.getConnection();

            sql = "DELETE FROM staff WHERE STAFF_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, staffId);

            ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to fetch a single staff record by ID
    public static Staff getStaff(String staffId) {
        Staff staff = new Staff();

        try {
            con = ConnectionManager.getConnection();

            sql = "SELECT * FROM staff WHERE STAFF_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, staffId);

            rs = ps.executeQuery();

            if (rs.next()) {
                staff.setStaffId(rs.getString("STAFF_ID"));
                staff.setStaffName(rs.getString("STAFF_NAME"));
                staff.setStaffIcNumber(rs.getString("STAFF_IC_NUMBER"));
                staff.setStaffNoPhone(rs.getString("STAFF_NO_PHONE"));
                staff.setStaffEmail(rs.getString("STAFF_EMAIL"));
                staff.setStaffPassword(rs.getString("STAFF_PASSWORD"));

                // Reading BLOB data and converting it to Base64 string
                InputStream inputStream = rs.getBinaryStream("STAFF_IMAGE");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] imageBytes = outputStream.toByteArray();
                staff.setStaffImage(java.util.Base64.getEncoder().encodeToString(imageBytes));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return staff;
    }

    // Method to fetch all staff records
    public static List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();

        try {
            con = ConnectionManager.getConnection();

            sql = "SELECT * FROM staff";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getString("STAFF_ID"));
                staff.setStaffName(rs.getString("STAFF_NAME"));
                staff.setStaffIcNumber(rs.getString("STAFF_IC_NUMBER"));
                staff.setStaffNoPhone(rs.getString("STAFF_NO_PHONE"));
                staff.setStaffEmail(rs.getString("STAFF_EMAIL"));
                staff.setStaffPassword(rs.getString("STAFF_PASSWORD"));

                // Reading BLOB data and converting it to Base64 string
                InputStream inputStream = rs.getBinaryStream("STAFF_IMAGE");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] imageBytes = outputStream.toByteArray();
                staff.setStaffImage(java.util.Base64.getEncoder().encodeToString(imageBytes));

                staffList.add(staff);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return staffList;
    }

    // Method to update a staff record
    public static boolean updateStaff(Staff staff) {
        boolean isUpdated = false;

        try {
            con = ConnectionManager.getConnection();

            sql = "UPDATE staff SET STAFF_NAME = ?, STAFF_IC_NUMBER = ?, STAFF_NO_PHONE = ?, STAFF_EMAIL = ?, STAFF_PASSWORD = ?, STAFF_IMAGE = ? WHERE STAFF_ID = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1, staff.getStaffName());
            ps.setString(2, staff.getStaffIcNumber());
            ps.setString(3, staff.getStaffNoPhone());
            ps.setString(4, staff.getStaffEmail());
            ps.setString(5, staff.getStaffPassword());

            // Updating the image as Base64 encoded string
            if (staff.getStaffImage() != null) {
                byte[] imageBytes = java.util.Base64.getDecoder().decode(staff.getStaffImage());
                InputStream inputStream = new ByteArrayInputStream(imageBytes);
                ps.setBinaryStream(6, inputStream, imageBytes.length);
            } else {
                ps.setNull(6, Types.BLOB); // If no image is provided, set to NULL
            }

            ps.setString(7, staff.getStaffId());

            int rowsAffected = ps.executeUpdate();
            isUpdated = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isUpdated;
    }
    
 // Method to check if an email already exists
    public static boolean isEmailExists(String email) {
        boolean exists = false;

        try {
            con = ConnectionManager.getConnection();
            sql = "SELECT COUNT(*) FROM staff WHERE STAFF_EMAIL = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    // Method to check if an IC number already exists
    public static boolean isIcNumberExists(String icNumber) {
        boolean exists = false;

        try {
            con = ConnectionManager.getConnection();
            sql = "SELECT COUNT(*) FROM staff WHERE STAFF_IC_NUMBER = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, icNumber);

            rs = ps.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
    
}
