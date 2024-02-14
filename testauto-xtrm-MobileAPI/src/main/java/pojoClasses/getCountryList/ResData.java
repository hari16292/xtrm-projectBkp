package pojoClasses.getCountryList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {

    @JsonProperty("country_id")
    private String country_id;
    @JsonProperty("country_name")
    private String country_name;
    @JsonProperty("country_code")
    private String country_code;
    @JsonProperty("country_calling_code")
    private String country_calling_code;
    @JsonProperty("country_flag")
    private String country_flag;
    @JsonProperty("country_iso2")
    private String country_iso2;
    @JsonProperty("currency_code")
    private String currency_code;
    @JsonProperty("currency_description")
    private String currency_description;
    @JsonProperty("currency_symbol_unicode_decimal")
    private String currency_symbol_unicode_decimal;
    @JsonProperty("IsACH")
    private String isACH;

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_calling_code() {
        return country_calling_code;
    }

    public void setCountry_calling_code(String country_calling_code) {
        this.country_calling_code = country_calling_code;
    }

    public String getCountry_flag() {
        return country_flag;
    }

    public void setCountry_flag(String country_flag) {
        this.country_flag = country_flag;
    }

    public String getCountry_iso2() {
        return country_iso2;
    }

    public void setCountry_iso2(String country_iso2) {
        this.country_iso2 = country_iso2;
    }

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

    public String getCurrency_symbol_unicode_decimal() {
        return currency_symbol_unicode_decimal;
    }

    public void setCurrency_symbol_unicode_decimal(String currency_symbol_unicode_decimal) {
        this.currency_symbol_unicode_decimal = currency_symbol_unicode_decimal;
    }

    public String getIsACH() {
        return isACH;
    }

    public void setIsACH(String isACH) {
        this.isACH = isACH;
    }
}
