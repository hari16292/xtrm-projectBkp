package resource;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.zaproxy.clientapi.core.ClientApi;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.MailinatorPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseClass {
    private static String OTP = null;
    public static WebDriver driver = null;
    WebDriverWait wait;
    protected static Properties prop = new Properties();
    static FileInputStream in;
    private static String parentTab = null;
    public static boolean mailVerificationSuccess = true;
    public static String mailVerificationErrMsg = "", env;
    static CommonMenu cm;
    String zap_proxy_address = "localhost";
    int zap_proxy_port = 8080;
    String zap_API_key = "r2keo0h8ljqittjbnvvlhggvin";
    static ClientApi zapAPI = null;
    ChromeOptions chromeOption;


    static {
        try {
            if (System.getProperty("environment") != null)
                setProperty("environment", System.getProperty("environment"), "environment");
            else setProperty("environment", "Development", "environment"); //value = SBox/Development
            env = loadPropertiesFile("environment");
            env = env.replaceAll("^\"|\"$", "");
            closePropertiesFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (System.getProperty("accountType") != null) {
                setProperty("type", System.getProperty("accountType"), "environment");
            } else setProperty("type", "company", "environment");  //value = user/company
        } catch (Exception ignored) {
        }
        try {
            if (System.getProperty("browserType") != null) {
                setProperty("browserType", System.getProperty("browserType"), "environment");
            } else setProperty("browserType", "Headed", "environment"); //value = Headless/Headed
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            if (System.getProperty("browser") != null) {
                setProperty("browser", System.getProperty("browser"), "environment");
            } else setProperty("browser", "Edge", "environment"); //value = Chrome/Firefox/Edge
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            setProperty("browser", "Chrome", "environment");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*try {
            if(System.getProperty("tcType") != null) {
                setProperty("tcType", System.getProperty("tcType"), "environment");
            } else setProperty("tcType", "All", "environment");
        }catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static void setProperty(String key, String value, String fileName) throws IOException {
        prop.put(key, value);
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/main/java/resource/" + fileName + ".properties");
        prop.store(fos, "");
        fos.close();
    }

    public void handleTaxInfoPopup() {
        try {
            waitForElementClickable(cm.getTaxInfoCloseBtn(), 5);
            Thread.sleep(500); //Unable to handle Tax Information popup in continuous run without timeout
            if (getPropertyValue("type", "environment").equalsIgnoreCase("user") && cm.getTaxInfoPopup().isDisplayed())
                cm.getTaxInfoCloseBtn().click();
        } catch (Exception ignored) {
        }
    }

    public String getPropertyValue(String key, String fileName) throws Exception {
        loadPropertiesFile(fileName);
        String value = prop.getProperty(key);
        closePropertiesFile();
        return value;
    }

    public WebDriver initializeBrowser() throws Exception {
        if (driver == null) {
            String browser = getPropertyValue("browser", "environment");
            String browserType = getPropertyValue("browserType", "environment");
            if (browser.equalsIgnoreCase("Chrome")) {
                chromeOption = new ChromeOptions();
                try {
                    if (browserType.equals("Headless")) {
                        HashMap<String, Object> chromePrefs = new HashMap<>();
                        chromePrefs.put("download.default_directory", System.getProperty("user.home") + "\\Downloads");
                        chromeOption.addArguments("--headless", "--window-size=1920,1200");
                        chromeOption.setExperimentalOption("prefs", chromePrefs);
                    }
                    zapReport();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //WebDriver Manager configuration
                /*WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(option);
                chromeOption.addArguments("headless", "--window-size=1920,1200");*/
                //driver = WebDriverManager.chromedriver().capabilities(chromeOption).create();

                //Selenium Manager Configuration
                driver = new ChromeDriver(chromeOption);
            } else if (browser.equalsIgnoreCase("Firefox")) {
                FirefoxOptions ffOption = new FirefoxOptions();
                try {
                    if (browserType.equals("Headless")) {
                        ffOption.setHeadless(true);
                        FirefoxProfile fp = new FirefoxProfile();
                        fp.setPreference("browser.download.folderList", 2);
                        fp.setPreference("browser.download.dir", System.getProperty("user.home") + "\\Downloads");
                        ffOption.setProfile(fp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //WebDriver Manager configuration
                //driver = WebDriverManager.firefoxdriver().capabilities(ffOption).create();

                //Selenium Manager Configuration
                driver = new FirefoxDriver(ffOption);
            } else if (browser.equalsIgnoreCase("Edge")) {
                EdgeOptions eOption = new EdgeOptions();
                try {
                    if (browserType.equals("Headless")) {
                        HashMap<String, Object> edgePrefs = new HashMap<>();
                        edgePrefs.put("download.default_directory", System.getProperty("user.home") + "\\Downloads");
                        eOption.addArguments("headless", "--window-size=1920,1200");
                        eOption.addArguments("user-agent=Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36");
                        eOption.setExperimentalOption("prefs", edgePrefs);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //WebDriver Manager configuration
                //driver = WebDriverManager.edgedriver().capabilities(eOption).create();

                //Selenium Manager Configuration
                driver = new EdgeDriver(eOption);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            cm = new CommonMenu(driver);
        }
        return driver;
    }

    public void zapReport() {
        String proxyServerURL = zap_proxy_address + ":" + zap_proxy_port;
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyServerURL);
        proxy.setSslProxy(proxyServerURL);
        chromeOption.setProxy(proxy);
        chromeOption.setAcceptInsecureCerts(true);
        chromeOption.addArguments("user-agent=Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36");
        zapAPI = new ClientApi(zap_proxy_address, zap_proxy_port, zap_API_key);
    }

    public String getRecipientEmail(String recipientType) throws Exception {
        String mailId = null;
        try {
            if (System.getProperty("environment").equals("SBox")) {
                switch (recipientType) {
                    case "Personal":
                        mailId = getPropertyValue("sboxRecipientUserEmail", "config");
                        break;
                    case "Company":
                        mailId = getPropertyValue("sboxRecipientCompanyEmail", "config");
                        break;
                    case "CompanyName":
                        mailId = getPropertyValue("sboxRecipientCompanyName", "config");
                        break;
                    case "Employee":
                        mailId = getPropertyValue("sboxRecipientCompanyEmployeeEmail", "config");
                        break;
                }
            } else {
                switch (recipientType) {
                    case "Personal":
                        mailId = getPropertyValue("devRecipientUserEmail", "config");
                        break;
                    case "Company":
                        mailId = getPropertyValue("devRecipientCompanyEmail", "config");
                        break;
                    case "CompanyName":
                        mailId = getPropertyValue("devRecipientCompanyName", "config");
                        break;
                    case "Employee":
                        mailId = getPropertyValue("devRecipientCompanyEmployeeEmail", "config");
                        break;
                }
            }
        } catch (Exception e) {
            switch (recipientType) {
                case "Personal":
                    mailId = getPropertyValue("devRecipientUserEmail", "config");
                    break;
                case "Company":
                    mailId = getPropertyValue("devRecipientCompanyEmail", "config");
                    break;
                case "CompanyName":
                    mailId = getPropertyValue("devRecipientCompanyName", "config");
                    break;
                case "Employee":
                    mailId = getPropertyValue("devRecipientCompanyEmployeeEmail", "config");
                    break;
            }
        }
        return mailId;
    }

    public String getLogin() throws Exception {
        String loginID = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            if (getPropertyValue("type", "environment").equalsIgnoreCase("user")) {
                loginID = getPropertyValue("sboxUserLogin", "config");
            } else if (getPropertyValue("type", "environment").equalsIgnoreCase("company")) {
                loginID = getPropertyValue("sboxCompLogin", "config");
            } else {
                Assert.fail("Type value missing in config");
            }
        } else {
            if (getPropertyValue("type", "environment").equalsIgnoreCase("user")) {
                loginID = getPropertyValue("devUserLogin", "config");
            } else if (getPropertyValue("type", "environment").equalsIgnoreCase("company")) {
                loginID = getPropertyValue("devCompLogin", "config");
            } else {
                Assert.fail("Type value missing in config");
            }
        }
        return loginID;
    }

    public String getPassword() throws Exception {
        String pwd = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            if (getPropertyValue("type", "environment").equalsIgnoreCase("user")) {
                pwd = getPropertyValue("sboxUserPwd", "config");
            } else if (getPropertyValue("type", "environment").equalsIgnoreCase("company")) {
                pwd = getPropertyValue("sboxCompPwd", "config");
            } else {
                Assert.fail("Type value missing in config");
            }
        } else {
            if (getPropertyValue("type", "environment").equalsIgnoreCase("user")) {
                pwd = getPropertyValue("devUserPwd", "config");
            } else if (getPropertyValue("type", "environment").equalsIgnoreCase("company")) {
                pwd = getPropertyValue("devCompPwd", "config");
            } else {
                Assert.fail("Type value missing in config");
            }
        }
        return pwd;
    }

    public String getCACompLogin() throws Exception {
        String loginID = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            loginID = getPropertyValue("sboxCACompLogin", "config");
        } else {
            loginID = getPropertyValue("devCACompLogin", "config");
        }
        return loginID;
    }

    public String getCACompPassword() throws Exception {
        String pwd = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            pwd = getPropertyValue("sboxCACompPwd", "config");
        } else {
            pwd = getPropertyValue("devCACompPwd", "config");
        }
        return pwd;
    }

    public String getCALogin() throws Exception {
        String loginID = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            loginID = getPropertyValue("sboxCALogin", "config");
        } else {
            loginID = getPropertyValue("devCALogin", "config");
        }
        return loginID;
    }

    public String getCAPassword() throws Exception {
        String pwd = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            pwd = getPropertyValue("sboxCAPwd", "config");
        } else {
            pwd = getPropertyValue("devCAPwd", "config");
        }
        return pwd;
    }

    public String getCAManagerLogin() throws Exception {
        String loginID = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            loginID = getPropertyValue("sboxCAManagerLogin", "config");
        } else {
            loginID = getPropertyValue("devCAManagerLogin", "config");
        }
        return loginID;
    }

    public String getCAManagerPassword() throws Exception {
        String pwd = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            pwd = getPropertyValue("sboxCAManagerPwd", "config");
        } else {
            pwd = getPropertyValue("devCAManagerPwd", "config");
        }
        return pwd;
    }

    public String getCAStdLogin() throws Exception {
        String loginID = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            loginID = getPropertyValue("sboxCAStdLogin", "config");
        } else {
            loginID = getPropertyValue("devCAStdLogin", "config");
        }
        return loginID;
    }

    public String getCAStdPassword() throws Exception {
        String pwd = null;
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            pwd = getPropertyValue("sboxCAStdPwd", "config");
        } else {
            pwd = getPropertyValue("devCAStdPwd", "config");
        }
        return pwd;
    }

    public String getEmailOTP(String email, String subject) {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.switchTo().window(windowHandle(driver, "Child"));
        MailinatorPage m = new MailinatorPage(driver);
        List<WebElement> sample = m.getMailList();
        for (WebElement data : sample) {
            if (data.getText().equals(subject)) {
                data.click();
                waitForIFrameAndSwitch(driver, m.getIframe(), 5);
                OTP = m.getMailOTP().getText();
                driver.close();
                break;
            }
        }
        driver.switchTo().window(windowHandle(driver, "Parent"));
        return OTP;
    }

    public void verifyEmailAmount(String email, String subject, String amount) {
        if (email.contains("mailinator")) {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + email);
            driver.switchTo().window(windowHandle(driver, "Child"));
            MailinatorPage m = new MailinatorPage(driver);
            List<WebElement> mailinatorMailList = m.getMailList();
            List<WebElement> mailinatorMailTime = m.getMailTime();
            if (mailinatorMailList.size() > 0) {
                if (mailinatorMailList.get(0).getText().contains(subject) && (mailinatorMailTime.get(0).getText().contains("just now"))) {
                    try {
                        mailinatorMailList.get(0).click();
                        waitForIFrameAndSwitch(driver, m.getIframe(), 5);
                        if (subject.contains("Prepaid Card "))
                            m.getPvvMailAmount().getText().contains(amount);
                        else
                            m.getMailBody().getText().contains(amount);
                    } catch (Exception e) {
                        mailVerificationSuccess = false;
                        mailVerificationErrMsg = "Amount Mismatched in Email Id -" + email + ", Transferred Amount - " + amount + ", Subject - " + subject + '\n';
                    }
                } else {
                    mailVerificationSuccess = false;
                    mailVerificationErrMsg += "Email notification not triggered to " + email + " for amount - " + amount + " and mail subject keyword - " + subject + '\n';
                }
            } else {
                mailVerificationSuccess = false;
                mailVerificationErrMsg += "Email notification not triggered to " + email + " for amount - " + amount + " and mail subject keyword - " + subject + '\n';
            }
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
        } else {
            mailVerificationSuccess = false;
            mailVerificationErrMsg += email + " - Email Id is not from mailinator domain" + '\n';
        }
    }

    public String getScreenshot(String tcName) throws IOException {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        String strDate = dateFormat.format(d);
        TakesScreenshot srcShot = (TakesScreenshot) driver;
        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
        String ssPath = System.getProperty("user.dir") + "/extentReport/" + tcName + "_" + strDate + ".png";
        File destFile = new File(ssPath);
        FileUtils.copyFile(srcFile, destFile);
        return ssPath;
    }

    public String windowHandle(WebDriver driver, String tab) {
        if (tab.equalsIgnoreCase("Child")) {
            Set<String> windows = driver.getWindowHandles();
            Iterator<String> it = windows.iterator();
            parentTab = it.next();
            String childTab = it.next();
            return childTab;
        } else return parentTab;
    }

    public String getWalletID(String currency) throws Exception {
        String id;
        try {
            if (getPropertyValue("environment", "environment").equals("SBox"))
                id = getPropertyValue("sbox" + currency, "config");
            else id = getPropertyValue("dev" + currency, "config");
        } catch (Exception e) {
            id = getPropertyValue("dev" + currency, "config");
        }
        return id;
    }

    public String getProgramID(String Program) throws Exception {
        String id;
        try {
            if (System.getProperty("environment").equals("SBox"))
                id = getPropertyValue("sbox" + Program, "config");
            else id = getPropertyValue("dev" + Program, "config");
        } catch (Exception e) {
            id = getPropertyValue("dev" + Program, "config");
        }
        closePropertiesFile();
        return id;
    }


    public void waitForElementVisible(WebElement element, int time) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementInvisible(WebElement element, int time) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementClickable(WebElement element, int time) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForIFrameAndSwitch(WebDriver driver, WebElement element, int time) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void waitUntilLoaderDisable() {
        try {
            waitForElementInvisible(cm.getLoaderGif(), 20);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public static String loadPropertiesFile(String key) throws Exception {
        //Initiate properties
        prop = new Properties();
        if (key.equalsIgnoreCase("environment")) {
            in = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resource/environment.properties");
        } else if(key.equalsIgnoreCase("config")) {
            in = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resource/config.properties");
        }
        prop.load(in);
        return prop.getProperty(key);
    }

    public static void closePropertiesFile() throws Exception {
        in.close();
    }


}