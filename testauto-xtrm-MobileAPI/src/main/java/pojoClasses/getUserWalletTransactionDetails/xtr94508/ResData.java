package pojoClasses.getUserWalletTransactionDetails.xtr94508;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("OrderNumber")
    private String orderNumber;
    @JsonProperty("DateBooked")
    private String dateBooked;
    @JsonProperty("BeneficiaryName")
    private String beneficiaryName;
    @JsonProperty("TransferAmount")
    private String transferAmount;
    @JsonProperty("TransferFees")
    private String transferFees;
    @JsonProperty("TransferCurrency")
    private String transferCurrency;
    @JsonProperty("TransferMethod")
    private String transferMethod;
    @JsonProperty("TransferStatus")
    private String transferStatus;
    @JsonProperty("TransferCompletionDate")
    private String transferCompletionDate;

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

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferFees() {
        return transferFees;
    }

    public void setTransferFees(String transferFees) {
        this.transferFees = transferFees;
    }

    public String getTransferCurrency() {
        return transferCurrency;
    }

    public void setTransferCurrency(String transferCurrency) {
        this.transferCurrency = transferCurrency;
    }

    public String getTransferMethod() {
        return transferMethod;
    }

    public void setTransferMethod(String transferMethod) {
        this.transferMethod = transferMethod;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getTransferCompletionDate() {
        return transferCompletionDate;
    }

    public void setTransferCompletionDate(String transferCompletionDate) {
        this.transferCompletionDate = transferCompletionDate;
    }
}
