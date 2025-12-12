package hostelMateConnection;


import java.sql.*;

/**
 * Author: FES
 * Date: January 2025
 * Purpose: Manage database connection for HostelMate project
 */
public class ConnectionManager {
    // MySQL connection details
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver"; // MySQL driver class
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/hostelmate"; // Update with your database URL
    private static final String DB_USER = "root"; // Your MySQL username
    private static final String DB_PASSWORD = ""; // Your MySQL password

    private static Connection con; // Connection object

    /**
     * Establishes a connection to the database.
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName(DB_DRIVER);
            try {
                // Establish the connection
                con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (SQLException ex) {
                // Print SQL exception error
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // Print class not found error
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        return con;
    }

    /**
     * Closes the current database connection.
     */
    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            // Print SQL exception error during connection close
            e.printStackTrace();
        }
    }
}