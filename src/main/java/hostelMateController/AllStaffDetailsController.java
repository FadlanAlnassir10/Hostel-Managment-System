package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import hostelMateDAO.StaffDAO; // Updated DAO class name
import hostelMateModel.Staff;

@WebServlet("/AllStaffDetailsController")
public class AllStaffDetailsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AllStaffDetailsController() {
        super();
    }

    /**
     * Handles the GET request to fetch all staff details.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all staff records from the DAO
        List<Staff> staffList = StaffDAO.getAllStaff(); // Updated method name

        // Debugging: Check if staffList is fetched
        if (staffList != null) {
            System.out.println("Staff list size: " + staffList.size());
        } else {
            System.out.println("Staff list is null");
        }
        
        // Get the status parameter from the query string
        String status = request.getParameter("status");

        // Set the staff list as a request attribute for access in the JSP
        request.setAttribute("staffList", staffList);
        request.setAttribute("status", status);
        
        // Forward the request to AllStaffDetails.jsp
        request.getRequestDispatcher("/AllStaffDetails.jsp").forward(request, response);
    }

    /**
     * Handles the POST request to forward it to the GET method
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
