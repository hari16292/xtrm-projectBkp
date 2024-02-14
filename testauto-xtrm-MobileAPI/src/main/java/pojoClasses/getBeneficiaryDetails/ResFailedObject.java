package pojoClasses.getBeneficiaryDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResFailedObject {
	
	@JsonProperty ("Status")
	private ResFailedStatus Status;

	public ResFailedStatus getStatus() {
		return Status;
	}

	public void setStatus(ResFailedStatus status) {
		Status = status;
	}

}
