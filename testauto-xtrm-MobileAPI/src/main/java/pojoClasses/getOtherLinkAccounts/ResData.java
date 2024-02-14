package pojoClasses.getOtherLinkAccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("AccountName")
    private String accountName;
    @JsonProperty("EmailID")
    private String emailID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
}
