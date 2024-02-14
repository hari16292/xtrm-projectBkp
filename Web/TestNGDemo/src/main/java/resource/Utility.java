package resource;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {

    WebDriver driver;
    public WebDriver initializeBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public void getScreenshot() throws IOException {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        String strDate = dateFormat.format(d);
        TakesScreenshot srcShot = (TakesScreenshot) driver;
        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcFile, new File(System.getProperty("user.dir") + "/screenshots/" + strDate + ".png"));
    }
}
