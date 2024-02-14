package functionalTestCases;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.MassPaymentPage;
import pageObject.SendPage;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class MassPaymentTestCase extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;

    MassPaymentPage msp;

    public MassPaymentTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"CompanyRegression", "MassPayment", "Security"})
    public void openBrowser() throws Exception {
        driver = BaseClass.driver;
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"CompanyRegression", "MassPayment", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"MassPayment", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getSendMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("Send"), "Issue in Send menu click");
    }

    @Test(groups = {"MassPayment", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_sendPage() throws Exception {
        tc01_homePage();
        SendPage sp = new SendPage(driver);
        waitForElementVisible(sp.getSendBtn(), 5);
        sp.getMassPaymentGoBtn().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("RewardSubmission"), "Issue in Mass Payment click");
    }

    @Test(groups = {"MassPayment", "CompanyRegression", "Security"}, dataProvider = "tc03_data", retryAnalyzer = RetryAnalyzer.class)
    public void tc03_uploadFile(String currency, String caseType, String invoice) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc02_sendPage();
            msp = new MassPaymentPage(driver);
            waitForElementClickable(msp.getMassPaymentFileBtn(), 5);
            msp.getMassPaymentFileBtn().click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().contains("CompanyMassPayment"), "Issue in Upload Mass Payment File button click");
            waitForElementClickable(msp.getUploadBtn(), 5);
            if (caseType.equals("Positive")) {
                msp.getUploadBtn().sendKeys(System.getProperty("user.dir") + "/src/main/java/resource/MassPayUploadFile.xlsx");
                waitForElementClickable(msp.getDeleteFileOption(), 5);
                Select walletDD = new Select(msp.getWalletDD());
                walletDD.selectByValue(getWalletID(currency));
                String purchaseOrder = RandomStringUtils.randomAlphanumeric(8);
                msp.getPurchaseOrderTxt().sendKeys(purchaseOrder);
                if (invoice.equals("No"))
                    msp.getInvoiceChkBox().click();
                msp.getAddInfoTxt().sendKeys("Selenium Auto Test Mass Payment, order ID - " + purchaseOrder);
                msp.getSubmitBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("Rewardsubmission"), "Redirected to wrong URL - " + driver.getCurrentUrl());
                Assert.assertTrue(msp.getConfirmMsg().getText().contains("Data successfully submitted"), "Uploaded file not processed");
                waitForElementClickable(msp.getDateOption(), 5);
                msp.getDateOption().click();
                waitForElementClickable(msp.getLast7DaysOption(), 5);
                msp.getLast7DaysOption().click();
                waitUntilLoaderDisable();
                waitForElementClickable(msp.getDetailsOption().get(0), 5);
                msp.getDetailsOption().get(0).click();
                waitUntilLoaderDisable();
                waitForElementClickable(msp.getDoneBtn(), 5);
                Assert.assertEquals(msp.getDescriptionMsg().getText(), "Selenium Auto Test Mass Payment, order ID - " + purchaseOrder);
                msp.getDoneBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("RewardSubmission"), "Redirected to wrong URL - " + driver.getCurrentUrl());
            } else {
                waitForElementClickable(msp.getSubmitBtn(), 5);
                msp.getSubmitBtn().click();
                waitForElementVisible(msp.getErrMsg(),5);
                Assert.assertEquals(msp.getErrMsg().getText(), "Please select files to submit");
                waitForElementClickable(msp.getUploadBtn(), 5);
                msp.getUploadBtn().sendKeys(System.getProperty("user.dir") + "/src/main/java/resource/MassPayUploadFile.xlsx");
                waitForElementClickable(msp.getSubmitBtn(), 5);
                msp.getSubmitBtn().click();
                waitForElementVisible(msp.getErrMsg(),5);
                Assert.assertEquals(msp.getErrMsg().getText(), "Please select remitting wallet");
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{"currency", "caseType", "invoice"}*/
                {"USD", "Positive", "Yes"},
                //{"USD", "Negative", "Yes"},
        };
    }
}
