package pojoClasses.updateBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqBeneficiaryBankInformation {

	@JsonProperty("institution_name")
	private String institution_name;
	@JsonProperty("withdraw_type")
	private String withdraw_type;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("SWIFTBIC")
	private String SWIFTBIC;
	@JsonProperty("beneficiary_account_number")
	private String beneficiary_account_number;
	@JsonProperty("routing_number")
	private String routing_number;
	@JsonProperty("remittance_line3")
	private String remittance_line3;
	@JsonProperty("remittance_line4")
	private String remittance_line4;
	@JsonProperty("country_ISO2")
	private String country_ISO2;
	
	
	public String getInstitution_name() {
		return institution_name;
	}
	public void setInstitution_name(String institution_name) {
		this.institution_name = institution_name;
	}
	public String getWithdraw_type() {
		return withdraw_type;
	}
	public void setWithdraw_type(String withdraw_type) {
		this.withdraw_type = withdraw_type;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSWIFTBIC() {
		return SWIFTBIC;
	}
	public void setSWIFTBIC(String sWIFTBIC) {
		SWIFTBIC = sWIFTBIC;
	}
	public String getBeneficiary_account_number() {
		return beneficiary_account_number;
	}
	public void setBeneficiary_account_number(String beneficiary_account_number) {
		this.beneficiary_account_number = beneficiary_account_number;
	}
	public String getRouting_number() {
		return routing_number;
	}
	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}
	public String getRemittance_line3() {
		return remittance_line3;
	}
	public void setRemittance_line3(String remittance_line3) {
		this.remittance_line3 = remittance_line3;
	}
	public String getRemittance_line4() {
		return remittance_line4;
	}
	public void setRemittance_line4(String remittance_line4) {
		this.remittance_line4 = remittance_line4;
	}
	public String getCountry_ISO2() {
		return country_ISO2;
	}
	public void setCountry_ISO2(String country_ISO2) {
		this.country_ISO2 = country_ISO2;
	}
	
	
	
}
