package pojoClasses.getLinkedBankAccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("beneficiary_id")
    private String beneficiary_id;
    @JsonProperty("beneficiary_name")
    private String beneficiary_name;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("country")
    private String country;
    @JsonProperty("transfer_fee")
    private String transfer_fee;
    @JsonProperty("account_number")
    private String account_number;
    @JsonProperty("bank_name")
    private String bank_name;
    @JsonProperty("payment_methods")
    private String payment_methods;
    @JsonProperty("branch_name")
    private String branch_name;
    @JsonProperty("country_flag")
    private String country_flag;
    @JsonProperty("status")
    private String status;
    @JsonProperty("bank_routing_code")
    private String bank_routing_code;
    @JsonProperty("bank_swiftbic")
    private String bank_swiftbic;

    public String getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(String beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public String getBeneficiary_name() {
        return beneficiary_name;
    }

    public void setBeneficiary_name(String beneficiary_name) {
        this.beneficiary_name = beneficiary_name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTransfer_fee() {
        return transfer_fee;
    }

    public void setTransfer_fee(String transfer_fee) {
        this.transfer_fee = transfer_fee;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getPayment_methods() {
        return payment_methods;
    }

    public void setPayment_methods(String payment_methods) {
        this.payment_methods = payment_methods;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getCountry_flag() {
        return country_flag;
    }

    public void setCountry_flag(String country_flag) {
        this.country_flag = country_flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBank_routing_code() {
        return bank_routing_code;
    }

    public void setBank_routing_code(String bank_routing_code) {
        this.bank_routing_code = bank_routing_code;
    }

    public String getBank_swiftbic() {
        return bank_swiftbic;
    }

    public void setBank_swiftbic(String bank_swiftbic) {
        this.bank_swiftbic = bank_swiftbic;
    }
}
