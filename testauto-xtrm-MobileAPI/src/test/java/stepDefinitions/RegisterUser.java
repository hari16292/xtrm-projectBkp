package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang.RandomStringUtils;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class RegisterUser extends Utility {
    String token, accountNumber;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.registerUser.ResObject successRegisterUser;
    pojoClasses.registerUser.ResFailedObject failedRegisterUser;

    @Given("Following valid and invalid data in request body {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void following_valid_and_invalid_data_in_request_body(String PAT, String firstName, String lastName, String email, String password, String mobileNo, String countryCode, String callingCode, String dob, String deviceIP, String deviceBrowser, String deviceType, String country, String state, String zipcode) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        switch (email) {
            case "Valid Email" :
                email = RandomStringUtils.randomAlphabetic(5)+"+autotest@mailinator.com";
                break;
            case "Invalid Email" :
                email = "123456";
                break;
            case "Empty Email" :
                email = "";
                break;
        }
        req = postGiven(token).body(payloadRequest.RegisterUser.request(accountNumber, firstName, lastName, email, password, mobileNo, countryCode, callingCode, dob, deviceIP, deviceBrowser, deviceType, country, state, zipcode));
    }

    @When("{string} the request to {string} UserRegistration API method")
    public void the_request_to_user_registration_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("UserRegistration response got success with status code {int}")
    public void user_registration_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired RegisterUser Pojo class")
    public void deserialize_the_response_with_desired_register_user_pojo_class() {
        if (res.statusCode() == 200) {
            successRegisterUser = res.as(pojoClasses.registerUser.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else if (res.statusCode() == 400) {
            failedRegisterUser = res.as(pojoClasses.registerUser.ResFailedObject.class);
        }
    }

}
