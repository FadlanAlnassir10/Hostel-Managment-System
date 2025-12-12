package hostelMateModel;

public class Customer {
    private String custId;
    private String custName;
    private String custNoPhone;
    private String custIcNumber;
    private String custEmail;
    private String custPassword;
    private String custImage; // Base64 encoded image

    // Default Constructor
    public Customer() {}

    // Parameterized Constructor
    public Customer(String custId, String custName, String custNoPhone, String custIcNumber, String custEmail, String custPassword, String custImage) {
        this.custId = custId;
        this.custName = custName;
        this.custNoPhone = custNoPhone;
        this.custIcNumber = custIcNumber;
        this.custEmail = custEmail;
        this.custPassword = custPassword;
        this.custImage = custImage;
    }

    // Getters and Setters

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustNoPhone() {
        return custNoPhone;
    }

    public void setCustNoPhone(String custNoPhone) {
        this.custNoPhone = custNoPhone;
    }

    public String getCustIcNumber() {
        return custIcNumber;
    }

    public void setCustIcNumber(String custIcNumber) {
        this.custIcNumber = custIcNumber;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    public String getCustImage() {
        return custImage;
    }

    public void setCustImage(String custImage) {
        this.custImage = custImage;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", custNoPhone='" + custNoPhone + '\'' +
                ", custIcNumber='" + custIcNumber + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custPassword='" + custPassword + '\'' +
                ", custImage='" + custImage + '\'' +
                '}';
    }
}
