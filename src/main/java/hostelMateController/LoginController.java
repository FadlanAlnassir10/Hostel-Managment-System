package hostelMateController;

import hostelMateDAO.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Call the DAO to validate the login and get user details
        String role = LoginDAO.validateLogin(email, password);
        String userId = LoginDAO.getUserIdByEmail(email);  // Changed to String as userId is a VARCHAR

        if (role != null) {
            // Get the session object
            HttpSession session = request.getSession();

            // Store the user ID in the session
            session.setAttribute("userId", userId);

            switch (role) {
                case "customer":
                    // For customer, set the customerId in session and redirect to customer homepage
                    session.setAttribute("customerId", userId);
                    response.sendRedirect("Homepage.jsp");
                    break;
                case "staff":
                    // For staff, set the staffId in session and redirect to staff homepage
                    session.setAttribute("staffId", userId);
                    response.sendRedirect("StaffHomepage.jsp");
                    break;
                case "admin":
                    session.setAttribute("AdminId", userId);
                    response.sendRedirect("AdminHomepage.html");
                    break;
                default:
                    response.sendRedirect("Login.jsp?error=unknown");
            }
        } else {
            // Check if email exists
            boolean emailExists = LoginDAO.isEmailExists(email);

            // Add the appropriate error message based on the validation
            if (!emailExists) {
                request.setAttribute("loginError", "Email does not exist.");
            } else {
                request.setAttribute("loginError", "Incorrect password.");
            }

            // Forward to login.jsp with error
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
