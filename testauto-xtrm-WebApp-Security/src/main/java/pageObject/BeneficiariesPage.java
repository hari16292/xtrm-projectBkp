package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BeneficiariesPage {
    WebDriver driver;

    public BeneficiariesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_txtSearch")
    private WebElement searchTxt;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlFilter")
    private WebElement filterDD;

    @FindBy(linkText = "More Info")
    private WebElement moreInfoLink;

    @FindBy(xpath = "//p[@class='small'][4]")
    private List<WebElement> personalBeneficiaryListMail;

    @FindBy(linkText = "Add New Personal Beneficiary")
    private WebElement addNewBeneficiaryBtn;

    @FindBy(xpath = "//section[@id='secContent'] //button[contains(@id,'dLabel')]")
    private List<WebElement> menuDD;

    @FindBy(linkText = "Send")
    private List<WebElement> sendBtn;

    @FindBy(linkText = "Add")
    private WebElement addBtn;

    @FindBy(id = "spnMsg")
    private WebElement message;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'Remove Beneficiary')]")
    private WebElement removeOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'Profile')]")
    private WebElement viewProfileOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Wallets')]")
    private WebElement viewWalletsOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Linked Banks') or contains(text(),'View Linked banks')]")
    private WebElement viewLinkedBanksOption;

    @FindBy(linkText = "Company Beneficiaries")
    private WebElement companyBeneficiaryButton;

    @FindBy(className = "CompanyNameLink")
    private List<WebElement> companyBeneficiaryName;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Users')]")
    private WebElement viewUsersOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'View Employees')]")
    private WebElement viewEmployeesOption;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[contains(text(),'Edit Company Details')]")
    private WebElement editCompanyDetailsOption;

    @FindBy(linkText = "Activity")
    private List<WebElement> activityBtn;

    public WebElement getSearchTxt() {
        return searchTxt;
    }

    public WebElement getFilterDD() {
        return filterDD;
    }

    public WebElement getMoreInfoLink() {
        return moreInfoLink;
    }

    public List<WebElement> getPersonalBeneficiaryListMail() {
        return personalBeneficiaryListMail;
    }

    public WebElement getAddNewBeneficiaryBtn() {
        return addNewBeneficiaryBtn;
    }

    public List<WebElement> getMenuDD() {
        return menuDD;
    }

    public List<WebElement> getSendBtn() {
        return sendBtn;
    }

    public WebElement getAddBtn() {
        return addBtn;
    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getRemoveOption() {
        return removeOption;
    }

    public WebElement getViewProfileOption() {
        return viewProfileOption;
    }

    public WebElement getViewWalletsOption() {
        return viewWalletsOption;
    }

    public WebElement getViewLinkedBanksOption() {
        return viewLinkedBanksOption;
    }

    public WebElement getCompanyBeneficiaryButton() {
        return companyBeneficiaryButton;
    }

    public List<WebElement> getCompanyBeneficiaryName() {
        return companyBeneficiaryName;
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
}
