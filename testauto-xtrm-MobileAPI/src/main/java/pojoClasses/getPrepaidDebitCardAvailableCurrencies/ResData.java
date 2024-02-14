package pojoClasses.getPrepaidDebitCardAvailableCurrencies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    @JsonProperty("CurrencyDescription")
    private String currencyDescription;
    @JsonProperty("CountryFlag")
    private String countryFlag;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }
}
