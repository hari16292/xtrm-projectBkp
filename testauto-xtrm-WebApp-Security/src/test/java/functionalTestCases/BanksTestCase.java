package functionalTestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.LinkBank_CardsPage;
import pageObject.LinkedCardsPage;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;
import resource.RetryAnalyzer;

@Listeners(resource.Listeners.class)
public class BanksTestCase extends BaseClass {
    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    LinkBank_CardsPage lp;
    LinkedCardsPage lcp;
    boolean cardDeleted = false, bankDeleted = false;

    @BeforeSuite(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkRapidCard", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
        lp = new LinkBank_CardsPage(driver);
        lcp = new LinkedCardsPage(driver);
    }

    @AfterSuite(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkRapidCard", "Security"})
    public void closeBrowser() {
        driver.quit();
    }


    @Test(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkRapidCard", "Security"}, retryAnalyzer = RetryAnalyzer.class)
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
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("choosepaymethod"), "LinkBankButton click redirected to wrong URL - " + driver.getCurrentUrl());
    }

    @Test(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkRapidCard", "Security"}, retryAnalyzer = RetryAnalyzer.class, dataProvider = "tc02_data")
    public void tc02_linkRapid(String cardNo, String month, String year, String cvv, String condition, String caseType,
                               String errMsg, String moreInfo) throws Exception {
        int cardCount = 0;
        if (caseType.equalsIgnoreCase("Positive") && !cardDeleted)
            cardCount = deleteCard();
        tc01_homePage();
        waitForElementClickable(lp.getRapidGoBtn(), 5);
        lp.getRapidGoBtn().click();
        waitUntilLoaderDisable();
        waitForElementClickable(lp.getRapidLinkCardBtn(), 5);
        if (moreInfo.equalsIgnoreCase("Yes")) {
            waitForElementClickable(lp.getRapidMoreInfoCSCLink(), 5);
            lp.getRapidMoreInfoCSCLink().click();
            driver.switchTo().window(windowHandle(driver, "child"));
            Assert.assertEquals(driver.getTitle(), "SECURITY007 - Card Security Code Overview (CVV Code ) : General Help Center", "What is CSC? hyperlink redirected to wrong URL");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
        }
        lp.getRapidCardNoTxt().sendKeys(cardNo);
        Select monthDD = new Select(lp.getRapidCardExpMonthDD());
        if (!month.isEmpty())
            monthDD.selectByValue(month);
        Select yearDD = new Select(lp.getRapidCardExpYearDD());
        if (!year.isEmpty())
            yearDD.selectByValue(year);
        lp.getRapidCardCVVTxt().sendKeys(cvv);
        if (condition.equalsIgnoreCase("Continue")) {
            lp.getRapidLinkCardBtn().click();
            waitUntilLoaderDisable();
            if (caseType.equalsIgnoreCase("Positive")) {
                waitForElementClickable(lcp.getLinkCardBtn(), 5);
                Assert.assertEquals(lcp.getErrMsg().getText(), "Successfully linked your bank for rapid bank transfer.", "Success message is invalid");
                Assert.assertEquals(lcp.getCardType().size(), cardCount + 1, "Added new card not listed in linked cards");
            } else {
                waitForElementClickable(lp.getRapidLinkCardBtn(), 5);
                Assert.assertEquals(lp.getRapidCardErrMsg().getText(), errMsg, "Error message mismatched");
            }
        } else {
            lp.getCancelBtn().click();
            waitUntilLoaderDisable();
            Assert.assertTrue(driver.getCurrentUrl().contains("LinkedCards"), "Rapid card cancel button click redirected to wrong url");
        }
    }

    @DataProvider
    public Object[][] tc02_data() {
        return new Object[][]{
                /*{"CardNo", "Month", "Year", "CVV", "Condition", "CaseType" "ErrorMsg", "MoreInfo"}*/
                {"4005519200000004", "11", "2030", "123", "Continue", "Positive", "", "Yes"},
                /*{"", "11", "2030", "123", "Continue", "Negative", "Card number is mandatory.", ""},
                {"123456789", "11", "2030", "123", "Continue", "Negative", "Invalid card.", ""},
                {"4005519200000004", "", "2030", "123", "Continue", "Negative", "Expiry month is mandatory.", ""},
                {"4005519200000004", "11", "", "123", "Continue", "Negative", "Expiry year is mandatory.", ""},
                {"4005519200000004", "11", "2030", "123", "Cancel", "", "", ""},*/
        };
    }

    @Test(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkBankTransfer", "Security"}, retryAnalyzer = RetryAnalyzer.class, dataProvider = "tc03_data")
    public void tc03_linkBankTransfer(String bankCountry, String accountType, String bankName, String accountNumber, String currency,
                                      String accountName, String mobileNumber, String address1, String address2, String building,
                                      String city, String country, String state, String postalCode, String condition, String caseType,
                                      String errMsg, String routingCode, String swiftBic, String verifyCaseType, String verifyCondition) throws Exception {
        int bankCount = 0;
        if (caseType.equalsIgnoreCase("Positive") && !bankDeleted)
            bankCount = deleteBank();
        tc01_homePage();
        waitForElementClickable(cm.getLogoutMenu(), 5);
        lp.getTransferGoBtn().click();
        waitUntilLoaderDisable();
        waitForElementClickable(lp.getTransferLinkContinueBtn(), 5);
        linkBank(bankCountry, accountType, bankName, accountNumber, currency, accountName, mobileNumber, address1, address2, building, city,
                country, state, postalCode, condition, caseType, errMsg, routingCode, swiftBic, verifyCaseType, verifyCondition, bankCount, "Transfer");
    }

    @Test(groups = {"Banks", "CompanyRegression", "UserRegression", "LinkBankFund", "Security"}, retryAnalyzer = RetryAnalyzer.class, dataProvider = "tc03_data")
    public void tc04_linkBankFund(String bankCountry, String accountType, String bankName, String accountNumber, String currency,
                                  String accountName, String mobileNumber, String address1, String address2, String building,
                                  String city, String country, String state, String postalCode, String condition, String caseType,
                                  String errMsg, String routingCode, String swiftBic, String verifyCaseType, String verifyCondition) throws Exception {
        int bankCount = 0;
        if (caseType.equalsIgnoreCase("Positive") && !bankDeleted)
            bankCount = deleteBank();
        tc01_homePage();
        waitForElementClickable(cm.getLogoutMenu(), 5);
        lp.getFundManualMethodOption().click();
        waitUntilLoaderDisable();
        waitForElementClickable(lp.getTransferLinkContinueBtn(), 5);
        linkBank(bankCountry, accountType, bankName, accountNumber, currency, accountName, mobileNumber, address1, address2, building, city,
                country, state, postalCode, condition, caseType, errMsg, routingCode, swiftBic, verifyCaseType, verifyCondition, bankCount, "Fund");
    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{"bankCountry", "accountType", "bankName", "accountNumber", "currency", "accountName", "mobileNumber", "address1", "address2", "building", "city", "country", "state", "postalCode", "condition", "caseType", "errMsg", "routingCode", "swiftCode", "verifyCaseType", "verifyCondition"}*/
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Positive", "", "011000138", "", "Positive", "Continue"},
                /*{"Select Bank Country", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Negative", "Please select bank country.", "", "", "", ""},
                {"United States of America", "Business", "", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Negative", "Please enter bank name.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Negative", "Please enter your account number.", "", "", "", ""},
                //{"United States of America", "Business", "Bank of America Corporation", "44332211", "Select account currency", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Negative", "Please select bank currency.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Negative", "Please enter your company name.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Negative", "Please enter your address line 1.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "", "United States of America", "Alaska", "642154", "Continue", "Negative", "Please enter your city.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "Select Country", "", "642154", "Continue", "Negative", "Please select your country.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Select State/County/Region", "642154", "Continue", "Negative", "Please select your state / county or region.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "", "Continue", "Negative", "Please enter your postal code.", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Cancel", "Positive", "", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Back", "Positive", "", "", "", "", ""},
                {"United States of America", "Business", "Bank of America Corporation", "44332211", "USD", "Auto" + RandomStringUtils.randomAlphabetic(3) + "Test", "9876543210", "Test address1", "Test address2", "Test building", "Brooklyn", "United States of America", "Alaska", "642154", "Continue", "Positive", "BankRoutingCode / NationalID cannot be blank", "", "", "Negative", "Continue"},
        */};
    }

    /******************************************************************************************************************/
    public int deleteCard() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        cm.getSettingsIcon().click();
        waitUntilLoaderDisable();
        waitForElementClickable(lcp.getLinkedCardsTab(), 5);
        lcp.getLinkedCardsTab().click();
        waitUntilLoaderDisable();
        waitForElementClickable(lcp.getLinkCardBtn(), 5);
        for (int i = 0; i < lcp.getCardType().size(); i++) {
            if (lcp.getCardType().get(i).getText().equals("Rapid Bank Transfer")) {
                lcp.getDeleteCardOption().get(i).click();
                waitUntilLoaderDisable();
                waitForElementClickable(lcp.getLinkCardBtn(), 5);
                Assert.assertEquals(lcp.getErrMsg().getText(), "Card successfully deleted.", "Card delete message is not valid");
            }
        }
        cardDeleted = true;
        return lcp.getCardType().size();
    }

