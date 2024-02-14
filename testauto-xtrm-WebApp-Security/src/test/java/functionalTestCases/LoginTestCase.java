package functionalTestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.LoginPage;
import pageObject.commonPages.LoginVerificationPage;
import pageObject.commonPages.LogoutPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class LoginTestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    LogoutPage lop;

    public LoginTestCase() {
        cm = new CommonMenu(driver);
    }


    @BeforeSuite(groups = {"Smoke", "Login", "CompanyRegression", "UserRegression", "Security"})
    public void openBrowser() throws Exception {
        driver = BaseClass.driver;
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"Smoke", "Login", "CompanyRegression", "UserRegression", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(dataProvider = "tc01_data", groups = {"Smoke", "Login", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_loginPage(String userName, String password, String caseType, String errorMsg) throws Exception {
        if (getPropertyValue("environment", "environment").equals("SBox")) {
            driver.get(getPropertyValue("sboxURL", "config"));
        } else {
            driver.get(getPropertyValue("devURL", "config"));
        }
        if(driver.getCurrentUrl().contains("SponsorHome")) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250)", "");
            waitForElementClickable(cm.getLogoutMenu(), 5);
            cm.getLogoutMenu().click();
            lop = new LogoutPage(driver);
            waitForElementVisible(lop.getLearnMoreBtn(), 10);
            Assert.assertTrue(driver.getCurrentUrl().contains("Logout"), "Issue in logout");
            if (getPropertyValue("environment", "environment").equals("SBox")) {
                driver.get(getPropertyValue("sboxURL", "config"));
            } else {
                driver.get(getPropertyValue("devURL", "config"));
            }
        }
        if (driver.getCurrentUrl().toLowerCase().contains("elogin")) {
            LoginPage lp = new LoginPage(driver);
            lp.getLoginTxt().clear();
            lp.getLoginTxt().sendKeys(userName);
            lp.getPasswordTxt().clear();
            lp.getPasswordTxt().sendKeys(password);
            lp.getSignInBtn().click();
            cm = new CommonMenu(driver);
            if (caseType.equals("Valid")) {
                waitUntilLoaderDisable();
                Assert.assertFalse(driver.getCurrentUrl().contains("Elogin"), "Still in login page");
                if (driver.getCurrentUrl().contains("LoginVerification")) {
                    LoginVerificationPage lvp = new LoginVerificationPage(driver);
                    waitForElementVisible(lvp.getGoBtn(), 2);
                } else {
                    Assert.assertFalse(driver.getCurrentUrl().contains("LoginVerification"), "Need OTP to Proceed");
                    waitForElementVisible(cm.getCompanyLogo(), 2);
                    if (errorMsg.equals("Logout")) {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("window.scrollBy(0,250)", "");
                        cm.getLogoutMenu().click();
                        LogoutPage lop = new LogoutPage(driver);
                        waitForElementVisible(lop.getLearnMoreBtn(), 10);
                        Assert.assertTrue(driver.getCurrentUrl().contains("Logout"), "Issue in logout");
                    }
                }
            } else if (caseType.equals("Invalid")) {
                //waitUntilLoaderDisable();
                waitForElementVisible(lp.getNotificationMsg(), 2);
                Assert.assertTrue(lp.getNotificationMsg().getText().contains(errorMsg), "Error messages are not matched");
            }
        }
    }

    @DataProvider
    public Object[][] tc01_data() throws Exception {
        return new Object[][]{
                /*{UserName, Password, CaseType, ErrorMessage}*/
                {getLogin(), getPassword(), "Valid", "Logout"},
                /*{"", "", "Invalid", "Enter your email"},
                {"hari@123", "", "Invalid", "Enter the password"},
                {"", "78945", "Invalid", "Enter your email"},
                {"hari@abcd.com", "gdhd", "Invalid", "Your login credentials aren't correct."}*/
        };
    }

    @Test(dataProvider = "tc02_data",groups = {"Smoke", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_verificationPage(String loginAccount) throws Exception {
        switch (loginAccount) {
            case "CACompany":
                tc01_loginPage(getCACompLogin(), getCACompPassword(), "Valid", "");
                break;
            case "Controller Admin":
                tc01_loginPage(getCALogin(), getCAPassword(), "Valid", "");
                break;
            case "Manager Admin":
                tc01_loginPage(getCAManagerLogin(), getCAManagerPassword(), "Valid", "");
                break;
            case "Standard Admin":
                tc01_loginPage(getCAStdLogin(), getCAStdPassword(), "Valid", "");
                break;
            default:
                tc01_loginPage(getLogin(), getPassword(), "Valid", "");
                break;
        }
        if (driver.getCurrentUrl().contains("LoginVerification.aspx")) {
            LoginVerificationPage lvp = new LoginVerificationPage(driver);
            lvp.getVerificationDD().click();
            lvp.getSelectEmail().click();
            lvp.getGoBtn().click();
            String OTP = null;
            switch (loginAccount) {
                case "CACompany":
                    OTP = getEmailOTP(getCACompLogin(), "Your login verification code");
                    break;
                case "Controller Admin":
                    OTP = getEmailOTP(getCALogin(), "Your login verification code");
                    break;
                case "Manager Admin":
                    OTP = getEmailOTP(getCAManagerLogin(), "Your login verification code");
                    break;
                case "Standard Admin":
                    OTP = getEmailOTP(getCAStdLogin(), "Your login verification code");
                    break;
                default:
                    OTP = getEmailOTP(getLogin(), "Your login verification code");
                    break;
            }
            lvp.getOtpTxt().sendKeys(OTP);
            lvp.getVerifyBtn().click();
        }
        cm = new CommonMenu(driver);
        if(getPropertyValue("type","environment").equalsIgnoreCase("user"))
            handleTaxInfoPopup();
        waitForElementClickable(cm.getCompanyLogo(), 5);

    }

    @DataProvider
    public Object[][] tc02_data() {
        return new Object[][] {
                {"defaultAccount"}
        };
    }
}
