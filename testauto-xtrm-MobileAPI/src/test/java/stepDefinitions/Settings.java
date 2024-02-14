package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import payloadRequest.ChangePassword;
import payloadRequest.GetOTPForgotPassword;
import payloadRequest.GetUserAccountInfo;
import payloadRequest.ValidateOTPForgotPassword;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Settings extends Utility {

    String token, accountNumber, emailId;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.getUserAccountInfo.ResObject successGetUserAccountInfo;
    pojoClasses.changePassword.ResObject successChangePassword;
    pojoClasses.changePassword.ResFailedObject failedChangePassword;
    pojoClasses.getOTPForgotPassword.ResObject successGetOTPForgotPassword;
    pojoClasses.getOTPForgotPassword.ResFailedObject failedGetOTPForgotPassword;
    pojoClasses.validateOTPForgotPassword.ResObject successValidateOTPForgotPassword;
    pojoClasses.validateOTPForgotPassword.ResFailedObject failedValidateOTPForgotPassword;


    @Given("Valid request for GetUserAccountInfo API call {string}")
    public void valid_request_for_get_user_account_info_api_call(String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        req = postGiven(token).body(GetUserAccountInfo.request(accountNumber));

    }

    @When("{string} the request to {string} Settings API method")
    public void the_request_to_settings_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Settings response got success with status code {int}")
    public void settings_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired GetUserAccountInfo Pojo class")
    public void deserialize_the_response_with_desired_get_user_account_info_pojo_class() {
        if (res.statusCode() == 200) {
            successGetUserAccountInfo = res.as(pojoClasses.getUserAccountInfo.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }


    @Given("Valid request for ChangePassword API call {string} {string} {string}")
    public void valid_request_for_change_password_api_call(String PAT, String currentPassword, String newPassword) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        req = postGiven(token).body(ChangePassword.request(accountNumber, currentPassword, newPassword));
    }

    @Then("deserialize the response with desired ChangePassword Pojo class")
    public void deserialize_the_response_with_desired_change_password_pojo_class() {
        if (res.statusCode() == 200) {
            successChangePassword = res.as(pojoClasses.changePassword.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else if (res.statusCode() == 400) {
            failedChangePassword = res.as(pojoClasses.changePassword.ResFailedObject.class);
        }
    }

    @Given("Valid request for GetOTPForgotPassword API call {string} {string}")
    public void valid_request_for_get_otp_forgot_password_api_call(String PAT, String email) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        req = postGiven(token).body(GetOTPForgotPassword.request(accountNumber, getEmailId(email)));
    }

    @Then("deserialize the response with desired GetOTPForgotPassword Pojo class")
    public void deserialize_the_response_with_desired_get_otp_forgot_password_pojo_class() {
        if (res.statusCode() == 200) {
            successGetOTPForgotPassword = res.as(pojoClasses.getOTPForgotPassword.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else if (res.statusCode() == 400) {
            failedGetOTPForgotPassword = res.as(pojoClasses.getOTPForgotPassword.ResFailedObject.class);
        }
    }

    @Then("Validate OTP triggered successfully {string}")
    public void validate_otp_triggered_successfully(String email) throws IOException {
        if (res.statusCode() == 200) {
            String OTP = getEmailOTP(getEmailId(email), "Your password verification code");
            Assert.assertNotNull(OTP);
        }
    }


    @Given("Valid request for ValidateOTPForgotPassword API call {string} {string}")
    public void valid_request_for_validate_otp_forgot_password_api_call(String PAT, String OTP) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        if (PAT.equals("Global Value") && OTP.equals("Valid OTP")) {
            valid_request_for_get_otp_forgot_password_api_call(accountNumber, "Valid Email");
            the_request_to_settings_api_method("Post", "getOTPForgotPassword");
            deserialize_the_response_with_desired_get_otp_forgot_password_pojo_class();
            OTP = getEmailOTP(getEmailId("Valid Email"), "Your password verification code");
        } else if(OTP.equals("Invalid OTP")) {
            OTP = "456123";
        } else if(OTP.equals("Empty OTP")) {
            OTP = "";
        }
        req = postGiven(token).body(ValidateOTPForgotPassword.request(accountNumber, OTP));
    }

    @Then("deserialize the response with desired ValidateOTPForgotPassword Pojo class")
    public void deserialize_the_response_with_desired_validate_otp_forgot_password_pojo_class() {
        if (res.statusCode() == 200) {
            successValidateOTPForgotPassword = res.as(pojoClasses.validateOTPForgotPassword.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else if (res.statusCode() == 400) {
            failedValidateOTPForgotPassword = res.as(pojoClasses.validateOTPForgotPassword.ResFailedObject.class);
        }
    }

    public String getEmailId(String email) throws IOException {
        switch (email) {
            case "Valid Email":
                if (env.equals("Development")) emailId = getGlobalValue("devEmail");
                else if (env.equals("SBox")) emailId = getGlobalValue("sboxEmail");
                break;
            case "Invalid Email":
                emailId = "abcd123";
                break;
            case "Empty Email":
                emailId = "";
                break;
        }
        return emailId;
    }
}
