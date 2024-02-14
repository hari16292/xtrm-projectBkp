package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.GetGiftCards;
import payloadRequest.GetOtherLinkAccounts;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class LinkOthers extends Utility {
    String token, account_number;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.getGiftCards.ResObject successGetGiftCardsRes;
    pojoClasses.getGiftCards.ResFailedObject failedGetGiftCardsRes;
    pojoClasses.getOtherLinkAccounts.ResObject successGetOtherLinkAccounts;

    @Given("Provide valid and invalid currency code in request parameter {string}")
    public void provide_valid_and_invalid_currency_code_in_request_parameter(String currencyCode) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        req = postGiven(token).body(GetGiftCards.request(currencyCode));
    }

    @When("{string} the request to {string} Link Others API method")
    public void the_request_to_link_others_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Link Others response got success with status code {int}")
    public void link_others_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.getStatusCode());
    }

    @Then("deserialize the response with desired GetGiftCards Pojo class")
    public void deserialize_the_response_with_desired_get_gift_cards_pojo_class() {
        if (res.statusCode() == 200) {
            successGetGiftCardsRes = res.as(pojoClasses.getGiftCards.ResObject.class);
        } else if (res.statusCode() == 400) {
            failedGetGiftCardsRes = res.as(pojoClasses.getGiftCards.ResFailedObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Provide GetOtherLinkAccounts valid and invalid user account number {string}")
    public void provide_get_other_link_accounts_valid_and_invalid_user_account_number(String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(GetOtherLinkAccounts.request(account_number));
    }

    @Then("deserialize the response with desired GetOtherLinkAccounts Pojo class")
    public void deserialize_the_response_with_desired_get_other_link_accounts_pojo_class() {
        if(res.statusCode() == 200) {
            successGetOtherLinkAccounts = res.as(pojoClasses.getOtherLinkAccounts.ResObject.class);
        } else if(res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }
}
