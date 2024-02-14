package pojoClasses.getCurrencyList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
