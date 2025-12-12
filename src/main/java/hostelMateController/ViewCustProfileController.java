package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import hostelMateDAO.CustomerDAO;
import hostelMateModel.Customer;

import java.io.IOException;

@WebServlet("/ViewCustProfileController")
public class ViewCustProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Default constructor
    public ViewCustProfileController() {
        super();
    }

    /**
     * Handles the GET request to view customer profile
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the customer ID from the session (customer's unique ID)
        String custId = (String) request.getSession().getAttribute("userId");

        if (custId != null) {
            // Fetch the customer data from the database using the customer ID
            Customer customer = CustomerDAO.getCustomer(custId);
            if (customer != null) {
                // Set the customer object and customer ID in the request for use in the JSP
                request.setAttribute("customer", customer);
                request.setAttribute("sessionId", custId); // Set the customer ID as the session ID
                
                // Forward the request to the customer profile JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerProfile.jsp");
                dispatcher.forward(request, response);
            } else {
                // Handle case where no customer is found (e.g., redirect to an error page)
                response.sendRedirect("error.jsp");
            }
        } else {
            // If there's no customer ID in the session, redirect to the login page
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Handles the POST request to view customer profile
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call the doGet method in the POST method as the action is the same
        doGet(request, response);
    }
}
