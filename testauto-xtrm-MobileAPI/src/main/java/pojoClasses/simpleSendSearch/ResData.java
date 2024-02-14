package pojoClasses.simpleSendSearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResData {
    @JsonProperty("member_name")
    private String member_name;
    @JsonProperty("account_number")
    private String account_number;
    @JsonProperty("account_type")
    private String account_type;
    @JsonProperty("location")
    private String location;
    @JsonProperty("employer")
    private String employer;
    @JsonProperty("website")
    private String website;
    @JsonProperty("email_id")
    private String email_id;
    @JsonProperty("Photo")
    private List<String> Photo;
    @JsonProperty("employer_account_number")
    private String employer_account_number;

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public List<String> getPhoto() {
        return Photo;
    }

    public void setPhoto(List<String> photo) {
        Photo = photo;
    }

    public String getEmployer_account_number() {
        return employer_account_number;
    }

    public void setEmployer_account_number(String employer_account_number) {
        this.employer_account_number = employer_account_number;
    }
}
