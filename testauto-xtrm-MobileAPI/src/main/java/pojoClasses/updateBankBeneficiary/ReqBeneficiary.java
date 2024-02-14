package pojoClasses.updateBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqBeneficiary {

	@JsonProperty("beneficiary_details")
	private ReqBeneficiaryDetails beneficiary_details;
	@JsonProperty("bank_details")
	private ReqBankDetails bank_details;
	
	public ReqBeneficiaryDetails getBeneficiary_details() {
		return beneficiary_details;
	}
	public void setBeneficiary_details(ReqBeneficiaryDetails beneficiary_details) {
		this.beneficiary_details = beneficiary_details;
	}
	public ReqBankDetails getBank_details() {
		return bank_details;
	}
	public void setBank_details(ReqBankDetails bank_details) {
		this.bank_details = bank_details;
	}
	
	
	
}
