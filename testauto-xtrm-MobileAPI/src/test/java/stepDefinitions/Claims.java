package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.GetClaimDetails;
import payloadRequest.GetClaims;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class Claims extends Utility {
    String token, accountNumber;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.getClaims.ResObject successGetClaims;
    pojoClasses.getClaimDetails.ResObject successGetClaimDetails;


    @Given("Valid request for GetClaims API call {string} {string} {string} {string}")
    public void valid_request_for_get_claims_api_call(String PAT, String claimStatus, String currentPage, String itemsPerPage) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        req = postGiven(token).body(GetClaims.request(accountNumber, claimStatus, currentPage, itemsPerPage));
    }

    @When("{string} the request to {string} Claims API method")
    public void the_request_to_claims_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Claims response got success with status code {int}")
    public void claims_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired GetClaims Pojo class")
    public void deserialize_the_response_with_desired_get_claims_pojo_class() {
        if (res.statusCode() == 200) {
            successGetClaims = res.as(pojoClasses.getClaims.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Valid request for GetClaimDetails API call {string} {string}")
    public void valid_request_for_get_claim_details_api_call(String PAT, String claimId) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        accountNumber = getPAT(PAT);
        String cID = "";
        if(claimId.equals("Valid Id")) {
            valid_request_for_get_claims_api_call(accountNumber, "All", "1", "2");
            the_request_to_claims_api_method("Post", "getClaims");
            deserialize_the_response_with_desired_get_claims_pojo_class();
            cID = successGetClaims.getData().get(0).getClaim_id();
        } else if(claimId.equals("Invalid Id")) {
            cID = "abcd12";
        } else if(claimId.equals("Empty Id")) {
            cID = "";
        }
        req = postGiven(token).body(GetClaimDetails.request(accountNumber, cID));

    }

    @Then("deserialize the response with desired GetClaimDetails Pojo class")
    public void deserialize_the_response_with_desired_get_claim_details_pojo_class() {
        if (res.statusCode() == 200) {
            successGetClaimDetails = res.as(pojoClasses.getClaimDetails.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }
}
