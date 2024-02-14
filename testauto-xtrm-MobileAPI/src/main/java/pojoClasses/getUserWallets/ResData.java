package pojoClasses.getUserWallets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("wallet_id")
    private String wallet_id;
    @JsonProperty("wallet_name")
    private String wallet_name;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("currency_symbol")
    private String currency_symbol;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("country_code")
    private String country_code;
    @JsonProperty("country_flag")
    private String country_flag;

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getWallet_name() {
        return wallet_name;
    }

    public void setWallet_name(String wallet_name) {
        this.wallet_name = wallet_name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_flag() {
        return country_flag;
    }

    public void setCountry_flag(String country_flag) {
        this.country_flag = country_flag;
    }
}
