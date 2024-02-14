package functionalTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.FundPage;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.TransactionDetailsPage;
import pageObject.commonPages.TransactionsPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class FundTestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;
    FundPage fp;

    int ranAmt;
    CommonMenu cm;

    public FundTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"Fund", "CompanyRegression", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"Fund", "CompanyRegression", "Security"})
    public void closeBrowser() {
        //driver.quit();
    }

    @Test(groups = {"Fund", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getFundMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("fundaccountsteps"), "Issue in Fund menu click");
    }

    @Test(dataProvider = "tc02_data", groups = {"Fund", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_fundWalletStep1(String walletCurrency, String fundType, String amount, boolean invoice, String caseType, String errMsg) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc01_homePage();
            fp = new FundPage(driver);
            Select wallet = new Select(fp.getWalletDD());
            Select fundMethod = new Select(fp.getFundMethodDD());
            wallet.selectByValue(getWalletID(walletCurrency));
            switch (fundType) {
                case ("Bank Debit"):
                    fundMethod.selectByValue("25008");
                    waitUntilLoaderDisable();
                    waitForElementVisible(fp.getBankACH(), 5);
                    Select bank = new Select(fp.getBankACH());
                    bank.selectByIndex(1);
                    break;
                case ("Bank Transfer"):
                    fundMethod.selectByValue("25009");
                    break;
                case ("Exchange"):
                    fundMethod.selectByValue("25010");
                    break;
                case ("Credit or Debit Card"):
                    fundMethod.selectByValue("25006");
                    break;
            }
            waitUntilLoaderDisable();
            waitForElementVisible(fp.getAmountTxt(), 5);
            fp.getAmountTxt().sendKeys(amount);
            if (!invoice && fundType.equals("Bank Transfer"))
                fp.getInvoiceRB().click();
            if (fundType.equals("Bank Transfer"))
                fp.getContinueBtn().click();
            else fp.getContinueACHBtn().click();
            waitUntilLoaderDisable();
            if (caseType.equals("Negative")) {
                if (fundType.equals("Bank Transfer")) {
                    waitForElementVisible(fp.getErrMsg(), 5);
                    Assert.assertEquals(fp.getErrMsg().getText(), errMsg, "Error message mismatched");
                } else {
                    Assert.assertTrue(fp.getAmountTxt().getAttribute("class").contains("requiredHighlight"), "Amount text box not highlighted. No error message showing in screen");
                }
            } else {
                switch (fundType) {
                    case "Bank Transfer":
                        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("fundaccountstepsei"), "Redirected to wrong URL");
                        break;
                    case "Bank Debit":
                        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("fundaccountstepsbank"), "Redirected to wrong URL");
                        break;
                    case "Credit or Debit Card":
                        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("fundcashaccountwithcc"), "Redirected to wrong URL");
                        break;
                }
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc02_data() {
        return new Object[][]{
                /*{currency, fundType, amount, invoice, caseType, errMsg}*/
                {"USD", "Bank Transfer", "500", true, "Positive", ""},
                /*{"USD", "Bank Transfer", "50", true, "Negative", "Minimum bank transfer amount is $500.00 USD or currency equivalent. More Info"},
                {"USD", "Bank Transfer", "0", true, "Negative", "Amount should be greater than zero"},
                {"USD", "Bank Debit", "50", true, "Positive", ""},
                {"USD", "Bank Debit", "0", true, "Negative", ""},
                {"USD", "Credit or Debit Card", "10", true, "Positive", ""},
                {"USD", "Credit or Debit Card", "0", true, "Negative", ""},*/
        };
    }

    @Test(dataProvider = "tc03_data", groups = {"Fund", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_fundWalletStep2(String currency, String fundType, String caseType) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            String amount, description = null;
            if (fundType.equals("Bank Transfer"))
                amount = "500";
            else amount = String.valueOf(getRandomAmount());
            tc02_fundWalletStep1("USD", fundType, amount, true, "Positive", "");
            waitUntilLoaderDisable();
            waitForElementVisible(fp.getAmountTxt(), 5);
            Assert.assertEquals(fp.getAmountTxt().getAttribute("value"), amount + ".00");
            if (caseType.equals("Negative")) {
                fp.getAmountTxt().clear();
                fp.getAmountTxt().sendKeys("0");
                if(fundType.equals("Bank Transfer")) {
                    waitUntilLoaderDisable();
                    waitForElementClickable(fp.getPurchaseOrderTxt(),5);
                    fp.getPurchaseOrderTxt().click();
                    waitUntilLoaderDisable();
                    waitForElementVisible(fp.getLabelMsg(), 5);
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Please set fund amount greater than 0.00");
                    fp.getAmountTxt().sendKeys(String.valueOf(getRandomAmount()));
                    waitForElementClickable(fp.getPurchaseOrderTxt(),5);
                    fp.getPurchaseOrderTxt().click();
                    waitUntilLoaderDisable();
                    waitForElementVisible(fp.getLabelMsg(), 5);
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Minimum bank transfer amount is $500.00 USD or currency equivalent. More Info");
                    fp.getAmountTxt().clear();
                    fp.getAmountTxt().sendKeys(amount);
                    waitForElementClickable(fp.getPurchaseOrderTxt(),5);
                    fp.getPurchaseOrderTxt().click();
                    description = "Please agree the terms and conditions";
                } else if (fundType.equals("Credit or Debit Card")) {
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    waitForElementVisible(fp.getLabelMsg(), 5);
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Please enter fund amount greater than zero");
                    waitForElementClickable(fp.getAmountTxt(),5);
                    fp.getAmountTxt().clear();
                    fp.getAmountTxt().sendKeys(amount);
                    String tempStr;
                    tempStr = fp.getFirstNameTxt().getAttribute("value");
                    fp.getFirstNameTxt().clear();
                    waitForElementClickable(fp.getRequestContinueBtn(), 5);
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    //Assert.assertEquals(fp.getLabelMsg().getText(), "Please enter FirstName");
                    fp.getFirstNameTxt().sendKeys(tempStr);
                    tempStr = fp.getLastNameTxt().getAttribute("value");
                    fp.getLastNameTxt().clear();
                    waitForElementClickable(fp.getRequestContinueBtn(), 5);
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    //Assert.assertEquals(fp.getLabelMsg().getText(), "Please enter LastName");
                    fp.getLastNameTxt().sendKeys(tempStr);
                    tempStr = fp.getAddr1Txt().getAttribute("value");
                    fp.getAddr1Txt().clear();
                    waitForElementClickable(fp.getRequestContinueBtn(), 5);
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Please enter address line1");
                    fp.getAddr1Txt().sendKeys(tempStr);
                    tempStr = fp.getCityTxt().getAttribute("value");
                    fp.getCityTxt().clear();
                    waitForElementClickable(fp.getRequestContinueBtn(), 5);
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Please enter city");
                    fp.getCityTxt().sendKeys(tempStr);
                    Select stateDD = new Select(fp.getStateDD());
                    tempStr = stateDD.getFirstSelectedOption().getAttribute("value");
                    stateDD.selectByValue("0");
                    waitForElementClickable(fp.getRequestContinueBtn(), 5);
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Please choose state");
                    stateDD.selectByValue(tempStr);
                    Select countryDD = new Select(fp.getCountryDD());
                    tempStr = countryDD.getFirstSelectedOption().getAttribute("value");
                    countryDD.selectByValue("0");
                    waitForElementClickable(fp.getRequestContinueBtn(), 5);
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Please choose country");
                    countryDD.selectByValue(tempStr);
                    stateDD.selectByVisibleText("Alaska");
                    tempStr = fp.getZipcodeTxt().getAttribute("value");
                    fp.getZipcodeTxt().clear();
                    waitForElementClickable(fp.getRequestContinueBtn(), 5);
                    fp.getRequestContinueBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertEquals(fp.getLabelMsg().getText(), "Please enter zipcode");
                    fp.getZipcodeTxt().sendKeys(tempStr);
                    description = "Please enter name on card";
                }
                waitForElementClickable(fp.getRequestContinueBtn(), 5);
                fp.getRequestContinueBtn().click();
                Assert.assertEquals(fp.getLabelMsg().getText(), description);
            } else {
                switch (fundType) {
                    case "Credit or Debit Card":
                        description = "Fund Request - Credit or Debit Card (Pending)";
                        Select walledDD = new Select(fp.getWalletDD());
                        Assert.assertEquals(walledDD.getFirstSelectedOption().getAttribute("value"), getWalletID(currency));
                        waitForElementVisible(fp.getSelectCardDD(), 5);
                        Select cardDD = new Select(fp.getSelectCardDD());
                        if (cardDD.getOptions().size() >= 1)
                            cardDD.selectByIndex(1);
                        else {
                            fp.getNameOnCardTxt().sendKeys("TestCard");
                            Select cardType = new Select(fp.getCardTypeDD());
                            cardType.selectByValue("Visa Card");
                            fp.getCardNoTxt().sendKeys("4111111111111111");
                            Select expMonth = new Select(fp.getExpMonthDD());
                            expMonth.selectByIndex(6);
                            Select expYear = new Select(fp.getExpYearDD());
                            expYear.selectByIndex(3);
                            fp.getCscTxt().sendKeys("123");
                        }
                        break;
                    case "Bank Transfer":
                        description = "Fund Request - Bank Transfer (Pending)";
                        Assert.assertEquals(fp.getFundingWalletId().getText(), getWalletID(currency));
                        fp.getTermsChkBox().click();
                        break;
                    case "Bank Debit":
                        fp.getTermsChkBox().click();
                        break;
                }
                waitUntilLoaderDisable();
                waitForElementClickable(fp.getRequestContinueBtn(), 5);
                fp.getRequestContinueBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("viewcashtransaction"), "Redirected to wrong URL");
                TransactionsPage tp = new TransactionsPage(driver);
                waitForElementClickable(tp.getTxnDateRange(), 5);
                tp.getTxnDateRange().click();
                waitForElementClickable(tp.getTxnDateLast7DaysBtn(), 5);
                tp.getTxnDateLast7DaysBtn().click();
                waitUntilLoaderDisable();
                tp.getTxnDetailsBtn().get(0).click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("viewcompanycreditdetails"), "Transaction Details Button not working");
                TransactionDetailsPage tdp = new TransactionDetailsPage(driver);
                Assert.assertEquals(tdp.getDescriptionFund().getText(), description, "Description mismatched in Transaction details page");
                Assert.assertTrue(tdp.getAmountFund().getText().contains(amount), "Amount mismatched in Transaction details page");
                tdp.getDoneBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("viewcashtransaction"), "Redirected to wrong URL");
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{currency, fundType, caseType}*/
                {"USD", "Bank Transfer", "Positive"},
                /*{"USD", "Bank Transfer", "Negative"},
                {"USD", "Bank Debit", "Positive"},
                {"USD", "Credit or Debit Card", "Positive"},
                {"USD", "Credit or Debit Card", "Negative"},*/
        };
    }


    /******************************************************************************************************************/

    public int getRandomAmount() {
        ranAmt = (int) (Math.random() * (49 - 5 + 1) + 5);
        return ranAmt;
    }
}
