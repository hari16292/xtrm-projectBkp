package functionalTestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.SendPage;
import pageObject.SimpleSendPage;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.TransactionDetailsPage;
import pageObject.commonPages.TransactionsPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

import java.util.List;

@Listeners(resource.Listeners.class)
public class SimpleSendTestCase extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    int ranAmt;

    SimpleSendPage ssp;
    String accountType;

    public SimpleSendTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"CompanyRegression", "UserRegression", "SimpleSend", "Security"})
    public void openBrowser() throws Exception {
        driver = BaseClass.driver;
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"CompanyRegression", "UserRegression", "SimpleSend", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"AdvanceSend", "CompanyRegression", "SimpleSend", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getSendMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("Send"), "Issue in Send menu click. Current URL - " + driver.getCurrentUrl());
    }

    @Test(dataProvider = "tc02_data", groups = {"SimpleSend", "CompanyRegression", "SimpleSend", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_send(String toAccount, String fromWallet, String amount, String fee, String condition, String caseType, String errMsg) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc01_homePage();
            accountType = getPropertyValue("type", "environment");
            if (accountType.equalsIgnoreCase("company")) {
                SendPage sp = new SendPage(driver);
                waitForElementVisible(sp.getSendBtn(), 5);
                sp.getSimpleSendGoBtn().click();
                waitUntilLoaderDisable();
            }
            Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSend"), "Issue in Simple Send page load. Current URL - " + driver.getCurrentUrl());
            ssp = new SimpleSendPage(driver);
            waitForElementClickable(ssp.getSendToSearchTxt(), 5);
            if (toAccount.equals("NewPersonal")) {
                if (caseType.equals("Positive")) {
                    ssp.getSendToSearchTxt().sendKeys(RandomStringUtils.randomAlphabetic(5) + "autotestuser@mailinator.com");
                } else {
                    ssp.getSendToSearchTxt().sendKeys("haritestuser1@mailinator.com");
                }
                waitForElementVisible(ssp.getRecipientListPopup(), 5);
                waitForElementClickable(ssp.getAddUserBtn(), 5);
                ssp.getAddUserBtn().click();
                waitUntilLoaderDisable();
                if (caseType.equals("Negative")) {
                    Assert.assertEquals(ssp.getErrMsg().getText(), errMsg, "Error message mismatched in new account creation");
                }
            } else if (toAccount.equals("NewCompany")) {
                String compName = RandomStringUtils.randomAlphabetic(5) + "AutoTestCompany";
                if (caseType.equals("Positive")) {
                    ssp.getSendToSearchTxt().sendKeys(compName);
                } else {
                    ssp.getSendToSearchTxt().sendKeys("hari test company");
                }
                waitForElementVisible(ssp.getRecipientListPopup(), 5);
                waitForElementClickable(ssp.getAddCompanyBtn(), 5);
                ssp.getAddCompanyBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("AddDealer"), "Issue in Add Company button click. Current URL - " + driver.getCurrentUrl());
                waitForElementVisible(ssp.getCompanyNameAvailMsg(), 5);
                if (caseType.equals("Negative")) {
                    Assert.assertEquals(ssp.getCompanyNameAvailMsg().getText(), "Company name not available as already used.");
                } else {
                    Assert.assertEquals(ssp.getCompanyNameAvailMsg().getText(), "Company name is available");
                    ssp.getAdminEmailTxt().sendKeys(compName + "@" + RandomStringUtils.randomAlphabetic(5) + ".com");
                    ssp.getAdminFNTxt().sendKeys("Auto");
                    ssp.getAdminLNTxt().sendKeys("Test");
                    ssp.getAddBtn().click();
                    waitUntilLoaderDisable();
                    Assert.assertTrue(driver.getCurrentUrl().contains("ManageDealers"), "Issue in Add button click. Current URL - " + driver.getCurrentUrl());
                    waitForElementVisible(ssp.getErrMsg(), 5);
                    Assert.assertEquals(ssp.getErrMsg().getText(), "Beneficiary company added and sent to XTRM for compliance approval. Once approved you will be able to pay them. More Info");
                }
            } else {
                ssp.getSendToSearchTxt().sendKeys(getRecipientEmail(toAccount));
                waitForElementVisible(ssp.getRecipientListPopup(), 5);
                waitForElementClickable(ssp.getSelectRecipientBtn().get(0), 5);
                if (ssp.getSelectRecipientBtn().size() > 1) {
                    for (int i = 0; i < ssp.getSelectRecipientBtn().size(); i++) {
                        if (ssp.getSelectRecipientType().get(i).getText().contains("Person") && toAccount.equals("Personal")) {
                            ssp.getSelectRecipientBtn().get(i).click();
                            break;
                        } else if (ssp.getSelectRecipientType().get(i).getText().contains("Company") && toAccount.equals("Company")) {
                            ssp.getSelectRecipientBtn().get(i).click();
                            break;
                        }
                    }
                } else ssp.getSelectRecipientBtn().get(0).click();
                waitUntilLoaderDisable();
            }
            if ((toAccount.equals("NewPersonal") && caseType.equals("Positive")) || toAccount.equals("Personal") || toAccount.equals("Company")) {
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSendPay"), "Issue in Simple Send Pay page load. Current URL - " + driver.getCurrentUrl());
                waitForElementClickable(ssp.getContinueBtn(), 5);
                Select wallet = new Select(ssp.getFromWalletDD());
                if (accountType.equalsIgnoreCase("company")) {
                    wallet.selectByValue(getWalletID(fromWallet));
                } else {
                    List<WebElement> walletOption = wallet.getOptions();
                    for (WebElement option : walletOption) {
                        if (option.getText().substring(0, 3).equals(fromWallet)) {
                            option.click();
                            break;
                        }
                    }
                }
                if (amount.equals("Random"))
                    amount = String.valueOf(getRandomAmount());
                else if (amount.equals("Empty"))
                    amount = "";
                ssp.getAmountTxt().sendKeys(amount);
                ssp.getDescriptionTxt().clear();
                ssp.getDescriptionTxt().sendKeys("Selenium Simple Send Auto Test from " + accountType + " to " + toAccount);
                simpleSendProceedButton(condition, 1, caseType, errMsg);
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @Test(dataProvider = "tc02_data", groups = {"SimpleSend", "CompanyRegression", "SimpleSend", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_sendConfirm(String toAccount, String fromWallet, String amount, String fee, String condition, String caseType, String errMsg) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            if ((toAccount.equals("NewPersonal") && caseType.equals("Positive")) || toAccount.equals("Personal") || toAccount.equals("Company")) {
                tc02_send(toAccount, fromWallet, "Random", fee, "Continue", "Positive", "");
                waitForElementClickable(ssp.getSendBtn(), 5);
                Assert.assertTrue(ssp.getConfirmAmountValue().getText().contains(String.valueOf(ranAmt)), "Amount mismatched in send confirm page");
                Select feeDD = new Select(ssp.getSelectFeeDD());
                if (fee.equals("Yes")) feeDD.selectByValue("0");
                else feeDD.selectByValue("1");
                Assert.assertEquals(ssp.getDescriptionValue().getText(), "Selenium Simple Send Auto Test from " + accountType + " to " + toAccount);
                simpleSendProceedButton(condition, 2, caseType, errMsg);
                if (caseType.equals("Positive") && condition.equals("Continue")) {
                    TransactionsPage tp = new TransactionsPage(driver);
                    if (accountType.equalsIgnoreCase("company")) {
                        waitForElementClickable(tp.getTxnDateRange(), 5);
                        tp.getTxnDateRange().click();
                        waitForElementClickable(tp.getTxnDateLast7DaysBtn(), 5);
                        tp.getTxnDateLast7DaysBtn().click();
                        waitUntilLoaderDisable();
                    }
                    Select txnFilter = new Select(tp.getTxnFilterDD());
                    List<WebElement> filterOptions = txnFilter.getOptions();
                    for (WebElement option : filterOptions) {
                        if (option.getText().equals("Sent")) {
                            option.click();
                            waitUntilLoaderDisable();
                            break;
                        }
                    }
                    waitUntilLoaderDisable();
                    waitUntilLoaderDisable();
                    waitForElementClickable(tp.getTxnDetailsBtn().get(0), 5);
                    tp.getTxnDetailsBtn().get(0).click();
                    waitUntilLoaderDisable();
                    TransactionDetailsPage tdp = new TransactionDetailsPage(driver);
                    String receiverEmail = null;
                    if (toAccount.equals("NewPersonal")) {
                        toAccount = "Personal";
                    }
                    if (accountType.equalsIgnoreCase("company")) {
                        Assert.assertTrue(driver.getCurrentUrl().contains("ViewReportByCampaign.aspx"), "Transaction Details Button not working. Current URL - " + driver.getCurrentUrl());
                        Assert.assertTrue(tp.getTxnPayMethod().get(0).getText().contains("Wallet"), "Expected Payment Method is Wallet but transaction initiated in " + tp.getTxnPayMethod().get(0).getText());
                        tdp.getShowTxnDetails().get(0).click();
                        waitUntilLoaderDisable();
                        Assert.assertTrue(driver.getCurrentUrl().contains("ViewCashTransactionDetails.aspx"), "Issue in transaction details view. Current URL - " + driver.getCurrentUrl());
                        Assert.assertEquals(tdp.getTxnDetailsMethod().getText(), "Wallet", "Expected Payment Method is Wallet but transaction initiated in " + tdp.getTxnDetailsMethod().getText());
                        Assert.assertEquals(tdp.getTxnDetailsType().getText(), toAccount, "Expected Type is " + toAccount + " but received  " + tdp.getTxnDetailsType().getText());
                        Assert.assertTrue(tdp.getTxnDetailsAmount().getText().replace(",", "").contains(String.valueOf(ranAmt)), "Expected Payment Amount is " + ranAmt + " but transferred amount is " + tdp.getTxnDetailsAmount().getText());
                        receiverEmail = tdp.getEmailID().getText();
                    } else {
                        Assert.assertTrue(driver.getCurrentUrl().contains("CashAccountDetails.aspx"), "Issue in transaction details view. Current URL - " + driver.getCurrentUrl());
                        Assert.assertEquals(tdp.getUserTxnDetailsMethod().getText(), "Wallet", "Expected Payment Method is Wallet but transaction initiated in " + tdp.getUserTxnDetailsMethod().getText());
                        Assert.assertTrue(tdp.getUserTxnDetailsAmount().getText().replace(",", "").contains(String.valueOf(ranAmt)), "Expected Payment Amount is " + ranAmt + " but transferred amount is " + tdp.getUserTxnDetailsAmount().getText());
                        receiverEmail = tdp.getUserEmailId().getText();
                    }
                    receiverEmail = receiverEmail.substring(receiverEmail.indexOf("(") + 1, receiverEmail.indexOf(")"));
                    receiverEmail = receiverEmail.substring(receiverEmail.indexOf(",") + 1, receiverEmail.length()).trim();
                    verifyEmailAmount(receiverEmail, "Payment received from", String.valueOf(ranAmt));
                }
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc02_data() {
        Object[][] data = {
                /*{"toAccount", "fromWallet", "amount", "fee", "condition", "caseType", "errMsg"}*/
                {"Personal", "USD", "Random", "Yes", "Continue", "Positive", ""},
                /*{"Personal", "USD", "Random", "Yes", "Cancel", "Positive", ""},
                {"Personal", "USD", "Random", "Yes", "Previous", "Positive", ""},
                {"Personal", "USD", "Empty", "Yes", "Continue", "Negative", "Enter amount greater than 0.00"},
                {"Personal", "USD", "0", "Yes", "Continue", "Negative", "Enter amount greater than 0.00"},
                {"Company", "USD", "Random", "Yes", "Continue", "Positive", ""},
                {"NewPersonal", "USD", "Random", "Yes", "Continue", "Positive", ""},
                {"NewPersonal", "USD", "Random", "Yes", "Continue", "Negative", "Email already exists"},*/
                /*{"NewCompany", "USD", "Random", "Yes", "Continue", "Positive", ""},
                {"NewCompany", "USD", "Random", "Yes", "Continue", "Negative", ""},*/

        };
        return data;
    }

    /******************************************************************************************************************/
    public int getRandomAmount() {
        ranAmt = (int) (Math.random() * (49 - 5 + 1) + 5);
        return ranAmt;
    }

    public void simpleSendProceedButton(String condition, int stepNumber, String caseType, String errMsg) {
        switch (condition) {
            case "Continue":
                if (caseType.equals("Positive")) {
                    if (stepNumber == 1) {
                        waitForElementClickable(ssp.getContinueBtn(), 5);
                        ssp.getContinueBtn().click();
                        waitUntilLoaderDisable();
                        Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSendConfirm"), "Issue in Continue button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                    } else if (stepNumber == 2) {
                        waitForElementClickable(ssp.getSendBtn(), 5);
                        ssp.getSendBtn().click();
                        waitUntilLoaderDisable();
                        if (accountType.equalsIgnoreCase("user")) {
                            Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSendSuccess"), "Issue in Send button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                            waitForElementClickable(ssp.getViewTxnBtn(), 5);
                            ssp.getViewTxnBtn().click();
                            waitUntilLoaderDisable();
                            handleTaxInfoPopup();
                            Assert.assertTrue(driver.getCurrentUrl().contains("ParticipantHome"), "Issue in View Transaction button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                        } else
                            Assert.assertTrue(driver.getCurrentUrl().contains("ViewCashTransaction"), "Issue in Send button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                    }
                } else {
                    if (stepNumber == 1) {
                        waitForElementClickable(ssp.getContinueBtn(), 5);
                        ssp.getContinueBtn().click();
                        waitUntilLoaderDisable();
                        Assert.assertTrue(ssp.getAmountTxt().getAttribute("class").contains("req"), "Amount Text box not highlighted");
                        if (accountType.equalsIgnoreCase("Company")) {
                            waitForElementClickable(ssp.getErrMsg(), 5);
                            Assert.assertEquals(ssp.getErrMsg().getText(), errMsg, "Error message not as expected for amount field in company account");
                        }
                    }
                }
                break;
            case "Previous":
                waitForElementClickable(ssp.getPreviousBtn(), 5);
                ssp.getPreviousBtn().click();
                waitUntilLoaderDisable();
                if (caseType.equals("Positive")) {
                    if (stepNumber == 1)
                        Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSend"), "Issue in Previous button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                    else if (stepNumber == 2)
                        Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSendPay"), "Issue in Previous button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                }
                break;
            case "Cancel":
                waitForElementClickable(ssp.getCancelBtn(), 5);
                ssp.getCancelBtn().click();
                waitUntilLoaderDisable();
                if (caseType.equals("Positive")) {
                    if (stepNumber == 1 || stepNumber == 2) {
                        if (accountType.equalsIgnoreCase("user"))
                            Assert.assertTrue(driver.getCurrentUrl().contains("SimpleSend"), "Issue in Cancel button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                        else
                            Assert.assertTrue(driver.getCurrentUrl().contains("Send"), "Issue in Cancel button click in step " + stepNumber + ". Current URL - " + driver.getCurrentUrl());
                    }

                }
                break;
        }
    }
}
