package pojoClasses.getOTPForgotPassword;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResObject {
    @JsonProperty("Status")
    private ResStatus status;
    @JsonProperty("account_number")
    private String account_number;
    @JsonProperty("client_id")
    private String client_id;
    @JsonProperty("client_secret")
    private String client_secret;
    @JsonProperty("email_id")
    private String email_id;
    @JsonProperty("data")
    private String data;

    public ResStatus getStatus() {
        return status;
    }

    public void setStatus(ResStatus status) {
        this.status = status;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getClient_id() {
        return client_id;
    }

    public void String(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
