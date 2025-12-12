package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import hostelMateDAO.CustomerDAO;
import hostelMateModel.Customer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@WebServlet("/UpdateCustProfileController")
@MultipartConfig  // Annotation to enable file upload handling
public class UpdateCustProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateCustProfileController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current customer from the session using the userId
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId != null) {
            // Fetch the customer object from the database using the userId
            Customer customer = CustomerDAO.getCustomer(userId);

            if (customer != null) {
                // Get customer profile data from the form
                String custName = request.getParameter("custName");
                String custEmail = request.getParameter("custEmail");
                String custIcNumber = request.getParameter("custIcNumber");
                String custPhone = request.getParameter("custPhone");

                // Handle file upload for profile image
                String custImage = null;
                Part profilePicturePart = request.getPart("custImage");

                if (profilePicturePart != null && profilePicturePart.getSize() > 0) {
                    // Convert uploaded image to Base64
                    InputStream imageInputStream = profilePicturePart.getInputStream();
                    byte[] imageBytes = imageInputStream.readAllBytes();
                    custImage = Base64.getEncoder().encodeToString(imageBytes);
                } else {
                    // If no new image is uploaded, retain the old one
                    custImage = customer.getCustImage();
                }

                // Update the customer object with the new values
                customer.setCustName(custName);
                customer.setCustEmail(custEmail);
                customer.setCustIcNumber(custIcNumber);
                customer.setCustNoPhone(custPhone);
                customer.setCustImage(custImage);

                // Update customer in the database
                boolean updated = CustomerDAO.updateCustomer(customer);

                if (updated) {
                    // On success, redirect to profile page with success message
                    request.getSession().setAttribute("sessionId", userId); // Store userId in session
                    request.setAttribute("customer", customer);
                    response.sendRedirect("ViewCustProfileController?update=success");
                } else {
                    // On failure, redirect with an error message
                    response.sendRedirect("ViewCustProfileController?update=error");
                }
            } else {
                // If customer is not found in the database, redirect to error page
                response.sendRedirect("error.jsp");
            }
        } else {
            // Handle session timeout or invalid user state
            response.sendRedirect("Login.jsp");
        }
    }
}
