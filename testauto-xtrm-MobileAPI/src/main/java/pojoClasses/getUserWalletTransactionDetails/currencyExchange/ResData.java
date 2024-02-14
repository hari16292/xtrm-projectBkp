package pojoClasses.getUserWalletTransactionDetails.currencyExchange;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("TransferType")
    private String transferType;
    @JsonProperty("TransactionStatus")
    private String transactionStatus;
    @JsonProperty("FromAmount")
    private String fromAmount;
    @JsonProperty("ToAmount")
    private String toAmount;
    @JsonProperty("ExchangeRate")
    private String exchangeRate;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Date")
    private String date;

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
