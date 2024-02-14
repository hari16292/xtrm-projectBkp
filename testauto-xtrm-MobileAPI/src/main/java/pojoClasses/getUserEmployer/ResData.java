package pojoClasses.getUserEmployer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("employer_company_name")
    private String employer_company_name;
    @JsonProperty("company_account_number")
    private String company_account_number;
    @JsonProperty("job_title")
    private String job_title;
    @JsonProperty("date_of_hire")
    private String date_of_hire;

    public String getEmployer_company_name() {
        return employer_company_name;
    }

    public void setEmployer_company_name(String employer_company_name) {
        this.employer_company_name = employer_company_name;
    }

    public String getCompany_account_number() {
        return company_account_number;
    }

    public void setCompany_account_number(String company_account_number) {
        this.company_account_number = company_account_number;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getDate_of_hire() {
        return date_of_hire;
    }

    public void setDate_of_hire(String date_of_hire) {
        this.date_of_hire = date_of_hire;
    }
}
