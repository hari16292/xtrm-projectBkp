package functionalTestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.WalletsPage;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.TransactionDetailsPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@Listeners(resource.Listeners.class)
public class WalletsTestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    WalletsPage wp;
    String accountType;
    TransactionDetailsPage tdp;

    public WalletsTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"Wallets", "CompanyRegression", "UserRegression", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"Wallets", "CompanyRegression", "UserRegression", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"Wallets", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        waitForElementClickable(cm.getWalletsMenu(), 5);
        cm.getWalletsMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("CashAccounts"), "Issue in Wallets menu click");
    }

    @Test(dataProvider = "tc02_data", groups = {"Wallets", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_searchWallet(String searchParam, String caseType) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc01_homePage();
            accountType = getPropertyValue("type", "environment");
            wp = new WalletsPage(driver);
            waitForElementClickable(wp.getGoBtn(), 5);
            if (accountType.equalsIgnoreCase("company") && caseType.equals("Positive")) {
                if (getPropertyValue("environment", "environment").equalsIgnoreCase("Development"))
                    wp.getSearchTxt().sendKeys(getPropertyValue("dev" + searchParam, "config"));
                else if (getPropertyValue("environment", "environment").equalsIgnoreCase("SBox"))
                    wp.getSearchTxt().sendKeys(getPropertyValue("sbox" + searchParam, "config"));
            } else
                wp.getSearchTxt().sendKeys(searchParam);
            wp.getGoBtn().click();
            waitUntilLoaderDisable();
            if (caseType.equals("Positive"))
                Assert.assertEquals(wp.getCurrencyValue().getText(), searchParam, "Currency value mismatched with search param");
            else
                Assert.assertEquals(wp.getNoRecordsMsg().getText(), "No records", "Wrong message displayed in negative case");
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc02_data() {
        return new Object[][]{
                /*{"searchParam", "caseType"}*/
                {"USD", "Positive"},
                /*{"SGD", "Positive"},
                {"ABC", "Negative"},
                {"123", "Negative"}*/
        };
    }

    @Test(dataProvider = "tc03_data", groups = {"Wallets", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_createWallet(String walletName, String moreInfo, String condition, String caseType) throws Exception {
        tc01_homePage();
        wp = new WalletsPage(driver);
        if (accountType.equalsIgnoreCase("user")) {
            waitForElementClickable(wp.getUserCreateWalletBtn(), 5);
            wp.getUserCreateWalletBtn().click();
        } else if (accountType.equalsIgnoreCase("company")) {
            waitForElementClickable(wp.getCompanyCreateWalletBtn(), 5);
            wp.getCompanyCreateWalletBtn().click();
        }
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("CreateCashAccount"), "Redirected to wrong URL in create wallet button click");
        waitForElementClickable(wp.getCreateBtn(), 5);
        if (walletName.equals("Valid")) {
            walletName = "Auto Test Wallet " + RandomStringUtils.randomAlphabetic(4);
        } else if (walletName.equals("Empty")) {
            walletName = "";
        }
        wp.getWalletNameTxt().sendKeys(walletName);
        Select currency = new Select(wp.getWalletCurrencyDD());
        if (walletName.equals("valid"))
            currency.selectByIndex((int) (Math.random() * (currency.getOptions().size() - 1 + 1) + 1));
        if (moreInfo.equals("Yes")) {
            wp.getWalletCurrencyMoreInfo().click();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("XTRM Supported Currencies for Wallet Funding : General Help Center"), "Redirect to wallet currency more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Create Wallet");
        }
        if (condition.equals("Create"))
            wp.getCreateBtn().click();
        else if (condition.equals("Cancel"))
            wp.getCancelBtn().click();
        waitUntilLoaderDisable();
        if (caseType.equals("Positive")) {
            waitForElementVisible(wp.getMessage(), 5);
            Assert.assertTrue(wp.getMessage().getText().contains("Wallet created successfully."), "Issue in wallet created confirmation message");
        } else
            Assert.assertTrue(wp.getWalletNameTxt().getAttribute("style").contains("border-color:"), "Wallet Text box not highlighted for ");
    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{"walletName", "moreInfo", "condition", caseType}*/
                //{"Valid", "Yes", "Create", "Positive"},
                {"Empty", "Yes", "Create", "Negative"}

        };
    }

    @Test(dataProvider = "tc04_data", groups = {"CompanyRegression", "UserRegression", "Wallets", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc04_walletActivity(String currency, String caseType, String searchParam, String allTxnCheck) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc01_homePage();
            String walletID = null;
            wp = new WalletsPage(driver);
            if (accountType.equalsIgnoreCase("user")) {
                waitForElementClickable(wp.getUserCreateWalletBtn(), 5);
                wp.getSearchTxt().sendKeys(currency);
            } else if (accountType.equalsIgnoreCase("company")) {
                waitForElementClickable(wp.getCompanyCreateWalletBtn(), 5);
                if (getPropertyValue("environment", "environment").equalsIgnoreCase("Development"))
                    walletID = getPropertyValue("dev" + currency, "config");
                else if (getPropertyValue("environment", "environment").equalsIgnoreCase("SBox"))
                    walletID = getPropertyValue("sbox" + currency, "config");
                wp.getSearchTxt().sendKeys(walletID);
            }
            wp.getGoBtn().click();
            waitUntilLoaderDisable();
            Select filter = null;
            if (currency.length() != 3) {
                Assert.assertEquals(wp.getNoRecordsMsg().getText(), "No records", "Issue in No records message");
            } else {
                wp.getActivityBtn().get(0).click();
                waitUntilLoaderDisable();
                if (accountType.equalsIgnoreCase("user"))
                    handleTaxInfoPopup();
                waitForElementClickable(wp.getActivityFilterDD(), 5);
                filter = new Select(wp.getActivityFilterDD());
                Assert.assertEquals(filter.getFirstSelectedOption().getText(), "All Activity", "Default selected wrong one in filter");
                Assert.assertTrue(wp.getActivityDetailsBtn().size() > 0, "No transaction showing in wallet activity for currency - " + currency);
                wp.getSearchTxt().clear();
                wp.getSearchTxt().sendKeys(searchParam);
                wp.getGoBtn().click();
                waitUntilLoaderDisable();
                if (caseType.equals("Positive")) {
                    if (accountType.equalsIgnoreCase("company"))
                        waitForElementVisible(wp.getNoPendingMsg(), 5);
                    Assert.assertTrue(wp.getActivityDetailsBtn().size() > 0, "No transaction list showing in wallet activity for searched value - " + searchParam);
                } else
                    Assert.assertTrue(wp.getNoRecordsMsg().getText().contains("No activity found"), "Transaction list showing in wallet activity for searched value - " + searchParam);
                wp.getSearchTxt().clear();
                wp.getGoBtn().click();
                waitUntilLoaderDisable();
                waitForElementClickable(wp.getGoBtn(), 5);
            }
            if (allTxnCheck.equals("Yes")) {
                if (caseType.equals("Positive")) {
                    tdp = new TransactionDetailsPage(driver);
                    assert filter != null;
                    List<WebElement> filterOptions = filter.getOptions();
                    for (WebElement option : filterOptions) {
                        if (option.getText().equalsIgnoreCase("Received")) {
                            option.click();
                            waitUntilLoaderDisable();
                            verifyTxn("Received", walletID, currency, "ViewReportByCompanyCredits", "ViewCompanyCreditDetails");
                            break;
                        }
                    }
                    waitForElementClickable(wp.getActivityFilterDD(), 5);
                    filter = new Select(wp.getActivityFilterDD());
                    filterOptions = filter.getOptions();
                    for (WebElement option : filterOptions) {
                        if (option.getText().equalsIgnoreCase("Sent")) {
                            option.click();
                            waitUntilLoaderDisable();
                            verifyTxn("Sent", walletID, currency, "ViewReportByCampaign", "ViewCashTransactionDetails");
                            break;
                        }
                    }
                    waitForElementClickable(wp.getActivityFilterDD(), 5);
                    filter = new Select(wp.getActivityFilterDD());
                    filterOptions = filter.getOptions();
                    for (WebElement option : filterOptions) {
                        if (option.getText().equalsIgnoreCase("Currency Exchange")) {
                            option.click();
                            waitUntilLoaderDisable();
                            verifyTxn("Currency Exchange", walletID, currency, "nil", "CurrencyExchangeDetails");
                            break;
                        }
                    }
                    waitForElementClickable(wp.getActivityFilterDD(), 5);
                    filter = new Select(wp.getActivityFilterDD());
                    filterOptions = filter.getOptions();
                    for (WebElement option : filterOptions) {
                        if (option.getText().equalsIgnoreCase("Transferred")) {
                            option.click();
                            waitUntilLoaderDisable();
                            verifyTxn("Transferred", walletID, currency, "nil", "Summary");
                            break;
                        }
                    }
                    waitForElementClickable(wp.getActivityFilterDD(), 5);
                    filter = new Select(wp.getActivityFilterDD());
                    filterOptions = filter.getOptions();
                    for (WebElement option : filterOptions) {
                        if (option.getText().equalsIgnoreCase("Pending")) {
                            if (accountType.equalsIgnoreCase("company")) {
                                option.click();
                                waitUntilLoaderDisable();
                                verifyTxn("Pending", walletID, currency, "nil", "ViewCompanyCreditDetails");
                                break;
                            } else {
                                option.click();
                                waitUntilLoaderDisable();
                                Assert.assertTrue(wp.getNoRecordsMsg().isDisplayed(), "No Activity Message not showing");
                            }
                        }
                    }
                    waitForElementClickable(wp.getActivityFilterDD(), 5);
                    filter = new Select(wp.getActivityFilterDD());
                    filterOptions = filter.getOptions();
                    for (WebElement option : filterOptions) {
                        if (option.getText().equalsIgnoreCase("Funded")) {
                            if (accountType.equalsIgnoreCase("company")) {
                                option.click();
                                waitUntilLoaderDisable();
                                verifyTxn("Funded", walletID, currency, "ViewReportByCompanyCredits", "ViewCompanyCreditDetails");
                                break;
                            }
                        }
                    }
                }
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc04_data() {
        return new Object[][]{
                /*{"currency", "caseType", "searchParam", "allTxnCheck"}*/
                {"USD", "Positive", "10.00", "Yes"},
                /*{"USD", "Positive", "Selenium", "No"},
                {"USD", "Negative", "abcxyz", "No"},
                {"USD", "Negative", "abcxyz", "No"},*/
        };
    }

    @Test(dataProvider = "tc05_data", groups = {"CompanyRegression", "UserRegression", "Wallets", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc05_walletActivityDownload(String searchParam, String caseType) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc01_homePage();
            accountType = getPropertyValue("type", "environment");
            wp = new WalletsPage(driver);
            waitForElementClickable(wp.getGoBtn(), 5);
            if (accountType.equalsIgnoreCase("company") && caseType.equals("Positive")) {
                if (getPropertyValue("environment", "environment").equalsIgnoreCase("Development"))
                    wp.getSearchTxt().sendKeys(getPropertyValue("dev" + searchParam, "config"));
                else if (getPropertyValue("environment", "environment").equalsIgnoreCase("SBox"))
                    wp.getSearchTxt().sendKeys(getPropertyValue("sbox" + searchParam, "config"));
            } else
                wp.getSearchTxt().sendKeys(searchParam);
            wp.getGoBtn().click();
            waitUntilLoaderDisable();
            wp.getActivityBtn().get(0).click();
            waitUntilLoaderDisable();
            String fileName;
            if (accountType.equalsIgnoreCase("user")) {
                handleTaxInfoPopup();
                fileName = "Transactions.csv";
            } else {
                fileName = "WalletActivity.csv";
            }
            waitForElementClickable(wp.getActivityDetailsBtn().get(0), 5);
            waitForElementClickable(wp.getActivityDownload(), 5);
            Thread.sleep(1000); //wait for load all transaction in activity page
            wp.getActivityDownload().click();
            waitUntilLoaderDisable();
            String home = System.getProperty("user.home");
            String fileLocation = home + "\\Downloads\\" + fileName;
            File file = new File(fileLocation);
            Thread.sleep(500); //wait for the downloaded file to appear in the downloads folder
            Assert.assertTrue(file.exists(), "File - " + fileName + " not available in downloads folder");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
            int rowCount = 0;
            while (bufferedReader.readLine() != null) {
                rowCount++;
            }
            bufferedReader.close();
            Assert.assertTrue(rowCount > 1, "No data's available in downloaded file");
            file.delete();
            Assert.assertFalse(file.exists(), "File - " + fileName + " not deleted and still available in downloads folder");
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc05_data() {
        return new Object[][]{
                /*{"currency", "caseType"}*/
                {"USD", "Positive"},
        };
    }

    /*****************************************************************************************************************/

    public void verifyTxn(String txnType, String walletID, String currency, String viewReportPage, String detailsPage) {
        if (accountType.equalsIgnoreCase("company") && !txnType.equals("Pending"))
            waitForElementVisible(wp.getNoPendingMsg(), 5);
        Assert.assertTrue(wp.getActivityDetailsBtn().size() > 0, txnType + " transaction list not available in wallet activity for currency - " + currency);
        wp.getActivityDetailsBtn().get(0).click();
        waitUntilLoaderDisable();
        if (accountType.equalsIgnoreCase("Company") && driver.getCurrentUrl().contains(viewReportPage)) {
            waitForElementClickable(tdp.getShowTxnDetails().get(0), 5);
            if (!viewReportPage.isEmpty()) {
                Assert.assertTrue(driver.getCurrentUrl().contains(viewReportPage), "Redirected to wrong URL - " + driver.getCurrentUrl());
                tdp.getShowTxnDetails().get(0).click();
                waitUntilLoaderDisable();
            }
            Assert.assertTrue(driver.getCurrentUrl().contains(detailsPage), "Redirected to wrong URL - " + driver.getCurrentUrl());
        } else if (accountType.equalsIgnoreCase("User") && txnType.equals("Transferred")) {
            Assert.assertTrue(wp.getUserTransferredDoneBtn().isDisplayed(), "Transaction details page not working");
        } else {
            Assert.assertTrue(wp.getDoneBtn().isDisplayed(), "Transaction details page not working");
        }
        cm.getWalletsMenu().click();
        waitUntilLoaderDisable();
        if (accountType.equalsIgnoreCase("Company")) {
            waitForElementClickable(wp.getCompanyCreateWalletBtn(), 5);
            wp.getSearchTxt().clear();
            wp.getSearchTxt().sendKeys(walletID);
        } else {
            wp.getSearchTxt().sendKeys(currency);
        }
        wp.getGoBtn().click();
        waitUntilLoaderDisable();
        wp.getActivityBtn().get(0).click();
        waitUntilLoaderDisable();
        if (accountType.equalsIgnoreCase("User"))
            handleTaxInfoPopup();
        waitUntilLoaderDisable();
    }
}
