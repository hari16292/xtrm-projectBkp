package pojoClasses.userWithdrawFund;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("TransactionID")
    private String transactionID;
    @JsonProperty("PaymentDate")
    private String paymentDate;
    @JsonProperty("PaymentStatus")
    private String paymentStatus;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("Fee")
    private String fee;
    @JsonProperty("TotalAmount")
    private String totalAmount;
    @JsonProperty("Currency")
    private String currency;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
