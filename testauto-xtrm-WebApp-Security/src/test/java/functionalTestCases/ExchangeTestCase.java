package functionalTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.ExchangePage;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.TransactionDetailsPage;
import pageObject.commonPages.TransactionsPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class ExchangeTestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;

    int ranAmt;
    CommonMenu cm;

    public ExchangeTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"Exchange", "CompanyRegression", "UserRegression", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"Exchange", "CompanyRegression", "UserRegression", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"Exchange", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getExchangeMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("CurrencyExchange"), "Issue in Exchange menu click");
    }

    @Test(dataProvider = "tc2_data", groups = {"Exchange", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_exchangePage(String fromWallet, String toCurrency, String caseType, String errorMessage) throws Exception {
        tc01_homePage();
        ExchangePage ep = new ExchangePage(driver);
        waitForElementVisible(ep.getAmountTxt(), 5);
        if (caseType.equals("Positive")) {
            getRandomAmount();
            ep.getAmountTxt().sendKeys(String.valueOf(ranAmt));
            for (WebElement option : ep.getFromWalletOption()) {
                if (!option.getText().equals("Select Currency Wallet") && (option.getText().substring(0, 3).equals(fromWallet) && !option.getText().substring(option.getText().indexOf("=")+3, option.getText().length()).equals("0.00"))) {
                    option.click();
                    break;
                }
            }
            Select toCur = new Select(ep.getToCurrency());
            Thread.sleep(1000);//Wait to load currencies list
            if (toCurrency.equalsIgnoreCase("Random")) {
                int randomToCurrency = (int) (Math.random() * (ep.getToCurrencyOption().size() - 1 + 1) + 1);
                toCur.selectByIndex(randomToCurrency);
            } else toCur.selectByIndex(4);
            waitUntilLoaderDisable();
            Assert.assertTrue(ep.getContinueBtn().isEnabled(), ep.getExchangeValue().getText());
            ep.getContinueBtn().click();
            waitUntilLoaderDisable();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("currencyexchangeconfirmation"), "Redirected to wrong URL - " + driver.getCurrentUrl());
            waitForElementClickable(ep.getContinueBtn(), 5);
            ep.getContinueBtn().click();
            waitUntilLoaderDisable();
            waitUntilLoaderDisable();
            if (getPropertyValue("type", "environment").equalsIgnoreCase("user"))
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("participanthome"), "Redirected to " + driver.getCurrentUrl());
            else
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("viewcashtransaction"), "Redirected to " + driver.getCurrentUrl());
            TransactionsPage tp = new TransactionsPage(driver);
            Assert.assertTrue(tp.getTxnDetailsBtn().size() > 0, "Transaction list looks empty in wallet activity page");
            tp.getTxnDetailsBtn().get(0).click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("currencyexchangedetails"), "Redirected to " + driver.getCurrentUrl());
            TransactionDetailsPage tdp = new TransactionDetailsPage(driver);
            Assert.assertEquals(tdp.getExchangeTransactionType().getText(), "Currency Exchange", "Exchange transaction type got mismatched");
            Assert.assertTrue(tdp.getExchangeFromAmount().getText().contains(String.valueOf(ranAmt)), "Amount mismatched in Transaction details page");
            Assert.assertEquals(tdp.getExchangeType().getText(), "Credit");
            tdp.getDoneBtn().click();
            waitUntilLoaderDisable();
            if (getPropertyValue("type", "environment").equalsIgnoreCase("user"))
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("participanthome"), "Redirected to " + driver.getCurrentUrl());
            else
                Assert.assertTrue(driver.getCurrentUrl().contains("CashAccounts"), "Redirected to " + driver.getCurrentUrl());
        }
    }


    @DataProvider
    public Object[][] tc2_data() throws Exception {
        return new Object[][]{
                /*{fromWallet, toCurrency, caseType, errorMessage}*/
                {"USD", "Fixed", "Positive", ""},

        };
    }


    /******************************************************************************************************************/

    public void getRandomAmount() {
        ranAmt = (int) (Math.random() * (49 - 5 + 1) + 5);
    }
}
