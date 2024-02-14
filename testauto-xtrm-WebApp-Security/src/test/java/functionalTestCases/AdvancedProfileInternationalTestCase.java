package functionalTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.advancedProfilePages.Step1Page;
import pageObject.advancedProfilePages.Step2Page;
import pageObject.advancedProfilePages.Step3Page;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class AdvancedProfileInternationalTestCase extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    Step1Page sp1;
    Step2Page sp2;
    Step3Page sp3;

    @BeforeSuite(groups = {"Profile", "AdvancedProfile"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
        sp1 = new Step1Page(driver);
        sp2 = new Step2Page(driver);
        sp3 = new Step3Page(driver);
    }

    @AfterSuite(groups = {"Profile", "AdvancedProfile"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"Profile", "AdvancedProfile"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getProfileMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("FullProfile"), "Issue in Profile menu click");
    }

    @Test(groups = {"Profile", "AdvancedProfile"}, dataProvider = "tc02_data", retryAnalyzer = RetryAnalyzer.class)
    public void tc02_advancedProfileStep1(String country, String paymentType, String moreInfo) throws Exception {
        tc01_homePage();
        waitForElementClickable(sp1.getAdvancedProfileTabOption(), 5);
        sp1.getAdvancedProfileTabOption().click();
        waitUntilLoaderDisable();
        waitForElementClickable(sp1.getAdvancedMoreInfoLink(), 5);
        Assert.assertEquals(sp1.getPageNumOne().getAttribute("class"), "active", "Page number 1 not highlighted");
        if (moreInfo.equals("Yes"))
            moreInfoCheck();
        else Thread.sleep(1500); //Payment type loading issue
        Select countryDD = new Select(sp1.getCountryDD());
        countryDD.selectByValue(country);
        if (country.equals("US"))
            waitForElementVisible(sp1.getPaymentTypeVisibleText(), 5);
        Select paymentTypeDD = new Select(sp1.getPaymentTypeDD());
        paymentTypeDD.selectByValue(paymentType);
        if (paymentType.equals("1"))
            Assert.assertTrue(sp1.getUSPagesCount().isDisplayed(), "US Advance Profile pages not showing");
        waitForElementClickable(sp1.getNextBtn(), 5);
        sp1.getNextBtn().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("EditCompanyAdvancedContact"), "Redirected to wrong URL - " + driver.getCurrentUrl());
        waitForElementClickable(sp1.getAdvancedMoreInfoLink(), 5);
        if (paymentType.equals("1")) {
            Assert.assertTrue(sp1.getUSPagesCount().isDisplayed(), "US Advance Profile pages not showing");
            Assert.assertEquals(sp1.getPageNumTwo().getAttribute("class"), "active", "Page number 2 not highlighted");
        }
    }

    @DataProvider
    public Object[][] tc02_data() {
        return new Object[][]{
                /*{"country", "paymentType", "moreInfo"}*/
                {"US", "2", "Yes"},
        };
    }

    /*****************************************************************************************************************/

    public void moreInfoCheck() {
        waitForElementClickable(sp1.getAdvancedMoreInfoLink(), 5);
        sp1.getAdvancedMoreInfoLink().click();
        driver.switchTo().window(windowHandle(driver, "Child"));
        Assert.assertTrue(driver.getTitle().contains("COMPLIANCE001"), "Redirect to more info page failed");
        driver.close();
        driver.switchTo().window(windowHandle(driver, "Parent"));
        Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Advanced Profile page");
        waitForElementClickable(sp1.getAdvancedMoreInfo1Link(), 5);
        sp1.getAdvancedMoreInfo1Link().click();
        driver.switchTo().window(windowHandle(driver, "Child"));
        Assert.assertTrue(driver.getTitle().contains("COMPLIANCE004"), "Redirect to more info page failed");
        driver.close();
        driver.switchTo().window(windowHandle(driver, "Parent"));
        Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Advanced Profile page");
        waitForElementClickable(sp1.getAdvancedServicesLink(), 5);
        sp1.getAdvancedServicesLink().click();
        driver.switchTo().window(windowHandle(driver, "Child"));
        Assert.assertTrue(driver.getTitle().contains("COMPLIANCE004"), "Redirect to more info page failed");
        driver.close();
        driver.switchTo().window(windowHandle(driver, "Parent"));
        Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Advanced Profile page");
        waitForElementClickable(sp1.getAdvancedMoreInfo2Link(), 5);
        sp1.getAdvancedMoreInfo2Link().click();
        driver.switchTo().window(windowHandle(driver, "Child"));
        Assert.assertTrue(driver.getTitle().contains("COMPLIANCE004"), "Redirect to more info page failed");
        driver.close();
        driver.switchTo().window(windowHandle(driver, "Parent"));
        Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Advanced Profile page");
        waitForElementClickable(sp1.getAdvancedMoreInfo3Link(), 5);
        sp1.getAdvancedMoreInfo3Link().click();
        driver.switchTo().window(windowHandle(driver, "Child"));
        Assert.assertTrue(driver.getTitle().contains("COMPLIANCE004"), "Redirect to more info page failed");
        driver.close();
        driver.switchTo().window(windowHandle(driver, "Parent"));
        Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Advanced Profile page");
    }
}