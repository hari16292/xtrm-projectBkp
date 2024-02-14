package pojoClasses.getLinkedCards;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("iCreditCardID")
    private String iCreditCardID;
    @JsonProperty("vFirstName")
    private String vFirstName;
    @JsonProperty("vLastName")
    private String vLastName;
    @JsonProperty("vAddress1")
    private String vAddress1;
    @JsonProperty("vAddress2")
    private String vAddress2;
    @JsonProperty("vCity")
    private String vCity;
    @JsonProperty("vState")
    private String vState;
    @JsonProperty("vCountry")
    private String vCountry;
    @JsonProperty("vZipCode")
    private String vZipCode;
    @JsonProperty("vNameonCard")
    private String vNameonCard;
    @JsonProperty("vCardType")
    private String vCardType;
    @JsonProperty("vCardNo")
    private String vCardNo;
    @JsonProperty("vExpMonth")
    private String vExpMonth;
    @JsonProperty("vExpYear")
    private String vExpYear;
    @JsonProperty("CardToken")
    private String CardToken;
    @JsonProperty("LinkCardType")
    private String LinkCardType;

    public String getiCreditCardID() {
        return iCreditCardID;
    }

    public void setiCreditCardID(String iCreditCardID) {
        this.iCreditCardID = iCreditCardID;
    }

    public String getvFirstName() {
        return vFirstName;
    }

    public void setvFirstName(String vFirstName) {
        this.vFirstName = vFirstName;
    }

    public String getvLastName() {
        return vLastName;
    }

    public void setvLastName(String vLastName) {
        this.vLastName = vLastName;
    }

    public String getvAddress1() {
        return vAddress1;
    }

    public void setvAddress1(String vAddress1) {
        this.vAddress1 = vAddress1;
    }

    public String getvAddress2() {
        return vAddress2;
    }

    public void setvAddress2(String vAddress2) {
        this.vAddress2 = vAddress2;
    }

    public String getvCity() {
        return vCity;
    }

    public void setvCity(String vCity) {
        this.vCity = vCity;
    }

    public String getvState() {
        return vState;
    }

    public void setvState(String vState) {
        this.vState = vState;
    }

    public String getvCountry() {
        return vCountry;
    }

    public void setvCountry(String vCountry) {
        this.vCountry = vCountry;
    }

    public String getvZipCode() {
        return vZipCode;
    }

    public void setvZipCode(String vZipCode) {
        this.vZipCode = vZipCode;
    }

    public String getvNameonCard() {
        return vNameonCard;
    }

    public void setvNameonCard(String vNameonCard) {
        this.vNameonCard = vNameonCard;
    }

    public String getvCardType() {
        return vCardType;
    }

    public void setvCardType(String vCardType) {
        this.vCardType = vCardType;
    }

    public String getvCardNo() {
        return vCardNo;
    }

    public void setvCardNo(String vCardNo) {
        this.vCardNo = vCardNo;
    }

    public String getvExpMonth() {
        return vExpMonth;
    }

    public void setvExpMonth(String vExpMonth) {
        this.vExpMonth = vExpMonth;
    }

    public String getvExpYear() {
        return vExpYear;
    }

    public void setvExpYear(String vExpYear) {
        this.vExpYear = vExpYear;
    }

    public String getCardToken() {
        return CardToken;
    }

    public void setCardToken(String cardToken) {
        CardToken = cardToken;
    }

    public String getLinkCardType() {
        return LinkCardType;
    }

    public void setLinkCardType(String linkCardType) {
        LinkCardType = linkCardType;
    }
}
