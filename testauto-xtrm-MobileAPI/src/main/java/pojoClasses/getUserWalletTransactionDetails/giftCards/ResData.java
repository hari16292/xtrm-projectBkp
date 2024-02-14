package pojoClasses.getUserWalletTransactionDetails.giftCards;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("Method")
    private String method;
    @JsonProperty("TransactionStatus")
    private String transactionStatus;
    @JsonProperty("TransactionNumber")
    private String transactionNumber;
    @JsonProperty("Giftcard")
    private String giftcard;
    @JsonProperty("GiftcardImageUrl")
    private String giftcardImageUrl;
    @JsonProperty("RecipientEmail")
    private String recipientEmail;
    @JsonProperty("RecipientName")
    private String recipientName;
    @JsonProperty("RecipientMobilePhoneNumber")
    private String recipientMobilePhoneNumber;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("TimeProcessed")
    private String timeProcessed;

    @JsonProperty("Fees")
    private String fees;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getGiftcard() {
        return giftcard;
    }

    public void setGiftcard(String giftcard) {
        this.giftcard = giftcard;
    }

    public String getGiftcardImageUrl() {
        return giftcardImageUrl;
    }

    public void setGiftcardImageUrl(String giftcardImageUrl) {
        this.giftcardImageUrl = giftcardImageUrl;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientMobilePhoneNumber() {
        return recipientMobilePhoneNumber;
    }

    public void setRecipientMobilePhoneNumber(String recipientMobilePhoneNumber) {
        this.recipientMobilePhoneNumber = recipientMobilePhoneNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimeProcessed() {
        return timeProcessed;
    }

    public void setTimeProcessed(String timeProcessed) {
        this.timeProcessed = timeProcessed;
    }

    public String getFees() {
        return fees;
    }
}
