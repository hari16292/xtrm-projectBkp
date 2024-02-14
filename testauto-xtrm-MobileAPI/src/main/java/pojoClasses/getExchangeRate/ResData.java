package pojoClasses.getExchangeRate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("account_number")
    private String account_number;
    @JsonProperty("from_currency")
    private String from_currency;
    @JsonProperty("to_currency")
    private String to_currency;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("exchange_rate")
    private String exchange_rate;

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getFrom_currency() {
        return from_currency;
    }

    public void setFrom_currency(String from_currency) {
        this.from_currency = from_currency;
    }

    public String getTo_currency() {
        return to_currency;
    }

    public void setTo_currency(String to_currency) {
        this.to_currency = to_currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
