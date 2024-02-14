package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.TC01_Request;
import resources.Utility;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class TC01 extends Utility {

    RequestSpecification req;
    Response res;

    @Given("Validate add new location")
    public void validate_add_new_location() throws Exception {
        req = given().spec(requestSpecification()).header("Content-Type", "application/json")
                .body(TC01_Request.request());
    }
    @When("Post the request to {string}")
    public void post_the_request_to(String endPoint) {
        res = req.when().get(endPoint);
    }
    @Then("status code should be {int}")
    public void status_code_should_be(Object statusCode) {
        assertEquals(statusCode, res.getStatusCode());

    }
    @Then("status code should not be {int}")
    public void status_code_should_not_be(Object statusCode) {
        assertNotEquals(statusCode, res.getStatusCode());
        res.then().assertThat().body("per_page", equalTo("6"));
        String response = res.then().extract().response().asString();
        JsonPath js = new JsonPath(response);
        String value = js.getString("data[0].id");
        System.out.println(value);

    }
}
