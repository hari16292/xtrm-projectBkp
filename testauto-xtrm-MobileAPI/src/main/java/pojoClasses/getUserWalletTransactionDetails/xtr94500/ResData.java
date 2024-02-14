package pojoClasses.getUserWalletTransactionDetails.xtr94500;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("OrderNumber")
    private String orderNumber;
    @JsonProperty("DateBooked")
    private String dateBooked;
    @JsonProperty("OrderDetails")
    private ResOrderDetails orderDetails;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public ResOrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ResOrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
