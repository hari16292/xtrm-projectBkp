package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundPage {
    WebDriver driver;

    public FundPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lnkbtnCreateCA")
    private WebElement createWalletBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlCashAccount")
    private WebElement walletDD;

    @FindBy(id = "ddlFundMethod")
    private WebElement fundMethodDD;

    @FindBy(xpath = "//input[@id='ctl00_ctl00_TopMenu_MiniTab_txtFundAmount' or @id='ctl00_ctl00_TopMenu_MiniTab_txtFundAmt']")
    private WebElement amountTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkInvoice")
    private WebElement invoiceRB;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueBtn;

    @FindBy(xpath = "//span[@id='lblErrorMsg']")
    private WebElement errMsg;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblTotAmt")
    private WebElement totalAmount;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_cbkTerms")
    private WebElement termsChkBox;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblFundingWalletID")
    private WebElement fundingWalletId;

    @FindBy(xpath = "//input[@id='ctl00_ctl00_TopMenu_MiniTab_btnPay' or @id='ctl00_ctl00_TopMenu_MiniTab_btnFund']")
    private WebElement requestContinueBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtPurchaseOrderNo")
    private WebElement purchaseOrderTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblMessage")
    private WebElement labelMsg;

    @FindBy(id = "ddlBankList")
    private WebElement bankACH;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    private WebElement continueACHBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlCreditCard")
    private WebElement selectCardDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtNameonCard")
    private WebElement nameOnCardTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlCardType")
    private WebElement cardTypeDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtCardNo")
    private WebElement cardNoTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlMonth")
    private WebElement expMonthDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlYear")
    private WebElement expYearDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtCVV2")
    private WebElement cscTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_cbkSaveCard")
    private WebElement saveCardDetailsBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtFName")
    private WebElement firstNameTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtLName")
    private WebElement lastNameTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtAddress1")
    private WebElement addr1Txt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtCity")
    private WebElement cityTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlCountry")
    private WebElement countryDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlState")
    private WebElement stateDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtZipcode")
    private WebElement zipcodeTxt;

    public WebElement getCreateWalletBtn() {
        return createWalletBtn;
    }

    public WebElement getFirstNameTxt() {
        return firstNameTxt;
    }

    public WebElement getLastNameTxt() {
        return lastNameTxt;
    }

    public WebElement getAddr1Txt() {
        return addr1Txt;
    }

    public WebElement getCityTxt() {
        return cityTxt;
    }

    public WebElement getCountryDD() {
        return countryDD;
    }

    public WebElement getStateDD() {
        return stateDD;
    }

    public WebElement getZipcodeTxt() {
        return zipcodeTxt;
    }

    public WebElement getNameOnCardTxt() {
        return nameOnCardTxt;
    }

    public WebElement getCardTypeDD() {
        return cardTypeDD;
    }

    public WebElement getCardNoTxt() {
        return cardNoTxt;
    }

    public WebElement getExpMonthDD() {
        return expMonthDD;
    }

    public WebElement getExpYearDD() {
        return expYearDD;
    }

    public WebElement getCscTxt() {
        return cscTxt;
    }

    public WebElement getSaveCardDetailsBtn() {
        return saveCardDetailsBtn;
    }

    public WebElement getSelectCardDD() {
        return selectCardDD;
    }

    public WebElement getContinueACHBtn() {
        return continueACHBtn;
    }

    public WebElement getBankACH() {
        return bankACH;
    }

    public WebElement getLabelMsg() {
        return labelMsg;
    }

    public WebElement getPurchaseOrderTxt() {
        return purchaseOrderTxt;
    }

    public WebElement getRequestContinueBtn() {
        return requestContinueBtn;
    }

    public WebElement getFundingWalletId() {
        return fundingWalletId;
    }

    public WebElement getTermsChkBox() {
        return termsChkBox;
    }

    public WebElement getTotalAmount() {
        return totalAmount;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }

    public WebElement getWalletDD() {
        return walletDD;
    }

    public WebElement getFundMethodDD() {
        return fundMethodDD;
    }

    public WebElement getAmountTxt() {
        return amountTxt;
    }

    public WebElement getInvoiceRB() {
        return invoiceRB;
    }

    public WebElement getContinueBtn() {
        return continueBtn;
    }
}
