package pojoClasses.fundTransfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("TransactionID")
    private String transactionID;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
