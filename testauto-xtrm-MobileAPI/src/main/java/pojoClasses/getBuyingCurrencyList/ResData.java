package pojoClasses.getBuyingCurrencyList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("CurrencyTypeID")
    private String currencyTypeID;
    @JsonProperty("CurrencyName")
    private String currencyName;
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    @JsonProperty("SymbolUnicodeDecimal")
    private String symbolUnicodeDecimal;
    @JsonProperty("CountryFlag")
    private String countryFlag;

    public String getCurrencyTypeID() {
        return currencyTypeID;
    }

    public void setCurrencyTypeID(String currencyTypeID) {
        this.currencyTypeID = currencyTypeID;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getSymbolUnicodeDecimal() {
        return symbolUnicodeDecimal;
    }

    public void setSymbolUnicodeDecimal(String symbolUnicodeDecimal) {
        this.symbolUnicodeDecimal = symbolUnicodeDecimal;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }
}
