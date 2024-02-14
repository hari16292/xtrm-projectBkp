package pojoClasses.createBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
	
	@JsonProperty("BeneficiaryId")
	private String beneficiaryId;
	@JsonProperty("BankBeneficiaryStatus")
	private String bankBeneficiaryStatus;
	@JsonProperty("AccountIdentityLevel")
	private String accountIdentityLevel;
	
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getBankBeneficiaryStatus() {
		return bankBeneficiaryStatus;
	}
	public void setBankBeneficiaryStatus(String bankBeneficiaryStatus) {
		this.bankBeneficiaryStatus = bankBeneficiaryStatus;
	}
	public String getAccountIdentityLevel() {
		return accountIdentityLevel;
	}
	public void setAccountIdentityLevel(String accountIdentityLevel) {
		this.accountIdentityLevel = accountIdentityLevel;
	}

}
