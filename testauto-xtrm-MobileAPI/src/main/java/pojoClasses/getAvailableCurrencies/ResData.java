package pojoClasses.getAvailableCurrencies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("currency_code")
    private String currency_code;
    @JsonProperty("currency_description")
    private String currency_description;
    @JsonProperty("country_flag")
    private String country_flag;

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_description() {
        return currency_description;
    }

    public void setCurrency_description(String currency_description) {
        this.currency_description = currency_description;
    }

    public String getCountry_flag() {
        return country_flag;
    }

    public void setCountry_flag(String country_flag) {
        this.country_flag = country_flag;
    }
}
