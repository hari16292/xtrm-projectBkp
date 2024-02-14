package pageObject.advancedSendPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class AdvanceSendStep1Page {
    WebDriver driver;

    public AdvanceSendStep1Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ddlPaymentTypes")
    private WebElement paymentTypeDD;

    @FindBy(id = "ddlCurrencyList")
    private WebElement fromWalletDD;

    @FindBy(id = "ddlProgram")
    private WebElement programDD;

    @FindBy(id = "txtDescription")
    private WebElement descriptionTxt;

    @FindBy(id = "ddlPaymentMethod")
    private WebElement paymentMethodDD;

    @FindBy(xpath = "(//a[text()='More Info'])[1]")
    private WebElement paymentTypeMoreInfo;

    @FindBy(xpath = "(//a[text()='More Info'])[2]")
    private WebElement paymentMethodMoreInfo;

    @FindBy(id = "btnCancelbtm")
    private WebElement cancelBtn;

    @FindBy(id = "btnDraft")
    private WebElement saveDraftBtn;

    @FindBy(id = "btnNext")
    private WebElement nextBtn;

    @FindBy(id = "Text1")
    private WebElement amountTxt;

    @FindBy(xpath = "//div[@id='PnlCancelCampaign'] //div[@class='modal-content']")
    private WebElement cancelAlert;

    @FindBy(xpath = "//label[normalize-space()='Cancel?']")
    private WebElement alertHeader;

    @FindBy(xpath = "//div[@class='PayoutFooter']//button[text()='Yes']")
    private WebElement alertYesBtn;

    @FindBy(id = "btnPrevious")
    private WebElement previousBtn;

    @FindBy(xpath = "//span[contains(@class,'Validator spnError')]")
    private WebElement errMsg;

    @FindBy(id = "ddlTangoCategory")
    private WebElement giftCardType;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtRewardAmount")
    private WebElement giftCardAmountTxt;

    @FindBy(xpath = "//span[@class='spnMinVal hide ng-binding']")
    private WebElement hiddenGiftCardMinAmount;

    @FindBy(xpath = "//span[@class='spnMaxVal hide ng-binding']")
    private WebElement hiddenGiftCardMaxAmount;

    @FindBy(id = "lblAmount")
    private WebElement giftCardFixedAmountValue;

    @FindBy(id = "txtSearch")
    private WebElement employerSearchTxt;

    @FindBy(id = "ui-id-1")
    private WebElement employerListPopup;

    @FindBy(xpath = "//ul[@id='ui-id-1']/li //div[@class='autocomplete_mid_text-container']/div[@class='name']")
    private List<WebElement> employerList;

    @FindBy(xpath = "//ul[@id='ui-id-1']/li //div[@class='autocomplete_rgt_text_container']/a")
    private List<WebElement> employerListSelectBtn;


    public WebElement getEmployerListPopup() {
        return employerListPopup;
    }

    public List<WebElement> getEmployerList() {
        return employerList;
    }

    public List<WebElement> getEmployerListSelectBtn() {
        return employerListSelectBtn;
    }

    public WebElement getEmployerSearchTxt() {
        return employerSearchTxt;
    }

    public WebElement getHiddenGiftCardMaxAmount() {
        return hiddenGiftCardMaxAmount;
    }

    public WebElement getHiddenGiftCardMinAmount() {
        return hiddenGiftCardMinAmount;
    }

    public WebElement getGiftCardAmountTxt() {
        return giftCardAmountTxt;
    }

    public WebElement getGiftCardFixedAmountValue() {
        return giftCardFixedAmountValue;
    }

    public WebElement getGiftCardType() {
        return giftCardType;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }

    public WebElement getPreviousBtn() {
        return previousBtn;
    }

    public WebElement getAlertYesBtn() {
        return alertYesBtn;
    }

    public WebElement getAlertHeader() {
        return alertHeader;
    }

    public WebElement getCancelAlert() {
        return cancelAlert;
    }

    public WebElement getAmountTxt() {
        return amountTxt;
    }

    public WebElement getPaymentTypeDD() {
        return paymentTypeDD;
    }

    public WebElement getFromWalletDD() {
        return fromWalletDD;
    }

    public WebElement getProgramDD() {
        return programDD;
    }

    public WebElement getDescriptionTxt() {
        return descriptionTxt;
    }

    public WebElement getPaymentMethodDD() {
        return paymentMethodDD;
    }

    public WebElement getPaymentTypeMoreInfo() {
        return paymentTypeMoreInfo;
    }

    public WebElement getPaymentMethodMoreInfo() {
        return paymentMethodMoreInfo;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public WebElement getSaveDraftBtn() {
        return saveDraftBtn;
    }

    public WebElement getNextBtn() {
        return nextBtn;
    }
}
