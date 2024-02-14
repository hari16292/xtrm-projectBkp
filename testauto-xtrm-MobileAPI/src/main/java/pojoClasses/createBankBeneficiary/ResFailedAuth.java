package pojoClasses.createBankBeneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResFailedAuth {

	@JsonProperty("error")
	private String error;
	@JsonProperty("error_message")
	private String error_message;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError_description() {
		return error_message;
	}
	public void setError_description(String error_message) {
		this.error_message = error_message;
	}
}
