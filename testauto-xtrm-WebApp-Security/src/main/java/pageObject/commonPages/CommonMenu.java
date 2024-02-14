package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonMenu {
    WebDriver driver;

    public CommonMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='Md4Left']")
    private WebElement companyLogo;

    @FindBy(xpath = "//div/ul/li/a[contains(@href,'Home.aspx') and (@class='home' or @class='aLeftMenu aLeftMenuHome')]")
    private WebElement homeMenu;

    @FindBy(xpath = "//a[contains(@href,'Send.aspx') and (@class='send' or @class='aLeftMenu')]")
    private WebElement sendMenu;

    @FindBy(xpath = "//a[contains(@href,'Transfer.aspx') and (@class='transfer' or @class='aLeftMenu aLeftMenuCertificate')]")
    private WebElement transferMenu;

    @FindBy(xpath = "//div[@class='rotate']")
    private WebElement loaderGif;

    @FindBy(xpath = "//a[contains(@href,'Logout.aspx') and (@class='logout' or @class='aLeftMenua LeftMenuLogout')]")
    private WebElement logoutMenu;

    @FindBy(id = "ctl00_ctl00_aAlertsCount")
    private WebElement draftPaymentIcon;

    @FindBy(xpath = "//a[normalize-space()='View All']")
    private WebElement draftPaymentViewAll;

    @FindBy(xpath = "//a[contains(@href,'CurrencyExchange.aspx') and (@class='exchange' or @class='aLeftMenu aLeftMenuExchange')]")
    private WebElement exchangeMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuFundStep']/a")
    private WebElement fundMenu;

    @FindBy(xpath = "//a[contains(@href,'CashAccounts.aspx')]")
    private WebElement walletsMenu;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement taxInfoPopup;

    @FindBy(xpath = "//button[normalize-space()='Close']")
    private WebElement taxInfoCloseBtn;

    @FindBy(xpath = "//a[contains(@href,'ViewReportByCampaign')]")
    private WebElement reportsMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuPartners']/a")
    private WebElement beneficiaryMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuConnectedAccounts']/a")
    private WebElement connectedMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuProfile']/a")
    private WebElement profileMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuEmployess']/a")
    private WebElement userMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuPrograms']/a")
    private WebElement programsMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuSponsorship']/a")
    private WebElement integrationMenu;

    @FindBy(xpath = "//li[@id='liLeftMenuClaims']/a")
    private WebElement claimsMenu;

    @FindBy(xpath = "//a[@class='settings-icon' or (@class='notifyButton' and @data-original-title='Settings')]")
    private WebElement settingsIcon;

    @FindBy(xpath = "//li[@id='liLeftMenuBanks']/a")
    private WebElement banksMenu;


    public WebElement getBeneficiaryMenu() {
        return beneficiaryMenu;
    }

    public WebElement getReportsMenu() {
        return reportsMenu;
    }

    public WebElement getTaxInfoPopup() {
        return taxInfoPopup;
    }

    public WebElement getTaxInfoCloseBtn() {
        return taxInfoCloseBtn;
    }

    public WebElement getWalletsMenu() {
        return walletsMenu;
    }

    public WebElement getFundMenu() {
        return fundMenu;
    }

    public WebElement getExchangeMenu() {
        return exchangeMenu;
    }

    public WebElement getDraftPaymentViewAll() {
        return draftPaymentViewAll;
    }

    public WebElement getTransferMenu() {
        return transferMenu;
    }

    public WebElement getDraftPaymentIcon() {
        return draftPaymentIcon;
    }

    public WebElement getLogoutMenu() {
        return logoutMenu;
    }

    public WebElement getLoaderGif() {
        return loaderGif;
    }

    public WebElement getCompanyLogo() {
        return companyLogo;
    }

    public WebElement getHomeMenu() {
        return homeMenu;
    }

    public WebElement getSendMenu() {
        return sendMenu;
    }

    public WebElement getConnectedMenu() {
        return connectedMenu;
    }

    public WebElement getProfileMenu() {
        return profileMenu;
    }

    public WebElement getUserMenu() {
        return userMenu;
    }

    public WebElement getProgramsMenu() {
        return programsMenu;
    }

    public WebElement getIntegrationMenu() {
        return integrationMenu;
    }

    public WebElement getClaimsMenu() {
        return claimsMenu;
    }

    public WebElement getSettingsIcon() {
        return settingsIcon;
    }

    public WebElement getBanksMenu() {
        return banksMenu;
    }
}
