package functionalTestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.*;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.LogoutPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Listeners(resource.Listeners.class)
public class ControllerAdminTestCase extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    LoginTestCase l;
    LogoutPage lop;
    WalletsPage wp;
    ControllerAdminPage cap;
    List<String> accessTypeList = new ArrayList<>();
    List<String> allAccessTypeList = new ArrayList<>(Arrays.asList("FundWallets", "BankDebit", "CreditOrDebitCard", "ElectronicInvoice", "MoveFunds", "SendFunds", "SimpleSend", "AdvancedSend", "DraftPayments", "MassPayments", "EmbeddedPayments", "TransferFunds", "BankAccount", "BankCheck", "VisaDebit", "CurrencyExchange", "CreateProgram", "LinkBankTransfer", "LinkBankFund", "ConnectedManager", "EditClaims", "EditBeneficiaries", "EditConnected", "EditWallets", "ViewClaims", "ViewReports", "ViewBeneficiaries", "ViewMassPayments", "ViewConnected", "ViewWallets", "87877", "87876", "86525", "114529", "114157", "114156"));

    @BeforeSuite(groups = {"ControllerAdmin", "CompanyRegression", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
        l = new LoginTestCase();
        lop = new LogoutPage(driver);
        wp = new WalletsPage(driver);
        cap = new ControllerAdminPage(driver);
    }

    @AfterSuite(groups = {"ControllerAdmin", "CompanyRegression", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"ControllerAdmin", "Security"}, dataProvider = "tc01_data", retryAnalyzer = RetryAnalyzer.class)
    public void tc01_setAccess(String loginAccount, String adminType, String accessLevel, String accessType) throws Exception {
        l.tc02_verificationPage(loginAccount);
        waitUntilLoaderDisable();
        waitForElementClickable(cm.getUserMenu(), 5);
        cm.getUserMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("SponsorManageEmployees"), "Issue in Users menu click");
        waitForElementClickable(cap.getFilterDD(), 5);
        Select filterDD = new Select(cap.getFilterDD());
        switch (adminType) {
            case "Controller Admin":
                filterDD.selectByValue("7");
                break;
            case "Manager Admin":
                filterDD.selectByValue("5");
                break;
            case "Standard Admin":
                filterDD.selectByValue("2");
                break;
        }
        waitUntilLoaderDisable();
        waitForElementClickable(cap.getEditBtn().get(0), 5);
        cap.getEditBtn().get(0).click();
        waitUntilLoaderDisable();
        waitForElementClickable(cap.getDoneBtn(), 5);
        Select accessLevelDD = new Select(cap.getAccessLevelDD());
        switch (accessLevel) {
            case "Limited":
                accessLevelDD.selectByValue("0");
                break;
            case "Full":
                accessLevelDD.selectByValue("1");
                break;
            case "Custom":
                accessLevelDD.selectByValue("2");
                cap.getSetCustomAccessBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("SponsorAdminAccess.aspx"), "Issue in set custom access button click");
                waitForElementClickable(cap.getCustomAccessDoneBtn(), 5);
                setCustomAccess(accessType);
                break;
            case "View":
                accessLevelDD.selectByValue("3");
                break;
        }
        waitForElementClickable(cap.getDoneBtn(), 5);
        cap.getDoneBtn().click();
        waitUntilLoaderDisable();
        waitForElementVisible(cap.getErrMsg(), 5);
        Assert.assertEquals(cap.getErrMsg().getText(), "User details updated successfully.", "Message mismatched in edit confirmation");
        cm.getLogoutMenu().click();
        waitForElementVisible(lop.getLearnMoreBtn(), 10);
        Assert.assertTrue(driver.getCurrentUrl().contains("Logout"), "Issue in logout");
        l.tc02_verificationPage(adminType);
        waitUntilLoaderDisable();
        waitForElementClickable(cm.getHomeMenu(), 5);
        verifyAccessLevel(accessLevel);
        cm.getLogoutMenu().click();
        waitForElementVisible(lop.getLearnMoreBtn(), 10);
        Assert.assertTrue(driver.getCurrentUrl().contains("Logout"), "Issue in logout");
    }

    @DataProvider
    public Object[][] tc01_data() {
        return new Object[][] {
                /*{"loginAccount", "adminType", "accessLevel", "accessType"},*/
                {"CACompany", "Controller Admin", "Limited", ""},
                /*{"CACompany", "Controller Admin", "View", ""},
                {"CACompany", "Controller Admin", "Full", ""},*/
                {"CACompany", "Controller Admin", "Custom", "CreditOrDebitCard,wallet"},
                /*{"CACompany", "Controller Admin", "Custom", "BankDebit,wallet"},
                {"CACompany", "Controller Admin", "Custom", "ElectronicInvoice,wallet"},
                {"CACompany", "Controller Admin", "Custom", "MoveFunds,wallet"},
                {"CACompany", "Controller Admin", "Custom", "SimpleSend,wallet"},
                {"CACompany", "Controller Admin", "Custom", "AdvancedSend,wallet"},
                {"CACompany", "Controller Admin", "Custom", "MassPayments,ViewMassPayments,wallet"},
                {"CACompany", "Controller Admin", "Custom", "EmbeddedPayments,wallet"},
                {"CACompany", "Controller Admin", "Custom", "BankAccount,wallet"},
                {"CACompany", "Controller Admin", "Custom", "BankCheck,wallet"},
                {"CACompany", "Controller Admin", "Custom", "VisaDebit,wallet"},
                {"CACompany", "Controller Admin", "Custom", "CurrencyExchange,wallet"},
                {"CACompany", "Controller Admin", "Custom", "CreateProgram"},
                {"CACompany", "Controller Admin", "Custom", "LinkBankTransfer"},
                {"CACompany", "Controller Admin", "Custom", "LinkBankFund"},
                {"CACompany", "Controller Admin", "Custom", "ConnectedManager,ViewConnected"},
                {"CACompany", "Controller Admin", "Custom", "EditClaims,ViewClaims"},
                {"CACompany", "Controller Admin", "Custom", "EditBeneficiaries,ViewBeneficiaries"},
                {"CACompany", "Controller Admin", "Custom", "EditConnected,ViewConnected"},
                {"CACompany", "Controller Admin", "Custom", "EditWallets,wallet"},
                {"CACompany", "Controller Admin", "Custom", "ViewClaims,wallet"},
                {"CACompany", "Controller Admin", "Custom", "ViewReports,wallet"},
                {"CACompany", "Controller Admin", "Custom", "ViewBeneficiaries,wallet"},
                {"CACompany", "Controller Admin", "Custom", "ViewMassPayments,wallet"},
                {"CACompany", "Controller Admin", "Custom", "ViewConnected,wallet"},
                {"CACompany", "Controller Admin", "Custom", "ViewWallets,wallet"},*/
        };
    }

    /******************************************************************************************************************/
    public void verifyAccessLevel(String accessLevel) throws Exception {
        Assert.assertTrue(cm.getHomeMenu().isDisplayed(), "Home Menu not available in " + accessLevel + " access level");
        Assert.assertTrue(cm.getProfileMenu().isDisplayed(), "Profile Menu not available in " + accessLevel + " access level");
        Assert.assertTrue(cm.getUserMenu().isDisplayed(), "Users Menu not available in " + accessLevel + " access level");
        //Assert.assertTrue(cm.getProgramsMenu().isDisplayed(), "Programs Menu not available in " + accessLevel + " access level");
        Assert.assertTrue(cm.getBanksMenu().isDisplayed(), "Banks Menu not available in " + accessLevel + " access level");
        Assert.assertTrue(cm.getIntegrationMenu().isDisplayed(), "Integration Menu not available in " + accessLevel + " access level");
        Assert.assertTrue(cm.getLogoutMenu().isDisplayed(), "Logout Menu not available in " + accessLevel + " access level");
        switch (accessLevel) {
            case "Limited":
                Assert.assertEquals(cap.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Message mismatched in home page");
                break;
            case "View":
                Assert.assertTrue(cm.getWalletsMenu().isDisplayed(), "Wallets Menu not available in View access level");
                Assert.assertTrue(cm.getClaimsMenu().isDisplayed(), "Claims Menu not available in View access level");
                Assert.assertTrue(cm.getBeneficiaryMenu().isDisplayed(), "Beneficiaries Menu not available in View access level");
                Assert.assertTrue(cm.getConnectedMenu().isDisplayed(), "Connected Menu not available in View access level");
                cm.getWalletsMenu().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("CashAccounts"), "Redirected to wrong URL in wallets menu click");
                waitForElementClickable(wp.getActivityBtn().get(0), 5);
                try {
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
                    Assert.assertTrue(wp.getActivityDDBtn().isEmpty(), "Wallets activity drop down button is available in View access level");
                    Assert.assertFalse(wp.getCompanyCreateWalletBtn().isEnabled(), "Create wallet button is available in View access level");
                } catch (NoSuchElementException ignored) {
                }
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                break;
            case "Full":
                Assert.assertTrue(cm.getWalletsMenu().isDisplayed(), "Wallets Menu not available in Full access level");
                Assert.assertTrue(cm.getFundMenu().isDisplayed(), "Fund Menu not available in Full access level");
                Assert.assertTrue(cm.getSendMenu().isDisplayed(), "Send Menu not available in Full access level");
                Assert.assertTrue(cm.getTransferMenu().isDisplayed(), "Transfer Menu not available in Full access level");
                Assert.assertTrue(cm.getExchangeMenu().isDisplayed(), "Exchange Menu not available in Full access level");
                Assert.assertTrue(cm.getClaimsMenu().isDisplayed(), "Claim Menu not available in Full access level");
                Assert.assertTrue(cm.getReportsMenu().isDisplayed(), "Report Menu not available in Full access level");
                Assert.assertTrue(cm.getBeneficiaryMenu().isDisplayed(), "Beneficiaries Menu not available in Full access level");
                Assert.assertTrue(cm.getConnectedMenu().isDisplayed(), "Connected Menu not available in Full access level");
                cm.getWalletsMenu().click();
                waitUntilLoaderDisable();
                waitForElementClickable(cm.getHomeMenu(), 5);
                Assert.assertTrue(wp.getCompanyCreateWalletBtn().isEnabled(), "Create wallet button is not available in Full access level");
                Assert.assertTrue(wp.getActivityDDBtn().get(0).isEnabled(), "Wallets activity drop down button is not available in Full access level");
                cm.getHomeMenu().click();
                waitUntilLoaderDisable();
                waitForElementClickable(cm.getHomeMenu(), 5);
                break;
            case "Custom":
                for (String accessType : allAccessTypeList) {
                    if (accessType.equalsIgnoreCase("BankDebit")) {
                        FundPage fp = new FundPage(driver);
                        Select fundMethodDD;
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getFundMenu().isDisplayed(), "Fund Menu not available in Custom access level");
                            cm.getFundMenu().click();
                            waitUntilLoaderDisable();
                            fundMethodDD = new Select(fp.getFundMethodDD());
                            waitForElementClickable(fp.getCreateWalletBtn(), 5);
                            fundMethodDD.selectByValue("25008");
                            Assert.assertTrue(fundMethodDD.getFirstSelectedOption().getText().contains("Bank Debit"), "Bank Debit option not available in Fund methods list");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                                if (cm.getFundMenu().isDisplayed()) {
                                    cm.getFundMenu().click();
                                    waitUntilLoaderDisable();
                                    fundMethodDD = new Select(fp.getFundMethodDD());
                                    waitForElementClickable(fp.getCreateWalletBtn(), 5);
                                    fundMethodDD.selectByValue("25008");
                                    Assert.assertFalse(fundMethodDD.getFirstSelectedOption().getText().contains("Bank Debit"), "Bank Debit option is available in Fund methods list");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("CreditOrDebitCard")) {
                        FundPage fp = new FundPage(driver);
                        Select fundMethodDD;
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getFundMenu().isDisplayed(), "Fund Menu not available in Custom access level");
                            cm.getFundMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(fp.getCreateWalletBtn(), 5);
                            fundMethodDD = new Select(fp.getFundMethodDD());
                            fundMethodDD.selectByValue("25006");
                            Assert.assertTrue(fundMethodDD.getFirstSelectedOption().getText().contains("Credit or Debit Card"), "Credit or Debit Card option not available in Fund methods list");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                                if (cm.getFundMenu().isDisplayed()) {
                                    cm.getFundMenu().click();
                                    waitUntilLoaderDisable();
                                    fundMethodDD = new Select(fp.getFundMethodDD());
                                    waitForElementClickable(fp.getCreateWalletBtn(), 5);
                                    fundMethodDD.selectByValue("25006");
                                    Assert.assertFalse(fundMethodDD.getFirstSelectedOption().getText().contains("Credit or Debit Card"), "Credit or Debit Card option is available in Fund methods list");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("ElectronicInvoice")) {
                        FundPage fp = new FundPage(driver);
                        Select fundMethodDD;
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getFundMenu().isDisplayed(), "Fund Menu not available in Custom access level");
                            cm.getFundMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(fp.getCreateWalletBtn(), 5);
                            fundMethodDD = new Select(fp.getFundMethodDD());
                            fundMethodDD.selectByValue("25009");
                            Assert.assertTrue(fundMethodDD.getFirstSelectedOption().getText().contains("Bank Transfer"), "Bank Transfer option not available in Fund methods list");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                                if (cm.getFundMenu().isDisplayed()) {
                                    cm.getFundMenu().click();
                                    waitUntilLoaderDisable();
                                    fundMethodDD = new Select(fp.getFundMethodDD());
                                    waitForElementClickable(fp.getCreateWalletBtn(), 5);
                                    fundMethodDD.selectByValue("25009");
                                    Assert.assertFalse(fundMethodDD.getFirstSelectedOption().getText().contains("Bank Transfer"), "Bank Transfer Card option is available in Fund methods list");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("MoveFunds")) {
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getWalletsMenu().isDisplayed(), "Wallets Menu not available in Custom access level");
                            cm.getWalletsMenu().click();
                            waitUntilLoaderDisable();
                            WalletsPage wp = new WalletsPage(driver);
                            waitForElementClickable(wp.getMoveOption(), 5);
                            Assert.assertTrue(wp.getMoveOption().isEnabled(), "Move option not available in Custom access level");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getWalletsMenu().isDisplayed()) {
                                    cm.getWalletsMenu().click();
                                    waitUntilLoaderDisable();
                                    WalletsPage wp = new WalletsPage(driver);
                                    waitForElementClickable(wp.getMoveOption(), 5);
                                    wp.getMoveOption().click();
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(wp.getMessage().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message mismatched in Move Wallet");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("SimpleSend")) {
                        SendPage sp = new SendPage(driver);
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getSendMenu().isDisplayed(), "Send Menu not available in Custom access level");
                            cm.getSendMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(sp.getSimpleSendGoBtn(), 5);
                            sp.getSimpleSendGoBtn().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSend"), "Simple send button click redirected to wrong page");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getSendMenu().isDisplayed()) {
                                    cm.getSendMenu().click();
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(sp.getSimpleSendGoBtn(), 5);
                                    sp.getSimpleSendGoBtn().click();
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(sp.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + sp.getErrMsg().getText() + ") mismatched in Simple Send");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("AdvancedSend")) {
                        SendPage sp = new SendPage(driver);
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getSendMenu().isDisplayed(), "Send Menu not available in Custom access level");
                            cm.getSendMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(sp.getAdvanceGoBtn(), 5);
                            sp.getAdvanceGoBtn().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("AdvancedSend"), "Advanced send button click redirected to wrong page");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getSendMenu().isDisplayed()) {
                                    cm.getSendMenu().click();
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(sp.getAdvanceGoBtn(), 5);
                                    sp.getAdvanceGoBtn().click();
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(sp.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + sp.getErrMsg().getText() + ") mismatched in Advanced Send");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("MassPayments")) {
                        SendPage sp = new SendPage(driver);
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getSendMenu().isDisplayed(), "Send Menu not available in Custom access level");
                            cm.getSendMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(sp.getMassPaymentGoBtn(), 5);
                            sp.getMassPaymentGoBtn().click();
                            waitUntilLoaderDisable();
                            if (accessTypeList.contains("ViewMassPayments"))
                                Assert.assertTrue(driver.getCurrentUrl().contains("RewardSubmission"), "Mass Payment Go button click redirected to wrong page");
                            else
                                Assert.assertEquals(sp.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + sp.getErrMsg().getText() + ") mismatched in Mass Payments");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getSendMenu().isDisplayed()) {
                                    cm.getSendMenu().click();
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(sp.getMassPaymentGoBtn(), 5);
                                    sp.getMassPaymentGoBtn().click();
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(sp.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + sp.getErrMsg().getText() + ") mismatched in Mass Payments");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("EmbeddedPayments")) {
                        SendPage sp = new SendPage(driver);
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getSendMenu().isDisplayed(), "Send Menu not available in Custom access level");
                            cm.getSendMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(sp.getEmbeddedPaymentGoBtn(), 5);
                            sp.getEmbeddedPaymentGoBtn().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("ConnectAPIDetails"), "Embedded Payment Go button click redirected to wrong page");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getSendMenu().isDisplayed()) {
                                    cm.getSendMenu().click();
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(sp.getEmbeddedPaymentGoBtn(), 5);
                                    sp.getEmbeddedPaymentGoBtn().click();
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(sp.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + sp.getErrMsg().getText() + ") mismatched in Mass Payments");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("BankAccount")) {
                        TransferPage tp = new TransferPage(driver);
                        Select transferToDD, fromWalletDD;
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getTransferMenu().isDisplayed(), "Transfer Menu not available in Custom access level");
                            cm.getTransferMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(tp.getTransferBtn(), 5);
                            fromWalletDD = new Select(tp.getFromWalletDD());
                            fromWalletDD.selectByValue(getWalletID() + ",1,USD");
                            waitUntilLoaderDisable();
                            waitForElementClickable(tp.getTransferBtn(), 5);
                            transferToDD = new Select(tp.getTransferTypeDD());
                            transferToDD.selectByValue("1");
                            waitUntilLoaderDisable();
                            Assert.assertTrue(tp.getLinkNewBank().isDisplayed(), "Receiving Bank drop down not available after selecting transfer type");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getTransferMenu().isDisplayed()) {
                                    cm.getTransferMenu().click();
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(tp.getTransferBtn(), 5);
                                    fromWalletDD = new Select(tp.getFromWalletDD());
                                    fromWalletDD.selectByValue(getWalletID() + ",1,USD");
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(tp.getTransferBtn(), 5);
                                    transferToDD = new Select(tp.getTransferTypeDD());
                                    transferToDD.selectByValue("1");
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(tp.getErrorMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + tp.getTransferTypeMsg().getText() + ") mismatched in Transfer Bank Payment");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("BankCheck")) {
                        TransferPage tp = new TransferPage(driver);
                        Select transferToDD, fromWalletDD;
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getTransferMenu().isDisplayed(), "Transfer Menu not available in Custom access level");
                            cm.getTransferMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(tp.getTransferBtn(), 5);
                            fromWalletDD = new Select(tp.getFromWalletDD());
                            fromWalletDD.selectByValue(getWalletID() + ",1,USD");
                            waitUntilLoaderDisable();
                            waitForElementClickable(tp.getTransferBtn(), 5);
                            transferToDD = new Select(tp.getTransferTypeDD());
                            transferToDD.selectByValue("3");
                            waitUntilLoaderDisable();
                            Assert.assertTrue(tp.getCheckContinueBtn().isDisplayed(), "Bank continue button not available after selecting transfer type");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getTransferMenu().isDisplayed()) {
                                    cm.getTransferMenu().click();
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(tp.getTransferBtn(), 5);
                                    fromWalletDD = new Select(tp.getFromWalletDD());
                                    fromWalletDD.selectByValue(getWalletID() + ",1,USD");
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(tp.getTransferBtn(), 5);
                                    transferToDD = new Select(tp.getTransferTypeDD());
                                    transferToDD.selectByValue("3");
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(tp.getErrorMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + tp.getTransferTypeMsg().getText() + ") mismatched in Transfer Bank check Payment");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("VisaDebit")) {
                        TransferPage tp = new TransferPage(driver);
                        Select transferToDD, fromWalletDD;
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getTransferMenu().isDisplayed(), "Transfer Menu not available in Custom access level");
                            cm.getTransferMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(tp.getTransferBtn(), 5);
                            fromWalletDD = new Select(tp.getFromWalletDD());
                            fromWalletDD.selectByValue(getWalletID() + ",1,USD");
                            waitUntilLoaderDisable();
                            waitForElementClickable(tp.getTransferBtn(), 5);
                            transferToDD = new Select(tp.getTransferTypeDD());
                            transferToDD.selectByValue("5");
                            waitUntilLoaderDisable();
                            Assert.assertTrue(tp.getRapidContinueBtn().isDisplayed(), "Rapid continue button not available after selecting transfer type");
                            waitForElementClickable(tp.getTransferBtn(), 5);
                            transferToDD.selectByValue("4");
                            waitUntilLoaderDisable();
                            Assert.assertTrue(tp.getRecipientEmailPVVTxt().isDisplayed(), "PVV Recipient email text box not available after selecting transfer type");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                if (cm.getTransferMenu().isDisplayed()) {
                                    cm.getTransferMenu().click();
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(tp.getTransferBtn(), 5);
                                    fromWalletDD = new Select(tp.getFromWalletDD());
                                    fromWalletDD.selectByValue(getWalletID() + ",1,USD");
                                    waitUntilLoaderDisable();
                                    waitForElementClickable(tp.getTransferBtn(), 5);
                                    transferToDD = new Select(tp.getTransferTypeDD());
                                    transferToDD.selectByValue("5");
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(tp.getErrorMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + tp.getTransferTypeMsg().getText() + ") mismatched in Transfer Rapid");
                                    waitForElementClickable(tp.getTransferBtn(), 5);
                                    transferToDD.selectByValue("4");
                                    waitUntilLoaderDisable();
                                    Assert.assertEquals(tp.getErrorMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + tp.getTransferTypeMsg().getText() + ") mismatched in Transfer PVV");
                                }
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("CurrencyExchange")) {
                        ExchangePage ep = new ExchangePage(driver);
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getExchangeMenu().isDisplayed(), "Exchange Menu not available in Custom access level");
                            cm.getExchangeMenu().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(ep.getContinueBtn(), 5);
                            if (ep.getFromWalletOption().size() > 1) {
                                for (WebElement option : ep.getFromWalletOption()) {
                                    String[] value = option.getAttribute("value").split(",");
                                    if (accessTypeList.contains(value[0])) {
                                        option.click();
                                        break;
                                    }
                                }
                            }
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                Assert.assertFalse(cm.getExchangeMenu().isDisplayed(), "Exchange Menu available in custom access level");
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("CreateProgram")) {
                        ProgramsPage pp = new ProgramsPage(driver);
                        cm.getSettingsIcon().click();
                        waitUntilLoaderDisable();
                        waitForElementClickable(pp.getProgramsTab(), 5);
                        pp.getProgramsTab().click();
                        waitUntilLoaderDisable();
                        if (accessTypeList.contains(accessType)) {
                            //cm.getProgramsMenu().click();
                            Assert.assertTrue(pp.getCreatePgbBtn().isDisplayed(), "Create Program Button not available in custom access level");
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                /*cm.getProgramsMenu().click();
                                waitUntilLoaderDisable();
                                waitForElementClickable(pp.getProgramBtn(), 5);*/
                                Assert.assertFalse(pp.getCreatePgbBtn().isDisplayed(), "Create Program Button available in custom access level");
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("LinkBankTransfer")) {
                        LinkBank_CardsPage lp = new LinkBank_CardsPage(driver);
                        cm.getBanksMenu().click();
                        //cm.getSettingsIcon().click();
                        /*waitUntilLoaderDisable();
                        waitForElementClickable(lp.getLinkedBanksTab(), 5);
                        lp.getLinkedBanksTab().click();*/
                        waitUntilLoaderDisable();
                        waitForElementClickable(lp.getLinkBankAccountBtn(), 5);
                        lp.getLinkBankAccountBtn().click();
                        waitUntilLoaderDisable();
                        waitForElementClickable(lp.getTransferGoBtn(), 5);
                        if (accessTypeList.contains(accessType)) {
                            lp.getTransferGoBtn().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("AddBeneficiary.aspx"), "LinkBankTransfer Go button click redirected to wrong URL - " + driver.getCurrentUrl());
                        } else {
                            lp.getTransferGoBtn().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(lp.getTransferGoBtn(), 5);
                            Assert.assertEquals(lp.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + lp.getErrMsg().getText() + ") mismatched in Transfer LinkBankTransfer");
                        }
                    } else if (accessType.equalsIgnoreCase("LinkBankFund")) {
                        LinkBank_CardsPage lp = new LinkBank_CardsPage(driver);
                        cm.getBanksMenu().click();
                        //cm.getSettingsIcon().click();
                        /*waitUntilLoaderDisable();
                        waitForElementClickable(lp.getLinkedBanksTab(), 5);
                        lp.getLinkedBanksTab().click();*/
                        waitUntilLoaderDisable();
                        waitForElementClickable(lp.getLinkBankAccountBtn(), 5);
                        lp.getLinkBankAccountBtn().click();
                        waitUntilLoaderDisable();
                        waitForElementClickable(cm.getLogoutMenu(), 5);
                        waitForElementClickable(lp.getFundGoBtn(), 5);
                        Select fundCountryDD = new Select(lp.getFundGoCountryDD());
                        fundCountryDD.selectByValue("USD");
                        waitForElementClickable(lp.getFundGoBtn(), 5);
                        waitUntilLoaderDisable();
                        Thread.sleep(1000); //few sec delay in enable button click
                        if (accessTypeList.contains(accessType)) {
                            lp.getFundGoBtn().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(lp.getPlaidIframe().isDisplayed(), "Plaid Iframe not showing");
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            js.executeScript("document.getElementById('plaid-link-iframe-1').setAttribute('style', 'position: fixed; inset: 0px; z-index: 2147483647; border-width: 0px; display: none; overflow: hidden auto;')");
                            js.executeScript("document.getElementsByClassName('modal-overlay')[0].setAttribute('class', 'modal-overlay hide')");
                            js.executeScript("document.getElementsByClassName('modal-animate')[0].setAttribute('class', 'modal-animate hide')");
                            waitForElementClickable(lp.getFundGoBtn(), 5);
                        } else {
                            lp.getFundGoBtn().click();
                            waitUntilLoaderDisable();
                            waitForElementClickable(lp.getFundGoBtn(), 5);
                            Assert.assertEquals(lp.getErrMsg().getText(), "Not authorized to complete this task. Please contact one of your administrators.", "Error message(" + lp.getErrMsg().getText() + ") mismatched in Transfer LinkBankFund");
                        }
                    } else if (accessType.equalsIgnoreCase("ConnectedManager")) {
                        ConnectedPage cp = new ConnectedPage(driver);
                        if (accessTypeList.contains(accessType)) {
                            if (accessTypeList.contains("ViewConnected")) {
                                cm.getConnectedMenu().click();
                                waitUntilLoaderDisable();
                                waitForElementClickable(cp.getConnectedCompaniesOption(), 5);
                                cp.getConnectedCompaniesOption().click();
                                waitUntilLoaderDisable();
                                waitForElementClickable(cp.getDownloadBtn(), 5);
                                waitForElementClickable(cp.getMenuDD().get(0), 5);
                                cp.getMenuDD().get(0).click();
                                Assert.assertTrue(cp.getGetAPICredentialsOption().isDisplayed(), "Connected Service Options are not available");
                            } else {
                                try {
                                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                    Assert.assertFalse(cm.getConnectedMenu().isDisplayed(), "Connected menu is displayed without enabled ViewConnected");
                                } catch (NoSuchElementException ignored) {
                                }
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                            }
                        }
                    } else if (accessType.equalsIgnoreCase("EditClaims")) {
                        if (accessTypeList.contains(accessType)) {
                            if (accessTypeList.contains("ViewClaims")) {
                                cm.getClaimsMenu().click();
                                waitUntilLoaderDisable();
                                Assert.assertTrue(driver.getCurrentUrl().contains("ClaimSummary"), "Claims menu click redirected to wrong URL - " + driver.getCurrentUrl());
                            } else {
                                try {
                                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                    Assert.assertFalse(cm.getClaimsMenu().isDisplayed(), "Claims menu is displayed without enabled ViewClaims");
                                } catch (NoSuchElementException ignored) {
                                }
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                            }
                        }
                    } else if (accessType.equalsIgnoreCase("EditBeneficiaries")) {
                        if (accessTypeList.contains(accessType)) {
                            BeneficiariesPage bp = new BeneficiariesPage(driver);
                            if (accessTypeList.contains("ViewBeneficiaries")) {
                                cm.getBeneficiaryMenu().click();
                                waitUntilLoaderDisable();
                                Assert.assertTrue(driver.getCurrentUrl().contains("CommunityMembers"), "Beneficiaries menu click redirected to wrong URL - " + driver.getCurrentUrl());
                                if (bp.getSendBtn().size() > 0) {
                                    waitForElementClickable(bp.getSendBtn().get(0), 5);
                                    if (accessTypeList.contains("AdvancedSend"))
                                        Assert.assertEquals(bp.getSendBtn().get(0).getAttribute("class"), "Send mdrnButton primary tooltip-inner", "Send button is grayed out");
                                    else
                                        Assert.assertEquals(bp.getSendBtn().get(0).getAttribute("class"), "Send mdrnButton primary tooltip-inner NotAuthorized", "Send button is not grayed out");
                                    bp.getMenuDD().get(0).click();
                                    waitForElementVisible(bp.getViewProfileOption(), 5);
                                    Assert.assertTrue(bp.getViewProfileOption().isDisplayed(), "View profile option is not available in menu");
                                    Assert.assertTrue(bp.getRemoveOption().isDisplayed(), "Remove Beneficiary option not available in menu");
                                    if (accessTypeList.contains("ViewConnected")) {
                                        Assert.assertTrue(bp.getViewWalletsOption().isDisplayed(), "View wallet option is not available in menu");
                                        Assert.assertTrue(bp.getViewLinkedBanksOption().isDisplayed(), "View linked banks option is not available in menu");
                                    }
                                }
                            } else {
                                try {
                                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                    Assert.assertFalse(cm.getBeneficiaryMenu().isDisplayed(), "Beneficiaries menu is displayed without enabled ViewBeneficiaries");
                                } catch (NoSuchElementException ignored) {
                                }
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                            }
                        }
                    } else if (accessType.equalsIgnoreCase("EditConnected")) {
                        if (accessTypeList.contains(accessType)) {
                            ConnectedPage cp = new ConnectedPage(driver);
                            if (accessTypeList.contains("ViewConnected")) {
                                cm.getConnectedMenu().click();
                                waitUntilLoaderDisable();
                                waitForElementClickable(cm.getLogoutMenu(), 5);
                                waitForElementClickable(cp.getConnectedCompaniesOption(), 5);
                                cp.getConnectedCompaniesOption().click();
                                waitUntilLoaderDisable();
                                if (cp.getMenuDD().size() > 0) {
                                    cp.getMenuDD().get(0).click();
                                    Assert.assertTrue(cp.getEditCompanyDetailsOption().isDisplayed(), "Edit Company option not available in drop down menu");
                                }
                            } else {
                                try {
                                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                    Assert.assertFalse(cm.getConnectedMenu().isDisplayed(), "Connected menu is available without ViewConnected access");
                                } catch (NoSuchElementException ignored) {
                                }
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                            }
                        }
                    } else if (accessType.equalsIgnoreCase("EditWallets")) {
                        if (accessTypeList.contains(accessType)) {
                            if (accessTypeList.contains(getWalletID())) {
                                cm.getWalletsMenu().click();
                                waitUntilLoaderDisable();
                                waitForElementClickable(cm.getLogoutMenu(), 5);
                                Assert.assertTrue(wp.getCompanyCreateWalletBtn().isEnabled(), "Create Wallet button not available");
                                if (wp.getActivityBtn().size() > 0) {
                                    wp.getActivityDDBtn().get(0).click();
                                    waitForElementClickable(wp.getWalletEditOption(), 5);
                                    wp.getWalletEditOption().click();
                                    waitUntilLoaderDisable();
                                    Assert.assertTrue(driver.getCurrentUrl().contains("EditCashAccount"), "Edit wallet option redirected to wrong URL - " + driver.getCurrentUrl());
                                }
                            } else {
                                try {
                                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                    Assert.assertFalse(cm.getWalletsMenu().isDisplayed(), "Wallet menu is available without ViewWallets access");
                                } catch (NoSuchElementException ignored) {
                                }
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                            }
                        }
                    } else if (accessType.equalsIgnoreCase("ViewClaims")) {
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getClaimsMenu().isDisplayed(), "Claims menu not showing");
                            cm.getClaimsMenu().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("ClaimSummary"), "Claims menu click redirected to wrong URL - " + driver.getCurrentUrl());
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                Assert.assertFalse(cm.getClaimsMenu().isDisplayed(), "Claims menu is showing");
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("ViewReports")) {
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getReportsMenu().isDisplayed(), "Reports menu not showing");
                            cm.getReportsMenu().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("ViewReportByCompanyCredits"), "Reports menu click redirected to wrong URL - " + driver.getCurrentUrl());
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                Assert.assertFalse(cm.getReportsMenu().isDisplayed(), "Reports menu is showing");
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("ViewBeneficiaries")) {
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getBeneficiaryMenu().isDisplayed(), "Beneficiary menu not showing");
                            cm.getBeneficiaryMenu().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("CommunityMembers"), "Beneficiary menu click redirected to wrong URL - " + driver.getCurrentUrl());
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                Assert.assertFalse(cm.getBeneficiaryMenu().isDisplayed(), "Beneficiary menu is showing");
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("ViewConnected")) {
                        if (accessTypeList.contains(accessType)) {
                            Assert.assertTrue(cm.getConnectedMenu().isDisplayed(), "Connected menu not showing");
                            cm.getConnectedMenu().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedUsers"), "Connected menu click redirected to wrong URL - " + driver.getCurrentUrl());
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                Assert.assertFalse(cm.getConnectedMenu().isDisplayed(), "Connected menu is showing");
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    } else if (accessType.equalsIgnoreCase("ViewWallets")) {
                        if (accessTypeList.contains(accessType) || accessTypeList.contains(getWalletID())) {
                            Assert.assertTrue(cm.getWalletsMenu().isDisplayed(), "Wallet menu not showing");
                            cm.getWalletsMenu().click();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().contains("CashAccounts"), "Wallet menu click redirected to wrong URL - " + driver.getCurrentUrl());
                        } else {
                            try {
                                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                                Assert.assertFalse(cm.getWalletsMenu().isDisplayed(), "Wallet menu is showing");
                            } catch (NoSuchElementException ignored) {
                            }
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        }
                    }
                }
                break;
        }
    }

    public void setCustomAccess(String allAccessType) throws Exception {
        if (allAccessType.contains(","))
            accessTypeList = new ArrayList<>(Arrays.asList(allAccessType.trim().split(",")));
        else accessTypeList.add(allAccessType);
        ListIterator<String> iterator = accessTypeList.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase("wallet")) {
                iterator.set(getWalletID());
                /*if (getPropertyValue("environment", "environment").equalsIgnoreCase("SBox"))
                    iterator.set(getPropertyValue("sboxCAWalletId", "config"));
                else iterator.set(getPropertyValue("devCAWalletId", "config"));*/
            }
        }
        Thread.sleep(500);
        for (String accessType : allAccessTypeList) {
            if (accessType.equalsIgnoreCase("FundWallets")) {
                flagCheckBox(accessType, cap.getFundWalletCB());
            } else if (accessType.equalsIgnoreCase("BankDebit")) {
                flagCheckBox(accessType, cap.getAchDebitCB());
            } else if (accessType.equalsIgnoreCase("CreditOrDebitCard")) {
                flagCheckBox(accessType, cap.getCreditCardsCB());
            } else if (accessType.equalsIgnoreCase("ElectronicInvoice")) {
                flagCheckBox(accessType, cap.getElectronicInvoiceCB());
            } else if (accessType.equalsIgnoreCase("MoveFunds")) {
                flagCheckBox(accessType, cap.getMoveFundCB());
            } else if (accessType.equalsIgnoreCase("SendFunds")) {
                flagCheckBox(accessType, cap.getSendFundCB());
            } else if (accessType.equalsIgnoreCase("SimpleSend")) {
                flagCheckBox(accessType, cap.getSimpleSendCB());
            } else if (accessType.equalsIgnoreCase("AdvancedSend")) {
                flagCheckBox(accessType, cap.getAdvanceSendCB());
            } else if (accessType.equalsIgnoreCase("DraftPayments")) {
                flagCheckBox(accessType, cap.getDraftPaymentCB());
            } else if (accessType.equalsIgnoreCase("MassPayments")) {
                flagCheckBox(accessType, cap.getMassPaymentCB());
            } else if (accessType.equalsIgnoreCase("EmbeddedPayments")) {
                flagCheckBox(accessType, cap.getEmbeddedPaymentCB());
            } else if (accessType.equalsIgnoreCase("TransferFunds")) {
                flagCheckBox(accessType, cap.getTransferFundsPaymentCB());
            } else if (accessType.equalsIgnoreCase("BankAccount")) {
                flagCheckBox(accessType, cap.getTransferBankAccountCB());
            } else if (accessType.equalsIgnoreCase("BankCheck")) {
                flagCheckBox(accessType, cap.getBankCheckCB());
            } else if (accessType.equalsIgnoreCase("VisaDebit")) {
                flagCheckBox(accessType, cap.getVisaDebitCB());
            } else if (accessType.equalsIgnoreCase("CurrencyExchange")) {
                flagCheckBox(accessType, cap.getCurrencyExchangeCB());
            } else if (accessType.equalsIgnoreCase("CreateProgram")) {
                flagCheckBox(accessType, cap.getCreateProgramsCB());
            } else if (accessType.equalsIgnoreCase("LinkBanks")) {
                flagCheckBox(accessType, cap.getLinkBanksCB());
            } else if (accessType.equalsIgnoreCase("LinkBankTransfer")) {
                flagCheckBox(accessType, cap.getLinkBankTransferCB());
            } else if (accessType.equalsIgnoreCase("LinkBankFund")) {
                flagCheckBox(accessType, cap.getLinkBankFundCB());
            } else if (accessType.equalsIgnoreCase("ConnectedManager")) {
                flagCheckBox(accessType, cap.getConnectedManagerCB());
            } else if (accessType.equalsIgnoreCase("EditClaims")) {
                flagCheckBox(accessType, cap.getEditClaimCB());
            } else if (accessType.equalsIgnoreCase("EditBeneficiaries")) {
                flagCheckBox(accessType, cap.getEditBeneficiariesCB());
            } else if (accessType.equalsIgnoreCase("EditConnected")) {
                flagCheckBox(accessType, cap.getEditConnectedCB());
            } else if (accessType.equalsIgnoreCase("EditWallets")) {
                flagCheckBox(accessType, cap.getEditWalletCB());
            } else if (accessType.equalsIgnoreCase("ViewClaims")) {
                flagCheckBox(accessType, cap.getViewClaimCB());
            } else if (accessType.equalsIgnoreCase("ViewReports")) {
                flagCheckBox(accessType, cap.getViewReportCB());
            } else if (accessType.equalsIgnoreCase("ViewBeneficiaries")) {
                flagCheckBox(accessType, cap.getViewBeneficiariesCB());
            } else if (accessType.equalsIgnoreCase("ViewMassPayments")) {
                flagCheckBox(accessType, cap.getViewMassPaymentCB());
            } else if (accessType.equalsIgnoreCase("ViewConnected")) {
                flagCheckBox(accessType, cap.getViewConnectedCB());
            } else if (accessType.equalsIgnoreCase("ViewWallets")) {
                flagCheckBox(accessType, cap.getViewWalletsCB());
            } else if (accessType.equalsIgnoreCase(getWalletID())) {
                for (int i = 0; i < cap.getListOfWalletsId().size(); i++) {
                    if (cap.getListOfWalletsId().get(i).getText().contains(accessType)) {
                        flagCheckBox(accessType, cap.getListOfWallets().get(i));
                    }
                }
            }
        }
        waitForElementClickable(cap.getCustomAccessDoneBtn(), 5);
        cap.getCustomAccessDoneBtn().click();
        waitUntilLoaderDisable();
        //verify enabled access
        cap.getSetCustomAccessBtn().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("SponsorAdminAccess.aspx"), "Issue in set custom access button click");
        waitForElementClickable(cap.getCustomAccessDoneBtn(), 5);
        for (String accessType : allAccessTypeList) {
            if (accessType.equalsIgnoreCase("FundWallets")) {
                verifyEnabledCheckBox(accessType, cap.getFundWalletCB(), accessTypeList.contains("BankDebit") || accessTypeList.contains("CreditOrDebitCard") || accessTypeList.contains("ElectronicInvoice"));
            } else if (accessType.equalsIgnoreCase("BankDebit")) {
                verifyEnabledCheckBox(accessType, cap.getAchDebitCB(), false);
            } else if (accessType.equalsIgnoreCase("CreditOrDebitCard")) {
                verifyEnabledCheckBox(accessType, cap.getCreditCardsCB(), false);
            } else if (accessType.equalsIgnoreCase("ElectronicInvoice")) {
                verifyEnabledCheckBox(accessType, cap.getElectronicInvoiceCB(), false);
            } else if (accessType.equalsIgnoreCase("MoveFunds")) {
                verifyEnabledCheckBox(accessType, cap.getMoveFundCB(), false);
            } else if (accessType.equalsIgnoreCase("SendFunds")) {
                verifyEnabledCheckBox(accessType, cap.getSendFundCB(), accessTypeList.contains("SimpleSend") || accessTypeList.contains("AdvancedSend") || accessTypeList.contains("DraftPayments") || accessTypeList.contains("MassPayments") || accessTypeList.contains("EmbeddedPayments"));
            } else if (accessType.equalsIgnoreCase("SimpleSend")) {
                verifyEnabledCheckBox(accessType, cap.getSimpleSendCB(), false);
            } else if (accessType.equalsIgnoreCase("AdvancedSend")) {
                verifyEnabledCheckBox(accessType, cap.getAdvanceSendCB(), accessTypeList.contains("DraftPayments"));
            } else if (accessType.equalsIgnoreCase("DraftPayments")) {
                verifyEnabledCheckBox(accessType, cap.getDraftPaymentCB(), false);
            } else if (accessType.equalsIgnoreCase("MassPayment")) {
                verifyEnabledCheckBox(accessType, cap.getMassPaymentCB(), false);
            } else if (accessType.equalsIgnoreCase("EmbeddedPayment")) {
                verifyEnabledCheckBox(accessType, cap.getEmbeddedPaymentCB(), false);
            } else if (accessType.equalsIgnoreCase("TransferFunds")) {
                verifyEnabledCheckBox(accessType, cap.getTransferFundsPaymentCB(), accessTypeList.contains("BankAccount") || accessTypeList.contains("BankCheck") || accessTypeList.contains("VisaDebit"));
            } else if (accessType.equalsIgnoreCase("BankAccount")) {
                verifyEnabledCheckBox(accessType, cap.getTransferBankAccountCB(), false);
            } else if (accessType.equalsIgnoreCase("BankCheck")) {
                verifyEnabledCheckBox(accessType, cap.getBankCheckCB(), false);
            } else if (accessType.equalsIgnoreCase("VisaDebit")) {
                verifyEnabledCheckBox(accessType, cap.getVisaDebitCB(), false);
            } else if (accessType.equalsIgnoreCase("CurrencyExchange")) {
                verifyEnabledCheckBox(accessType, cap.getCurrencyExchangeCB(), false);
            } else if (accessType.equalsIgnoreCase("CreateProgram")) {
                verifyEnabledCheckBox(accessType, cap.getCreateProgramsCB(), false);
            } else if (accessType.equalsIgnoreCase("LinkBanks")) {
                verifyEnabledCheckBox(accessType, cap.getLinkBanksCB(), accessTypeList.contains("LinkBankTransfer") || accessTypeList.contains("LinkBankFund"));
            } else if (accessType.equalsIgnoreCase("LinkBankTransfer")) {
                verifyEnabledCheckBox(accessType, cap.getLinkBankTransferCB(), false);
            } else if (accessType.equalsIgnoreCase("LinkBankFund")) {
                verifyEnabledCheckBox(accessType, cap.getLinkBankFundCB(), false);
            } else if (accessType.equalsIgnoreCase("ConnectedManagers")) {
                verifyEnabledCheckBox(accessType, cap.getConnectedManagerCB(), false);
            } else if (accessType.equalsIgnoreCase("EditClaims")) {
                verifyEnabledCheckBox(accessType, cap.getEditClaimCB(), false);
            } else if (accessType.equalsIgnoreCase("EditBeneficiaries")) {
                verifyEnabledCheckBox(accessType, cap.getEditBeneficiariesCB(), false);
            } else if (accessType.equalsIgnoreCase("EditConnected")) {
                verifyEnabledCheckBox(accessType, cap.getEditConnectedCB(), false);
            } else if (accessType.equalsIgnoreCase("EditWallets")) {
                verifyEnabledCheckBox(accessType, cap.getEditWalletCB(), false);
            } else if (accessType.equalsIgnoreCase("ViewClaims")) {
                verifyEnabledCheckBox(accessType, cap.getViewClaimCB(), false);
            } else if (accessType.equalsIgnoreCase("ViewReports")) {
                verifyEnabledCheckBox(accessType, cap.getViewReportCB(), false);
            } else if (accessType.equalsIgnoreCase("ViewBeneficiaries")) {
                verifyEnabledCheckBox(accessType, cap.getViewBeneficiariesCB(), false);
            } else if (accessType.equalsIgnoreCase("ViewMassPayments")) {
                verifyEnabledCheckBox(accessType, cap.getViewMassPaymentCB(), false);
            } else if (accessType.equalsIgnoreCase("ViewConnected")) {
                verifyEnabledCheckBox(accessType, cap.getViewConnectedCB(), false);
            } else if (accessType.equalsIgnoreCase("ViewWallets")) {
                verifyEnabledCheckBox(accessType, cap.getViewWalletsCB(), accessTypeList.contains(getWalletID()));
            } else if (accessType.equalsIgnoreCase(getWalletID())) {
                for (int i = 0; i < cap.getListOfWalletsId().size(); i++) {
                    if (cap.getListOfWalletsId().get(i).getText().contains(accessType)) {
                        verifyEnabledCheckBox(accessType, cap.getListOfWallets().get(i), false);
                    }
                }
            }
        }
        waitForElementClickable(cap.getCustomAccessDoneBtn(), 5);
        cap.getCustomAccessDoneBtn().click();
        waitUntilLoaderDisable();
    }

    public void flagCheckBox(String accessType, WebElement element) {
        if (accessTypeList.contains(accessType)) {
            if (!element.isSelected()) {
                element.click();
                waitUntilLoaderDisable();
            }
        } else {
            if (element.isSelected()) {
                element.click();
                waitUntilLoaderDisable();
            }
        }
    }

    public void verifyEnabledCheckBox(String accessType, WebElement element, boolean condition) {
        if (accessTypeList.contains(accessType) || condition)
            Assert.assertTrue(element.isSelected(), accessType + " checkbox disabled");
        else Assert.assertFalse(element.isSelected(), accessType + " checkbox enabled");
    }

    public String getWalletID() throws Exception {
        String id;
        if (getPropertyValue("environment", "environment").equalsIgnoreCase("SBox"))
            id = getPropertyValue("sboxCAWalletId", "config");
        else id = getPropertyValue("devCAWalletId", "config");
        return id;
    }
}
