package pojoClasses.getDevices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
	@JsonProperty("id")
	private String id;
	@JsonProperty("device")
	private String device;
	@JsonProperty("browser")
	private String browser;
	@JsonProperty("last_login")
	private String last_login;
	@JsonProperty("ip_address")
	private String ip_address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
}
