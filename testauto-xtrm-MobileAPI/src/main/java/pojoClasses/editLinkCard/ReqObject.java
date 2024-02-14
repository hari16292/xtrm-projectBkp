package pojoClasses.editLinkCard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqObject {

    @JsonProperty("linkedCardId")
    private String linkedCardId;
    @JsonProperty("account_number")
    private String account_number;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("country_code_iso2")
    private String country_code_iso2;
    @JsonProperty("postal_code")
    private String postal_code;
    @JsonProperty("credit_card_number")
    private String credit_card_number;
    @JsonProperty("credit_card_type")
    private String credit_card_type;

    public String getLinkedCardId() {
        return linkedCardId;
    }

    public void setLinkedCardId(String linkedCardId) {
        this.linkedCardId = linkedCardId;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code_iso2() {
        return country_code_iso2;
    }

    public void setCountry_code_iso2(String country_code_iso2) {
        this.country_code_iso2 = country_code_iso2;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCredit_card_number() {
        return credit_card_number;
    }

    public void setCredit_card_number(String credit_card_number) {
        this.credit_card_number = credit_card_number;
    }

    public String getCredit_card_type() {
        return credit_card_type;
    }

    public void setCredit_card_type(String credit_card_type) {
        this.credit_card_type = credit_card_type;
    }

    public String getName_on_card() {
        return name_on_card;
    }

    public void setName_on_card(String name_on_card) {
        this.name_on_card = name_on_card;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpire_month() {
        return expire_month;
    }

    public void setExpire_month(String expire_month) {
        this.expire_month = expire_month;
    }

    public String getExpire_year() {
        return expire_year;
    }

    public void setExpire_year(String expire_year) {
        this.expire_year = expire_year;
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

    @JsonProperty("name_on_card")
    private String name_on_card;
    @JsonProperty("currency_code")
    private String currency_code;
    @JsonProperty("cvv")
    private String cvv;
    @JsonProperty("expire_month")
    private String expire_month;
    @JsonProperty("expire_year")
    private String expire_year;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;


}
