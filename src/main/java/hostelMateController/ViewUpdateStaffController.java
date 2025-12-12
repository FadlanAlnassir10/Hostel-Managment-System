package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import hostelMateDAO.StaffDAO;  // Changed to StaffDAO
import hostelMateModel.Staff;

@WebServlet("/ViewUpdateStaffController")
public class ViewUpdateStaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewUpdateStaffController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String staffId = request.getParameter("staffId");

        if (staffId == null || staffId.trim().isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().write("<h1>Error: staffId is missing or empty.</h1>");
            return;
        }

        try {
            // Fetch staff details using DAO
            Staff staff = StaffDAO.getStaff(staffId);  // Changed to StaffDAO

            if (staff != null && staff.getStaffId() != null) {
                // Set the staff object as a request attribute
                request.setAttribute("staff", staff);

                // Forward to UpdateStaff.jsp
                request.getRequestDispatcher("UpdateStaff.jsp").forward(request, response);
            } else {
                response.setContentType("text/html");
                response.getWriter().write("<h1>Error: No staff found for staffId: " + staffId + "</h1>");
            }
        } catch (Exception e) {
            response.setContentType("text/html");
            response.getWriter().write("<h1>Error: An exception occurred while fetching staff details.</h1>");
            response.getWriter().write("<p>" + e.getMessage() + "</p>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // Handle POST as GET
    }
}
