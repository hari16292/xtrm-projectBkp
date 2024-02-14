package pojoClasses.getEmployers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResObject {

	@JsonProperty("Status")
	private ResStatus Status;
	@JsonProperty("data")
	private List<ResData> data;
	
	public ResStatus getStatus() {
		return Status;
	}
	public void setStatus(ResStatus status) {
		Status = status;
	}
	public List<ResData> getData() {
		return data;
	}
	public void setData(List<ResData> data) {
		this.data = data;
	}
}
