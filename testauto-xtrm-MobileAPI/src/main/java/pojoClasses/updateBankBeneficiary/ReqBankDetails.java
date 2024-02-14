package pojoClasses.updateBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqBankDetails {
	
	@JsonProperty("beneficiary_bank_information")
	private ReqBeneficiaryBankInformation beneficiary_bank_information;

	public ReqBeneficiaryBankInformation getBeneficiary_bank_information() {
		return beneficiary_bank_information;
	}

	public void setBeneficiary_bank_information(ReqBeneficiaryBankInformation beneficiary_bank_information) {
		this.beneficiary_bank_information = beneficiary_bank_information;
	}
	
	

}
