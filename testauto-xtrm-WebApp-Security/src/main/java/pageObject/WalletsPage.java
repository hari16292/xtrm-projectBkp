package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WalletsPage {

    WebDriver driver;

    public WalletsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='ctl00_ctl00_TopMenu_MiniTab_txtSearch' or @id='txtSearch']")
    private WebElement searchTxt;

    @FindBy(xpath = "//input[@id='ctl00_ctl00_TopMenu_MiniTab_btnGo' or @id='btnGo']")
    private WebElement goBtn;

    @FindBy(id = "btnCreate")
    private WebElement userCreateWalletBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lnkbtnCreateCA")
    private WebElement companyCreateWalletBtn;

    @FindBy(xpath = "//td[@class='col3']")
    private WebElement currencyValue;

    @FindBy(xpath = "//tr[@id='tdNo']/td")
    private WebElement noRecordsMsg;

    @FindBy(xpath = "//input[@placeholder='Enter wallet name']")
    private WebElement walletNameTxt;

    @FindBy(xpath = "//input[@value='Create']")
    private WebElement createBtn;

    @FindBy(xpath = "//input[@value='Cancel']")
    private WebElement cancelBtn;

    @FindBy(xpath = "//a[normalize-space()='More Info']")
    private WebElement walletCurrencyMoreInfo;

    @FindBy(xpath = "//span[@id='lblMsg']")
    private WebElement message;

    @FindBy(xpath = "//a[normalize-space()='Activity']")
    private List<WebElement> activityBtn;

    @FindBy(xpath = "//select[@id='ctl00_ctl00_TopMenu_MiniTab_ddlFilter' or @id='ddlFilter']")
    private WebElement activityFilterDD;

    @FindBy(xpath = "//a[normalize-space()='Details']")
    private List<WebElement> activityDetailsBtn;

    @FindBy(xpath = "//input[contains(@id,'btnContinue')]")
    private WebElement doneBtn;

    @FindBy(xpath = "//a[normalize-space()='Done']")
    private WebElement userTransferredDoneBtn;

    @FindBy(xpath = "//td[normalize-space()='No pending activity']")
    private WebElement noPendingMsg;

    @FindBy(xpath = "//a[@id='lnkbtnDownload' or @id='ctl00_ctl00_TopMenu_MiniTab_lbtnDownloadReport']")
    private WebElement activityDownload;

    @FindBy(xpath = "//tr[contains(@id,'tr')]//button[@id='dLabel']")
    private List<WebElement> activityDDBtn;

    @FindBy(xpath = "//li[@id='li8']/a")
    private WebElement moveOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[@class='EditCA'][normalize-space()='Edit']")
    private WebElement walletEditOption;


    public WebElement getActivityDownload() {
        return activityDownload;
    }

    public WebElement getNoPendingMsg() {
        return noPendingMsg;
    }

    public WebElement getDoneBtn() {
        return doneBtn;
    }

    public WebElement getUserTransferredDoneBtn() {
        return userTransferredDoneBtn;
    }

    public List<WebElement> getActivityDetailsBtn() {
        return activityDetailsBtn;
    }

    public WebElement getActivityFilterDD() {
        return activityFilterDD;
    }

    public List<WebElement> getActivityBtn() {
        return activityBtn;
    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public WebElement getWalletCurrencyMoreInfo() {
        return walletCurrencyMoreInfo;
    }

    public WebElement getCreateBtn() {
        return createBtn;
    }

    @FindBy(id = "ddlCurrency")
    private WebElement walletCurrencyDD;

    public WebElement getWalletNameTxt() {
        return walletNameTxt;
    }

    public WebElement getWalletCurrencyDD() {
        return walletCurrencyDD;
    }

    public WebElement getNoRecordsMsg() {
        return noRecordsMsg;
    }

    public WebElement getCurrencyValue() {
        return currencyValue;
    }

    public WebElement getSearchTxt() {
        return searchTxt;
    }

    public WebElement getGoBtn() {
        return goBtn;
    }

    public WebElement getUserCreateWalletBtn() {
        return userCreateWalletBtn;
    }

    public WebElement getCompanyCreateWalletBtn() {
        return companyCreateWalletBtn;
    }

    public List<WebElement> getActivityDDBtn() {
        return activityDDBtn;
    }

    public WebElement getMoveOption() {
        return moveOption;
    }

    public WebElement getWalletEditOption() {
        return walletEditOption;
    }
}



