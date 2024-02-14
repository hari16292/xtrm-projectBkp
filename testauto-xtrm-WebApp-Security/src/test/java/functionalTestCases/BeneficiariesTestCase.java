package functionalTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.BeneficiariesPage;
import pageObject.advancedSendPages.DraftPaymentsPage;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;
import resource.RetryAnalyzer;

import java.util.List;

@Listeners(resource.Listeners.class)
public class BeneficiariesTestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    BeneficiariesPage bp;
    DraftPaymentsPage dpp;
    boolean draftAvailable = true;

    @BeforeSuite(groups = {"Beneficiaries", "CompanyRegression", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
        bp = new BeneficiariesPage(driver);
        dpp = new DraftPaymentsPage(driver);
    }

    @AfterSuite(groups = {"Beneficiaries", "CompanyRegression", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"Beneficiaries", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getBeneficiaryMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("CommunityMembers"), "Issue in Beneficiaries menu click");
    }

    @Test(dataProvider = "tc2_data", groups = {"Beneficiaries", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_personalBeneficiaries(String filter, String sort, String condition, String moreInfo) throws Exception {
        tc01_homePage();
        waitUntilLoaderDisable();
        waitForElementClickable(bp.getMoreInfoLink(), 5);
        if (moreInfo.equals("Yes")) {
            bp.getMoreInfoLink().click();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("COMPANY004"), "Redirect to more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Beneficiaries page");
        }
        Select filterDD = new Select(bp.getFilterDD());
        filterDD.selectByValue(filter);
        List<WebElement> userMail = bp.getPersonalBeneficiaryListMail();
        if (userMail.size() > 0 && !condition.equals("Add")) {
            int c = 0;
            for (WebElement mail : userMail) {
                if (mail.getText().equalsIgnoreCase(getPropertyValue("devBeneficiaryUserEmail", "config"))) {
                    conditions(condition, c, "Individual");
                    break;
                }
                c++;
            }
        } else
            conditions(condition, 0, "Individual");
    }

    @DataProvider
    public Object[][] tc2_data() {
        return new Object[][]{
                /*{"filter", "sort", "condition", "moreInfo"}*/
                {"all", "vLastName", "Add", "No"},
                {"all", "vLastName", "Send", "Yes"},
                {"all", "vLastName", "View Profile", "No"},
                {"all", "vLastName", "View Wallets", "No"},
                {"all", "vLastName", "View Linked Banks", "No"},
                {"all", "vLastName", "Remove", "No"},
        };
    }

    @Test(dataProvider = "tc3_data", groups = {"Beneficiaries", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_companyBeneficiaries(String filter, String condition, String moreInfo) throws Exception {
        tc01_homePage();
        waitUntilLoaderDisable();
        waitForElementClickable(bp.getMoreInfoLink(), 5);
        bp.getCompanyBeneficiaryButton().click();
        waitUntilLoaderDisable();
        waitForElementClickable(bp.getMoreInfoLink(), 5);
        if (moreInfo.equals("Yes")) {
            bp.getMoreInfoLink().click();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("COMPANY004"), "Redirect to more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in Beneficiaries page");
        }
        Select filterDD = new Select(bp.getFilterDD());
        filterDD.selectByValue(filter);
        waitUntilLoaderDisable();
        List<WebElement> companyName = bp.getCompanyBeneficiaryName();
        if (companyName.size() > 0) {
            int c = 1;
            for (WebElement company : companyName) {
                if (company.getText().equals(getPropertyValue("devBeneficiaryCompanyName", "config"))) {
                    conditions(condition, c, "Company");
                    break;
                }
                c++;
            }
        }
    }

    @DataProvider
    public Object[][] tc3_data() {
        return new Object[][]{
                /*{"filter", "condition", "moreInfo"}*/ //Filter value CN - Connected
                {"Advanced", "Send", "Yes"},
                {"Advanced", "View Profile", "No"},
                {"Advanced", "View Wallets", "No"},
                {"Advanced", "View Linked Banks", "No"},
                {"Advanced", "View Users", "No"},
                {"Advanced", "View Employees", "No"},
                {"Advanced", "Edit Company Details", "No"},
        };
    }

    @Test(groups = {"Beneficiaries", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
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

    public void conditions(String condition, int count, String type) throws Exception {
        switch (condition) {
            case "Send":
                waitForElementClickable(bp.getSendBtn().get(count), 5);
                bp.getSendBtn().get(count).click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("advancedsend/step1"), "Redirected to wrong url in send button click - " + driver.getCurrentUrl());
                break;
            case "Add":
                waitForElementVisible(bp.getSearchTxt(), 5);
                bp.getSearchTxt().sendKeys(getPropertyValue("devBeneficiaryUserEmail", "config"));
                waitForElementClickable(bp.getAddNewBeneficiaryBtn(), 5);
                bp.getAddBtn().click();
                waitUntilLoaderDisable();
                waitForElementVisible(bp.getMessage(), 5);
                Assert.assertTrue(bp.getMessage().getText().contains("successfully added to your community"), "Showing invalid add beneficiary message.");
                break;
            case "Remove":
                waitForElementClickable(bp.getMenuDD().get(count), 5);
                bp.getMenuDD().get(count).click();
                waitForElementClickable(bp.getRemoveOption(), 5);
                bp.getRemoveOption().click();
                waitUntilLoaderDisable();
                waitForElementVisible(bp.getMessage(), 5);
                Assert.assertTrue(bp.getMessage().getText().contains("successfully deleted from your beneficiaries"), "Showing invalid remove beneficiary message.");
                break;
            case "View Profile":
                waitForElementClickable(bp.getMenuDD().get(count), 5);
                bp.getMenuDD().get(count).click();
                waitForElementClickable(bp.getViewProfileOption(), 5);
                bp.getViewProfileOption().click();
                waitUntilLoaderDisable();
                if (type.equals("Individual"))
                    Assert.assertTrue(driver.getCurrentUrl().contains("FullProfileInView"), "Redirected to wrong url in Individual View profile option click - " + driver.getCurrentUrl());
                else
                    Assert.assertTrue(driver.getCurrentUrl().contains("SponFullProfileInView"), "Redirected to wrong url in Company View profile option click - " + driver.getCurrentUrl());
                break;
            case "View Wallets":
                waitForElementClickable(bp.getMenuDD().get(count), 5);
                bp.getMenuDD().get(count).click();
                waitForElementClickable(bp.getViewWalletsOption(), 5);
                bp.getViewWalletsOption().click();
                waitUntilLoaderDisable();
                if (type.equals("Individual"))
                    Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedUserCashAccounts"), "Redirected to wrong url in Individual View wallets option click - " + driver.getCurrentUrl());
                else
                    Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedCashAccounts"), "Redirected to wrong url in Company View wallets option click - " + driver.getCurrentUrl());
                if (bp.getActivityBtn().size() > 0) {
                    waitForElementClickable(bp.getActivityBtn().get(0), 5);
                    bp.getActivityBtn().get(0).click();
                    waitUntilLoaderDisable();
                    if (type.equals("Individual"))
                        Assert.assertTrue(driver.getCurrentUrl().contains("ViewConnectedAccountUserTransactions"), "Redirected to wrong url in Individual wallet activity option click - " + driver.getCurrentUrl());
                    else
                        Assert.assertTrue(driver.getCurrentUrl().contains("ViewConnectedAccountTransactions"), "Redirected to wrong url in Company wallet activity option click - " + driver.getCurrentUrl());
                }
                break;
            case "View Linked Banks":
                waitForElementClickable(bp.getMenuDD().get(count), 5);
                bp.getMenuDD().get(count).click();
                waitForElementClickable(bp.getViewLinkedBanksOption(), 5);
                bp.getViewLinkedBanksOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("ConnectedPayment"), "Redirected to wrong url in View wallets option click - " + driver.getCurrentUrl());
                break;
            case "View Users":
                waitForElementClickable(bp.getMenuDD().get(count), 5);
                bp.getMenuDD().get(count).click();
                waitForElementClickable(bp.getViewUsersOption(), 5);
                bp.getViewUsersOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("SponsorManageConnectedAdmins"), "Redirected to wrong url in View users option click - " + driver.getCurrentUrl());
                break;
            case "View Employees":
                waitForElementClickable(bp.getMenuDD().get(count), 5);
                bp.getMenuDD().get(count).click();
                waitForElementClickable(bp.getViewEmployeesOption(), 5);
                bp.getViewEmployeesOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("SponsorManageConnectedEmployees"), "Redirected to wrong url in View employees option click - " + driver.getCurrentUrl());
                break;
            case "Edit Company Details":
                waitForElementClickable(bp.getMenuDD().get(count), 5);
                bp.getMenuDD().get(count).click();
                waitForElementClickable(bp.getEditCompanyDetailsOption(), 5);
                bp.getEditCompanyDetailsOption().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("EditDealer"), "Redirected to wrong url in edit company details option click - " + driver.getCurrentUrl());
                break;
        }
    }

    public void redirectDraftPayment() {
        waitForElementClickable(cm.getDraftPaymentIcon(), 5);
        cm.getDraftPaymentIcon().click();
        cm.getDraftPaymentViewAll().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("/AllAwardsCampaign.aspx"), "Redirect to Draft got failed");
        waitForElementClickable(cm.getLogoutMenu(), 5);
    }

    public void dltDraftPayment() {
        waitForElementClickable(dpp.getDltDropDownBtn(), 5);
        dpp.getDltDropDownBtn().click();
        dpp.getDltOption().click();
        waitUntilLoaderDisable();
        Assert.assertEquals(dpp.getConfirmationMsg().getText(), "Draft deleted successfully.");
    }

}
