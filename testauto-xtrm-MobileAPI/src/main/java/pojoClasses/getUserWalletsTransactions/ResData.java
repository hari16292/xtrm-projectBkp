package pojoClasses.getUserWalletsTransactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("transaction_id")
    private String transaction_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("transaction_icon")
    private String transaction_icon;
    @JsonProperty("transaction_type")
    private String transaction_type;
    @JsonProperty("transaction_date")
    private String transaction_date;
    @JsonProperty("description")
    private String description;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("type")
    private String type;
    @JsonProperty("currency_symbol")
    private String currency_symbol;
    @JsonProperty("countryflag")
    private String countryflag;
    @JsonProperty("currency_name")
    private String currency_name;
    @JsonProperty("TotalRecords")
    private String TotalRecords;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransaction_icon() {
        return transaction_icon;
    }

    public void setTransaction_icon(String transaction_icon) {
        this.transaction_icon = transaction_icon;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public String getCountryflag() {
        return countryflag;
    }

    public void setCountryflag(String countryflag) {
        this.countryflag = countryflag;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(String totalRecords) {
        TotalRecords = totalRecords;
    }
}
