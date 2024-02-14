package functionalTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.TransferPage;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.TransactionDetailsPage;
import pageObject.commonPages.TransactionsPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

import java.util.List;

@Listeners(resource.Listeners.class)
public class TransferTestCase extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    int ranAmt;
    String OTP, selectedBankCurrency;
    TransferPage tp;

    public TransferTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"Transfer", "CompanyRegression", "UserRegression", "Sanity", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"Transfer", "CompanyRegression", "UserRegression", "Sanity", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"Transfer", "CompanyRegression", "UserRegression", "Sanity", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getTransferMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("transfer"), "Redirected to " + driver.getCurrentUrl());
    }

    @Test(dataProvider = "tc02_data", groups = {"Transfer", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_transferPage1(String fromWallet, String currencyID, String transferTo, String caseType, String moreInfo, String errorMsg) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc01_homePage();
            tp = new TransferPage(driver);
            Select wallet = new Select(tp.getFromWalletDD());
            if (getPropertyValue("type", "environment").equalsIgnoreCase("Company"))
                wallet.selectByValue(getWalletID(fromWallet) + "," + currencyID + "," + fromWallet);
            else {
                List<WebElement> walletOptions = wallet.getOptions();
                int len;
                for (WebElement option : walletOptions) {
                    len = option.getText().length();
                    if (option.getText().substring(len - 3, len).contains(fromWallet)) {
                        option.click();
                        break;
                    }
                }
            }
            Select transfer = new Select(tp.getTransferTypeDD());
            switch (transferTo) {
                case "Rapid Transfer":
                    if (getPropertyValue("type", "environment").equalsIgnoreCase("Company"))
                        transfer.selectByValue("5");
                    else transfer.selectByValue("7");
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getTransferTypeMsg(), 5);
                    Assert.assertEquals(tp.getTransferTypeMsg().getText(), "Instant Bank Transfer. USA Only.");
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getRapidReceivingBankDD(), 5);
                    if (caseType.equals("Positive")) {
                        Select bank = new Select(tp.getRapidReceivingBankDD());
                        if (bank.getOptions().size() > 1) {
                            bank.selectByIndex(1);
                        } else {
                            throw new Exception("Rapid card not linked");
                        }
                        tp.getRapidAmountTxt().sendKeys(String.valueOf(getRandomAmount()));
                    }
                    tp.getRapidContinueBtn().click();
                    waitUntilLoaderDisable();
                    if (caseType.equals("Negative")) {
                        waitForElementVisible(tp.getErrorMsg(), 5);
                        Assert.assertEquals(tp.getErrorMsg().getText(), errorMsg);
                    } else {
                        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("cardpay"), "Redirected to " + driver.getCurrentUrl() + " instead of card pay page.");
                    }
                    break;
                case "Standard Transfer":
                    transfer.selectByValue("1");
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getTransferTypeMsg(), 5);
                    Assert.assertEquals(tp.getTransferTypeMsg().getText(), "1-5 Business Day Bank Transfer. Worldwide.");
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getStandardReceivingBankDD(), 5);
                    Select bank = new Select(tp.getStandardReceivingBankDD());
                    if (bank.getOptions().size() > 1) {
                        bank.selectByIndex(1);
                        selectedBankCurrency = bank.getFirstSelectedOption().getText();
                        selectedBankCurrency = selectedBankCurrency.substring(selectedBankCurrency.indexOf("(") + 1, selectedBankCurrency.indexOf(")"));
                    } else {
                        throw new Exception("Standard Bank account not linked");
                    }
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getStandardAmountTxt(), 5);
                    if (caseType.equals("Positive"))
                        tp.getStandardAmountTxt().sendKeys(String.valueOf(getRandomAmount()));
                    tp.getStandardContinueBtn().click();
                    if (caseType.equals("Positive"))
                        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("pay"));
                    else if (caseType.equals("Negative")) {
                        Assert.assertTrue(tp.getStandardAmountTxt().getAttribute("class").contains("Req"), "Amount text box is not highlighted");
                        System.out.println("Error message not showing in Standard Transfer negative test case");
                    }
                    break;

                case "Virtual Prepaid Card":
                    transfer.selectByValue("4");
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getTransferTypeMsg(), 5);
                    Assert.assertEquals(tp.getTransferTypeMsg().getText(), "Instant Delivery. up to $1000 USD");
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getRecipientEmailPVVTxt(), 5);
                    Assert.assertEquals(tp.getRecipientEmailPVVTxt().getAttribute("value"), getLogin());
                    Assert.assertEquals(tp.getRecipientEmailPVVMsg().getText(), "Can be sent to you or gifted to any email recipient");
                    Select cardCurrency = new Select(tp.getPvvCardCurrencyDD());
                    cardCurrency.selectByValue(currencyID);
                    waitUntilLoaderDisable();
                    Assert.assertTrue(tp.getPvvCardTypeDD().isDisplayed(), "PVV Card Type Dropdown list not available in screen");
                    if (moreInfo.equals("Yes")) {
                        tp.getMoreInfoLink().click();
                        driver.switchTo().window(windowHandle(driver, "Child"));
                        if (getPropertyValue("type", "environment").equalsIgnoreCase("company"))
                            Assert.assertTrue(driver.getTitle().contains("TRANSFER001"), "Redirect to Payment Type more info page failed");
                        else
                            Assert.assertTrue(driver.getTitle().contains("Personal Accounts"), "Redirect to Payment Type more info page failed");
                        driver.close();
                        driver.switchTo().window(windowHandle(driver, "Parent"));
                        Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in redirect to Transfer page");
                        tp.getPvvMoreInfo().click();
                        driver.switchTo().window(windowHandle(driver, "Child"));
                        Assert.assertTrue(driver.getTitle().contains("USER020"), "Redirect to PVV Card currency more info page failed");
                        driver.close();
                        driver.switchTo().window(windowHandle(driver, "Parent"));
                        Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in redirect to Transfer page");
                    }
                    waitForElementVisible(tp.getPvvAmountTxt(), 5);
                    if (caseType.equals("Positive")) {
                        getRandomAmount();
                        tp.getPvvAmountTxt().sendKeys(String.valueOf(ranAmt));
                        tp.getPvvContinueBtn().click();
                        waitUntilLoaderDisable();
                        waitUntilLoaderDisable();
                        if (getPropertyValue("type", "environment").equalsIgnoreCase("company"))
                            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("giftcardpay"), "Redirected to " + driver.getCurrentUrl());
                        else
                            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("transfer"), "Redirected to " + driver.getCurrentUrl());
                    } else if (caseType.equals("Negative")) {
                        tp.getPvvContinueBtn().click();
                        waitUntilLoaderDisable();
                        waitForElementVisible(tp.getErrorMsg(), 5);
                        Assert.assertEquals(tp.getErrorMsg().getText(), errorMsg);
                    }
                    break;

                case "Bank Check":
                    if (getPropertyValue("type", "environment").equalsIgnoreCase("Company"))
                        transfer.selectByValue("3");
                    else transfer.selectByValue("5");
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getTransferTypeMsg(), 5);
                    Assert.assertEquals(tp.getTransferTypeMsg().getText(), "2-7 Days Check Delivery");
                    Assert.assertTrue(tp.getCheckAmountTxt().isDisplayed(), "Postal Address for Check is not available");
                    Assert.assertEquals(tp.getCheckCountryMsg().getText(), "Checks can only be sent to US addresses.");
                    if (caseType.equals("Positive")) {
                        getRandomAmount();
                        tp.getCheckAmountTxt().sendKeys(String.valueOf(ranAmt));
                        tp.getCheckContinueBtn().click();
                        waitUntilLoaderDisable();
                        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("checkdirectpay"), "Redirected to " + driver.getCurrentUrl());
                    } else if (caseType.equals("Negative")) {
                        tp.getCheckAddressLine1Txt().clear();
                        tp.getCheckAddressLine2Txt().clear();
                        Select state = new Select(tp.getCheckStateDD());
                        state.selectByValue("0");
                        tp.getCheckCityTxt().clear();
                        tp.getCheckPostalCodeTxt().clear();
                        tp.getCheckContinueBtn().click();
                        waitUntilLoaderDisable();
                        Assert.assertEquals(tp.getErrorMsg().getText(), errorMsg, "Error message not showing");
                        Assert.assertTrue(tp.getCheckAddressLine1Txt().getAttribute("class").contains("requiredHighlight"), "Address line 1 text box not highlighted as mandatory");
                        Assert.assertTrue(tp.getCheckStateDD().getAttribute("class").contains("requiredHighlight"), "State dropdown box not highlighted as mandatory");
                        Assert.assertTrue(tp.getCheckCityTxt().getAttribute("class").contains("requiredHighlight"), "City text box not highlighted as mandatory");
                        Assert.assertTrue(tp.getCheckPostalCodeTxt().getAttribute("class").contains("requiredHighlight"), "Postal Code text box not highlighted as mandatory");
                        Assert.assertTrue(tp.getCheckAmountTxt().getAttribute("class").contains("requiredHighlight"), "Amount text box not highlighted as mandatory");
                    }
                    break;

                case "Gift Card":
                    if (getPropertyValue("type", "environment").equalsIgnoreCase("user")) {
                        transfer.selectByValue("3");
                        waitUntilLoaderDisable();
                        waitForElementVisible(tp.getTransferTypeMsg(), 5);
                        Assert.assertEquals(tp.getTransferTypeMsg().getText(), "Over 500 global cards to choose from");
                        waitForElementVisible(tp.getRecipientEmailPVVTxt(), 5);
                        Assert.assertEquals(tp.getRecipientEmailPVVTxt().getAttribute("value"), getLogin());
                        Assert.assertEquals(tp.getRecipientEmailPVVMsg().getText(), "Can be sent to you or gifted to any email recipient");
                        cardCurrency = new Select(tp.getPvvCardCurrencyDD());
                        cardCurrency.selectByValue(currencyID);
                        waitUntilLoaderDisable();
                        Assert.assertTrue(tp.getPvvCardTypeDD().isDisplayed(), "Gift Card Type Dropdown list not available in screen");
                        if (moreInfo.equals("Yes")) {
                            tp.getMoreInfoLink().click();
                            driver.switchTo().window(windowHandle(driver, "Child"));
                            Assert.assertTrue(driver.getTitle().contains("Personal Accounts"), "Redirect to Payment Type more info page failed");
                            driver.close();
                            driver.switchTo().window(windowHandle(driver, "Parent"));
                            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in redirect to Transfer page");
                        }
                        waitForElementClickable(cm.getLogoutMenu(), 5);
                        Select retailerDD = new Select(tp.getGiftCardRetailerDD());
                        retailerDD.selectByIndex(1);
                        waitUntilLoaderDisable();
                        waitForElementClickable(cm.getLogoutMenu(), 5);
                        Thread.sleep(2000); //Loading gif not showing here
                        Select valueDD = new Select(tp.getGiftCardValueDD());
                        valueDD.selectByValue("U768149");
                        waitForElementVisible(tp.getPvvAmountTxt(), 5);
                        if (caseType.equals("Positive")) {
                            getRandomAmount();
                            tp.getPvvAmountTxt().sendKeys(String.valueOf(ranAmt));
                            tp.getPvvContinueBtn().click();
                            waitUntilLoaderDisable();
                            waitUntilLoaderDisable();
                            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("transfer"), "Redirected to " + driver.getCurrentUrl());
                        } else if (caseType.equals("Negative")) {
                            tp.getPvvContinueBtn().click();
                            waitUntilLoaderDisable();
                            waitForElementVisible(tp.getErrorMsg(), 5);
                            Assert.assertEquals(tp.getErrorMsg().getText(), errorMsg);
                        }
                    }
                    break;
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc02_data() {
        return new Object[][]{
                /*{fromWallet, currencyID, transferTo, caseType, moreInfo, errorMsg}*/
                {"USD", "1", "Rapid Transfer", "Positive", "No", ""},
                //{"USD", "1", "Rapid Transfer", "Negative", "No", "Please enter the missing fields"},
                {"USD", "1", "Standard Transfer", "Positive", "No", ""},
                //{"USD", "1", "Standard Transfer", "Negative", "No", ""},
                {"USD", "1", "Virtual Prepaid Card", "Positive", "Yes", ""},
                //{"USD", "1", "Virtual Prepaid Card", "Negative", "No", "Enter amount between 5.00 and 1,000.00"},
                {"USD", "1", "Bank Check", "Positive", "Yes", ""},
                //{"USD", "1", "Bank Check", "Negative", "No", "Please enter the missing fields"},
                {"USD", "1", "Gift Card", "Positive", "Yes", ""},
                //{"USD", "1", "Gift Card", "Negative", "No", "Enter amount between 0.01 and 1,000.00"},
        };
    }

    @Test(dataProvider = "tc03_data", groups = {"Transfer", "CompanyRegression", "UserRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_transferPage2(String fromWallet, String currencyID, String transferTo, String caseType, String moreInfo, String errorMsg) throws Exception {
        if((getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive") && caseType.equals("Positive")) || !getPropertyValue("tcType", "environment").equalsIgnoreCase("Positive")) {
            tc02_transferPage1(fromWallet, currencyID, transferTo, "Positive", "No", "");
            String transferAmount = null, subject = null;
            if (transferTo.equals("Standard Transfer") || transferTo.equals("Rapid Transfer") || transferTo.equals("Bank Check")) {
                waitForElementClickable(tp.getStandardVerificationDD(), 5);
                Assert.assertTrue(tp.getStandardAmount().getText().contains(String.valueOf(ranAmt)), "Amount mismatched in transfer");
                transferAmount = tp.getStandardTotalAmount().getText().substring(1, (tp.getStandardTotalAmount().getText().length() - 3)).trim();
                Select verification = new Select(tp.getStandardVerificationDD());
                verification.selectByVisibleText("Email");
                waitForElementClickable(tp.getStandardSendCodeBtn(), 5);
                tp.getStandardSendCodeBtn().click();
                waitUntilLoaderDisable();
                waitForElementVisible(tp.getStandardVerifyBtn(), 10);
                Assert.assertEquals(tp.getStandardOtpConfirmationMsg().getText(), "Verification code successfully sent to your email.", "OTP verification message not matched");
                if (caseType.equals("Positive")) {
                    OTP = getEmailOTP(getLogin(), "Your fund transfer verification code");
                    tp.getStandardOtpTxt().sendKeys(OTP);
                }
                tp.getStandardVerifyBtn().click();
                waitUntilLoaderDisable();
                waitUntilLoaderDisable();
                if (caseType.equals("Positive")) {
                    waitForElementVisible(tp.getOtpVerifyMsg(), 5);
                    Assert.assertEquals(tp.getOtpVerifyMsg().getText(), "Verified successfully. Click 'Transfer' to complete transaction.");
                    tp.getStandardTransferBtn().click();
                    waitUntilLoaderDisable();
                    waitUntilLoaderDisable();
                    if (transferTo.equals("Standard Transfer"))
                        subject = "Your transfer request is processing";
                    else if (transferTo.equals("Rapid Transfer"))
                        subject = "You have transferred money to your bank";
                    else subject = "Transfer from XTRM processed";
                } else if (caseType.equals("Negative")) {
                    waitForElementVisible(tp.getStandardVerificationMsg(), 5);
                    Assert.assertEquals(tp.getStandardVerificationMsg().getText(), "Enter 6 digit number");
                    tp.getStandardOtpTxt().sendKeys("123456");
                    tp.getStandardVerifyBtn().click();
                    waitUntilLoaderDisable();
                    waitForElementVisible(tp.getStandardVerificationMsg(), 5);
                    Assert.assertEquals(tp.getStandardVerificationMsg().getText(), "Invalid verification code. Please try again or select different authentication method.");
                }
            } else if (transferTo.equals("Virtual Prepaid Card") || (transferTo.equals("Gift Card") && getPropertyValue("type", "environment").equalsIgnoreCase("user"))) {
                waitForElementVisible(tp.getGiftCardPayAmount(), 5);
                Assert.assertTrue(tp.getGiftCardPayAmount().getText().contains(String.valueOf(ranAmt)), "Amount mismatched in GiftCardPay page");
                transferAmount = String.valueOf(ranAmt);
                Assert.assertEquals(tp.getGiftCardPayEmail().getText(), getLogin());
                if (caseType.equals("Positive"))
                    tp.getGiftCardPayAgreeCheckBox().click();
                tp.getPvvContinueBtn().click();
                if (caseType.equals("Positive")) {
                    waitUntilLoaderDisable();
                    Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("giftcarddetails"), "Redirected to " + driver.getCurrentUrl());
                    Assert.assertEquals(tp.getErrorMsg().getText(), "Purchase successful. Redemption information below and also sent via email.");
                    if (getPropertyValue("type", "environment").equalsIgnoreCase("company"))
                        Assert.assertTrue(tp.getGiftCardPayCopyLinkBtn().isDisplayed(), "Copy Link button not available");
                    tp.getStandardContinueBtn().click();
                    waitUntilLoaderDisable();
                    if (getPropertyValue("type", "environment").equalsIgnoreCase("user")) {
                        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("participanthome"), "Redirected to " + driver.getCurrentUrl());
                        handleTaxInfoPopup();
                        waitForElementClickable(cm.getCompanyLogo(), 5);
                    }
                    subject = "Prepaid Card " + fromWallet + " is here!";
                } else if (caseType.equals("Negative")) {
                    Assert.assertEquals(tp.getErrorMsg().getText(), errorMsg);
                }
            }
            if (caseType.equals("Positive")) {
                TransactionsPage tp = new TransactionsPage(driver);
                if (getPropertyValue("type", "environment").equalsIgnoreCase("Company")) {
                    Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("viewcashtransaction"), "Redirected to " + driver.getCurrentUrl());
                    waitForElementClickable(tp.getTxnDateRange(), 5);
                    tp.getTxnDateRange().click();
                    waitForElementClickable(tp.getTxnDateLast7DaysBtn(), 5);
                    tp.getTxnDateLast7DaysBtn().click();
                    waitUntilLoaderDisable();
                } else {
                    Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("participanthome"), "Redirected to " + driver.getCurrentUrl());
                    handleTaxInfoPopup();
                    waitForElementClickable(cm.getCompanyLogo(), 5);
                }
                Select txnFilter = new Select(tp.getTxnFilterDD());
                List<WebElement> filterOptions = txnFilter.getOptions();
                for (WebElement option : filterOptions) {
                    if (option.getText().equals("Transferred")) {
                        option.click();
                        break;
                    }
                }
                waitUntilLoaderDisable();
                Assert.assertTrue(tp.getTxnDetailsBtn().size() > 0, "Transaction list looks empty in wallet activity page");
                tp.getTxnDetailsBtn().get(0).click();
                waitUntilLoaderDisable();
                TransactionDetailsPage tdp = new TransactionDetailsPage(driver);
                if (!transferTo.equals("Virtual Prepaid Card") && !transferTo.equals("Gift Card")) {
                    Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("summary"), "Redirected to " + driver.getCurrentUrl());
                    if (fromWallet.equals(selectedBankCurrency) || !transferTo.equals("Standard Transfer")) {
                        waitForElementVisible(tdp.getTransferCurrency(), 10);
                        Assert.assertEquals(fromWallet, tdp.getTransferCurrency().getText(), "Transfer currency mismatch in transaction details. Expected currency is " + fromWallet + "actual currency is " + tdp.getTransferCurrency().getText());
                        Assert.assertTrue(transferAmount.contains(tdp.getTransferAmount().getText()), "Transfer amount mismatch in transaction details");
                    } else {
                        waitForElementVisible(tdp.getSettlementAmount(), 10);
                        Assert.assertEquals(fromWallet, tdp.getSettlementCurrency().getText(), "Settlement currency mismatch in transaction details. Expected currency is " + fromWallet + "actual currency is " + tdp.getSettlementCurrency().getText());
                        Assert.assertTrue(transferAmount.contains(tdp.getSettlementAmount().getText()), "Settlement amount mismatch in transaction details");
                    }
                }
                verifyEmailAmount(getLogin(), subject, transferAmount);
                if (!mailVerificationSuccess) {
                    mailVerificationSuccess = true;
                    String errMsg = mailVerificationErrMsg;
                    mailVerificationErrMsg = "";
                    throw new Exception("Transfer Type - " + transferTo + '\n' + errMsg);
                }
                if (getPropertyValue("type", "environment").equalsIgnoreCase("company")) {
                    waitForElementClickable(tdp.getDoneBtn(), 5);
                    tdp.getDoneBtn().click();
                } else {
                    if (transferTo.equals("Gift Card") || transferTo.equals("Virtual Prepaid Card")) {
                        waitForElementClickable(tdp.getGiftCardDoneBtn(), 5);
                        tdp.getGiftCardDoneBtn().click();
                    } else if (transferTo.equals("Rapid Transfer") || transferTo.equals("Bank Check") || transferTo.equals("Standard Transfer")) {
                        waitForElementClickable(tdp.getRapidDoneBtn(), 5);
                        tdp.getRapidDoneBtn().click();
                    }
                }
                waitUntilLoaderDisable();
                if (getPropertyValue("type", "environment").equalsIgnoreCase("company"))
                    Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("viewcashtransaction"), "Redirected to " + driver.getCurrentUrl());
                else
                    Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("participanthome"), "Redirected to " + driver.getCurrentUrl());
            }
        } else throw new SkipException("Invalid Run Condition");

    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{fromWallet, currencyID, transferTo, caseType, moreInfo, errorMsg}*/
                {"USD", "1", "Standard Transfer", "Positive", "No", ""},
                //{"USD", "1", "Standard Transfer", "Negative", "No", ""},
                {"USD", "1", "Rapid Transfer", "Positive", "No", ""},
                //{"USD", "1", "Rapid Transfer", "Negative", "No", ""},
                {"USD", "1", "Virtual Prepaid Card", "Positive", "No", ""},
                //{"USD", "1", "Virtual Prepaid Card", "Negative", "No", "Please agree the terms and conditions"},
                {"USD", "1", "Bank Check", "Positive", "Yes", ""},
                //{"USD", "1", "Bank Check", "Negative", "No", ""},
                {"USD", "1", "Gift Card", "Positive", "No", ""},
                //{"USD", "1", "Gift Card", "Negative", "No", "Please agree the terms and conditions"},
        };
    }

    /******************************************************************************************************************/
    public int getRandomAmount() {
        ranAmt = (int) (Math.random() * (49 - 5 + 1) + 5);
        return ranAmt;
    }
}
