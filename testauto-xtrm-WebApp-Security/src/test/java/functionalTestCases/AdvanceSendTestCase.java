package functionalTestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import pageObject.SendPage;
import pageObject.advancedSendPages.*;
import pageObject.commonPages.CommonMenu;
import pageObject.commonPages.TransactionDetailsPage;
import pageObject.commonPages.TransactionsPage;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class AdvanceSendTestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    AdvanceSendStep1Page asp1;
    AdvanceSendStep2Page asp2;
    AdvanceSendStep3Page asp3;
    AdvanceSendStep4Page asp4;
    AdvanceSendStep5Page asp5;
    AdvanceSendStep6Page asp6;
    DraftPaymentsPage dpp;
    String balAmt;
    JavascriptExecutor js;
    int ranAmt, randomRecentPayee, selectedRecipientsCount;
    boolean dltDraft = true, draftAvailable = true;

    public AdvanceSendTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"AdvanceSend", "CompanyRegression", "Sanity", "DraftPayment", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
    }

    @AfterSuite(groups = {"AdvanceSend", "CompanyRegression", "Sanity", "DraftPayment", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"AdvanceSend", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getSendMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("Send"), "Issue in Send menu click");
    }

    @Test(groups = {"AdvanceSend", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_sendPage() throws Exception {
        tc01_homePage();
        SendPage sp = new SendPage(driver);
        waitForElementVisible(sp.getSendBtn(), 5);
        sp.getAdvanceGoBtn().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("AdvancedSend"), "Issue in AdvancedSend click");
    }

    @Test(dataProvider = "tc03_data", groups = {"AdvanceSend", "CompanyRegression"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_advanceSendStep1(String paymentType, String fromWallet, String program, String paymentMethod, String amount, String moreInfo, String condition, String caseType, String errMsg) throws Exception {
        tc02_sendPage();
        js = (JavascriptExecutor) driver;
        asp1 = new AdvanceSendStep1Page(driver);
        if (moreInfo.equals("Yes")) {
            asp1.getPaymentTypeMoreInfo().click();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("COMPANY007"), "Redirect to Payment Type more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in AdvancedSend");
            asp1.getPaymentMethodMoreInfo().click();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("SEND001"), "Redirect to Payment Type more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM", "Issue in AdvancedSend");
        }
        waitForElementClickable(asp1.getPaymentTypeDD(), 5);
        Select paymentTypeDD = new Select(asp1.getPaymentTypeDD());
        switch (paymentType) {
            case "Personal":
                paymentTypeDD.selectByVisibleText("Personal");
                break;
            case "Company":
                paymentTypeDD.selectByVisibleText("Company");
                break;
            case "Employer":
                paymentTypeDD.selectByVisibleText("Employer");
                break;
        }
        waitForElementClickable(asp1.getPaymentMethodDD(), 5);
        if (paymentType.equals("Employer") && caseType.equals("Positive")) {
            waitForElementVisible(asp1.getEmployerSearchTxt(), 5);
            asp1.getEmployerSearchTxt().sendKeys(getRecipientEmail("CompanyName"));
            waitForElementVisible(asp1.getEmployerListPopup(), 5);
            waitForElementClickable(asp1.getEmployerList().get(0), 5);
            boolean isEmployerListAvailable = false;
            for (int i = 0; i < asp1.getEmployerList().size(); i++) {
                waitForElementClickable(asp1.getEmployerListSelectBtn().get(i), 5);
                if (asp1.getEmployerList().get(i).getText().equals(getRecipientEmail("CompanyName"))) {
                    asp1.getEmployerListSelectBtn().get(i).click();
                    isEmployerListAvailable = true;
                    break;
                }
            }
            if (!isEmployerListAvailable) {
                throw new Exception("Searched company " + getRecipientEmail("Company") + " is not listed in employer popup");
            }
        }
        Select paymentMethodDD = new Select(asp1.getPaymentMethodDD());
        switch (paymentMethod) {
            case "Select":
                paymentMethodDD.selectByVisibleText("Select Payment Method");
                break;
            case "Wallet":
                paymentMethodDD.selectByVisibleText("Wallet (Recommended)");
                break;
            case "Bank":
                paymentMethodDD.selectByVisibleText("Direct to Bank");
                break;
            case "Virtual Prepaid Card":
                paymentMethodDD.selectByVisibleText("Direct to Prepaid Virtual Card");
                break;
            case "Digital Gift Card":
                paymentMethodDD.selectByVisibleText("Direct to Digital Gift Card");
                break;
            case "Check":
                paymentMethodDD.selectByVisibleText("Direct to Check");
                break;
            case "Reward Link":
                paymentMethodDD.selectByVisibleText("Direct to Reward Link");
                break;
        }
        Select fromWalletDD = new Select(asp1.getFromWalletDD());
        fromWalletDD.selectByValue(getWalletID(fromWallet));
        Select programDD = new Select(asp1.getProgramDD());
        programDD.selectByValue(getProgramID(program));
        asp1.getDescriptionTxt().sendKeys("Selenium Advance Send Auto Test for Company to " + paymentType + " and payment method is " + paymentMethod);
        if (!paymentMethod.equals("Select") && !paymentMethod.equals("Digital Gift Card") && !paymentMethod.equals("Virtual Prepaid Card") && !paymentMethod.equals("Reward Link")) {
            waitForElementVisible(asp1.getAmountTxt(), 5);
            switch (amount) {
                case "Random":
                    getRandomAmount();
                    amount = String.valueOf(ranAmt);
                    break;
                case "":
                    amount = "";
                    break;
                case "0":
                    amount = "0";
                    break;
            }
            js.executeScript("arguments[0].value = '" + amount + "';", asp1.getAmountTxt());
            Thread.sleep(500); //Entered amount got deleted after Next button click without timeout
            asp1.getAmountTxt().clear();
            Thread.sleep(500); //Entered amount got deleted after Next button click without timeout
            asp1.getAmountTxt().sendKeys(amount);
        }
        dpp = new DraftPaymentsPage(driver);
        if (paymentMethod.equals("Digital Gift Card") || paymentMethod.equals("Virtual Prepaid Card") || paymentMethod.equals("Reward Link")) {
            advSendProceedButton(condition, 0, "Positive", "");
            Assert.assertTrue(driver.getCurrentUrl().contains("GiftCard.aspx"));
            waitForElementClickable(asp1.getGiftCardType(), 5);
            asp1.getGiftCardType().isDisplayed();
            if (caseType.equals("Positive")) {
                Select giftCardType = new Select(asp1.getGiftCardType());
                int giftCard = (int) (Math.random() * (giftCardType.getOptions().size() - 0 + 1) + 0);
                if (giftCard == 0)
                    giftCardType.selectByIndex(giftCard);
                else
                    giftCardType.selectByIndex(giftCard - 1);
                boolean dgcTxtAvailable = false;
                boolean dgcFixedValAvailable = false;
                try {
                    asp1.getGiftCardAmountTxt().isDisplayed();
                    dgcTxtAvailable = true;
                } catch (Exception e) {
                    try {
                        asp1.getGiftCardFixedAmountValue().isDisplayed();
                        dgcFixedValAvailable = true;
                    } catch (Exception exp) {
                        e.printStackTrace();
                    }
                }
                if (dgcTxtAvailable) {
                    waitForElementVisible(asp1.getGiftCardAmountTxt(), 5);
                    float minVal = Float.parseFloat((String) js.executeScript("return arguments[0].innerHTML;", asp1.getHiddenGiftCardMinAmount()));
                    float maxVal = Float.parseFloat((String) js.executeScript("return arguments[0].innerHTML;", asp1.getHiddenGiftCardMaxAmount()));
                    if (amount.equals("Random")) {
                        getRandomAmount();
                        amount = String.valueOf(ranAmt);
                    }
                    ranAmt = (int) (Math.random() * (maxVal - minVal + 1) + minVal);
                    if (ranAmt > Integer.parseInt(amount) && Integer.parseInt(amount) > minVal) {
                        ranAmt = Integer.parseInt(amount);
                    }
                    asp1.getGiftCardAmountTxt().sendKeys(String.valueOf(ranAmt));
                    Thread.sleep(1000); //given amount deleted randomly, so need to hold execution sometime
                } else if (dgcFixedValAvailable) {
                    ranAmt = Integer.parseInt(asp1.getGiftCardFixedAmountValue().getText());
                } else {
                    throw new Exception("Some exception occurred in " + paymentMethod);
                }
            }
        }
        advSendProceedButton(condition, 1, caseType, errMsg);

    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{PaymentType, fromWallet, program, paymentMethod, amount, moreInfo, condition, caseType, errMsg}*/
                /*Test data for User Wallet*/
                {"Personal", "USD", "Campaign", "Wallet", "Random", "Yes", "Next", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Random", "No", "Cancel", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Random", "No", "SaveDraft", "Positive", ""},
                {"Personal", "USD", "Campaign", "Select", "Random", "No", "Next", "Negative", "Select payment method"},
                {"Personal", "USD", "Campaign", "Wallet", "0", "No", "Next", "Negative", "Enter amount greater than 0."},
                /*Test data for User Bank*/
                {"Personal", "USD", "Campaign", "Bank", "Random", "No", "Next", "Positive", ""},
                /*Test data for User Bank Check*/
                {"Personal", "USD", "Campaign", "Check", "Random", "No", "Next", "Positive", ""},
                /*Test data for User Digital Gift Card*/
                {"Personal", "USD", "Campaign", "Digital Gift Card", "Random", "No", "Next", "Positive", ""},
                {"Personal", "USD", "Campaign", "Digital Gift Card", "", "No", "Next", "Negative", "Enter amount"},
                /*Test data for User Virtual Prepaid Card*/
                {"Personal", "USD", "Campaign", "Virtual Prepaid Card", "Random", "No", "Next", "Positive", ""},
                {"Personal", "USD", "Campaign", "Virtual Prepaid Card", "", "No", "Next", "Negative", "Enter amount"},
                /*Test data for User Reward Link*/
                {"Personal", "USD", "Campaign", "Reward Link", "Random", "No", "Next", "Positive", ""},
                {"Personal", "USD", "Campaign", "Reward Link", "", "No", "Next", "Negative", "Enter amount"},
                /*Test data for Company Wallet*/
                {"Company", "USD", "Campaign", "Wallet", "Random", "No", "Next", "Positive", ""},
                /*Test data for Company Bank*/
                {"Company", "USD", "Campaign", "Bank", "Random", "No", "Next", "Positive", ""},
                /*Test data for Company Bank Check*/
                {"Company", "USD", "Campaign", "Check", "Random", "No", "Next", "Positive", ""},
                /*Test data for Employer Wallet*/
                {"Employer", "USD", "Campaign", "Wallet", "Random", "No", "Next", "Positive", ""},
                {"Employer", "USD", "Campaign", "Wallet", "Random", "No", "Next", "Negative", "Select Employer"},
        };
    }

    @Test(dataProvider = "tc04_data", groups = {"AdvanceSend", "CompanyRegression"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc04_advanceSendStep2(String paymentType, String fromWallet, String program, String paymentMethod, String condition, String caseType, String errMsg) throws Exception {
        asp2 = new AdvanceSendStep2Page(driver);
        tc03_advanceSendStep1(paymentType, fromWallet, program, paymentMethod, String.valueOf(getRandomAmount()), "No", "Next", "Positive", "");
        waitUntilLoaderDisable();
        waitForElementClickable(asp2.getRecipientSearch(), 20);
        if (caseType.equals("Positive")) {
            waitUntilLoaderDisable();
            if (asp2.getRecentPayeeAddBtn().size() > 0) {
                waitForElementClickable(asp2.getRecentPayeeAddBtn().get(0), 5);
                randomRecentPayee = (int) (Math.random() * ((asp2.getRecentPayeeAddBtn().size() - 1) - 0 + 1) + 0);
                switch (paymentType) {
                    case "Personal":
                        selectRecentPayee(paymentType);
                        asp2.getRecipientSearch().sendKeys(getRecipientEmail(paymentType));
                        break;
                    case "Company":
                        selectRecentPayee(paymentType);
                        asp2.getRecipientSearch().sendKeys(getRecipientEmail("CompanyName"));
                        break;
                    case "Employer":
                        selectRecentPayee("Employee");
                        asp2.getRecipientSearch().sendKeys(getRecipientEmail("Employee"));
                        break;
                }
            }
            waitForElementClickable(asp2.getAddUser(), 5);
            asp2.getAddUser().click();
            waitUntilLoaderDisable();
            if (paymentMethod.equals("Check")) {
                try {
                    Assert.assertFalse(asp2.getCheckMissing().size() > 0, "Recipient account personal details are missing");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (paymentMethod.equals("Bank")) {
            Select benBank;
            if (asp2.getSelectBank().size() > 1) {
                for (int i = 0; i < asp2.getSelectBank().size(); i++) {
                    benBank = new Select(asp2.getSelectBank().get(i));
                    if (benBank.getOptions().size() > 1) {
                        benBank.selectByIndex(1);
                    } else {
                        //asp2.getDltSelectedRecipientBtn().get(i).click();
                        throw new Exception("Beneficiary Bank not linked");
                    }
                }
            } else {
                benBank = new Select(asp2.getSelectBank().get(0));
                if (benBank.getOptions().size() > 1) {
                    benBank.selectByIndex(1);
                } else {
                    //asp2.getDltSelectedRecipientBtn().get(0).click();
                    throw new Exception("Beneficiary Bank not linked");
                }
            }
        }
        if (asp2.getSelectedRecipientList().size() > 0) {
            selectedRecipientsCount = asp2.getSelectedRecipientList().size();
        }
        advSendProceedButton(condition, 2, caseType, errMsg);

    }

    @DataProvider
    public Object[][] tc04_data() {
        return new Object[][]{
                /*{paymentType, fromWallet, program, paymentMethod, condition, caseType, errMsg}*/
                /*Test data for User Wallet*/
                {"Personal", "USD", "Campaign", "Wallet", "Next", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Cancel", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Previous", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "SaveDraft", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Next", "Negative", "Select at least 1 recipient."},
                /*Test data for User Bank*/
                {"Personal", "USD", "Campaign", "Bank", "Next", "Positive", ""},
                /*Test data for User Check*/
                {"Personal", "USD", "Campaign", "Check", "Next", "Positive", ""},
                /*Test data for User Digital Gift Card*/
                {"Personal", "USD", "Campaign", "Digital Gift Card", "Next", "Positive", ""},
                /*Test data for User Virtual Prepaid Card*/
                {"Personal", "USD", "Campaign", "Virtual Prepaid Card", "Next", "Positive", ""},
                /*Test data for User Reward Link*/
                {"Personal", "USD", "Campaign", "Reward Link", "Next", "Positive", ""},
                /*Test data for Company Wallet*/
                {"Company", "USD", "Campaign", "Wallet", "Next", "Positive", ""},
                /*Test data for Company Bank*/
                {"Company", "USD", "Campaign", "Bank", "Next", "Positive", ""},
                /*Test data for Company Check*/
                {"Company", "USD", "Campaign", "Check", "Next", "Positive", ""},
                /*Test data for Employer Wallet*/
                {"Employer", "USD", "Campaign", "Wallet", "Next", "Positive", ""},
        };
    }

    @Test(dataProvider = "tc05_data", groups = {"AdvanceSend", "CompanyRegression"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc05_advanceSendStep3(String paymentType, String fromWallet, String program, String paymentMethod, String condition, String caseType, String errMsg) throws Exception {
        asp3 = new AdvanceSendStep3Page(driver);
        tc04_advanceSendStep2(paymentType, fromWallet, program, paymentMethod, "Next", "Positive", "");
        waitUntilLoaderDisable();
        waitForElementVisible(asp3.getAdditionalCommentsTxt().get(0), 5);
        if (asp3.getAdditionalCommentsTxt().size() > 0) {
            for (int i = 0; i < asp3.getAdditionalCommentsTxt().size(); i++) {
                Assert.assertEquals(asp3.getAdditionalCommentsTxt().get(i).getAttribute("value"), "Selenium Advance Send Auto Test for Company to " + paymentType + " and payment method is " + paymentMethod);
            }
        }
        for (int i = 0; i < asp3.getAmountTxt().size(); i++) {
            Assert.assertEquals(asp3.getAmountTxt().get(0).getAttribute("value"), ranAmt + ".00");
        }
        if (caseType.equals("Negative")) {
            if (asp3.getDltRecipientsPayment().size() > 0) {
                for (int i = 0; i < asp3.getDltRecipientsPayment().size(); i++) {
                    asp3.getDltRecipientsPayment().get(i).click();
                }
                Thread.sleep(1000);//to avoid invalid error message "Amount Should be greater than zero" in negative scenario
            }
        }
        advSendProceedButton(condition, 3, caseType, errMsg);
        waitUntilLoaderDisable();
    }

    @DataProvider
    public Object[][] tc05_data() {
        return new Object[][]{
                /*{paymentType, fromWallet, program, paymentMethod, condition, caseType, errMsg}*/
                /*Test data for User Wallet*/
                {"Personal", "USD", "Campaign", "Wallet", "Next", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Cancel", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Previous", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "SaveDraft", "Positive", ""},
                {"Personal", "USD", "Campaign", "Wallet", "Next", "Negative", ""},
                /*Test data for User Bank*/
                {"Personal", "USD", "Campaign", "Bank", "Next", "Positive", ""},
                /*Test data for User Check*/
                {"Personal", "USD", "Campaign", "Check", "Next", "Positive", ""},
                /*Test data for User Digital Gift Card*/
                {"Personal", "USD", "Campaign", "Digital Gift Card", "Next", "Positive", ""},
                /*Test data for User Virtual Prepaid Card*/
                {"Personal", "USD", "Campaign", "Virtual Prepaid Card", "Next", "Positive", ""},
                /*Test data for User Reward Link*/
                {"Personal", "USD", "Campaign", "Reward Link", "Next", "Positive", ""},
                /*Test data for Company Wallet*/
                {"Company", "USD", "Campaign", "Wallet", "Next", "Positive", ""},
                /*Test data for Company Bank*/
                {"Company", "USD", "Campaign", "Bank", "Next", "Positive", ""},
                /*Test data for Company Check*/
                {"Company", "USD", "Campaign", "Check", "Next", "Positive", ""},
                /*Test data for Employer Wallet*/
                {"Employer", "USD", "Campaign", "Wallet", "Next", "Positive", ""},
        };
    }

    @Test(dataProvider = "tc06_data", groups = {"AdvanceSend", "CompanyRegression"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc06_advanceSendStep4(String paymentType, String fromWallet, String program, String paymentMethod, String condition, String caseType) throws Exception {
        asp4 = new AdvanceSendStep4Page(driver);
        tc05_advanceSendStep3(paymentType, fromWallet, program, paymentMethod, "Next", "Positive", "");
        Assert.assertTrue(asp4.getSummaryAmount().getText().contains(String.valueOf(ranAmt * selectedRecipientsCount)), "Total Summary Amount is mismatched in step4");
        advSendProceedButton(condition, 4, caseType, "");
    }

    @DataProvider
    public Object[][] tc06_data() {
        return new Object[][]{
                /*{paymentType, fromWallet, program, paymentMethod, condition, caseType}*/
                /*Test data for User Wallet*/
                {"Personal", "USD", "Campaign", "Wallet", "Next", "Positive"},
                {"Personal", "USD", "Campaign", "Wallet", "Cancel", "Positive"},
                {"Personal", "USD", "Campaign", "Wallet", "Previous", "Positive"},
                {"Personal", "USD", "Campaign", "Wallet", "SaveDraft", "Positive"},
                /*Test data for User Bank*/
                {"Personal", "USD", "Campaign", "Bank", "Next", "Positive"},
                /*Test data for User Check*/
                {"Personal", "USD", "Campaign", "Check", "Next", "Positive"},
                /*Test data for User Digital Gift Card*/
                {"Personal", "USD", "Campaign", "Digital Gift Card", "Next", "Positive"},
                /*Test data for User Virtual Prepaid Card*/
                {"Personal", "USD", "Campaign", "Virtual Prepaid Card", "Next", "Positive"},
                /*Test data for User Reward Link*/
                {"Personal", "USD", "Campaign", "Reward Link", "Next", "Positive"},
                /*Test data for Company Wallet*/
                {"Company", "USD", "Campaign", "Wallet", "Next", "Positive"},
                /*Test data for Company Bank*/
                {"Company", "USD", "Campaign", "Bank", "Next", "Positive"},
                /*Test data for Company Check*/
                {"Company", "USD", "Campaign", "Check", "Next", "Positive"},
                /*Test data for Employer Wallet*/
                {"Employer", "USD", "Campaign", "Wallet", "Next", "Positive"},
        };
    }

    @Test(dataProvider = "tc07_data", groups = {"AdvanceSend", "CompanyRegression"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc07_advanceSendStep5(String paymentType, String fromWallet, String program, String paymentMethod, String condition, String caseType) throws Exception {
        asp5 = new AdvanceSendStep5Page(driver);
        tc06_advanceSendStep4(paymentType, fromWallet, program, paymentMethod, "Next", "Positive");
        waitForElementVisible(asp5.getAmount(), 10);
        Assert.assertTrue(asp5.getAmount().getText().replace(",", "").contains(String.valueOf(ranAmt * selectedRecipientsCount)), "Total amount mismatched in step5");
        balAmt = asp5.getRemainingBalAmt().getText();
        advSendProceedButton(condition, 5, caseType, "");
        waitUntilLoaderDisable();
    }

    @DataProvider
    public Object[][] tc07_data() {
        return new Object[][]{
                /*{paymentType, fromWallet, program, paymentMethod, condition, caseType}*/
                /*Test data for User Wallet*/
                {"Personal", "USD", "Campaign", "Wallet", "Next", "Positive"},
                {"Personal", "USD", "Campaign", "Wallet", "Cancel", "Positive"},
                {"Personal", "USD", "Campaign", "Wallet", "Previous", "Positive"},
                {"Personal", "USD", "Campaign", "Wallet", "SaveDraft", "Positive"},
                /*Test data for User Bank*/
                {"Personal", "USD", "Campaign", "Bank", "Next", "Positive"},
                /*Test data for User Check*/
                {"Personal", "USD", "Campaign", "Check", "Next", "Positive"},
                /*Test data for User Digital Gift Card*/
                {"Personal", "USD", "Campaign", "Digital Gift Card", "Next", "Positive"},
                /*Test data for User Virtual Prepaid Card*/
                {"Personal", "USD", "Campaign", "Virtual Prepaid Card", "Next", "Positive"},
                /*Test data for User Reward Link*/
                {"Personal", "USD", "Campaign", "Reward Link", "Next", "Positive"},
                /*Test data for Company Wallet*/
                {"Company", "USD", "Campaign", "Wallet", "Next", "Positive"},
                /*Test data for Company Bank*/
                {"Company", "USD", "Campaign", "Bank", "Next", "Positive"},
                /*Test data for Company Check*/
                {"Company", "USD", "Campaign", "Check", "Next", "Positive"},
                /*Test data for Employer Wallet*/
                {"Employer", "USD", "Campaign", "Wallet", "Next", "Positive"},
        };
    }

    @Test(dataProvider = "tc08_data", groups = {"AdvanceSend", "CompanyRegression", "Sanity", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc08_advanceSendStep6(String paymentType, String fromWallet, String program, String paymentMethod, String condition, String caseType) throws Exception {
        asp6 = new AdvanceSendStep6Page(driver);
        tc07_advanceSendStep5(paymentType, fromWallet, program, paymentMethod, "Next", "Positive");
        Assert.assertEquals(asp6.getPaymentConfirmation().getText(), "Your payment has been processed.");
        asp6.getViewTxnBtn().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("ViewCashTransaction.aspx"), "View Transaction button not working");
        TransactionsPage tp = new TransactionsPage(driver);
        waitForElementClickable(tp.getTxnDateRange(), 5);
        tp.getTxnDateRange().click();
        waitForElementClickable(tp.getTxnDateLast7DaysBtn(), 5);
        tp.getTxnDateLast7DaysBtn().click();
        waitUntilLoaderDisable();
        Select txnFilter = new Select(tp.getTxnFilterDD());
        txnFilter.selectByValue("2");
        waitUntilLoaderDisable();
        waitForElementClickable(tp.getTxnDetailsBtn().get(0), 5);
        tp.getTxnDetailsBtn().get(0).click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("ViewReportByCampaign.aspx"), "Transaction Details Button not working");
        for (int j = 0; j < tp.getTxnPayMethod().size(); j++) {
            if (paymentMethod.equals("Virtual Prepaid Card"))
                Assert.assertTrue(tp.getTxnPayMethod().get(j).getText().contains("Direct to Prepaid Virtual Card"), "Expected Payment Method is Direct to Prepaid Virtual Card but transaction initiated in " + tp.getTxnPayMethod().get(j).getText());
            else
                Assert.assertTrue(tp.getTxnPayMethod().get(j).getText().contains(paymentMethod), "Expected Payment Method is " + paymentMethod + " but transaction initiated in " + tp.getTxnPayMethod().get(j).getText());
        }
        TransactionDetailsPage tdp = new TransactionDetailsPage(driver);
        String receiverEmail;
        for (int j = 0; j < tdp.getShowTxnDetails().size(); j++) {
            tdp.getShowTxnDetails().get(j).click();
            waitUntilLoaderDisable();
            Assert.assertTrue((tdp.getTxnDetailsType().getText().contains(paymentType)), "Expected Type is " + paymentType + " but received  " + tdp.getTxnDetailsType().getText());
            Assert.assertTrue(tdp.getTxnDetailsMethod().getText().contains(paymentMethod), "Expected Payment Method is " + paymentMethod + " but transaction initiated in " + tdp.getTxnDetailsMethod().getText());
            if (paymentType.equals("Employer")) {
                ranAmt = ranAmt * selectedRecipientsCount;
            }
            Assert.assertTrue(tdp.getTxnDetailsAmount().getText().replace(",", "").contains(String.valueOf(ranAmt)), "Expected Payment Amount is " + ranAmt + " but transferred amount is " + tdp.getTxnDetailsAmount().getText());
            receiverEmail = tdp.getEmailID().getText();
            if (receiverEmail.contains(","))
                receiverEmail = receiverEmail.substring(receiverEmail.indexOf(",") + 2, receiverEmail.indexOf(")"));
            else
                receiverEmail = receiverEmail.substring(receiverEmail.indexOf("(") + 1, receiverEmail.indexOf(")"));

            /*Recipient email amount verification*/
            if (!paymentMethod.equals("Digital Gift Card") && !paymentMethod.equals("Virtual Prepaid Card") && !paymentMethod.equals("Reward Link")) {
                if (paymentMethod.equals("Check")) {
                    verifyEmailAmount(receiverEmail, "initiated a payment via check", String.valueOf(ranAmt));
                } else {
                    if (paymentType.equals("Personal"))
                        verifyEmailAmount(receiverEmail, "Payment received from", String.valueOf(ranAmt));
                    else if (paymentType.equals("Company") || paymentType.equals("Employer"))
                        verifyEmailAmount(receiverEmail, "Payment notification from", String.valueOf(ranAmt));
                }
            }
            if (!mailVerificationSuccess && j == tdp.getShowTxnDetails().size() - 1) {
                mailVerificationSuccess = true;
                String errMsg = mailVerificationErrMsg;
                mailVerificationErrMsg = "";
                throw new Exception("Payment Method - " + paymentMethod + '\n' + errMsg);
            }
            tdp.getTxnDetailsDoneBtn().click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().contains("ViewReportByCampaign.aspx"), "Transaction Details Done Button not working");
        }

    }

    @DataProvider
    public Object[][] tc08_data() {
        return new Object[][]{
                /*{paymentType, fromWallet, program, paymentMethod, condition, caseType}*/
                {"Personal", "USD", "Campaign", "Wallet", "Next", "Positive"},
                /*{"Personal", "USD", "Campaign", "Bank", "Next", "Positive"},
                {"Personal", "USD", "Campaign", "Check", "Next", "Positive"},
                {"Personal", "USD", "Campaign", "Digital Gift Card", "Next", "Positive"},
                {"Personal", "USD", "Campaign", "Virtual Prepaid Card", "Next", "Positive"},
                {"Personal", "USD", "Campaign", "Reward Link", "Next", "Positive"},
                {"Company", "USD", "Campaign", "Wallet", "Next", "Positive"},
                {"Company", "USD", "Campaign", "Bank", "Next", "Positive"},
                {"Company", "USD", "Campaign", "Check", "Next", "Positive"},
                {"Employer", "USD", "Campaign", "Wallet", "Next", "Positive"},*/
        };
    }

    @Test(dataProvider = "draftPaymentData", groups = {"AdvanceSend", "CompanyRegression", "DraftPayment"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc09_draftPaymentStep1(String paymentType, String fromWallet, String program, String paymentMethod) throws Exception {
        dltDraft = false;
        tc03_advanceSendStep1(paymentType, fromWallet, program, paymentMethod, String.valueOf(getRandomAmount()), "No", "SaveDraft", "Positive", "");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                waitForElementClickable(dpp.getEditDescription(), 5);
                dpp.getEditDescription().click();
                waitForElementVisible(dpp.getEditDescriptionTxt(), 5);
                Thread.sleep(500); //Sometime entered details not saved in description
                dpp.getEditDescriptionTxt().sendKeys(" - edited in Draft");
                dpp.getEditDescriptionSave().click();
                waitForElementVisible(dpp.getDescriptionMsgValue(), 5);
                Assert.assertTrue(dpp.getDescriptionMsgValue().getText().contains(" - edited in Draft"), "Updated description not available");
            } else {
                redirectDraftPayment();
            }
            dpp.getContinueBtn().get(0).click();
            draftPaymentStep1assertion(paymentType, fromWallet, paymentMethod, (i + 1));
        }
    }

    @Test(dataProvider = "draftPaymentData", groups = {"AdvanceSend", "CompanyRegression", "DraftPayment"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc10_draftPaymentStep2(String paymentType, String fromWallet, String program, String paymentMethod) throws Exception {
        dltDraft = false;
        tc04_advanceSendStep2(paymentType, fromWallet, program, paymentMethod, "SaveDraft", "Positive", "");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                waitForElementClickable(dpp.getEditDescription(), 5);
                dpp.getEditDescription().click();
                waitForElementVisible(dpp.getEditDescriptionTxt(), 5);
                dpp.getEditDescriptionTxt().sendKeys(" - edited in Draft");
                dpp.getEditDescriptionSave().click();
                waitForElementVisible(dpp.getDescriptionMsgValue(), 5);
                Assert.assertTrue(dpp.getDescriptionMsgValue().getText().contains(" - edited in Draft"), "Updated description not available");
            } else {
                redirectDraftPayment();
            }
            dpp.getContinueBtn().get(0).click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step2.aspx"), "Redirected to - " + driver.getCurrentUrl() + "in iteration " + (i + 1));
            waitForElementClickable(asp1.getNextBtn(), 5);
            Assert.assertEquals(asp2.getSelectedRecipientList().size(), 2, "Added recipients list is 2 but available recipient list is " + asp2.getSelectedRecipientList().size() + "in iteration " + (i + 1));
            advSendProceedButton("Previous", 2, "Positive", "");
            draftPaymentStep1assertion(paymentType, fromWallet, paymentMethod, (i + 1));
            advSendProceedButton("Next", 1, "Positive", "");
            waitUntilLoaderDisable();
            waitForElementClickable(asp1.getNextBtn(), 5);
            Assert.assertEquals(asp2.getSelectedRecipientList().size(), 0, "Added recipients didn't removed automatically in iteration " + (i + 1));
            //advSendProceedButton("Next", 2, "Positive", "");
        }
    }

    @Test(dataProvider = "draftPaymentData", groups = {"AdvanceSend", "CompanyRegression", "DraftPayment"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc11_draftPaymentStep3(String paymentType, String fromWallet, String program, String paymentMethod) throws Exception {
        dltDraft = false;
        tc05_advanceSendStep3(paymentType, fromWallet, program, paymentMethod, "SaveDraft", "Positive", "");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                waitForElementClickable(dpp.getEditDescription(), 5);
                dpp.getEditDescription().click();
                waitForElementVisible(dpp.getEditDescriptionTxt(), 5);
                dpp.getEditDescriptionTxt().sendKeys(" - edited in Draft");
                dpp.getEditDescriptionSave().click();
                waitForElementVisible(dpp.getDescriptionMsgValue(), 5);
                Assert.assertTrue(dpp.getDescriptionMsgValue().getText().contains(" - edited in Draft"), "Updated description not available");
            } else {
                redirectDraftPayment();
            }
            dpp.getContinueBtn().get(0).click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step3.aspx"), "Current URL is - " + driver.getCurrentUrl() + "in iteration " + (i + 1));
            waitForElementClickable(asp1.getNextBtn(), 5);
            for (int j = 0; j < asp3.getAdditionalCommentsTxt().size(); j++) {
                Assert.assertEquals(asp3.getAdditionalCommentsTxt().get(j).getAttribute("value"), "Selenium Advance Send Auto Test for Company to " + paymentType + " and payment method is " + paymentMethod + " - edited in Draft", "Description not matched in iteration " + (i + 1));
            }
            if (i == 0) {
                asp3.getAmountTxt().get(0).clear();
                asp3.getAmountTxt().get(0).sendKeys(String.valueOf(ranAmt + 5));
                asp3.getAdditionalCommentsTxt().get(0).click();
                Thread.sleep(1000); //SaveDraft button clicked before entered amount got reflect in text box
                advSendProceedButton("SaveDraft", 3, "Positive", "");
                waitUntilLoaderDisable();
                dpp.getContinueBtn().get(0).click();
            }
            waitUntilLoaderDisable();
            Assert.assertEquals(asp3.getAmountTxt().size(), 2, "Added recipients list is 2 but available recipient list is " + asp3.getAmountTxt().size() + " in iteration - " + (i + 1));
            for (int j = 0; j < asp3.getAmountTxt().size(); j++) {
                if (j == 0)
                    Assert.assertEquals(asp3.getAmountTxt().get(j).getAttribute("value"), ranAmt + 5 + ".00", "Transaction amount mismatched in iteration " + (i + 1));
                else
                    Assert.assertEquals(asp3.getAmountTxt().get(j).getAttribute("value"), ranAmt + ".00", "Transaction amount mismatched in iteration " + (i + 1));
            }
            advSendProceedButton("Previous", 3, "Positive", "");
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step2.aspx"), "Current URL is - " + driver.getCurrentUrl() + "in iteration " + (i + 1));
            waitForElementClickable(asp1.getNextBtn(), 5);
            Assert.assertEquals(asp2.getSelectedRecipientList().size(), 2, "Added recipients list is 2 but available recipient list is " + asp2.getSelectedRecipientList().size() + " in iteration " + (i + 1));
            advSendProceedButton("Next", 2, "Positive", "");
            Assert.assertEquals(asp3.getAmountTxt().size(), 2, "Added recipients list is 2 but available recipient list is " + asp3.getAmountTxt().size() + " in step3 iteration - " + (i + 1));
            for (int j = 0; j < asp3.getAmountTxt().size(); j++) {
                Assert.assertEquals(asp3.getAmountTxt().get(j).getAttribute("value"), ranAmt + ".00", "Amount mismatched");
            }
        }
    }

    @Test(dataProvider = "draftPaymentData", groups = {"AdvanceSend", "CompanyRegression", "DraftPayment"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc12_draftPaymentStep4(String paymentType, String fromWallet, String program, String paymentMethod) throws Exception {
        dltDraft = false;
        tc06_advanceSendStep4(paymentType, fromWallet, program, paymentMethod, "SaveDraft", "Positive");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                waitForElementClickable(dpp.getEditDescription(), 5);
                dpp.getEditDescription().click();
                waitForElementVisible(dpp.getEditDescriptionTxt(), 5);
                dpp.getEditDescriptionTxt().sendKeys(" - edited in Draft");
                dpp.getEditDescriptionSave().click();
                waitForElementVisible(dpp.getDescriptionMsgValue(), 5);
                Assert.assertTrue(dpp.getDescriptionMsgValue().getText().contains(" - edited in Draft"), "Updated description not available");
            } else {
                redirectDraftPayment();
            }
            dpp.getContinueBtn().get(0).click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step4.aspx"), "Current URL is - " + driver.getCurrentUrl() + "in iteration " + (i + 1));
            waitForElementClickable(asp1.getNextBtn(), 5);
            Assert.assertTrue(asp4.getSummaryAmount().getText().contains(String.valueOf(ranAmt * selectedRecipientsCount)), "Summary amount mismatched in iteration - " + (i + 1));
            Assert.assertEquals(asp4.getSelectedRecipient().size(), 2, "Selected recipients count mismatched in iteration " + (i + 1));
            Assert.assertEquals(asp4.getDescriptionValue().getText(), "Selenium Advance Send Auto Test for Company to " + paymentType + " and payment method is " + paymentMethod + " - edited in Draft", "Description not matched in iteration " + (i + 1));
            advSendProceedButton("Previous", 4, "Positive", "");
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step3.aspx"), "Current URL is - " + driver.getCurrentUrl() + "in iteration " + (i + 1));
            waitForElementClickable(asp1.getNextBtn(), 5);
            Assert.assertEquals(asp3.getAmountTxt().size(), 2, "Added recipients list is 2 but available recipient list is " + asp3.getAmountTxt().size() + " in step3 iteration - " + (i + 1));
            for (int j = 0; j < asp3.getAmountTxt().size(); j++) {
                Assert.assertEquals(asp3.getAmountTxt().get(j).getAttribute("value"), ranAmt + ".00", "Transaction amount mismatched in iteration " + (i + 1));
                Assert.assertEquals(asp3.getAdditionalCommentsTxt().get(j).getAttribute("value"), "Selenium Advance Send Auto Test for Company to " + paymentType + " and payment method is " + paymentMethod + " - edited in Draft", "Description not matched in iteration " + (i + 1));
            }
            advSendProceedButton("Next", 3, "Positive", "");
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step4.aspx"), "Current URL is - " + driver.getCurrentUrl() + "in iteration " + (i + 1));
            waitForElementClickable(asp1.getNextBtn(), 5);
            Assert.assertTrue(asp4.getSummaryAmount().getText().contains(String.valueOf(ranAmt * selectedRecipientsCount)), "Summary amount mismatched in iteration - " + (i + 1));
            Assert.assertEquals(asp4.getSelectedRecipient().size(), 2, "Selected recipients count mismatched in iteration " + (i + 1));
        }
    }

    @Test(dataProvider = "draftPaymentData", groups = {"AdvanceSend", "CompanyRegression", "DraftPayment", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc13_draftPaymentStep5(String paymentType, String fromWallet, String program, String paymentMethod) throws Exception {
        dltDraft = false;
        tc07_advanceSendStep5(paymentType, fromWallet, program, paymentMethod, "SaveDraft", "Positive");
        dpp.getContinueBtn().get(0).click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step5.aspx"), "Current URL is - " + driver.getCurrentUrl());
        Assert.assertTrue(asp5.getAmount().getText().contains(String.valueOf(ranAmt * selectedRecipientsCount)), "Transaction amount mismatched. Total amount is - " + asp5.getAmount().getText() + ", but actual amount is - " + ranAmt * selectedRecipientsCount);
        waitForElementClickable(asp1.getNextBtn(), 5);
        advSendProceedButton("Previous", 5, "Positive", "");
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step4.aspx"), "Current URL is - " + driver.getCurrentUrl());
        waitForElementClickable(asp1.getNextBtn(), 5);
        Assert.assertTrue(asp4.getSummaryAmount().getText().contains(String.valueOf(ranAmt * selectedRecipientsCount)), "Summary amount mismatched");
        advSendProceedButton("Next", 4, "Positive", "");
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step5.aspx"), "Current URL is - " + driver.getCurrentUrl());
        Assert.assertTrue(asp5.getAmount().getText().contains(String.valueOf(ranAmt * selectedRecipientsCount)), "Summary amount mismatched");
        waitForElementClickable(asp1.getNextBtn(), 5);
        advSendProceedButton("Next", 5, "Positive", "");
        redirectDraftPayment();
        while (draftAvailable) {
            if (dpp.getContinueBtn().size() > 0)
                dltDraftPayment();
            else
                draftAvailable = false;
        }
    }

    @DataProvider
    public Object[][] draftPaymentData() {
        return new Object[][]{
                /*paymentType, fromWallet, program, paymentMethod*/
                {"Personal", "USD", "Campaign", "Wallet"}
        };
    }

    /******************************************************************************************************************/
    public int getRandomAmount() {
        ranAmt = (int) (Math.random() * (49 - 5 + 1) + 5);
        return ranAmt;
    }

    public void selectRecentPayee(String payeeEmail) throws Exception {
        if (asp2.getRecentPayeeEmail().get(randomRecentPayee).getText().equals(getRecipientEmail(payeeEmail))) {
            if (randomRecentPayee < asp2.getRecentPayeeAddBtn().size() - 1) {
                randomRecentPayee = randomRecentPayee + 1;
            } else {
                randomRecentPayee = randomRecentPayee - 1;
            }
        }
        if (asp2.getRecentPayeeEmail().get(randomRecentPayee).getText().contains("@mailinator.com") || asp2.getRecentPayeeEmail().get(randomRecentPayee).getText().contains("@xtrm.com")) {
            asp2.getRecentPayeeAddBtn().get(randomRecentPayee).click();
        } else {
            for (int i = 0; i < asp2.getRecentPayeeAddBtn().size(); i++) {
                if (!asp2.getRecentPayeeEmail().get(i).getText().equals(getRecipientEmail(payeeEmail)) && (asp2.getRecentPayeeEmail().get(i).getText().contains("@mailinator.com") || asp2.getRecentPayeeEmail().get(randomRecentPayee).getText().contains("@xtrm.com"))) {
                    asp2.getRecentPayeeAddBtn().get(i).click();
                    break;
                }
            }
        }
        waitUntilLoaderDisable();
    }

    public void dltDraftPayment() {
        if (!driver.getCurrentUrl().contains("/AllAwardsCampaign.aspx")) {
            cm.getDraftPaymentIcon().click();
            cm.getDraftPaymentViewAll().click();
            waitUntilLoaderDisable();
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("/AllAwardsCampaign.aspx"), "Redirected to - " + driver.getCurrentUrl());
        waitForElementClickable(dpp.getDltDropDownBtn(), 5);
        dpp.getDltDropDownBtn().click();
        dpp.getDltOption().click();
        waitUntilLoaderDisable();
        Assert.assertEquals(dpp.getConfirmationMsg().getText(), "Draft deleted successfully.");
    }

    public void draftPaymentStep1assertion(String paymentType, String fromWallet, String paymentMethod, int iteration) throws Exception {
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("step1.aspx"), "Current URL is - " + driver.getCurrentUrl());
        waitForElementClickable(asp1.getNextBtn(), 5);
        waitForElementClickable(asp1.getPaymentTypeDD(), 5);
        waitForElementVisible(asp1.getPaymentTypeDD(), 5);
        waitUntilLoaderDisable();
        Assert.assertEquals(new Select(asp1.getPaymentTypeDD()).getFirstSelectedOption().getText(), paymentType, "Payment Type not matched in iteration " + iteration);
        Assert.assertEquals(new Select(asp1.getFromWalletDD()).getFirstSelectedOption().getAttribute("value"), getWalletID(fromWallet), "Selected wallet not matched in iteration " + iteration);
        Assert.assertEquals(asp1.getDescriptionTxt().getAttribute("value"), "Selenium Advance Send Auto Test for Company to " + paymentType + " and payment method is " + paymentMethod + " - edited in Draft", "Description not matched in iteration " + iteration);
        Assert.assertTrue(new Select(asp1.getPaymentMethodDD()).getFirstSelectedOption().getText().contains(paymentMethod), "Payment Method not matched in iteration " + iteration);
        Assert.assertEquals(asp1.getAmountTxt().getAttribute("value"), ranAmt + ".00", "Amount not matched in iteration " + iteration);
    }

    public void redirectDraftPayment() {
        waitForElementClickable(cm.getDraftPaymentIcon(), 5);
        cm.getDraftPaymentIcon().click();
        cm.getDraftPaymentViewAll().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("/AllAwardsCampaign.aspx"), "Redirect to Draft got failed");
        waitForElementClickable(dpp.getDltDropDownBtn(), 5);
    }

    public void advSendProceedButton(String condition, int stepNumber, String caseType, String errMsg) {
        switch (condition) {
            case "Next":
                waitForElementClickable(asp1.getNextBtn(), 5);
                asp1.getNextBtn().click();
                waitUntilLoaderDisable();
                if (stepNumber == 3 && caseType.equals("Negative")) {
                    Assert.assertTrue(driver.getCurrentUrl().contains("Step2.aspx"), "Negative Test case got failed in Step" + stepNumber);
                }
                if (stepNumber == 5) {
                    Assert.assertTrue(asp5.getSendNowTitle().isDisplayed(), "Send Now Confirmation pop up message not showing");
                    asp5.getSendNowBtn().click();
                    waitUntilLoaderDisable();
                    waitUntilLoaderDisable();
                    waitUntilLoaderDisable();
                }
                if (caseType.equals("Positive") && stepNumber != 0)
                    Assert.assertTrue(driver.getCurrentUrl().contains("Step" + (stepNumber + 1) + ".aspx"), "Next button not working in Step number " + stepNumber + "Current URL - " + driver.getCurrentUrl());
                else if (caseType.equals("Negative"))
                    Assert.assertEquals(asp1.getErrMsg().getText(), errMsg);
                break;
            case "SaveDraft":
                waitForElementClickable(asp1.getSaveDraftBtn(), 5);
                asp1.getSaveDraftBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("/AllAwardsCampaign.aspx"), "Redirect to - " + driver.getCurrentUrl());
                if (dltDraft) {
                    dltDraftPayment();
                }
                break;
            case "Cancel":
                waitForElementClickable(asp1.getCancelBtn(), 5);
                asp1.getCancelBtn().click();
                Assert.assertTrue(asp1.getCancelAlert().isDisplayed(), "Cancel Alert is not displaying");
                Assert.assertEquals(asp1.getAlertHeader().getText(), "Cancel?");
                asp1.getAlertYesBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("/Send.aspx"));
                break;
            case "Previous":
                waitForElementClickable(asp1.getPreviousBtn(), 5);
                asp1.getPreviousBtn().click();
                waitUntilLoaderDisable();
                Assert.assertTrue(driver.getCurrentUrl().contains("Step" + (stepNumber - 1) + ".aspx"), "Previous button not working");
        }
    }
}
