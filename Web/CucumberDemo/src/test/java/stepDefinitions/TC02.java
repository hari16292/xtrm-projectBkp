package stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.restassured.RestAssured.given;

public class TC02 {

    String res;

    @Given("Validate payload with random number")
    public void validate_payload_with_random_number() {
        res = given().body("{\n" +
                        "    \"id\": " + Math.random() * (999 - 1 + 1) + 1   + ",\n" +
                        "    \"name\": \"Air India\",\n" +
                        "    \"country\": \"India\",\n" +
                        "    \"logo\": \"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Ftopic%2FIndian-Airlines&psig=AOvVaw0KFYEt8&ust=1682478742658000&source=images&cd=vfe&ved=0CBEQj\",\n" +
                        "    \"slogan\": \"From India\",\n" +
                        "    \"head_quaters\": \"TN, India\",\n" +
                        "    \"website\": \"https://www.airindia.in/\",\n" +
                        "    \"established\": \"1990\"\n" +
                        "}").header("Content-Type", "application/json").when().post("https://api.instantwebtools.net/v1/airlines")
                .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Response: ");
        System.out.println(res);

        JsonPath js = new JsonPath(res);
        String id = js.getString("_id");

        System.out.println("id value - "+ id);
    }


    @Given("validate error message")
    public void validate_error_message() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
         driver.get("https://portal.banka.banfico.net/");
         driver.manage().window().maximize();
         //driver.manage().timeouts().getPageLoadTimeout(Duration.ofSeconds());
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[contains(@class, 'MuiButtonBase-root MuiButton-root')]")).click();
    }
}
