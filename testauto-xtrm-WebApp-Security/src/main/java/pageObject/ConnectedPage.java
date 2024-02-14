package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConnectedPage {
    WebDriver driver;

    public ConnectedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "More Info")
    private WebElement moreInfoLink;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlFilter")
    private WebElement filterDD;

    @FindBy(xpath = "//div[@class='divConnectedAccounts'] //td[@class='col1']")
    private List<WebElement> personalBeneficiaryListMail;

    @FindBy(xpath = "//table[@id='ctl00_ctl00_TopMenu_MiniTab_tblListView'] //button[contains(@id,'dLabel')]")
    private List<WebElement> menuDD;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'Profile')]")
    private WebElement viewProfileOption;

    @FindBy(linkText = "Send")
    private List<WebElement> sendBtn;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'Send Payments')]")
    private WebElement compSendPaymentOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Wallets')]")
    private WebElement viewWalletsOption;

    @FindBy(linkText = "Wallets")
    private List<WebElement> compWalletBtn;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Linked Banks') or contains(text(),'View Linked banks')]")
    private WebElement viewLinkedBanksOption;

    @FindBy(linkText = "Connected Companies")
    private WebElement connectedCompaniesOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Users')]")
    private WebElement viewUsersOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Employees')]")
    private WebElement viewEmployeesOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'Edit Company Details')]")
    private WebElement editCompanyDetailsOption;

    @FindBy(linkText = "Activity")
    private List<WebElement> activityBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lnkbtnDownload")
    private WebElement downloadBtn;

    @FindBy(linkText = "Get API Credentials")
    private WebElement getAPICredentialsOption;

    public WebElement getMoreInfoLink() {
        return moreInfoLink;
    }

    public WebElement getFilterDD() {
        return filterDD;
    }

    public List<WebElement> getPersonalBeneficiaryListMail() {
        return personalBeneficiaryListMail;
    }

    public List<WebElement> getSendBtn() {
        return sendBtn;
    }

    public WebElement getCompSendPaymentOption() {
        return compSendPaymentOption;
    }

    public List<WebElement> getMenuDD() {
        return menuDD;
    }

    public WebElement getViewProfileOption() {
        return viewProfileOption;
    }

    public WebElement getViewWalletsOption() {
        return viewWalletsOption;
    }

    public List<WebElement> getCompWalletBtn() {
        return compWalletBtn;
    }

    public WebElement getViewLinkedBanksOption() {
        return viewLinkedBanksOption;
    }

    public WebElement getConnectedCompaniesOption() {
        return connectedCompaniesOption;
    }

    public WebElement getViewUsersOption() {
        return viewUsersOption;
    }

    public WebElement getViewEmployeesOption() {
        return viewEmployeesOption;
    }

    public WebElement getEditCompanyDetailsOption() {
        return editCompanyDetailsOption;
    }

    public List<WebElement> getActivityBtn() {
        return activityBtn;
    }

    public WebElement getDownloadBtn() {
        return downloadBtn;
    }

    public WebElement getGetAPICredentialsOption() {
        return getAPICredentialsOption;
    }
}
