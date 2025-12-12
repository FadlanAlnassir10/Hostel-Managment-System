package hostelMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import hostelMateDAO.CustomerDAO;
import hostelMateModel.Customer;

@WebServlet("/RegisterCustController")
public class RegisterCustController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterCustController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String icNumber = request.getParameter("icNumber");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String image = request.getParameter("image");

        // Generate customer ID
        String custId = generateCustId();
        
        // Check for duplicate IC Number or Email
        if (CustomerDAO.isEmailExists(email)) {
            request.setAttribute("signupError", "Email is already registered. Please use a different email.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        if (CustomerDAO.isIcNumberExists(icNumber)) {
            request.setAttribute("signupError", "IC Number is already registered. Please use a different IC Number.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        // Create a new customer object
        Customer customer = new Customer();
        customer.setCustName(name);
        customer.setCustIcNumber(icNumber);
        customer.setCustEmail(email);
        customer.setCustNoPhone(phone);
        customer.setCustPassword(password);
        customer.setCustId(custId);
        customer.setCustImage(image != null ? image : null);

        try {
            boolean isRegistered = CustomerDAO.addCustomer(customer);
            if (isRegistered) {
                request.setAttribute("signupError", "Register Succesfull.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    private String generateCustId() {
        String prefix = "C";
        int nextId = 100;

        try {
            var allCustomers = CustomerDAO.getAllCustomers();
            if (!allCustomers.isEmpty()) {
                String lastId = allCustomers.get(allCustomers.size() - 1).getCustId();
                int lastNumericPart = Integer.parseInt(lastId.substring(1)); // Assuming IDs are in the form C100, C101, etc.
                nextId = lastNumericPart + 1;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
        }

        return prefix + nextId;
    }
}
