package pojoClasses.searchBank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
	
	@JsonProperty("BankName")
	private String BankName;

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

}
