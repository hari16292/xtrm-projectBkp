package pojoClasses.getUserWalletTransactionDetails.xtr94507;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResOrderDetails {
    @JsonProperty("BeneficiaryName")
    private String beneficiaryName;
    @JsonProperty("CheckMailingAddress")
    private String checkMailingAddress;
    @JsonProperty("TransferAmount")
    private String transferAmount;
    @JsonProperty("TransferCurrency")
    private String transferCurrency;
    @JsonProperty("TransferMethod")
    private String transferMethod;
    @JsonProperty("TransferStatus")
    private String transferStatus;
    @JsonProperty("TransferCompletionDate")
    private String transferCompletionDate;

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getCheckMailingAddress() {
        return checkMailingAddress;
    }

    public void setCheckMailingAddress(String checkMailingAddress) {
        this.checkMailingAddress = checkMailingAddress;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
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
