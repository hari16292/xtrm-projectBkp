package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.BookCurrencyExchange;
import payloadRequest.GetExchangeRate;
import resources.APIPath;
import resources.Utility;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Exchange extends Utility {
    String token, exchange, account_number;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    pojoClasses.commonClasses.ResFailedAuth failedAuthRes;
    pojoClasses.getExchangeRate.ResObject successGetExchangeRate;
    pojoClasses.getExchangeRate.ResFailedObject failedGetExchangeRate;
    pojoClasses.bookCurrencyExchange.ResObject successBookCurrencyExchange;
    pojoClasses.bookCurrencyExchange.ResFailedObject failedBookCurrencyExchange;

    @Given("Provide valid and invalid request parameters {string} {string} {string} {string}")
    public void provide_valid_and_invalid_request_parameters(String PAT, String amount, String fromCurrency, String toCurrency) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(GetExchangeRate.request(account_number, amount, fromCurrency, toCurrency));
    }

    @When("{string} the request to {string} GetExchangeRate API method")
    public void the_request_to_get_exchange_rate_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Exchange response got success with status code {int}")
    public void exchange_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.getStatusCode());
    }

    @Then("deserialize the response with desired GetExchangeRate Pojo class")
    public void deserialize_the_response_with_desired_get_exchange_rate_pojo_class() {
        if (res.statusCode() == 200) {
            successGetExchangeRate = res.as(pojoClasses.getExchangeRate.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else {
            failedGetExchangeRate = res.as(pojoClasses.getExchangeRate.ResFailedObject.class);
        }
    }



    @Given("Provide valid and invalid request parameters {string} {string} {string} {string} {string}")
    public void provide_valid_and_invalid_request_parameters(String PAT, String amount, String exchangeRate, String fromCurrency, String toCurrency) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        switch (exchangeRate) {
            case "Valid Exchange":
                provide_valid_and_invalid_request_parameters(account_number, amount, fromCurrency, toCurrency);
                the_request_to_get_exchange_rate_api_method("post", "getExchangeRate");
                deserialize_the_response_with_desired_get_exchange_rate_pojo_class();
                exchange = successGetExchangeRate.getData().getExchange_rate();
                break;
            case "Invalid Exchange":
                exchange = "abc";
                break;
            case "Empty Exchange":
                exchange = "";
                break;
        }
        req = postGiven(token).body(BookCurrencyExchange.request(account_number, amount, exchange, fromCurrency, toCurrency));
    }

    @Then("deserialize the response with desired BookCurrencyExchange Pojo class")
    public void deserialize_the_response_with_desired_book_currency_exchange_pojo_class() {
        if (res.statusCode() == 200) {
            successBookCurrencyExchange = res.as(pojoClasses.bookCurrencyExchange.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else {
            failedBookCurrencyExchange = res.as(pojoClasses.bookCurrencyExchange.ResFailedObject.class);
        }
    }


}
