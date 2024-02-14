package functionalTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.ConnectedPage;
import pageObject.advancedSendPages.DraftPaymentsPage;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;
import resource.RetryAnalyzer;

import java.util.List;

@Listeners(resource.Listeners.class)
public class ConnectedTestCase extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    ConnectedPage cp;
    DraftPaymentsPage dpp;
    boolean draftAvailable = true;
    String userMailID = "haritestuser1@mailinator.com", compName = "corporon";

    @BeforeSuite(groups = {"Connected", "CompanyRegression", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
        cp = new ConnectedPage(driver);
        dpp = new DraftPaymentsPage(driver);
    }

    @AfterSuite(groups = {"Connected", "CompanyRegression", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"Connected", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getConnectedMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedUsers"), "Issue in Connected menu click");
    }

    @Test(dataProvider = "tc2_data", groups = {"Connected", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_connectedIndividuals(String filter, String condition, String moreInfo) throws Exception {
        tc01_homePage();
        waitUntilLoaderDisable();
        waitForElementClickable(cp.getMoreInfoLink(), 5);
        if (moreInfo.equals("Yes")) {
            cp.getMoreInfoLink().click();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("COMPANY004"), "Redirect to more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Beneficiaries page");
        }
        Select filterDD = new Select(cp.getFilterDD());
        if (!filter.equals(""))
            filterDD.selectByValue(filter);
        List<WebElement> userMail = cp.getPersonalBeneficiaryListMail();
        if (userMail.size() > 0) {
            int c = 1;
            for (WebElement mail : userMail) {
                if (mail.getText().contains(userMailID)) {
                    conditions(condition, c, "Individual");
                    break;
                }
                c++;
            }
        } else throw new Exception("No records available in Connected Individuals");
    }

    @DataProvider
    public Object[][] tc2_data() {
        return new Object[][]{
                /*{"filter", "condition", "moreInfo"}*/
                {"", "Send", "Yes"},
                {"", "View Profile", "No"},
                {"", "View Wallets", "No"},
                {"", "View Linked Banks", "No"},
        };
    }

    @Test(dataProvider = "tc3_data", groups = {"Connected", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_connectedCompanies(String filter, String condition, String moreInfo) throws Exception {
        tc01_homePage();
        waitUntilLoaderDisable();
        waitForElementClickable(cp.getConnectedCompaniesOption(), 5);
        cp.getConnectedCompaniesOption().click();
        waitUntilLoaderDisable();
        waitForElementClickable(cp.getMoreInfoLink(), 5);
        if (moreInfo.equals("Yes")) {
            cp.getMoreInfoLink().click();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("COMPANY004"), "Redirect to more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Beneficiaries page");
        }
        Select filterDD = new Select(cp.getFilterDD());
        filterDD.selectByValue(filter);
        waitUntilLoaderDisable();
        List<WebElement> userMail = cp.getPersonalBeneficiaryListMail();
        if (userMail.size() > 0) {
            int c = 1;
            for (WebElement mail : userMail) {
                if (mail.getText().contains(compName)) {
                    conditions(condition, c, "Company");
                    break;
                }
                c++;
            }
        } else throw new Exception("No records available in Connected Companies");
    }

    @DataProvider
    public Object[][] tc3_data() {
        return new Object[][]{
                /*{"filter", "condition", "moreInfo"}*/
                {"Advanced", "Send", "Yes"},
                {"Advanced", "Wallets", "No"},
                {"Advanced", "View Wallets", "No"},
                {"Advanced", "View Profile", "No"},
                {"Advanced", "View Linked Banks", "No"},
                {"Advanced", "View Users", "No"},
                {"Advanced", "View Employees", "No"},
                {"Advanced", "Edit Company Details", "No"},
        };
    }


    @Test(groups = {"Connected", "CompanyRegression"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc04_dltDraft() throws Exception {
        tc01_homePage();
        redirectDraftPayment();
        while (draftAvailable) {
            if (dpp.getContinueBtn().size() > 0)
                dltDraftPayment();
            else
                draftAvailable = false;
        }
    }


    /*****************************************************************************************************************/

    public void conditions(String condition, int count, String type) {
        switch (condition) {
            case "Send":
                if (type.equals("Company")) {
                    waitForElementClickable(cp.getMenuDD().get(count), 5);
                    cp.getMenuDD().get(count).click();
                    waitForElementClickable(cp.getCompSendPaymentOption(), 5);
                    cp.getCompSendPaymentOption().click();
                } else {
                    waitForElementClickable(cp.getSendBtn().get(count), 5);
                    cp.getSendBtn().get(count).click();
                }
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("advancedsend/step1"), "Redirected to wrong url in send button click - " + driver.getCurrentUrl());
                break;
            case "View Profile":
                waitForElementClickable(cp.getMenuDD().get(count), 5);
                cp.getMenuDD().get(count).click();
                waitForElementClickable(cp.getViewProfileOption(), 5);
                cp.getViewProfileOption().click();
                waitUntilLoaderDisable();
                if (type.equals("Individual"))
                    Assert.assertTrue(driver.getCurrentUrl().contains("FullProfileInView"), "Redirected to wrong url in Individual View profile option click - " + driver.getCurrentUrl());
                else
                    Assert.assertTrue(driver.getCurrentUrl().contains("SponFullProfileInView"), "Redirected to wrong url in Company View profile option click - " + driver.getCurrentUrl());
                break;
            case "View Wallets":
                waitForElementClickable(cp.getMenuDD().get(count), 5);
                cp.getMenuDD().get(count).click();
                waitForElementClickable(cp.getViewWalletsOption(), 5);
                cp.getViewWalletsOption().click();
                waitUntilLoaderDisable();
                if (type.equals("Individual"))
                    Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedUserCashAccounts"), "Redirected to wrong url in Individual View wallets option click - " + driver.getCurrentUrl());
                else
                    Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedCashAccounts"), "Redirected to wrong url in Company View wallets option click - " + driver.getCurrentUrl());
                if (cp.getActivityBtn().size() > 0) {
                    waitForElementClickable(cp.getActivityBtn().get(0), 5);
                    cp.getActivityBtn().get(0).click();
                    waitUntilLoaderDisable();
                    if (type.equals("Individual"))
                        Assert.assertTrue(driver.getCurrentUrl().contains("ViewConnectedAccountUserTransactions"), "Redirected to wrong url in Individual wallet activity option click - " + driver.getCurrentUrl());
                    else
                        Assert.assertTrue(driver.getCurrentUrl().contains("ViewConnectedAccountTransactions"), "Redirected to wrong url in Company wallet activity option click - " + driver.getCurrentUrl());
                }
                break;
            case "Wallets":
                waitForElementClickable(cp.getCompWalletBtn().get(count), 5);
                cp.getCompWalletBtn().get(count).click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedCashAccounts"), "Redirected to wrong url in Company Wallets button click - " + driver.getCurrentUrl());
                break;
            case "View Linked Banks":
                waitForElementClickable(cp.getMenuDD().get(count), 5);
                cp.getMenuDD().get(count).click();
                waitForElementClickable(cp.getViewLinkedBanksOption(), 5);
                cp.getViewLinkedBanksOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedPayment"), "Redirected to wrong url in View wallets option click - " + driver.getCurrentUrl());
                break;
            case "View Users":
                waitForElementClickable(cp.getMenuDD().get(count), 5);
                cp.getMenuDD().get(count).click();
                waitForElementClickable(cp.getViewUsersOption(), 5);
                cp.getViewUsersOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("SponsorManageConnectedAdmins"), "Redirected to wrong url in View users option click - " + driver.getCurrentUrl());
                break;
            case "View Employees":
                waitForElementClickable(cp.getMenuDD().get(count), 5);
                cp.getMenuDD().get(count).click();
                waitForElementClickable(cp.getViewEmployeesOption(), 5);
                cp.getViewEmployeesOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("SponsorManageConnectedEmployees"), "Redirected to wrong url in View employees option click - " + driver.getCurrentUrl());
                break;
            case "Edit Company Details":
                waitForElementClickable(cp.getMenuDD().get(count), 5);
                cp.getMenuDD().get(count).click();
                waitForElementClickable(cp.getEditCompanyDetailsOption(), 5);
                cp.getEditCompanyDetailsOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("EditDealer"), "Redirected to wrong url in edit company details option click - " + driver.getCurrentUrl());
                break;
        }
    }

    public void redirectDraftPayment() {
        waitForElementClickable(cm.getDraftPaymentIcon(), 5);
        cm.getDraftPaymentIcon().click();
        waitForElementVisible(cm.getDraftPaymentViewAll(), 5);
        cm.getDraftPaymentViewAll().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("/AllAwardsCampaign.aspx"), "Redirect to Draft got failed");
        waitForElementClickable(dpp.getDltDropDownBtn(), 5);
    }

    public void dltDraftPayment() {
        waitForElementClickable(dpp.getDltDropDownBtn(), 5);
        dpp.getDltDropDownBtn().click();
        dpp.getDltOption().click();
        waitUntilLoaderDisable();
        Assert.assertEquals(dpp.getConfirmationMsg().getText(), "Draft deleted successfully.");
    }

}
