package pojoClasses.createBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqBeneficiaryDetails {
	@JsonProperty("beneficiary_information")
	private ReqBeneficiaryInformation beneficiary_information;

	public ReqBeneficiaryInformation getBeneficiary_information() {
		return beneficiary_information;
	}

	public void setBeneficiary_information(ReqBeneficiaryInformation beneficiary_information) {
		this.beneficiary_information = beneficiary_information;
	}
	
	
}
