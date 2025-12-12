// ViewStaffProfileController.java
package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hostelMateDAO.StaffDAO;
import hostelMateModel.Staff;

import java.io.IOException;

@WebServlet("/ViewStaffProfileController")
public class ViewStaffProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewStaffProfileController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String staffId = (String) request.getSession().getAttribute("userId");
    	
    	

        if (staffId != null) {
            Staff staff = StaffDAO.getStaff(staffId);

            if (staff != null) {
                request.setAttribute("staff", staff);
                request.setAttribute("sessionId", staffId); // Set the customer ID as the session ID
                request.getRequestDispatcher("StaffProfile.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Staff not found.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Staff ID is required.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
