package functionalTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.advancedProfilePages.Step1Page;
import pageObject.advancedProfilePages.Step2Page;
import pageObject.advancedProfilePages.Step3Page;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class AdvancedProfileUSATestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    Step1Page sp1;
    Step2Page sp2;
    Step3Page sp3;

    @BeforeSuite(groups = {"Profile", "AdvancedProfile", "CompanyRegression"})
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
                {"US", "1", "Yes"},
        };
    }

    @Test(groups = {"Profile", "AdvancedProfile"}, dataProvider = "tc03_data", retryAnalyzer = RetryAnalyzer.class)
    public void tc03_advancedProfileStep2(String compName, String website, String compType, String compState, String taxNum, String addr1, String addr2, String addr3, String businessCity, String businessState, String zipcode, String txnPurpose, String condition, String moreInfo, String caseType) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc02_advancedProfileStep1("US", "1", "No");
            waitForElementClickable(sp1.getAdvancedMoreInfoLink(), 5);
            if (moreInfo.equals("Yes")) {
                moreInfoCheck();
            }
            waitForElementClickable(sp2.getSaveContinueBtn(), 5);
            sp2.getCompanyNameTxt().clear();
            sp2.getCompanyNameTxt().sendKeys(compName);
            sp2.getWebsiteTxt().clear();
            sp2.getWebsiteTxt().sendKeys(website);
            Select compTypeDD = new Select(sp2.getCompanyTypeDD());
            compTypeDD.selectByValue(compType);
            Select compStateDD = new Select(sp2.getCompanyStateDD());
            compStateDD.selectByValue(compState);
            sp2.getTaxNumberTxt().clear();
            sp2.getTaxNumberTxt().sendKeys(taxNum);
            sp2.getAddress1Txt().clear();
            sp2.getAddress1Txt().sendKeys(addr1);
            sp2.getAddress2Txt().clear();
            sp2.getAddress2Txt().sendKeys(addr2);
            sp2.getAddress3Txt().clear();
            sp2.getAddress3Txt().sendKeys(addr3);
            sp2.getCityTxt().clear();
            sp2.getCityTxt().sendKeys(businessCity);
            Select businessStateDD = new Select(sp2.getBusinessStateDD());
            businessStateDD.selectByValue(businessState);
            sp2.getBusinessZipcodeTxt().clear();
            sp2.getBusinessZipcodeTxt().sendKeys(zipcode);
            sp2.getPurposeOfTxnTxt().clear();
            sp2.getPurposeOfTxnTxt().sendKeys(txnPurpose);
            switch (condition) {
                case "Next":
                    sp2.getSaveContinueBtn().click();
                    waitUntilLoaderDisable();
                    if (caseType.equals("Positive"))
                        Assert.assertEquals(sp1.getPageNumThree().getAttribute("class"), "active", "Page number 3 not highlighted");
                    else Assert.assertEquals(sp2.getErrMsg().getText(), "Please complete missing fields");
                    break;
                case "Previous":
                    sp2.getPreviousBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertEquals(sp1.getPageNumOne().getAttribute("class"), "active", "Page number 1 not highlighted");
                    break;
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{"compName", "website", "compType", "compState", "taxNum", "addr1", "addr2", "addr3", "businessCity", "businessState", "zipcode", "txnPurpose", "condition", "moreInfo", "caseType"}*/
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Next", "Yes", "Positive"},
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Previous", "No", "Positive"},
                {"", "", "0", "0", "", "", "", "", "", "0", "", "", "Next", "No", "Negative"},
                {"", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Next", "No", "Positive"},
                {"Test Company", "", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Next", "No", "Negative"},
                {"Test Company", "www.xtrm.com", "0", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Next", "No", "Negative"},
                {"Test Company", "www.xtrm.com", "Partnership", "0", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Next", "No", "Negative"},
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Next", "No", "Negative"},
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "", "", "Test City", "CA", "4455544", "Test Transaction", "Next", "No", "Positive"},
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "", "CA", "4455544", "Test Transaction", "Next", "No", "Negative"},
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "0", "4455544", "Test Transaction", "Next", "No", "Negative"},
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "", "Test Transaction", "Next", "No", "Negative"},
                {"Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "", "Next", "No", "Negative"},
        };
    }

    @Test(groups = {"Profile", "AdvancedProfile"}, dataProvider = "tc04_data", retryAnalyzer = RetryAnalyzer.class)
    public void tc04_advancedProfileStep3(String jobTitle, String firstName, String lastName, String businessMail, String birthMonth, String birthDate, String birthYear, String citizenship, String signatoryType, String signatoryNumber, String signatoryAddr1, String signatoryCity, String signatoryState, String zipcode, String phone, String condition, String moreInfo, String caseType) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc03_advancedProfileStep2("Test Company", "www.xtrm.com", "Partnership", "CA", "5646321", "Test Addr1", "Test Addr2", "Test Addr3", "Test City", "CA", "4455544", "Test Transaction", "Next", "No", "Positive");
            waitForElementClickable(sp1.getAdvancedMoreInfoLink(), 5);
            if (moreInfo.equals("Yes")) {
                moreInfoCheck();
                waitForElementClickable(sp3.getSignatoryMoreInfoLink(), 5);
                sp3.getSignatoryMoreInfoLink().click();
                driver.switchTo().window(windowHandle(driver, "Child"));
                Assert.assertTrue(driver.getTitle().contains("COMPLIANCE008"), "Redirect to more info page failed");
                driver.close();
                driver.switchTo().window(windowHandle(driver, "Parent"));
                Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Advanced Profile page");
            }
            waitForElementClickable(sp3.getFinishBtn(), 5);
            Select jobTitleDD = new Select(sp3.getJobTitleTxt());
            jobTitleDD.selectByValue(jobTitle);
            sp3.getFirstNameTxt().clear();
            sp3.getFirstNameTxt().sendKeys(firstName);
            sp3.getLastNameTxt().clear();
            sp3.getLastNameTxt().sendKeys(lastName);
            sp3.getEmailTxt().clear();
            sp3.getEmailTxt().sendKeys(businessMail);
            Select monthDD = new Select(sp3.getBirthMonthDD());
            monthDD.selectByValue(birthMonth);
            sp3.getBirthDateTxt().clear();
            if(!birthDate.equals(""))
                sp3.getBirthDateTxt().sendKeys(birthDate);
            sp3.getBirthYearTxt().clear();
            if(!birthYear.equals(""))
                sp3.getBirthYearTxt().sendKeys(birthYear);
            Select citizenshipDD = new Select(sp3.getCitizenshipDD());
            citizenshipDD.selectByValue(citizenship);
            Select signatoryTypeDD = new Select(sp3.getSignatoryTypeDD());
            signatoryTypeDD.selectByValue(signatoryType);
            sp3.getSignatoryNumberTxt().clear();
            sp3.getSignatoryNumberTxt().sendKeys(signatoryNumber);
            sp3.getAddr1Txt().clear();
            sp3.getAddr1Txt().sendKeys(signatoryAddr1);
            sp3.getCityTxt().clear();
            sp3.getCityTxt().sendKeys(signatoryCity);
            Select stateDD = new Select(sp3.getStateDD());
            stateDD.selectByValue(signatoryState);
            sp3.getZipcodeTxt().clear();
            sp3.getZipcodeTxt().sendKeys(zipcode);
            sp3.getPhoneTxt().clear();
            sp3.getPhoneTxt().sendKeys(phone);
            switch (condition) {
                case "Next":
                    sp3.getFinishBtn().click();
                    waitUntilLoaderDisable();
                    if (caseType.equals("Positive")) {
                        waitForElementClickable(sp3.getDoneBtn(), 5);
                        Assert.assertEquals(sp3.getSuccessMsg().getText(), "ADVANCED PROFILE SUBMITTED SUCCESSFULLY");
                        sp3.getDoneBtn().click();
                        waitUntilLoaderDisable();
                        Assert.assertTrue(driver.getCurrentUrl().contains("MemberPaymentOptions"), "Redirected to wrong URL from success page - "+driver.getCurrentUrl());
                    }
                    else Assert.assertEquals(sp2.getErrMsg().getText(), "Please complete missing fields");
                    break;
                case "Previous":
                    sp3.getPreviousBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertEquals(sp1.getPageNumTwo().getAttribute("class"), "active", "Page number 2 not highlighted");
                    break;
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc04_data() {
        return new Object[][] {
                /*{"jobTitle", "firstName", "lastName", "businessMail", "birthMonth", "birthDate", "birthYear", "citizenship", "signatoryType", "signatoryNumber", "signatoryAddr1", "signatoryCity", "signatoryState", "zipcode", "phone", "condition", "moreInfo", "caseType"}*/
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Next", "Yes", "Positive"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Positive"},
                {"0", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "0", "", "", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "0", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "0", "98798654", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "", "Test Address1", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "", "LA", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "0", "TX", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "0", "654658", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "", "98765423155", "Previous", "No", "Negative"},
                {"Owner", "Hari", "Test", "testapicompany1@mailinator.com", "7", "22", "1999", "US", "IdentityCard", "98798654", "Test Address1", "LA", "TX", "654658", "", "Previous", "No", "Negative"},
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
