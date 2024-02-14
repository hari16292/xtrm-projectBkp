package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransferPage {

    WebDriver driver;

    public TransferPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@id='ddlCurrency' or @id='ctl00_ctl00_TopMenu_MiniTab_ddlCashAccount']")
    private WebElement fromWalletDD;

    @FindBy(id = "ddlTransferTo")
    private WebElement transferTypeDD;

    @FindBy(xpath = "//a[contains(@href,'4000080372-xtrm-personal-digital-wallet-overview') or contains(@href,'4000115369-wallet-transfer-options-overview')]")
    private WebElement moreInfoLink;

    @FindBy(xpath = "//span[@id='lblHelp' or @id='ctl00_ctl00_TopMenu_MiniTab_lblHelp']")
    private WebElement transferTypeMsg;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lbtnNewCard")
    private WebElement linkRapidCard;

    @FindBy(xpath = "//input[@id='txtRapidAmount' or @id='ctl00_ctl00_TopMenu_MiniTab_txtRapidAmount']")
    private WebElement rapidAmountTxt;

    @FindBy(xpath = "//input[@id='txtAmt' or @id='ctl00_ctl00_TopMenu_MiniTab_txtAmt']")
    private WebElement standardAmountTxt;

    @FindBy(xpath = "//select[@id='ddlRapidAccount' or @id='ctl00_ctl00_TopMenu_MiniTab_ddlRapidAccount']")
    private WebElement rapidReceivingBankDD;

    @FindBy(xpath = "//select[@id='ddlBankList' or @id='ctl00_ctl00_TopMenu_MiniTab_ddlBankList']")
    private WebElement standardReceivingBankDD;

    @FindBy(xpath = "//input[@id='btnSubmitRapid' or @id='ctl00_ctl00_TopMenu_MiniTab_btnSubmitRapid']")
    private WebElement rapidContinueBtn;

    @FindBy(xpath = "//input[@id='btnContinue' or @id='ctl00_ctl00_TopMenu_MiniTab_btnContinue']")
    private WebElement standardContinueBtn;

    @FindBy(xpath = "//span[@id='lblMsg' or @class='spnMsg Validator' or @id='ctl00_ctl00_TopMenu_MiniTab_lblMsg']")
    private WebElement errorMsg;

    @FindBy(xpath = "//input[contains(@id,'txtTangoEmail')]")
    private WebElement recipientEmailPVVTxt;

    @FindBy(xpath = "//div[@class='Label']")
    private WebElement recipientEmailPVVMsg;

    @FindBy(id = "ddlTangoCurrencyList")
    private WebElement pvvCardCurrencyDD;

    @FindBy(xpath = "//div[@id='spnPVVRestrictedMsg']/a")
    private WebElement pvvMoreInfo;

    @FindBy(id = "ddlTangoCategory")
    private WebElement pvvCardTypeDD;

    @FindBy(id = "ddlTangoRewards")
    private WebElement pvvCardValueDD;

    @FindBy(xpath = "//input[contains(@id,'txtRewardAmount')]")
    private WebElement pvvAmountTxt;

    @FindBy(xpath = "//input[contains(@id,'btnSubmitGiftCard')]")
    private WebElement pvvContinueBtn;

    @FindBy(xpath = "//input[@id='txtCDAmount' or @id='ctl00_ctl00_TopMenu_MiniTab_txtCDAmount']")
    private WebElement checkAmountTxt;

    @FindBy(xpath = "//input[@id='btnCheckDirect' or @id='ctl00_ctl00_TopMenu_MiniTab_btnCheckDirect']")
    private WebElement checkContinueBtn;

    @FindBy(xpath = "//p[@class='Label']")
    private WebElement checkCountryMsg;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblInstitutionNameVal")
    private WebElement standardBankName;

    @FindBy(xpath = "//select[@id='ddlVerification' or @id='ctl00_ctl00_TopMenu_MiniTab_ddlVerification']")
    private WebElement standardVerificationDD;

    @FindBy(xpath = "//input[@id='btnGo' or @id='ctl00_ctl00_TopMenu_MiniTab_btnGo']")
    private WebElement standardSendCodeBtn;

    @FindBy(xpath = "//p[@role='alert']")
    private WebElement standardOtpConfirmationMsg;

    @FindBy(xpath = "//input[@id='txtActivationCode' or @id='ctl00_ctl00_TopMenu_MiniTab_txtActivationCode']")
    private WebElement standardOtpTxt;

    @FindBy(xpath = "//button[@id='btnVerifyTransfer' or @id='ctl00_ctl00_TopMenu_MiniTab_btnVerifyTransfer']")
    private WebElement standardVerifyBtn;

    @FindBy(xpath = "//p[@role='alert']")
    private WebElement standardVerificationMsg;

    @FindBy(xpath = "(//td[contains(text(),'Transferring to your bank')])")
    private WebElement standardBankTransferMsg;

    @FindBy(xpath = "//input[@id='btnPay' or @id='ctl00_ctl00_TopMenu_MiniTab_btnPay']")
    private WebElement standardTransferBtn;

    @FindBy(id = "spnEstimatedArrival")
    private WebElement standardEstimatedArrivalDate;

    @FindBy(xpath = "//span[@id='lblWithdrawAmt' or @id='ctl00_ctl00_TopMenu_MiniTab_lblWithdrawAmt']")
    private WebElement standardAmount;

    @FindBy(xpath = "//span[@id='lblTotal' or @id='ctl00_ctl00_TopMenu_MiniTab_lblTotal']")
    private WebElement standardTotalAmount;

    @FindBy(id = "divSecureMessage")
    private WebElement otpVerifyMsg;

    @FindBy(xpath = "//td[normalize-space()='Amount :']/following-sibling::td")
    private WebElement giftCardPayAmount;

    @FindBy(xpath = "//td[normalize-space()='Recipient Email :']/following-sibling::td")
    private WebElement giftCardPayEmail;

    @FindBy(id = "cbkTerms")
    private WebElement giftCardPayAgreeCheckBox;

    @FindBy(xpath = "//input[@value='Copy Link']")
    private WebElement giftCardPayCopyLinkBtn;

    @FindBy(xpath = "//input[@id='txtCDAddressLine1' or @id='ctl00_ctl00_TopMenu_MiniTab_txtCDAddressLine1']")
    private WebElement checkAddressLine1Txt;

    @FindBy(xpath = "//input[@id='txtCDAddressLine2' or @id='ctl00_ctl00_TopMenu_MiniTab_txtCDAddressLine2']")
    private WebElement checkAddressLine2Txt;

    @FindBy(xpath = "//input[@id='txtCDCity' or @id='ctl00_ctl00_TopMenu_MiniTab_txtCDCity']")
    private WebElement checkCityTxt;

    @FindBy(xpath = "//select[@id='ddlCDState' or @id='ctl00_ctl00_TopMenu_MiniTab_ddlCDState']")
    private WebElement checkStateDD;

    @FindBy(xpath = "//input[@id='txtCDPostalCode' or @id='ctl00_ctl00_TopMenu_MiniTab_txtCDPostalCode']")
    private WebElement checkPostalCodeTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblVerfication")
    private WebElement checkOTPConfirmationMsg;

    @FindBy(id = "ddlTangoCategory")
    private WebElement giftCardRetailerDD;

    @FindBy(id = "ddlTangoRewards")
    private WebElement giftCardValueDD;

    @FindBy(xpath = "//li[@id='li10']/a")
    private WebElement transferBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lnkbtnLinkNewBank")
    private WebElement linkNewBank;

    public WebElement getGiftCardRetailerDD() {
        return giftCardRetailerDD;
    }

    public WebElement getGiftCardValueDD() {
        return giftCardValueDD;
    }

    public WebElement getCheckOTPConfirmationMsg() {
        return checkOTPConfirmationMsg;
    }

    public WebElement getCheckAddressLine1Txt() {
        return checkAddressLine1Txt;
    }

    public WebElement getCheckAddressLine2Txt() {
        return checkAddressLine2Txt;
    }

    public WebElement getCheckCityTxt() {
        return checkCityTxt;
    }

    public WebElement getCheckStateDD() {
        return checkStateDD;
    }

    public WebElement getCheckPostalCodeTxt() {
        return checkPostalCodeTxt;
    }

    public WebElement getGiftCardPayCopyLinkBtn() {
        return giftCardPayCopyLinkBtn;
    }

    public WebElement getGiftCardPayAgreeCheckBox() {
        return giftCardPayAgreeCheckBox;
    }

    public WebElement getGiftCardPayEmail() {
        return giftCardPayEmail;
    }

    public WebElement getGiftCardPayAmount() {
        return giftCardPayAmount;
    }

    public WebElement getOtpVerifyMsg() {
        return otpVerifyMsg;
    }

    public WebElement getStandardAmount() {
        return standardAmount;
    }

    public WebElement getStandardTotalAmount() {
        return standardTotalAmount;
    }

    public WebElement getStandardBankName() {
        return standardBankName;
    }

    public WebElement getStandardVerificationDD() {
        return standardVerificationDD;
    }

    public WebElement getStandardSendCodeBtn() {
        return standardSendCodeBtn;
    }

    public WebElement getStandardOtpConfirmationMsg() {
        return standardOtpConfirmationMsg;
    }

    public WebElement getStandardOtpTxt() {
        return standardOtpTxt;
    }

    public WebElement getStandardVerifyBtn() {
        return standardVerifyBtn;
    }

    public WebElement getStandardVerificationMsg() {
        return standardVerificationMsg;
    }

    public WebElement getStandardBankTransferMsg() {
        return standardBankTransferMsg;
    }

    public WebElement getStandardTransferBtn() {
        return standardTransferBtn;
    }

    public WebElement getStandardEstimatedArrivalDate() {
        return standardEstimatedArrivalDate;
    }

    public WebElement getCheckAmountTxt() {
        return checkAmountTxt;
    }

    public WebElement getCheckContinueBtn() {
        return checkContinueBtn;
    }

    public WebElement getCheckCountryMsg() {
        return checkCountryMsg;
    }

    public WebElement getPvvContinueBtn() {
        return pvvContinueBtn;
    }

    public WebElement getPvvCardTypeDD() {
        return pvvCardTypeDD;
    }

    public WebElement getPvvCardValueDD() {
        return pvvCardValueDD;
    }

    public WebElement getPvvAmountTxt() {
        return pvvAmountTxt;
    }

    public WebElement getRecipientEmailPVVTxt() {
        return recipientEmailPVVTxt;
    }

    public WebElement getRecipientEmailPVVMsg() {
        return recipientEmailPVVMsg;
    }

    public WebElement getPvvCardCurrencyDD() {
        return pvvCardCurrencyDD;
    }

    public WebElement getPvvMoreInfo() {
        return pvvMoreInfo;
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public WebElement getStandardContinueBtn() {
        return standardContinueBtn;
    }

    public WebElement getRapidContinueBtn() {
        return rapidContinueBtn;
    }

    public WebElement getRapidReceivingBankDD() {
        return rapidReceivingBankDD;
    }

    public WebElement getStandardReceivingBankDD() {
        return standardReceivingBankDD;
    }

    public WebElement getTransferTypeMsg() {
        return transferTypeMsg;
    }

    public WebElement getLinkRapidCard() {
        return linkRapidCard;
    }

    public WebElement getRapidAmountTxt() {
        return rapidAmountTxt;
    }

    public WebElement getStandardAmountTxt() {
        return standardAmountTxt;
    }

    public WebElement getMoreInfoLink() {
        return moreInfoLink;
    }

    public WebElement getFromWalletDD() {
        return fromWalletDD;
    }

    public WebElement getTransferTypeDD() {
        return transferTypeDD;
    }

    public WebElement getTransferBtn() {
        return transferBtn;
    }

    public WebElement getLinkNewBank() {
        return linkNewBank;
    }
}
