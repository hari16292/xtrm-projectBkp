package pojoClasses.getBeneficiaryDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
	
	@JsonProperty ("BeneficiaryId")
	private String BeneficiaryId;
	@JsonProperty ("BeneficiaryContactName")
	private String BeneficiaryContactName;
	@JsonProperty ("Currency")
	private String Currency;
	@JsonProperty ("AlertBeneficiaryOnPayment")
	private String AlertBeneficiaryOnPayment;
	@JsonProperty ("BankAddressLine1")
	private String BankAddressLine1;
	@JsonProperty ("BankAddressLine2")
	private String BankAddressLine2;
	@JsonProperty ("BankCity")
	private String BankCity;
	@JsonProperty ("BankCountryISO2")
	private String BankCountryISO2;
	@JsonProperty ("BankPostalCode")
	private String BankPostalCode;
	@JsonProperty ("BankRegion")
	private String BankRegion;
	@JsonProperty ("InstitutionName")
	private String InstitutionName;
	@JsonProperty ("RoutingCode")
	private String RoutingCode;
	@JsonProperty ("SWIFTBIC")
	private String SWIFTBIC;
	@JsonProperty ("AccountNumber")
	private String AccountNumber;
	@JsonProperty ("BeneficiaryAddress1")
	private String BeneficiaryAddress1;
	@JsonProperty ("Email")
	private String Email;
	@JsonProperty ("BeneficiaryPhoneNumber")
	private String BeneficiaryPhoneNumber;
	@JsonProperty ("PaymentMethod")
	private String PaymentMethod;
	@JsonProperty ("Status")
	private String Status;
	@JsonProperty ("RegulatoryFields")
	private String RegulatoryFields;
	@JsonProperty ("ValidationRuleSpecs")
	private String ValidationRuleSpecs;
	
	public String getBeneficiaryId() {
		return BeneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		BeneficiaryId = beneficiaryId;
	}
	public String getBeneficiaryContactName() {
		return BeneficiaryContactName;
	}
	public void setBeneficiaryContactName(String beneficiaryContactName) {
		BeneficiaryContactName = beneficiaryContactName;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getAlertBeneficiaryOnPayment() {
		return AlertBeneficiaryOnPayment;
	}
	public void setAlertBeneficiaryOnPayment(String alertBeneficiaryOnPayment) {
		AlertBeneficiaryOnPayment = alertBeneficiaryOnPayment;
	}
	public String getBankAddressLine1() {
		return BankAddressLine1;
	}
	public void setBankAddressLine1(String bankAddressLine1) {
		BankAddressLine1 = bankAddressLine1;
	}
	public String getBankAddressLine2() {
		return BankAddressLine2;
	}
	public void setBankAddressLine2(String bankAddressLine2) {
		BankAddressLine2 = bankAddressLine2;
	}
	public String getBankCity() {
		return BankCity;
	}
	public void setBankCity(String bankCity) {
		BankCity = bankCity;
	}
	public String getBankCountryISO2() {
		return BankCountryISO2;
	}
	public void setBankCountryISO2(String bankCountryISO2) {
		BankCountryISO2 = bankCountryISO2;
	}
	public String getBankPostalCode() {
		return BankPostalCode;
	}
	public void setBankPostalCode(String bankPostalCode) {
		BankPostalCode = bankPostalCode;
	}
	public String getBankRegion() {
		return BankRegion;
	}
	public void setBankRegion(String bankRegion) {
		BankRegion = bankRegion;
	}
	public String getInstitutionName() {
		return InstitutionName;
	}
	public void setInstitutionName(String institutionName) {
		InstitutionName = institutionName;
	}
	public String getRoutingCode() {
		return RoutingCode;
	}
	public void setRoutingCode(String routingCode) {
		RoutingCode = routingCode;
	}
	public String getSWIFTBIC() {
		return SWIFTBIC;
	}
	public void setSWIFTBIC(String sWIFTBIC) {
		SWIFTBIC = sWIFTBIC;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getBeneficiaryAddress1() {
		return BeneficiaryAddress1;
	}
	public void setBeneficiaryAddress1(String beneficiaryAddress1) {
		BeneficiaryAddress1 = beneficiaryAddress1;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getBeneficiaryPhoneNumber() {
		return BeneficiaryPhoneNumber;
	}
	public void setBeneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
		BeneficiaryPhoneNumber = beneficiaryPhoneNumber;
	}
	public String getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getRegulatoryFields() {
		return RegulatoryFields;
	}
	public void setRegulatoryFields(String regulatoryFields) {
		RegulatoryFields = regulatoryFields;
	}
	public String getValidationRuleSpecs() {
		return ValidationRuleSpecs;
	}
	public void setValidationRuleSpecs(String validationRuleSpecs) {
		ValidationRuleSpecs = validationRuleSpecs;
	}
	

}
