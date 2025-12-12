package hostelMateDAO;

import hostelMateConnection.ConnectionManager;
import hostelMateModel.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // Method to insert a new admin
    public boolean addAdmin(Admin admin) {
        String query = "INSERT INTO Admin (ADMIN_ID, ADMIN_PASSWORD, ADMIN_NAME) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, admin.getAdminId());
            preparedStatement.setString(2, admin.getAdminPassword());
            preparedStatement.setString(3, admin.getAdminName());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get an admin by ID
    public Admin getAdminById(int adminId) {
        String query = "SELECT * FROM Admin WHERE ADMIN_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, adminId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(resultSet.getInt("ADMIN_ID"));
                    admin.setAdminPassword(resultSet.getString("ADMIN_PASSWORD"));
                    admin.setAdminName(resultSet.getString("ADMIN_NAME"));
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get an admin by email (for login validation)
    public static Admin getAdminByEmail(String email) {
        String query = "SELECT * FROM Admin WHERE ADMIN_EMAIL = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(resultSet.getInt("ADMIN_ID"));
                    admin.setAdminPassword(resultSet.getString("ADMIN_PASSWORD"));
                    admin.setAdminName(resultSet.getString("ADMIN_NAME"));
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to update an admin's details
    public boolean updateAdmin(Admin admin) {
        String query = "UPDATE Admin SET ADMIN_PASSWORD = ?, ADMIN_NAME = ? WHERE ADMIN_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getAdminPassword());
            preparedStatement.setString(2, admin.getAdminName());
            preparedStatement.setInt(3, admin.getAdminId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete an admin by ID
    public boolean deleteAdmin(int adminId) {
        String query = "DELETE FROM Admin WHERE ADMIN_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, adminId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all admins
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        String query = "SELECT * FROM Admin";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminId(resultSet.getInt("ADMIN_ID"));
                admin.setAdminPassword(resultSet.getString("ADMIN_PASSWORD"));
                admin.setAdminName(resultSet.getString("ADMIN_NAME"));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }
}
