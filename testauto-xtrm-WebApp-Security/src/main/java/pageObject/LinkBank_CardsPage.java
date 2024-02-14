package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinkBank_CardsPage {
    WebDriver driver;

    public LinkBank_CardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@id='li8']/a")
    private WebElement linkedBanksTab;

    @FindBy(xpath = "//input[contains(@id,'btnAddNewBene')]")
    private WebElement linkBankAccountBtn;

    @FindBy(id = "btnGoTransfer")
    private WebElement transferGoBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnGoFund")
    private WebElement fundGoBtn;

    @FindBy(id = "lblErrMsg")
    private WebElement errMsg;

    @FindBy(id = "plaid-link-iframe-1")
    private WebElement plaidIframe;

    @FindBy(xpath = "//iframe[@id='plaid-link-iframe-1'] //button[contains(@class, 'Touchable-module_resetButtonOrLink')]")
    private WebElement plaidCloseIcon;

    @FindBy(id = "btnGoRapidTransfer")
    private WebElement rapidGoBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnAddCard")
    private WebElement rapidLinkCardBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtCardNo")
    private WebElement rapidCardNoTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtCVV")
    private WebElement rapidCardCVVTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlMonth")
    private WebElement rapidCardExpMonthDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlYear")
    private WebElement rapidCardExpYearDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblApprovedtext")
    private WebElement rapidCardErrMsg;

    @FindBy(xpath = "//input[contains(@id,'btnCancel')]")
    private WebElement cancelBtn;

    @FindBy(className = "Internallinks")
    private WebElement rapidMoreInfoCSCLink;

    @FindBy(xpath = "//table[@id='ctl00_ctl00_TopMenu_MiniTab_gvBenDetails']/tbody/tr[not(contains(@class,'GridHeader'))]")
    private List<WebElement> bankListCount;

    @FindBy(xpath = "//span[contains(@id,'lblPaymentMethod') or contains(@id,'lblACHFund')]")
    private List<WebElement> bankMethod;

    @FindBy(xpath = "//button[contains(@id,'ctl00_ctl00_TopMenu_MiniTab_gvBenDetails')]")
    private List<WebElement> bankDetailsDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblMsg")
    private WebElement bankMsg;

    @FindBy(xpath = "//li/a[contains(@id,'ctl00_ctl00_TopMenu_MiniTab_gvBenDetails')]")
    private List<WebElement> bankDeleteOption;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlBankDirectDebitCountries")
    private WebElement fundGoCountryDD;

    @FindBy(xpath = "//input[contains(@id,'btnLinkAccount')]")
    private WebElement transferLinkContinueBtn;

    @FindBy(xpath = "//select[contains(@id,'ddlBankCountry')]")
    private WebElement transferBankCountryDD;

    @FindBy(xpath = "//select[contains(@id,'ddlFundAccountType')]")
    private WebElement transferAccountTypeDD;

    @FindBy(xpath = "//input[contains(@id,'txtBankName')]")
    private WebElement transferBankNameTxt;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiaryAccountNumber')]")
    private WebElement transferAccountNumberTxt;

    @FindBy(xpath = "//select[contains(@id,'ddlCurrency')]")
    private WebElement transferCurrencyDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtOrgName")
    private WebElement transferAccountNameTxt;

    @FindBy(id = "txtBeneficiaryContactFname")
    private WebElement transferIndividualFNTxt;

    @FindBy(id = "txtBeneficiaryContactLname")
    private WebElement transferIndividualLNTxt;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiaryPhoneNo')]")
    private WebElement transferMobileNumberTxt;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiaryAddress1')]")
    private WebElement transferAddress1Txt;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiaryAddress2')]")
    private WebElement transferAddress2Txt;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiarybuilding')]")
    private WebElement transferBuildingTxt;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiaryCity')]")
    private WebElement transferCityTxt;

    @FindBy(xpath = "//select[contains(@id,'ddlBeneficiaryCountry')]")
    private WebElement transferCountryDD;

    @FindBy(xpath = "//select[contains(@id,'ddlBeneficiaryState')]")
    private WebElement transferStateDD;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiaryZip')]")
    private WebElement transferPostalCodeTxt;

    @FindBy(xpath = "//input[contains(@id,'txtBeneficiaryAccountNumber')]")
    private WebElement transferReEnterAccountNumberTxt;

    @FindBy(xpath = "//input[contains(@id,'txtRoutingCode')]")
    private WebElement transferRoutingTxt;

    @FindBy(xpath = "//input[contains(@id,'txtSwiftBic')]")
    private WebElement transferSwiftBicTxt;

    @FindBy(xpath = "//input[contains(@id,'btnBack')]")
    private WebElement transferBackBtn;

    @FindBy(xpath = "//span[contains(@id,'lblErr')]")
    private WebElement transferErrMsg;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_aLinkBankManual")
    private WebElement fundManualMethodOption;

    @FindBy(id = "txtBeneficiaryName")
    private WebElement fundVerifyBeneficiaryNameTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnSubmit")
    private WebElement fundVerifySubmitBtn;



    public WebElement getLinkedBanksTab() {
        return linkedBanksTab;
    }

    public WebElement getLinkBankAccountBtn() {
        return linkBankAccountBtn;
    }

    public WebElement getTransferGoBtn() {
        return transferGoBtn;
    }

    public WebElement getFundGoBtn() {
        return fundGoBtn;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }

    public WebElement getPlaidIframe() {
        return plaidIframe;
    }

    public WebElement getPlaidCloseIcon() {
        return plaidCloseIcon;
    }

    public WebElement getRapidGoBtn() {
        return rapidGoBtn;
    }

    public WebElement getRapidLinkCardBtn() {
        return rapidLinkCardBtn;
    }

    public WebElement getRapidCardNoTxt() {
        return rapidCardNoTxt;
    }

    public WebElement getRapidCardCVVTxt() {
        return rapidCardCVVTxt;
    }

    public WebElement getRapidCardExpMonthDD() {
        return rapidCardExpMonthDD;
    }

    public WebElement getRapidCardExpYearDD() {
        return rapidCardExpYearDD;
    }

    public WebElement getRapidCardErrMsg() {
        return rapidCardErrMsg;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public WebElement getRapidMoreInfoCSCLink() {
        return rapidMoreInfoCSCLink;
    }

    public List<WebElement> getBankListCount() {
        return bankListCount;
    }

    public List<WebElement> getBankMethod() {
        return bankMethod;
    }

    public List<WebElement> getBankDetailsDD() {
        return bankDetailsDD;
    }

    public WebElement getBankMsg() {
        return bankMsg;
    }

    public List<WebElement> getBankDeleteOption() {
        return bankDeleteOption;
    }

    public WebElement getFundGoCountryDD() {
        return fundGoCountryDD;
    }

    public WebElement getTransferLinkContinueBtn() {
        return transferLinkContinueBtn;
    }

    public WebElement getTransferBankCountryDD() {
        return transferBankCountryDD;
    }

    public WebElement getTransferAccountTypeDD() {
        return transferAccountTypeDD;
    }

    public WebElement getTransferBankNameTxt() {
        return transferBankNameTxt;
    }

    public WebElement getTransferAccountNumberTxt() {
        return transferAccountNumberTxt;
    }

    public WebElement getTransferCurrencyDD() {
        return transferCurrencyDD;
    }

    public WebElement getTransferAccountNameTxt() {
        return transferAccountNameTxt;
    }

    public WebElement getTransferIndividualFNTxt() {
        return transferIndividualFNTxt;
    }

    public WebElement getTransferIndividualLNTxt() {
        return transferIndividualLNTxt;
    }

    public WebElement getTransferMobileNumberTxt() {
        return transferMobileNumberTxt;
    }

    public WebElement getTransferAddress1Txt() {
        return transferAddress1Txt;
    }

    public WebElement getTransferAddress2Txt() {
        return transferAddress2Txt;
    }

    public WebElement getTransferBuildingTxt() {
        return transferBuildingTxt;
    }

    public WebElement getTransferCityTxt() {
        return transferCityTxt;
    }

    public WebElement getTransferCountryDD() {
        return transferCountryDD;
    }

    public WebElement getTransferStateDD() {
        return transferStateDD;
    }

    public WebElement getTransferPostalCodeTxt() {
        return transferPostalCodeTxt;
    }

    public WebElement getTransferReEnterAccountNumberTxt() {
        return transferReEnterAccountNumberTxt;
    }

    public WebElement getTransferRoutingTxt() {
        return transferRoutingTxt;
    }

    public WebElement getTransferSwiftBicTxt() {
        return transferSwiftBicTxt;
    }

    public WebElement getTransferBackBtn() {
        return transferBackBtn;
    }

    public WebElement getTransferErrMsg() {
        return transferErrMsg;
    }

    public WebElement getFundManualMethodOption() {
        return fundManualMethodOption;
    }

    public WebElement getFundVerifyBeneficiaryNameTxt() {
        return fundVerifyBeneficiaryNameTxt;
    }

    public WebElement getFundVerifySubmitBtn() {
        return fundVerifySubmitBtn;
    }
}
