package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.GetBuyingCurrencyList;
import payloadRequest.GetFee;
import payloadRequest.GetStateList;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class General extends Utility {
    String token, accountNumber, toAccountNumber;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.getBuyingCurrencyList.ResObject successGetBuyingCurrencyList;
    pojoClasses.getCountryList.ResObject successGetCountryList;
    pojoClasses.getStateList.ResObject successGetStateList;
    pojoClasses.getPaymentMethods.ResObject successGetPaymentMethods;
    pojoClasses.getFee.ResObject successGetFee;

    @Given("Provide GetBuyingCurrencyList valid and invalid request")
    public void provide_get_buying_currency_list_valid_and_invalid_request() throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        req = postGiven(token).body(GetBuyingCurrencyList.request());
    }

    @When("{string} the request to {string} General API method")
    public void the_request_to_general_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("General response got success with status code {int}")
    public void general_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired GetBuyingCurrencyList pojo class")
    public void deserialize_the_response_with_desired_get_buying_currency_list_pojo_class() {
        if (res.statusCode() == 200) {
            successGetBuyingCurrencyList = res.as(pojoClasses.getBuyingCurrencyList.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Provide GetCountryList valid and invalid request")
    public void provide_get_country_list_valid_and_invalid_request() throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        req = postGiven(token).body("{}");
    }

    @Then("deserialize the response with desired GetCountryList pojo class")
    public void deserialize_the_response_with_desired_get_country_list_pojo_class() {
        if (res.statusCode() == 200) {
            successGetCountryList = res.as(pojoClasses.getCountryList.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Provide GetStateList valid and invalid request")
    public void provide_get_state_list_valid_and_invalid_request() throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        req = postGiven(token).body(GetStateList.request());
    }

    @Then("deserialize the response with desired GetStateList pojo class")
    public void deserialize_the_response_with_desired_get_state_list_pojo_class() {
        if (res.statusCode() == 200) {
            successGetStateList = res.as(pojoClasses.getStateList.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Provide GetPaymentMethods valid and invalid request")
    public void provide_get_payment_methods_valid_and_invalid_request() throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        req = postGiven(token).body("{}");
    }

    @Then("deserialize the response with desired GetPaymentMethods pojo class")
    public void deserialize_the_response_with_desired_get_payment_methods_pojo_class() {
        if (res.statusCode() == 200) {
            successGetPaymentMethods = res.as(pojoClasses.getPaymentMethods.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Provide GetFee valid and invalid request {string} {string} {string} {string} {string} {string}")
    public void provide_get_fee_valid_and_invalid_request(String PAT, String toAccount, String currency, String amount, String transactionType, String paymentMethod) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        toAccountNumber = getToAccountPAT(toAccount);
        req = postGiven(token).body(GetFee.request(accountNumber, toAccountNumber, currency, amount, transactionType, paymentMethod));
    }

    @Then("deserialize the response with desired GetFee pojo class")
    public void deserialize_the_response_with_desired_get_fee_pojo_class() {
        if(res.statusCode() == 200) {
            successGetFee = res.as(pojoClasses.getFee.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

}
