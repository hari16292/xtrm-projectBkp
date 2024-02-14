package pojoClasses.deleteBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResFailedObject {

	@JsonProperty("Status")
	private ResFailedStatus status;

	public ResFailedStatus getStatus() {
		return status;
	}

	public void setStatus(ResFailedStatus status) {
		this.status = status;
	}
	
}
