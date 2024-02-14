package pojoClasses.getBeneficiaryDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResObject {

	@JsonProperty ("Status")
	private ResStatus Status;
	@JsonProperty ("data")
	private ResData data;
	
	public ResStatus getStatus() {
		return Status;
	}
	public void setStatus(ResStatus status) {
		Status = status;
	}
	public ResData getData() {
		return data;
	}
	public void setData(ResData data) {
		this.data = data;
	}
}
