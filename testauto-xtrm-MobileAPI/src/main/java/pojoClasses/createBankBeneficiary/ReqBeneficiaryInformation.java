package pojoClasses.createBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqBeneficiaryInformation {
	@JsonProperty("beneficiary_contact_name")
	private String beneficiary_contact_name;
	@JsonProperty("beneficiary_phone_number")
	private String beneficiary_phone_number;
	@JsonProperty("address_line1")
	private String address_line1;
	@JsonProperty("address_line2")
	private String address_line2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("region")
	private String region;
	@JsonProperty("postalcode")
	private String postalcode;
	@JsonProperty("country_ISO2")
	private String country_ISO2;
	
	public String getBeneficiary_contact_name() {
		return beneficiary_contact_name;
	}
	public void setBeneficiary_contact_name(String beneficiary_contact_name) {
		this.beneficiary_contact_name = beneficiary_contact_name;
	}
	public String getBeneficiary_phone_number() {
		return beneficiary_phone_number;
	}
	public void setBeneficiary_phone_number(String beneficiary_phone_number) {
		this.beneficiary_phone_number = beneficiary_phone_number;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getCountry_ISO2() {
		return country_ISO2;
	}
	public void setCountry_ISO2(String country_ISO2) {
		this.country_ISO2 = country_ISO2;
	}
	
	

}
