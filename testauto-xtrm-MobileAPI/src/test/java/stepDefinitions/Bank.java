package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.*;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class Bank extends Utility {

    String token;
    String beneID = "";
    String account_number;
    RequestSpecification req;
    pojoClasses.getLinkedBankAccounts.ResObject successGetLinkedBankAccounts;
    pojoClasses.getLinkedBankAccounts.ResFailed failedGetLinkedBankAccounts;
    pojoClasses.getBeneficiaryDetails.ResObject successGetBeneficiaryDetails;
    pojoClasses.getBeneficiaryDetails.ResFailedObject failedGetBeneficiaryDetails;
    pojoClasses.searchBank.ResObject successSearchBank;
    pojoClasses.searchBank.ResFailedObject failedSearchBank;
    pojoClasses.getBankWithdrawTypes.ResObject successGetBankWithdrawTypes;
    pojoClasses.createBankBeneficiary.ResObject successCreateBankBeneficiary;
    pojoClasses.createBankBeneficiary.ResFailedObject failedCreateBankBeneficiary;
    pojoClasses.deleteBankBeneficiary.ResObject successDeleteBankBeneficiaryRes;
    pojoClasses.deleteBankBeneficiary.ResFailedObject failedDeleteBankBeneficiaryRes;
    ResFailedAuth failedAuthRes;
    pojoClasses.getCurrencyList.ResObject successGetCurrencyList;
    APIPath apiPath;
    Response res;

    @Given("Provide valid and invalid user account number {string} in request parameters")
    public void provide_valid_and_invalid_user_account_number_in_request_parameters(String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(GetLinkedBankAccounts.request(account_number));
    }

    @When("{string} the request to {string} Bank API method")
    public void the_request_to_bank_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Bank response got success with status code {int}")
    public void bank_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.getStatusCode());
    }

    @Then("deserialize the response with desired GetLinkedBankAccounts Pojo class")
    public void deserialize_the_response_with_desired_get_linked_bank_accounts_pojo_class() {
        if (res.getStatusCode() == 200) {
            successGetLinkedBankAccounts = res.as(pojoClasses.getLinkedBankAccounts.ResObject.class);
        } else {
            failedGetLinkedBankAccounts = res.as(pojoClasses.getLinkedBankAccounts.ResFailed.class);
        }
    }

    @Given("Provide valid and invalid {string} in request parameter {string}")
    public void provide_valid_and_invalid_in_request_parameter(String Beneficiary_ID, String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        if (Beneficiary_ID.equals("Valid ID")) {
            provide_valid_and_invalid_user_account_number_in_request_parameters("Global Value");
            the_request_to_bank_api_method("post", "getLinkedBankAccounts");
            deserialize_the_response_with_desired_get_linked_bank_accounts_pojo_class();
            int beneCount = successGetLinkedBankAccounts.getData().size();
            if (beneCount > 0) {
                beneID = successGetLinkedBankAccounts.getData().get(0).getBeneficiary_id();
            } else {
                provide_valid_and_invalid_data_set_in_request_parameter("Global Value", "Hari Test", "5648972549", "Addr1", "Addr2", "Toronto", "Tamil Nadu", "645463", "IN", "KOTAK MAHINDRA BANK LIMITED", "INR", "KKBKINBBXXX", "9213447220", "KKBK0003539", "", "", "IN", 200);
                the_request_to_bank_api_method("post", "createBankBeneficiary");
                provide_valid_and_invalid_user_account_number_in_request_parameters("Global Value");
                the_request_to_bank_api_method("post", "getLinkedBankAccounts");
                deserialize_the_response_with_desired_get_linked_bank_accounts_pojo_class();
                beneCount = successGetLinkedBankAccounts.getData().size();
                if (beneCount > 0) {
                    beneID = successGetLinkedBankAccounts.getData().get(0).getBeneficiary_id();
                }
            }
        } else if (Beneficiary_ID.equals("Invalid ID")) {
            beneID = "2121";
        } else if (Beneficiary_ID.equals("Empty ID")) {
            beneID = "";
        }
        account_number = getPAT(PAT);
        req = postGiven(token)
                .body(GetBeneficiaryDetails.request(account_number, beneID));
    }

    @Then("deserialize the response with desired GetBeneficiaryDetails Pojo class")
    public void deserialize_the_response_with_desired_get_beneficiary_details_pojo_class() {
        if (res.getStatusCode() == 200) {
            successGetBeneficiaryDetails = res.as(pojoClasses.getBeneficiaryDetails.ResObject.class);
        } else if (res.getStatusCode() == 400) {
            failedGetBeneficiaryDetails = res.as(pojoClasses.getBeneficiaryDetails.ResFailedObject.class);
        } else if (res.getStatusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Then("Fetch {string} value from failed response")
    public void fetch_value_from_failed_response(String message) {
        if (res.getStatusCode() != 200) {
            //assertEquals(failedGetBeneficiaryDetails.getStatus().getMessage(), message);
        }

    }

    @Given("Provide valid and invalid data set in request parameter {string} {string}")
    public void provide_valid_and_invalid_data_set_in_request_parameter(String BankName, String CountryCode) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }

        req = postGiven(token).body(SearchBank.request(BankName, CountryCode));

    }

    @Then("deserialize the response with desired SearchBank Pojo class")
    public void deserialize_the_response_with_desired_search_bank_pojo_class() {
        if (res.getStatusCode() == 200) {
            successSearchBank = res.as(pojoClasses.searchBank.ResObject.class);
        } else {
            failedSearchBank = res.as(pojoClasses.searchBank.ResFailedObject.class);
        }
    }

    @Given("Provide valid and invalid data set in request parameter {string}")
    public void provide_valid_and_invalid_data_set_in_request_parameter(String country_ISO2) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        req = postGiven(token).body(GetBankWithdrawTypes.request(country_ISO2));
    }

    @Then("deserialize the response with desired GetBankWithdrawTypes Pojo class")
    public void deserialize_the_response_with_desired_get_bank_withdraw_types_pojo_class() {
        if (res.getStatusCode() == 200) {
            successGetBankWithdrawTypes = res.as(pojoClasses.getBankWithdrawTypes.ResObject.class);
        }
    }

    @Given("Provide valid and invalid data set in request parameter {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {int}")
    public void provide_valid_and_invalid_data_set_in_request_parameter(String PAT,
                                                                        String beneficiary_contact_name, String beneficiary_phone_number, String address_line1,
                                                                        String address_line2, String city, String region, String postalcode,
                                                                        String beneficiary_information_country_ISO2, String institution_name, String currency, String SWIFTBIC,
                                                                        String beneficiary_account_number, String routing_number, String remittance_line3, String remittance_line4,
                                                                        String country_ISO2, int StatusCode) throws Exception {
        //Getting Bearer token value
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        //Delete all existing Banks
        if (StatusCode == 200) {
            provide_valid_and_invalid_user_account_number_in_request_parameters(PAT);
            the_request_to_bank_api_method("post", "getLinkedBankAccounts");
            deserialize_the_response_with_desired_get_linked_bank_accounts_pojo_class();
            int beneCount = successGetLinkedBankAccounts.getData().size();
            if (beneCount > 0) {
                for (int i = 0; i <= beneCount - 1; i++) {
                    beneID = successGetLinkedBankAccounts.getData().get(i).getBeneficiary_id();
                    provide_valid_and_invalid_data_in_delete_bank_beneficiary(account_number, beneID);
                    the_request_to_bank_api_method("post", "deleteBankBeneficiary");
                }
            }
        }
        req = postGiven(token)
                .body(CreateBankBeneficiary.request(account_number, beneficiary_contact_name, beneficiary_phone_number,
                        address_line1, address_line2, city, region, postalcode, beneficiary_information_country_ISO2,
                        institution_name, currency, SWIFTBIC, beneficiary_account_number, routing_number,
                        remittance_line3, remittance_line4, country_ISO2));
    }

    @Then("deserialize the response with desired CreateBankBeneficiary Pojo class")
    public void deserialize_the_response_with_desired_create_bank_beneficiary_pojo_class() {
        if (res.getStatusCode() == 200) {
            successCreateBankBeneficiary = res.as(pojoClasses.createBankBeneficiary.ResObject.class);
        } else if (res.getStatusCode() == 401) {
            failedAuthRes = res.as(ResFailedAuth.class);
        } else {
            failedCreateBankBeneficiary = res.as(pojoClasses.createBankBeneficiary.ResFailedObject.class);
        }
    }

    @Given("Provide valid and invalid data in DeleteBankBeneficiary {string} {string}")
    public void provide_valid_and_invalid_data_in_delete_bank_beneficiary(String PAT, String BeneficiaryID) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        if (BeneficiaryID.equals("Fetch from GetLinkedBankAccounts")) {
            provide_valid_and_invalid_user_account_number_in_request_parameters(PAT);
            the_request_to_bank_api_method("post", "getLinkedBankAccounts");
            deserialize_the_response_with_desired_get_linked_bank_accounts_pojo_class();
            int beneCount = successGetLinkedBankAccounts.getData().size();
            if (beneCount > 0) {
                beneID = successGetLinkedBankAccounts.getData().get(0).getBeneficiary_id();
            }
        } else if (BeneficiaryID.equals("Invalid ID")) {
            beneID = "112233";
        }
        req = postGiven(token).body(DeleteBankBeneficiary.request(account_number, beneID));
    }

    @Then("deserialize the response with desired DeleteBankBeneficiary Pojo class")
    public void deserialize_the_response_with_desired_delete_bank_beneficiary_pojo_class() {
        if (res.statusCode() == 200) {
            successDeleteBankBeneficiaryRes = res.as(pojoClasses.deleteBankBeneficiary.ResObject.class);
        } else if (res.getStatusCode() == 401) {
            failedAuthRes = res.as(ResFailedAuth.class);
        } else if (res.statusCode() == 400) {
            failedDeleteBankBeneficiaryRes = res.as(pojoClasses.deleteBankBeneficiary.ResFailedObject.class);
        }
    }

    @Given("Provide valid and invalid request body")
    public void provide_valid_and_invalid_request_body() throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        req = postGiven(token).body("{}");
    }

    @Then("deserialize the response with desired GetCurrencyList Pojo class")
    public void deserialize_the_response_with_desired_get_currency_list_pojo_class() {
        if (res.statusCode() == 200) {
            successGetCurrencyList = res.as(pojoClasses.getCurrencyList.ResObject.class);
        }
    }

    @Then("Validate total number of currency in response is equal to {int}")
    public void validate_total_number_of_currency_in_response_is_equal_to(Object totalCurrency) {
        assertEquals(totalCurrency, successGetCurrencyList.getData().size());
    }
}
