package pojoClasses.registerUser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqRegisterUser {

    @JsonProperty("account_number")
    private String account_number;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("mobile_no")
    private String mobile_no;
    @JsonProperty("country_calling_flag")
    private String country_calling_flag;
    @JsonProperty("calling_code")
    private String calling_code;
    @JsonProperty("date_of_birth")
    private String date_of_birth;
    @JsonProperty("device_ip")
    private String device_ip;
    @JsonProperty("device_browser")
    private String device_browser;
    @JsonProperty("device_type")
    private String device_type;
    @JsonProperty("country")
    private String country;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zip_code")
    private String zip_code;


    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCountry_calling_flag() {
        return country_calling_flag;
    }

    public void setCountry_calling_flag(String country_calling_flag) {
        this.country_calling_flag = country_calling_flag;
    }

    public String getCalling_code() {
        return calling_code;
    }

    public void setCalling_code(String calling_code) {
        this.calling_code = calling_code;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getDevice_ip() {
        return device_ip;
    }

    public void setDevice_ip(String device_ip) {
        this.device_ip = device_ip;
    }

    public String getDevice_browser() {
        return device_browser;
    }

    public void setDevice_browser(String device_browser) {
        this.device_browser = device_browser;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }


}
