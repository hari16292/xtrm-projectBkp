package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import resource.Utility;

import java.io.IOException;

public class TC01 extends Utility {
    WebDriver driver;

    @Test
    public void test01() throws IOException {
        driver = initializeBrowser();
        driver.get("https://www.mailinator.com/");
        getScreenshot();
    }

}
