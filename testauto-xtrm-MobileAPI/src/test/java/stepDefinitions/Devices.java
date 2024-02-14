package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.GetDevices;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class Devices extends Utility {
    String token, accountNumber;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.getDevices.ResObject successGetDevices;


    @Given("Provide GetDevices valid and invalid request {string}")
    public void provide_get_devices_valid_and_invalid_request(String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        req = postGiven(token).body(GetDevices.request(accountNumber));
    }

    @When("{string} the request to {string} Devices API method")
    public void the_request_to_devices_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Devices response got success with status code {int}")
    public void devices_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired GetDevices pojo class")
    public void deserialize_the_response_with_desired_get_devices_pojo_class() {
        if (res.statusCode() == 200) {
            successGetDevices = res.as(pojoClasses.getDevices.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }


}
