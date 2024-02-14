package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.LinkBankBeneficiary;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class Banks extends Utility {
    String token;
    String issuerAccountNumber;

    RequestSpecification req;

    APIPath apiPath;
    Response res;


    @Given("Provide valid and invalid data set in request parameters {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provide_valid_and_invalid_data_set_in_request_parameters(String IssuerAccountNumber, String UserID, String InstitutionName, String Currency, String SWIFTBIC, String AccountNumber, String RoutingNumber, String CountryISO2, String ContactName) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        //issuerAccountNumber = getIssuerAccountNumber(IssuerAccountNumber);
        req = postGiven(token)
                .body(LinkBankBeneficiary.request(InstitutionName, Currency, SWIFTBIC, AccountNumber, RoutingNumber, CountryISO2, ContactName));
    }

    @When("Post the request to {string} Bank API method")
    public void post_the_request_to_bank_api_method(String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Bank response got success with status code {int}")
    public void bank_response_got_success_with_status_code_status_code(Object statusCode) {
        assertEquals(statusCode, res.getStatusCode());
    }

}
