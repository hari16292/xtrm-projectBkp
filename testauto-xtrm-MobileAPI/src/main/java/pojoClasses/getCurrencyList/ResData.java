package pojoClasses.getCurrencyList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
	@JsonProperty("CurrencyTypeID")
	private String CurrencyTypeID;
	@JsonProperty("CurrencyName")
	private String CurrencyName;
	@JsonProperty("CurrencyCode")
	private String CurrencyCode;
	@JsonProperty("SymbolUnicodeDecimal")
	private String SymbolUnicodeDecimal;
	@JsonProperty("CountryFlag")
	private String CountryFlag;
	public String getCurrencyTypeID() {
		return CurrencyTypeID;
	}
	public void setCurrencyTypeID(String currencyTypeID) {
		CurrencyTypeID = currencyTypeID;
	}
	public String getCurrencyName() {
		return CurrencyName;
	}
	public void setCurrencyName(String currencyName) {
		CurrencyName = currencyName;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public String getSymbolUnicodeDecimal() {
		return SymbolUnicodeDecimal;
	}
	public void setSymbolUnicodeDecimal(String symbolUnicodeDecimal) {
		SymbolUnicodeDecimal = symbolUnicodeDecimal;
	}
	public String getCountryFlag() {
		return CountryFlag;
	}
	public void setCountryFlag(String countryFlag) {
		CountryFlag = countryFlag;
	}
	
	

}
