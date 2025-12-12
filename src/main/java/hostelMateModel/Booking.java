package hostelMateModel;

import java.sql.Date;

public class Booking {
    private String bookingId;
    private Date bookingDate;
    private Date bookingDistributeKey;
    private Date bookingReturnKey;
    private String custId;
    private String roomId;
    private String paymentId;
    private double totalPayment; // Add this property

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getBookingDistributeKey() {
        return bookingDistributeKey;
    }

    public void setBookingDistributeKey(Date bookingDistributeKey) {
        this.bookingDistributeKey = bookingDistributeKey;
    }

    public Date getBookingReturnKey() {
        return bookingReturnKey;
    }

    public void setBookingReturnKey(Date bookingReturnKey) {
        this.bookingReturnKey = bookingReturnKey;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
