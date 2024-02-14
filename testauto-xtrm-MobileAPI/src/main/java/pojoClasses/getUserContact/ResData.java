package pojoClasses.getUserContact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("email")
    private String email;
    @JsonProperty("mobile_number_country_flag")
    private String mobile_number_country_flag;
    @JsonProperty("mobile_number_calling_code")
    private String mobile_number_calling_code;
    @JsonProperty("mobile_number")
    private String mobile_number;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("country_id")
    private String country_id;
    @JsonProperty("country_iso2")
    private String country_iso2;
    @JsonProperty("state")
    private String state;
    @JsonProperty("state_id")
    private String state_id;
    @JsonProperty("postal_code")
    private String postal_code;
    @JsonProperty("default_currency_code")
    private String default_currency_code;
    @JsonProperty("Account_Identity_Level")
    private String Account_Identity_Level;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number_country_flag() {
        return mobile_number_country_flag;
    }

    public void setMobile_number_country_flag(String mobile_number_country_flag) {
        this.mobile_number_country_flag = mobile_number_country_flag;
    }

    public String getMobile_number_calling_code() {
        return mobile_number_calling_code;
    }

    public void setMobile_number_calling_code(String mobile_number_calling_code) {
        this.mobile_number_calling_code = mobile_number_calling_code;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_iso2() {
        return country_iso2;
    }

    public void setCountry_iso2(String country_iso2) {
        this.country_iso2 = country_iso2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getDefault_currency_code() {
        return default_currency_code;
    }

    public void setDefault_currency_code(String default_currency_code) {
        this.default_currency_code = default_currency_code;
    }

    public String getAccount_Identity_Level() {
        return Account_Identity_Level;
    }

    public void setAccount_Identity_Level(String account_Identity_Level) {
        Account_Identity_Level = account_Identity_Level;
    }
}
