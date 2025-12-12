package hostelMateModel;

import java.io.Serializable;

public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    private String staffId;       // VARCHAR(10) - Primary Key
    private String staffName;     // VARCHAR(255)
    private String staffIcNumber; // VARCHAR(50)
    private String staffNoPhone;  // VARCHAR(255)
    private String staffEmail;    // VARCHAR(255) - Unique
    private String staffPassword; // VARCHAR(255)
    private String staffImage;    // Base64 encoded string for easier handling of images

    // Default Constructor
    public Staff() {}

    // Getters and Setters
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffIcNumber() {
        return staffIcNumber;
    }

    public void setStaffIcNumber(String staffIcNumber) {
        this.staffIcNumber = staffIcNumber;
    }

    public String getStaffNoPhone() {
        return staffNoPhone;
    }

    public void setStaffNoPhone(String staffNoPhone) {
        this.staffNoPhone = staffNoPhone;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffImage() {
        return staffImage;
    }

    public void setStaffImage(String staffImage) {
        this.staffImage = staffImage;
    }
}
