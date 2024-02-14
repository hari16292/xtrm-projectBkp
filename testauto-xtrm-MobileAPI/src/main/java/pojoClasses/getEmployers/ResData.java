package pojoClasses.getEmployers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
	@JsonProperty("company_name")
	private String company_name;
	@JsonProperty("account_number")
	private String account_number;
	@JsonProperty("company_logo")
	private String company_logo;
	@JsonProperty("company_location")
	private String company_location;

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getCompany_logo() {
		return company_logo;
	}

	public void setCompany_logo(String company_logo) {
		this.company_logo = company_logo;
	}

	public String getCompany_location() {
		return company_location;
	}

	public void setCompany_location(String company_location) {
		this.company_location = company_location;
	}
}
