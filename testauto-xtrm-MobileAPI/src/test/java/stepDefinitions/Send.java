package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.FundTransfer;
import payloadRequest.RecentPayees;
import payloadRequest.SimpleSendSearch;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Send extends Utility {
    String token;
    String account_number, to_account_number, description, amount;
    Random rand = new Random();
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    Wallet wal = new Wallet();
    ResFailedAuth failedAuthRes;
    pojoClasses.fundTransfer.ResObject successFundTransferRes;
    pojoClasses.simpleSendSearch.ResObject successSimpleSendSearch;
    pojoClasses.simpleSendSearch.ResFailedObject failedSimpleSendSearch;

    @Given("Provide FundTransfer valid and invalid request {string} {string} {string} {string}")
    public void provide_fund_transfer_valid_and_invalid_request(String PAT, String ToAccount, String CurrencyCode, String ToEmail) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        to_account_number = getToAccountPAT(ToAccount);
        description = "Test Auto Description - " + currentTime.format(myFormat);
        amount = String.valueOf(rand.nextInt(10) + 1);
        req = postGiven(token).body(FundTransfer.request(account_number, amount, CurrencyCode, to_account_number, ToEmail, description));
    }

    @When("{string} the request to {string} Send API method")
    public void the_request_to_send_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Send response got success with status code {int}")
    public void send_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired FundTransfer pojo class")
    public void deserialize_the_response_with_desired_fund_transfer_pojo_class() {
        if (res.statusCode() == 200) {
            successFundTransferRes = res.as(pojoClasses.fundTransfer.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Then("Verify the details in Transaction details")
    public void verify_the_details_in_transaction_details() throws Exception {
        if (res.statusCode() == 200) {
            String transactionDetails = successFundTransferRes.getData().getTransactionID();
            wal.provide_get_user_wallet_transaction_details_valid_and_invalid_request_parameters("Global Value", transactionDetails);
            wal.the_request_to_wallet_api_method("post", "getUserWalletTransactionDetails");
            wal.wallet_response_got_success_with_status_code(200);
            wal.deserialize_the_response_with_desired_get_user_wallet_transaction_details_pojo_class();
            String amountActual = (String) wal.successGetUserWalletTransactionDetailsSendRes.getData().getAmount();
            assertEquals("$" + amount + ".00 USD", amountActual);
        }
    }

    @Given("Provide SimpleSendSearch valid and invalid request {string} {string}")
    public void provide_simple_send_search_valid_and_invalid_request(String PAT, String SearchKey) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(SimpleSendSearch.request(account_number, SearchKey));
    }

    @Then("deserialize the response with desired SimpleSendSearch pojo class")
    public void deserialize_the_response_with_desired_simple_send_search_pojo_class() {
        if (res.statusCode() == 200) {
            successSimpleSendSearch = res.as(pojoClasses.simpleSendSearch.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else {
            failedSimpleSendSearch = res.as(pojoClasses.simpleSendSearch.ResFailedObject.class);
        }
    }

    @Given("Provide RecentPayees valid and invalid request {string} {string} {string}")
    public void provide_recentpayees_valid_and_invalid_request(String PAT, String currentPage, String itemsPerPage) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(RecentPayees.request(account_number, currentPage, itemsPerPage));
    }

}
