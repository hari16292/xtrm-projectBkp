package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import payloadRequest.*;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Profile extends Utility {
    String token, accountNumber, updatedTaxNumber, updatedAddress2, compAccNo;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.getUserPersonal.ResObject successGetUserPersonal;
    pojoClasses.getUserContact.ResObject successGetUserContact;
    pojoClasses.getUserEmployer.ResObject successGetUserEmployer;
    pojoClasses.getEmployers.ResObject successGetEmployers;
    pojoClasses.updateUserPersonalProfile.ResObject successUpdateUserPersonalProfile;


    @Given("get access token")
    public void get_access_token() throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
    }

    @Given("Provide GetUserPersonal valid and invalid request {string}")
    public void provide_get_user_personal_valid_and_invalid_request(String PAT) throws Exception {
        accountNumber = getPAT(PAT);
        req = postGiven(token).body(GetUserPersonal.request(accountNumber));
    }

    @When("{string} the request to {string} Profile API method")
    public void the_request_to_profile_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Profile response got success with status code {int}")
    public void profile_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired GetUserPersonal pojo class")
    public void deserialize_the_response_with_desired_get_user_personal_pojo_class() {
        if (res.statusCode() == 200) {
            successGetUserPersonal = res.as(pojoClasses.getUserPersonal.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Then("deserialize the response with desired GetUserContact pojo class")
    public void deserialize_the_response_with_desired_get_user_contact_pojo_class() {
        if (res.statusCode() == 200) {
            successGetUserContact = res.as(pojoClasses.getUserContact.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Then("deserialize the response with desired GetUserEmployer pojo class")
    public void deserialize_the_response_with_desired_get_user_employer_pojo_class() {
        if (res.statusCode() == 200) {
            successGetUserEmployer = res.as(pojoClasses.getUserEmployer.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Provide GetEmployers valid and invalid request {string}")
    public void provide_get_employers_valid_and_invalid_request(String searchKey) throws Exception {
        req = postGiven(token).body(GetEmployers.request(searchKey));
    }

    @Then("deserialize the response with desired GetEmployers pojo class")
    public void deserialize_the_response_with_desired_get_employers_pojo_class() {
        if (res.statusCode() == 200) {
            successGetEmployers = res.as(pojoClasses.getEmployers.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Then("validate company details available for search details {int}")
    public void validate_company_details_available_for_search_details(int statusCode) {
        if (statusCode == 200) {
            Assert.assertTrue(successGetEmployers.getData().size() >= 1);
        }
    }

    @Given("Provide UpdateUserPersonalProfile valid and invalid request {string} {string} {string} {string} {string}")
    public void provide_update_user_personal_profile_valid_and_invalid_request(String PAT, String firstName, String lastName, String taxNumber, String dob) throws Exception {
        accountNumber = getPAT(PAT);
        switch (taxNumber) {
            case "Valid Number":
                updatedTaxNumber = RandomStringUtils.randomAlphanumeric(7);
                break;
        }
        req = postGiven(token).body(UpdateUserPersonalProfile.request(accountNumber, firstName, lastName, updatedTaxNumber, dob));
    }

    @Then("deserialize the response with desired UpdateUserPersonalProfile pojo class")
    public void deserialize_the_response_with_desired_update_user_personal_profile_pojo_class() {
        if (res.statusCode() == 200) {
            successUpdateUserPersonalProfile = res.as(pojoClasses.updateUserPersonalProfile.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }


    @Then("Verify employer has deleted from the user")
    public void verify_employer_has_deleted_from_the_user() throws Exception {
        if(res.statusCode() == 200) {
            provide_get_user_personal_valid_and_invalid_request("Global Value");
            the_request_to_profile_api_method("post", "getUserEmployer");
            deserialize_the_response_with_desired_get_user_employer_pojo_class();
            assertTrue(successGetUserEmployer.getData() == null);
        }
    }


    @Then("Validate updated data available in GetUserPersonal")
    public void validate_updated_data_available_in_get_user_personal() throws Exception {
        if (res.statusCode() == 200) {
            provide_get_user_personal_valid_and_invalid_request("Global Value");
            the_request_to_profile_api_method("post", "getUserPersonal");
            deserialize_the_response_with_desired_get_user_personal_pojo_class();
            assertTrue(successGetUserPersonal.getData().getSsn_Tax_ID().equals(updatedTaxNumber));
        }
    }


    @Given("Provide UpdateUserContactInfo valid and invalid request {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provide_update_user_contact_info_valid_and_invalid_request(String PAT, String addr1, String addr2, String aptNo, String city, String country, String state, String zipcode) throws Exception {
        accountNumber = getPAT(PAT);
        switch (addr2) {
            case "Valid Address":
                updatedAddress2 = RandomStringUtils.randomAlphabetic(25);
                break;
        }
        req = postGiven(token).body(UpdateUserContactInfo.request(accountNumber, addr1, updatedAddress2, aptNo, city, country, state, zipcode));
    }

    @Then("Validate updated data available in GetUserContact")
    public void validate_updated_data_available_in_get_user_contact() throws Exception {
        if (res.statusCode() == 200) {
            provide_get_user_personal_valid_and_invalid_request("Global Value");
            the_request_to_profile_api_method("post", "getUserContact");
            deserialize_the_response_with_desired_get_user_contact_pojo_class();
            assertTrue(successGetUserContact.getData().getAddress2().equals(updatedAddress2));

        }
    }


    @Given("Provide DeleteUserEmployerInfo valid and invalid request {string} {string}")
    public void provide_delete_user_employer_info_valid_and_invalid_request(String PAT, String companyAccount) throws Exception {
        accountNumber = getPAT(PAT);
        String userCompany = "";
        switch (companyAccount) {
            case "Valid Company":
                provide_get_user_personal_valid_and_invalid_request("Global Value");
                the_request_to_profile_api_method("post", "getUserEmployer");
                deserialize_the_response_with_desired_get_user_employer_pojo_class();
                if (successGetUserEmployer.getData() == null) {
                    provide_save_user_employer_info_valid_and_invalid_request("Global Value", "Valid Company", "Engineer");
                    the_request_to_profile_api_method("post", "saveUserEmployerInfo");
                    provide_get_user_personal_valid_and_invalid_request("Global Value");
                    the_request_to_profile_api_method("post", "getUserEmployer");
                    deserialize_the_response_with_desired_get_user_employer_pojo_class();
                }
                userCompany = successGetUserEmployer.getData().getCompany_account_number();
                break;
            case "Invalid Company":
                userCompany = "SPN0000000";
                break;
            case "Empty Value":
                userCompany = "";
                break;
        }
        req = postGiven(token).body(DeleteUserEmployerInfo.request(accountNumber, userCompany));
    }


    @Given("Provide SaveUserEmployerInfo valid and invalid request {string} {string} {string}")
    public void provide_save_user_employer_info_valid_and_invalid_request(String PAT, String companyAccount, String designation) throws Exception {
        accountNumber = getPAT(PAT);
        switch (companyAccount) {
            case "Valid Company":
                provide_get_employers_valid_and_invalid_request("TestAPICompany");
                the_request_to_profile_api_method("post", "getEmployers");
                deserialize_the_response_with_desired_get_employers_pojo_class();
                compAccNo = successGetEmployers.getData().get(0).getAccount_number();
        }
        req = postGiven(token).body(SaveUserEmployerInfo.request(accountNumber, compAccNo, designation));
    }

    @Then("deserialize the response with desired SaveUserEmployerInfo pojo class")
    public void deserialize_the_response_with_desired_save_user_employer_info_pojo_class() {
        if (res.statusCode() == 200) {
            successUpdateUserPersonalProfile = res.as(pojoClasses.updateUserPersonalProfile.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Then("verify added employer details with GetUserEmployer")
    public void verify_added_employer_details_with_get_user_employer() throws Exception {
        provide_get_user_personal_valid_and_invalid_request("Global Value");
        the_request_to_profile_api_method("post", "getUserEmployer");
        deserialize_the_response_with_desired_get_user_employer_pojo_class();
        assertTrue(successGetUserEmployer.getData().getCompany_account_number().equals(compAccNo));
    }


}
