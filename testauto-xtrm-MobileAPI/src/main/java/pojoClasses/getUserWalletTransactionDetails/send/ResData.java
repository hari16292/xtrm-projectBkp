package pojoClasses.getUserWalletTransactionDetails.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("From")
    private String from;
    @JsonProperty("To")
    private String to;
    @JsonProperty("TransactionType")
    private String transactionType;
    @JsonProperty("TransactionStatus")
    private String transactionStatus;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("Fees")
    private String fees;
    @JsonProperty("Total")
    private String total;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("TransactionDate")
    private String transactionDate;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
