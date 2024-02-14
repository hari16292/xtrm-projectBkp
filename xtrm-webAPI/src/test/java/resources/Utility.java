package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import payloadRequest.Auth;
import pojoClasses.token.ResSuccessAuthorization;
import stepDefinitions.AccessToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Utility {
    PrintStream log;
    public static RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    static Properties prop = new Properties();
    static FileInputStream fis;
    public static String env;

    static {
        try {
            env = getGlobalValue("environment");
            env = env.replaceAll("^\"|\"$", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestSpecification reqSpec() throws Exception {
        if (reqSpec == null) {
            log = new PrintStream(Files.newOutputStream(Paths.get("logs.txt")));
            if(env.equals("Development")) {
                reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("devBaseURL")).setRelaxedHTTPSValidation()
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
            } else if(env.equals("SBox")) {
                reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("sboxBaseURL")).setRelaxedHTTPSValidation()
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
            }

        }
        return reqSpec;
    }

    public ResponseSpecification responseSpecification() {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return resSpec;
    }

    public static String getGlobalValue(String key) throws IOException {
        if (key.equals("environment")) {
            fis = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/java/resources/environment.properties");
        } else {
            fis = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/java/resources/global.properties");
        }
        prop.load(fis);
        String value = prop.getProperty(key);
        return value;
    }

    public Headers postHeaders(String token) {
        Header jsonHeader = new Header("Content-Type", "application/json");
        Header tokenHeader = new Header("Authorization", "Bearer " + token);
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(jsonHeader);
        headerList.add(tokenHeader);
        Headers h1 = new Headers(headerList);
        return h1;
    }

    public void getToken() throws Exception {
        String clientID = getClientID("Global Value");
        String clientSecret = getClientSecret("Global Value");
        ResSuccessAuthorization res = given().spec(reqSpec()).header("Content-Type", "application/x-www-form-urlencoded")
                .body(Auth.request(clientID, clientSecret))
                .when().post(APIPath.valueOf("accessToken").getAPIPath()).as(ResSuccessAuthorization.class);
        AccessToken.token = res.getAccess_token();
    }

    public RequestSpecification postGiven(String token) throws Exception {
        return given().spec(reqSpec()).headers(postHeaders(token));
    }

    public String getEmailOTP(String email) {
        String OTP = null;
        ChromeOptions option = new ChromeOptions();
        option.addArguments("headless");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(option);
        driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> sample = driver.findElements(By.xpath("//table[@class='table-striped jambo_table']/tbody/tr/td"));
        for (WebElement data : sample) {
            if (data.getText().equals("Your fund transfer verification code")) {
                data.click();
                driver.switchTo().frame(0);
                OTP = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[contains(text(), 'Your verification code is:')]/span")).getText();
                break;
            }
        }
        return OTP;
    }

    public String getIssuerAccountNumber(String accountNumber) throws IOException {
        switch (accountNumber) {
            case "Global Value":
                if (env.equals("Development"))
                    accountNumber = getGlobalValue("devIssuerAccountNumber");
                else if (env.equals("SBox"))
                    accountNumber = getGlobalValue("sboxIssuerAccountNumber");
                break;
            case "Empty Value":
                accountNumber = "";
                break;
            case "Invalid Value":
                accountNumber = "PAT2190548";
                break;
        }
        return accountNumber;
    }

    public String getClientID(String clientID) throws IOException {
        switch (clientID) {
            case "Global Value":
                if (env.equals("Development"))
                    clientID = getGlobalValue("devUserClientId");
                else if (env.equals("SBox"))
                    clientID = getGlobalValue("sboxUserClientId");
                break;
            case "Invalid Value":
                clientID = "2110039_API_User";
                break;
            case "Empty Value":
                clientID = "";
                break;
        }
        return clientID;
    }

    public String getClientSecret(String clientSecretKey) throws IOException {
        switch (clientSecretKey) {
            case "Global Value":
                if (env.equals("Development"))
                    clientSecretKey = getGlobalValue("devUserClientSecret");
                else if (env.equals("SBox"))
                    clientSecretKey = getGlobalValue("sboxUserClientSecret");
                break;
            case "Invalid Value":
                clientSecretKey = "vo8cs6X4vYoWB4HaUhAfSba9qltSAOcoN4w9zN/CE=";
                break;
            case "Empty Value":
                clientSecretKey = "";
                break;
        }
        return clientSecretKey;
    }

    public String getToAccountPAT(String toAccount) throws IOException {
        switch (toAccount) {
            case "Valid PAT":
                if (env.equals("Development"))
                    toAccount = getGlobalValue("devToUser");
                else if (env.equals("SBox"))
                    toAccount = getGlobalValue("sboxToUser");
                break;
            case "Valid SPN":
                if (env.equals("Development"))
                    toAccount = getGlobalValue("devToCompany");
                else if (env.equals("SBox"))
                    toAccount = getGlobalValue("sboxToCompany");
                break;
            case "Invalid PAT":
                toAccount = "PAT2036512";
                break;
            case "Empty PAT":
                toAccount = "";
                break;
        }
        return toAccount;
    }
}
