package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SimpleSendPage {
    WebDriver driver;

    public SimpleSendPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtCompanyName")
    private WebElement sendToSearchTxt;

    @FindBy(id = "ui-id-1")
    private WebElement recipientListPopup;

    @FindBy(xpath = "(//a[(contains(text(),'Select') and @class='AddEmployee button-primary bold') or normalize-space()='Continue'])")
    private List<WebElement> selectRecipientBtn;

    @FindBy(xpath = "(//a[(contains(text(),'Select') and @class='AddEmployee button-primary bold') or normalize-space()='Continue'])/../../preceding-sibling::td[@align='left']/p/span")
    private List<WebElement> selectRecipientType;

    @FindBy(xpath = "//select[contains(@id,'ddlCashAccount')]")
    private WebElement fromWalletDD;

    @FindBy(xpath = "//input[contains(@id,'txtAmount')]")
    private WebElement amountTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlPrograms")
    private WebElement programDD;

    @FindBy(xpath = "//textarea[contains(@id,'txtDescription')]")
    private WebElement descriptionTxt;

    @FindBy(xpath = "//input[contains(@id,'btnCompanyContinue')]")
    private WebElement continueBtn;

    @FindBy(xpath = "//input[contains(@id,'btnPrevious')]")
    private WebElement previousBtn;

    @FindBy(xpath = "//input[contains(@id,'btnCancel')]")
    private WebElement cancelBtn;

    @FindBy(xpath = "//input[contains(@id,'btnSimpleSendContinue')]")
    private WebElement sendBtn;

    @FindBy(xpath = "//span[contains(@id,'lblCurrency')]")
    private WebElement confirmAmountValue;

    @FindBy(xpath = "//select[contains(@id,'ddlFee')]")
    private WebElement selectFeeDD;

    @FindBy(className = "tdDescription")
    private WebElement descriptionValue;

    @FindBy(xpath = "//a[normalize-space()='View Transactions']")
    private WebElement viewTxnBtn;

    @FindBy(xpath = "//span[@id='ctl00_ctl00_TopMenu_MiniTab_lblMsg' or @id='ctl00_ctl00_TopMenu_MiniTab_spnMsg']")
    private WebElement errMsg;

    @FindBy(xpath = "//a[@class='AddUser']")
    private WebElement addUserBtn;

    @FindBy(xpath = "//a[@class='AddCompany']")
    private WebElement addCompanyBtn;

    @FindBy(xpath = "//span[@id='spnOrgAvail']/div")
    private WebElement companyNameAvailMsg;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtPartnerAdminEmail")
    private WebElement adminEmailTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtPartnerAdminFName")
    private WebElement adminFNTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtPartnerAdminLName")
    private WebElement adminLNTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnAdd")
    private WebElement addBtn;

    public WebElement getAddBtn() {
        return addBtn;
    }

    public WebElement getAdminEmailTxt() {
        return adminEmailTxt;
    }

    public WebElement getAdminFNTxt() {
        return adminFNTxt;
    }

    public WebElement getAdminLNTxt() {
        return adminLNTxt;
    }

    public WebElement getCompanyNameAvailMsg() {
        return companyNameAvailMsg;
    }

    public WebElement getAddUserBtn() {
        return addUserBtn;
    }

    public WebElement getAddCompanyBtn() {
        return addCompanyBtn;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }

    public WebElement getViewTxnBtn() {
        return viewTxnBtn;
    }

    public WebElement getSendBtn() {
        return sendBtn;
    }

    public WebElement getConfirmAmountValue() {
        return confirmAmountValue;
    }

    public WebElement getSelectFeeDD() {
        return selectFeeDD;
    }

    public WebElement getDescriptionValue() {
        return descriptionValue;
    }

    public WebElement getFromWalletDD() {
        return fromWalletDD;
    }

    public WebElement getAmountTxt() {
        return amountTxt;
    }

    public WebElement getProgramDD() {
        return programDD;
    }

    public WebElement getDescriptionTxt() {
        return descriptionTxt;
    }

    public WebElement getContinueBtn() {
        return continueBtn;
    }

    public WebElement getPreviousBtn() {
        return previousBtn;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public WebElement getSendToSearchTxt() {
        return sendToSearchTxt;
    }

    public WebElement getRecipientListPopup() {
        return recipientListPopup;
    }

    public List<WebElement> getSelectRecipientBtn() {
        return selectRecipientBtn;
    }

    public List<WebElement> getSelectRecipientType() {
        return selectRecipientType;
    }
}