    public int deleteBank() throws Exception {
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
        if(lp.getBankDetailsDD().size() > 0)
            for (int i = 0; i <= lp.getBankDetailsDD().size(); i++) {
                lp.getBankDetailsDD().get(0).click();
                waitForElementClickable(lp.getBankDeleteOption().get(0), 5);
                lp.getBankDeleteOption().get(0).click();
                waitUntilLoaderDisable();
                //Thread.sleep(5000); //Loader is missing in delete banks
                waitForElementVisible(lp.getBankMsg(), 5);
                Assert.assertEquals(lp.getBankMsg().getText(), "Bank account deleted successfully", "Displayed bank successful delete message is not valid");
                waitForElementClickable(lp.getLinkBankAccountBtn(), 5);
            }
        bankDeleted = true;
        return lp.getBankDetailsDD().size();
    }

    public void linkBank(String bankCountry, String accountType, String bankName, String accountNumber, String currency,
                         String accountName, String mobileNumber, String address1, String address2, String building, String city,
                         String country, String state, String postalCode, String condition, String caseType, String errMsg,
                         String routingCode, String swiftBic, String verifyCaseType, String verifyCondition, int bankCount, String bankType) {
        Select bankCountryDD = new Select(lp.getTransferBankCountryDD());
        if (!bankCountryDD.getFirstSelectedOption().getText().equalsIgnoreCase(bankCountry))
            bankCountryDD.selectByVisibleText(bankCountry);
        waitUntilLoaderDisable();
        waitForElementClickable(lp.getTransferLinkContinueBtn(), 5);
        Select accountTypeDD = new Select(lp.getTransferAccountTypeDD());
        if (!accountTypeDD.getFirstSelectedOption().getText().equalsIgnoreCase(accountType))
            accountTypeDD.selectByVisibleText(accountType);
        lp.getTransferBankNameTxt().clear();
        lp.getTransferBankNameTxt().sendKeys(bankName);
        lp.getTransferAccountNumberTxt().clear();
        lp.getTransferAccountNumberTxt().sendKeys(accountNumber);
        Select currencyDD = new Select(lp.getTransferCurrencyDD());
        Assert.assertTrue(currencyDD.getFirstSelectedOption().getText().contains(currency), "Default selected currency mismatched");
        lp.getTransferAccountNameTxt().clear();
        lp.getTransferAccountNameTxt().sendKeys(accountName);
        lp.getTransferMobileNumberTxt().clear();
        lp.getTransferMobileNumberTxt().sendKeys(mobileNumber);
        lp.getTransferAddress1Txt().clear();
        lp.getTransferAddress1Txt().sendKeys(address1);
        lp.getTransferAddress2Txt().clear();
        lp.getTransferAddress2Txt().sendKeys(address2);
        lp.getTransferBuildingTxt().clear();
        lp.getTransferBuildingTxt().sendKeys(building);
        Select countryDD = new Select(lp.getTransferCountryDD());
        if (!countryDD.getFirstSelectedOption().getText().equalsIgnoreCase(country))
            countryDD.selectByVisibleText(country);
        lp.getTransferCityTxt().clear();
        lp.getTransferCityTxt().sendKeys(city);
        lp.getTransferPostalCodeTxt().clear();
        lp.getTransferPostalCodeTxt().sendKeys(postalCode);
        Select stateDD = new Select(lp.getTransferStateDD());
        if (!stateDD.getFirstSelectedOption().getText().equalsIgnoreCase(state) && !country.equalsIgnoreCase("Select Country"))
            stateDD.selectByVisibleText(state);
        if (condition.equalsIgnoreCase("Continue")) {
            lp.getTransferLinkContinueBtn().click();
            waitUntilLoaderDisable();
            if (caseType.equalsIgnoreCase("Positive")) {
                Assert.assertTrue(driver.getCurrentUrl().contains("AddBeneficiaryStep2"), bankType + " Link Bank continue button redirected to wrong URL - " + driver.getCurrentUrl());
                waitForElementClickable(cm.getLogoutMenu(), 5);
                Assert.assertEquals(lp.getTransferAccountNumberTxt().getAttribute("value"), accountNumber, "Account number mismatched in confirmation page");
                lp.getTransferReEnterAccountNumberTxt().sendKeys(accountNumber);
                if (!routingCode.isEmpty())
                    lp.getTransferRoutingTxt().sendKeys(routingCode);
                if (!swiftBic.isEmpty())
                    lp.getTransferSwiftBicTxt().sendKeys(swiftBic);
                waitForElementClickable(lp.getTransferLinkContinueBtn(), 5);
                if (verifyCondition.equalsIgnoreCase("Continue")) {
                    lp.getTransferLinkContinueBtn().click();
                    waitUntilLoaderDisable();
                    if (verifyCaseType.equalsIgnoreCase("Positive")) {
                        if (bankType.equalsIgnoreCase("Fund")) {
                            waitForElementVisible(lp.getFundVerifySubmitBtn(), 5);
                            lp.getFundVerifyBeneficiaryNameTxt().sendKeys(accountName);
                            lp.getFundVerifySubmitBtn().click();
                            waitForElementVisible(lp.getBankMsg(), 5);
                            Assert.assertTrue(driver.getCurrentUrl().contains("Payment"), "Verify link bank Fund continue button click redirected to wrong URL");
                            Assert.assertEquals(lp.getBankMsg().getText(), "Bank debit request submitted successfully. More Info", "Fund linked bank success message not valid - " + lp.getBankMsg().getText());
                        } else {
                            waitForElementVisible(lp.getBankMsg(), 5);
                            Assert.assertTrue(driver.getCurrentUrl().contains("Payment"), "Verify link bank transfer continue button click redirected to wrong URL");
                            Assert.assertEquals(lp.getBankMsg().getText(), "Bank linked successfully. Transfer Funds", "Transfer linked bank success message not valid - " + lp.getBankMsg().getText());
                        }
                        Assert.assertEquals(lp.getBankDetailsDD().size(), bankCount + 1, "Added new " + bankType + " bank not listed in linked banks");
                    } else
                        Assert.assertEquals(lp.getTransferErrMsg().getText(), errMsg, "Error message not matched in " + bankType + " verify link bank page");
                } else if (verifyCondition.equalsIgnoreCase("Back")) {
                    lp.getTransferBackBtn().click();
                    waitUntilLoaderDisable();
                    waitForElementClickable(cm.getLogoutMenu(), 5);
                    Assert.assertTrue(driver.getCurrentUrl().contains("AddBeneficiary"), "Back button click in " + bankType + " verify page redirected to wrong url - " + driver.getCurrentUrl());
                } else {
                    lp.getCancelBtn().click();
                    waitUntilLoaderDisable();
                    waitForElementClickable(cm.getLogoutMenu(), 5);
                    Assert.assertTrue(driver.getCurrentUrl().contains("Payment"), "Cancel button click in " + bankType + " verify page redirected to wrong url - " + driver.getCurrentUrl());
                }
            } else
                Assert.assertEquals(lp.getTransferErrMsg().getText(), errMsg, "Error message not matched in " + bankType + " link bank page");
        } else {
            lp.getCancelBtn().click();
            waitUntilLoaderDisable();
            waitForElementClickable(cm.getLogoutMenu(), 5);
            Assert.assertTrue(driver.getCurrentUrl().contains("Payment"), "Cancel button click in " + bankType + " link bank page redirected to wrong url - " + driver.getCurrentUrl());
        }
    }
}
