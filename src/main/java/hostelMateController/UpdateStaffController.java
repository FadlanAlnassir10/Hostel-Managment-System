package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Base64;

import hostelMateDAO.StaffDAO;  // Changed to StaffDAO
import hostelMateModel.Staff;

@WebServlet("/UpdateStaffController")
@MultipartConfig  // Annotation to enable file upload handling
public class UpdateStaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateStaffController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Retrieve form data
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        String staffIcNumber = request.getParameter("icNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        // Retrieve the existing staff record from the database
        Staff existingStaff = StaffDAO.getStaff(staffId);  // Changed to StaffDAO

        // Get the password from the existing staff record
        String password = existingStaff != null ? existingStaff.getStaffPassword() : null;

        // Handle file upload for profile picture
        Part profilePicturePart = request.getPart("staffImage");
        String staffImageBase64 = null;

        if (profilePicturePart != null && profilePicturePart.getSize() > 0) {
            // Convert uploaded image to Base64
            byte[] imageBytes = profilePicturePart.getInputStream().readAllBytes();
            staffImageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        } else {
            // If no new image is uploaded, retain the old one
            staffImageBase64 = existingStaff != null ? existingStaff.getStaffImage() : null;
        }

        // Create a Staff object with the updated data
        Staff updatedStaff = new Staff();
        updatedStaff.setStaffId(staffId);
        updatedStaff.setStaffName(staffName);
        updatedStaff.setStaffIcNumber(staffIcNumber);
        updatedStaff.setStaffNoPhone(phoneNumber);
        updatedStaff.setStaffEmail(email);
        updatedStaff.setStaffPassword(password); // Use updated or existing password
        updatedStaff.setStaffImage(staffImageBase64);  // Set the image (Base64 string)

        // Debug: Print the updated staff data
        System.out.println("Staff ID: " + staffId);
        System.out.println("Staff Name: " + staffName);
        System.out.println("Staff IC Number: " + staffIcNumber);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Profile Image Base64: " + staffImageBase64);

        // Update the staff record in the database
        boolean isUpdated = StaffDAO.updateStaff(updatedStaff);  // Changed to StaffDAO

     // Redirect with query parameter based on update status
        if (isUpdated) {
            response.sendRedirect("AllStaffDetailsController?status=success");
        } else {
            response.sendRedirect("AllStaffDetailsController?status=failure");
        }
    }
}
