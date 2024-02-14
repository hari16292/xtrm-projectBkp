package pojoClasses.getClaims;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("claim_id")
    private String claim_id;
    @JsonProperty("company_name")
    private String company_name;
    @JsonProperty("claim_date")
    private String claim_date;
    @JsonProperty("status")
    private String status;
    @JsonProperty("type")
    private String type;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("deal_reg_id")
    private String deal_reg_id;

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getClaim_date() {
        return claim_date;
    }

    public void setClaim_date(String claim_date) {
        this.claim_date = claim_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDeal_reg_id() {
        return deal_reg_id;
    }

    public void setDeal_reg_id(String deal_reg_id) {
        this.deal_reg_id = deal_reg_id;
    }

    public String getEnd_customer_name() {
        return end_customer_name;
    }

    public void setEnd_customer_name(String end_customer_name) {
        this.end_customer_name = end_customer_name;
    }

    @JsonProperty("end_customer_name")
    private String end_customer_name;


}
