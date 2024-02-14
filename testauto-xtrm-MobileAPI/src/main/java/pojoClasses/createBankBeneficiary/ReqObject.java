package pojoClasses.createBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqObject {
	@JsonProperty("account_number")
	private String account_number;
	@JsonProperty("beneficiary")
	private ReqBeneficiary beneficiary;
	
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public ReqBeneficiary getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(ReqBeneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}
	
	

}
