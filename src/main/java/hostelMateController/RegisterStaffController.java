package hostelMateController;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import hostelMateDAO.StaffDAO;
import hostelMateModel.Staff;

@WebServlet("/RegisterStaffController")
@MultipartConfig
public class RegisterStaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String staffName = request.getParameter("staffName");
        String icNumber = request.getParameter("icNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Part profilePicture = request.getPart("profilePicture");
        
     // Check if email or IC number already exists
        if (StaffDAO.isEmailExists(email)) {
            request.setAttribute("message", "The email address is already in use. Please use a unique email.");
            request.getRequestDispatcher("RegisterStaff.jsp").forward(request, response);
            return;
        }

        if (StaffDAO.isIcNumberExists(icNumber)) {
            request.setAttribute("message", "The IC number is already in use. Please use a unique IC number.");
            request.getRequestDispatcher("RegisterStaff.jsp").forward(request, response);
            return;
        }

        // Validate email domain
        if (!email.endsWith("@staff.com")) {
            request.setAttribute("message", "Invalid email address. Please use an email ending with @staff.com.");
            request.getRequestDispatcher("RegisterStaff.jsp").forward(request, response);
            return;
        }

        // Generate new staff ID
        String staffId = generateStaffId();

        try {
            // Create a new Staff object
            Staff staff = new Staff();
            staff.setStaffId(staffId);
            staff.setStaffName(staffName);
            staff.setStaffIcNumber(icNumber);
            staff.setStaffNoPhone(phoneNumber);
            staff.setStaffEmail(email);
            staff.setStaffPassword(password);

            // Convert profile picture to Base64 (if provided)
            if (profilePicture != null && profilePicture.getSize() > 0) {
                byte[] imageBytes = profilePicture.getInputStream().readAllBytes();
                String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
                staff.setStaffImage(base64Image);
            } else {
                staff.setStaffImage(null); // No image provided
            }

            // Insert staff into the database using StaffDAO
            StaffDAO.addStaff(staff);

   
            request.setAttribute("message", "New staff member added successfully with Staff ID: " + staffId);

        } catch (Exception e) {
            e.printStackTrace();
            // Set error message if something goes wrong
            request.setAttribute("message", "Error adding staff.");
        }

        // Forward to AllStaffDetails.jsp to display the list of staff and the message
        request.getRequestDispatcher("AllStaffDetailsController").forward(request, response);
    }

    // Method to generate a new staff ID
    private String generateStaffId() {
        String prefix = "S"; // Prefix for staff ID
        int nextId = 100;    // Default ID if no records exist

        try {
            // Get the last staff ID using StaffDAO method to fetch all staff records
            var allStaff = StaffDAO.getAllStaff();

            if (!allStaff.isEmpty()) {
                String lastId = allStaff.get(allStaff.size() - 1).getStaffId(); // Get the last ID
                int lastNumericPart = Integer.parseInt(lastId.substring(1));   // Extract numeric part
                nextId = lastNumericPart + 1;                                 // Increment ID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prefix + nextId; // Return the new staff ID
    }
}