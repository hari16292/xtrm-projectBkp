package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MassPaymentPage {
    WebDriver driver;

    public MassPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnUploadMassPayment")
    private WebElement massPaymentFileBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_FUConnectFiles")
    private WebElement uploadBtn;

    @FindBy(id = "ddlPrograms")
    private WebElement programDD;

    @FindBy(id = "ddlWallets")
    private WebElement walletDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtPurchaseOrder")
    private WebElement purchaseOrderTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkInvoice")
    private WebElement invoiceChkBox;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtareabody")
    private WebElement addInfoTxt;

    @FindBy(xpath = "//a[normalize-space()='Delete']")
    private WebElement deleteFileOption;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnSubmit")
    private WebElement submitBtn;

    @FindBy(xpath = "//span[@class='Validator']")
    private WebElement confirmMsg;

    @FindBy(id = "reportrange")
    private WebElement dateOption;

    @FindBy(xpath = "//li[normalize-space()='Last 7 Days']")
    private WebElement last7DaysOption;

    @FindBy(xpath = "//a[normalize-space()='Details']")
    private List<WebElement> detailsOption;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnDone")
    private WebElement doneBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblDescription")
    private WebElement descriptionMsg;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ltrlMessage")
    private WebElement errMsg;

    public WebElement getErrMsg() {
        return errMsg;
    }

    public WebElement getDescriptionMsg() {
        return descriptionMsg;
    }

    public WebElement getDoneBtn() {
        return doneBtn;
    }

    public List<WebElement> getDetailsOption() {
        return detailsOption;
    }

    public WebElement getDateOption() {
        return dateOption;
    }

    public WebElement getLast7DaysOption() {
        return last7DaysOption;
    }

    public WebElement getConfirmMsg() {
        return confirmMsg;
    }

    public WebElement getSubmitBtn() {
        return submitBtn;
    }

    public WebElement getMassPaymentFileBtn() {
        return massPaymentFileBtn;
    }

    public WebElement getUploadBtn() {
        return uploadBtn;
    }

    public WebElement getProgramDD() {
        return programDD;
    }

    public WebElement getWalletDD() {
        return walletDD;
    }

    public WebElement getPurchaseOrderTxt() {
        return purchaseOrderTxt;
    }

    public WebElement getInvoiceChkBox() {
        return invoiceChkBox;
    }

    public WebElement getAddInfoTxt() {
        return addInfoTxt;
    }

    public WebElement getDeleteFileOption() {
        return deleteFileOption;
    }
}
