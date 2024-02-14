package pojoClasses.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResSuccessAuthorization {
	@JsonProperty("access_token")
	private String access_token;
	@JsonProperty("token_type")
	private String token_type;
	@JsonProperty("expires_in")
	private String expires_in;
	@JsonProperty("refresh_token")
	private String refresh_token;
	@JsonProperty("client_id")
	private String client_id;
	@JsonProperty(".issued")
	private String issued;
	@JsonProperty(".expires")
	private String expires;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getIssued() {
		return issued;
	}
	public void setIssued(String issued) {
		this.issued = issued;
	}
	public String getExpires() {
		return expires;
	}
	public void setExpires(String expires) {
		this.expires = expires;
	}

}
