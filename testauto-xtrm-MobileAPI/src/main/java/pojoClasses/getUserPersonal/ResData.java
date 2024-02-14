package pojoClasses.getUserPersonal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("Date_of_Birth")
    private String date_of_Birth;
    @JsonProperty("SSN_Tax_ID")
    private String ssn_Tax_ID;
    @JsonProperty("Account_Identity_Level")
    private String account_Identity_Level;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_Birth() {
        return date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        this.date_of_Birth = date_of_Birth;
    }

    public String getSsn_Tax_ID() {
        return ssn_Tax_ID;
    }

    public void setSsn_Tax_ID(String ssn_Tax_ID) {
        this.ssn_Tax_ID = ssn_Tax_ID;
    }

    public String getAccount_Identity_Level() {
        return account_Identity_Level;
    }

    public void setAccount_Identity_Level(String account_Identity_Level) {
        this.account_Identity_Level = account_Identity_Level;
    }
}
