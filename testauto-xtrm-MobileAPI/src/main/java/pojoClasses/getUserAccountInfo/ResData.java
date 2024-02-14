package pojoClasses.getUserAccountInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("register_date")
    private String register_date;
    @JsonProperty("name")
    private String name;
    @JsonProperty("account_no")
    private String account_no;
    @JsonProperty("email_id")
    private String email_id;
    @JsonProperty("email_validated")
    private String email_validated;
    @JsonProperty("verified")
    private String verified;
    @JsonProperty("account_identity_level")
    private String account_identity_level;

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getEmail_validated() {
        return email_validated;
    }

    public void setEmail_validated(String email_validated) {
        this.email_validated = email_validated;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getAccount_identity_level() {
        return account_identity_level;
    }

    public void setAccount_identity_level(String account_identity_level) {
        this.account_identity_level = account_identity_level;
    }
}
