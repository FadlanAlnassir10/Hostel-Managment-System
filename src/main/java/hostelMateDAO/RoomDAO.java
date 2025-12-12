package hostelMateDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import hostelMateConnection.ConnectionManager;
import hostelMateModel.Room;

public class RoomDAO {

    // Method to get all room IDs and room names for the dropdown
    public static List<Room> getAllRooms() throws SQLException {
        String query = "SELECT ROOM_ID, ROOM_NAME FROM room"; // Only fetch necessary columns (roomId and roomName)
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getString("ROOM_ID"));
                room.setRoomName(resultSet.getString("ROOM_NAME"));
                rooms.add(room);
            }
        }
        return rooms;
    }
    
 // Method to get all room details (including image)
    public static List<Room> getAllRoomDetails() throws SQLException {
        String query = "SELECT ROOM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_IMAGE FROM room"; // Fetch all columns
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getString("ROOM_ID"));
                room.setRoomName(resultSet.getString("ROOM_NAME"));
                room.setRoomPrice(resultSet.getDouble("ROOM_PRICE"));
                room.setRoomDesc(resultSet.getString("ROOM_DESC"));

                // Handle the image (BLOB to Base64)
                InputStream inputStream = resultSet.getBinaryStream("ROOM_IMAGE");
                if (inputStream != null) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    room.setRoomImage(java.util.Base64.getEncoder().encodeToString(imageBytes));
                } else {
                    room.setRoomImage(null); // If no image is present
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return rooms;
    }

    // Method to add a new room with image handling
    public static void addRoom(Room room) throws SQLException {
        String query = "INSERT INTO room (ROOM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_IMAGE) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, room.getRoomId());
            statement.setString(2, room.getRoomName());
            statement.setDouble(3, room.getRoomPrice());
            statement.setString(4, room.getRoomDesc());

            // Handle image as Base64 string
            if (room.getRoomImage() != null) {
                byte[] imageBytes = java.util.Base64.getDecoder().decode(room.getRoomImage());
                InputStream inputStream = new ByteArrayInputStream(imageBytes);
                statement.setBinaryStream(5, inputStream, imageBytes.length);
            } else {
                statement.setNull(5, Types.BLOB); // If no image, set as NULL
            }

            statement.executeUpdate();
        }
    }

    // Method to fetch a room by ID with image handling
    public static Room getRoomById(String roomId) {
        Room room = new Room();

        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM room WHERE ROOM_ID = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, roomId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        room.setRoomId(rs.getString("ROOM_ID"));
                        room.setRoomName(rs.getString("ROOM_NAME"));
                        room.setRoomPrice(rs.getDouble("ROOM_PRICE"));
                        room.setRoomDesc(rs.getString("ROOM_DESC"));

                        // Reading BLOB data (image) and converting it to Base64 string
                        InputStream inputStream = rs.getBinaryStream("ROOM_IMAGE");
                        if (inputStream != null) {
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                            byte[] imageBytes = outputStream.toByteArray();
                            room.setRoomImage(java.util.Base64.getEncoder().encodeToString(imageBytes));
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return room;
    }


    // Method to update a room with image handling
    public static boolean updateRoom(Room room) {
        boolean isUpdated = false;

        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "UPDATE room SET ROOM_NAME = ?, ROOM_PRICE = ?, ROOM_DESC = ?, ROOM_IMAGE = ? WHERE ROOM_ID = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, room.getRoomName());
                ps.setDouble(2, room.getRoomPrice());
                ps.setString(3, room.getRoomDesc());

                // Handle image update (Base64 to BLOB)
                if (room.getRoomImage() != null) {
                    byte[] imageBytes = java.util.Base64.getDecoder().decode(room.getRoomImage());
                    InputStream inputStream = new ByteArrayInputStream(imageBytes);
                    ps.setBinaryStream(4, inputStream, imageBytes.length);
                } else {
                    ps.setNull(4, Types.BLOB); // If no image is provided, set to NULL
                }

                ps.setString(5, room.getRoomId());

                // Execute update and check the number of affected rows
                int rowsAffected = ps.executeUpdate();
                isUpdated = rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }


    // Method to delete a room by ID
    public static void deleteRoom(String roomId) throws SQLException {
        String query = "DELETE FROM room WHERE ROOM_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomId);
            statement.executeUpdate();
        }
    }
    
 // Method to check if a room ID or room name already exists
    public static boolean isRoomUnique(String roomId, String roomName) throws SQLException {
        String query = "SELECT COUNT(*) FROM room WHERE ROOM_ID = ? OR ROOM_NAME = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomId);
            statement.setString(2, roomName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0; // Returns true if no matching record is found
                }
            }
        }
        return false; // Return false if validation fails
    }
    

}
