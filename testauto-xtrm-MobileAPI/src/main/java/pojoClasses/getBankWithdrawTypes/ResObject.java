package pojoClasses.getBankWithdrawTypes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResObject {
	
	@JsonProperty("Status")
	private ResStatus Status;
	@JsonProperty("data")
	private List<String> data;
	public ResStatus getStatus() {
		return Status;
	}
	public void setStatus(ResStatus status) {
		Status = status;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	
	

}
