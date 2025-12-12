package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import hostelMateDAO.StaffDAO;
import hostelMateModel.Staff;

/**
 * Servlet implementation class DeleteStaffController
 */
@WebServlet("/DeleteStaffController")
public class DeleteStaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public DeleteStaffController() {
        super();
    }

    /**
     * Handles the GET request to delete a staff member by their ID.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the staff ID from the URL parameter
        String staffId = request.getParameter("staffId");

        if (staffId == null || staffId.isEmpty()) {
            // Respond with an error if the staff ID is not provided
            response.getWriter().println("Error: Staff ID is required.");
            return;
        }

        try {
            // Call the DAO method to delete the staff record
            StaffDAO.deleteStaff(staffId); // No need for rowsAffected or boolean

            // Set a success message
            request.setAttribute("message", "Staff with ID " + staffId + " has been deleted successfully.");

            // Fetch the updated list of staff after deletion
            List<Staff> staffList = StaffDAO.getAllStaff();  // Fetch the updated staff list

            // Set the updated staff list as a request attribute
            request.setAttribute("staffList", staffList);

            // Forward to the JSP page to display the updated staff list and message
            request.getRequestDispatcher("/AllStaffDetails.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();

            // Set an error message
            request.setAttribute("error", "Error occurred while deleting staff: " + e.getMessage());

            // Forward to the JSP page to display the error message
            request.getRequestDispatcher("/AllStaffDetails.jsp").forward(request, response);
        }
    }
}
