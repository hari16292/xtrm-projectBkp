package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.Auth;
import pojoClasses.commonClasses.ResFailedoAuth;
import pojoClasses.token.*;
import resources.APIPath;
import resources.Utility;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AccessToken extends Utility {

    public static String token = null;
    RequestSpecification req;
    ResSuccessAuthorization successAuthRes;
    ResFailedoAuth failedAuthRes;
    APIPath apiPath;
    Response res;
    String clientID, clientSecret;

    @Given("Validate user credential with {string} and {string}")
    public void validate_user_credential_with_and(String clientIDKey, String clientSecretKey) throws Exception {
        clientID = getClientID(clientIDKey);
        clientSecret = getClientSecret(clientSecretKey);
        req = given().spec(reqSpec()).header("Content-Type", "application/x-www-form-urlencoded")
                .body(Auth.request(clientID, clientSecret));
    }

    @When("{string} the request to {string} token API method")
    public void the_request_to_token_api_method(String httpMethod, String apiMethodPath) throws IOException {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Token response got success with status code {int}")
    public void response_got_success_with_status_code(Object statusCode) {
        assertEquals(statusCode, res.getStatusCode());
    }

    @Then("Fetch access token after deserialize response with Pojo class")
    public void fetch_access_token_after_deserialize_response_with_pojo_class() {
        if (res.getStatusCode() == 200) {
            successAuthRes = res.as(ResSuccessAuthorization.class);
            token = successAuthRes.getAccess_token();
        } else {
            failedAuthRes = res.as(ResFailedoAuth.class);
        }
    }
}
