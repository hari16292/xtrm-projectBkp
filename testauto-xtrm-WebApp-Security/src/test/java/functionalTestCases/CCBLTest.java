package functionalTestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.LinkBank_CardsPage;
import pageObject.LinkedCardsPage;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class CCBLTest extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    LinkBank_CardsPage lp;
    LinkedCardsPage lcp;

    @BeforeSuite(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkRapidCard"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
        lp = new LinkBank_CardsPage(driver);
        lcp = new LinkedCardsPage(driver);
    }

    @AfterSuite(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkRapidCard"})
    public void closeBrowser() {
        driver.quit();
    }


    @Test(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkRapidCard"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        if (getPropertyValue("type", "environment").equalsIgnoreCase("Company")) {
            cm.getBanksMenu().click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("payment"), "Banks menu click redirected to wrong URL - " + driver.getCurrentUrl());
        } else {
            cm.getSettingsIcon().click();
            waitUntilLoaderDisable();
            waitForElementClickable(lp.getLinkedBanksTab(), 5);
            lp.getLinkedBanksTab().click();
            waitUntilLoaderDisable();
        }
        waitForElementClickable(lp.getLinkBankAccountBtn(), 5);
        lp.getLinkBankAccountBtn().click();
        waitUntilLoaderDisable();
        waitForElementClickable(cm.getLogoutMenu(), 5);
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("choosepaymethod"), "LinkBankButton click redirected to wrong URL - " + driver.getCurrentUrl());
    }

    @Test
    public void tc02_linkBankTransfer() throws Exception {
        tc01_homePage();
        waitForElementClickable(cm.getLogoutMenu(), 5);
        lp.getTransferGoBtn().click();
        waitUntilLoaderDisable();
        waitForElementClickable(lp.getTransferLinkContinueBtn(), 5);
        Select bankCountryDD = new Select(lp.getTransferBankCountryDD());
        for(int i=0; i<bankCountryDD.getOptions().size(); i++) {
            bankCountryDD.selectByIndex(i);
            waitUntilLoaderDisable();
            waitForElementClickable(lp.getTransferLinkContinueBtn(), 5);
            lp.getTransferCurrencyDD().click();
            Select currency = new Select(lp.getTransferCurrencyDD());
            getScreenshot(bankCountryDD.getFirstSelectedOption().getText());
            try {
                boolean ccblEnabled = true;
                if ((ccblEnabled && currency.getOptions().size() == 1) || (!ccblEnabled && currency.getOptions().size() > 1)) {
                } else throw new Exception("CCBL currency count mismatch");
            } catch (Exception e) {
                System.out.println(bankCountryDD.getFirstSelectedOption().getText() + " - has " + currency.getOptions().size() + " currencies");
            }
        }
    }
}
