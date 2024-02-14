package pageObject.advancedProfilePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Step1Page {
    WebDriver driver;

    public Step1Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Company (Advanced)")
    private WebElement advancedProfileTabOption;

    @FindBy(xpath = "//span[normalize-space()='Account Identity Level:']/../a")
    private WebElement advancedMoreInfoLink;

    @FindBy(xpath = "//div[@id='ctl00_ctl00_TopMenu_MiniTab_divHelpText']/a")
    private WebElement advancedMoreInfo1Link;

    @FindBy(xpath = "//span[@id='ctl00_ctl00_TopMenu_MiniTab_lblAdvancedServices']//a")
    private WebElement advancedMoreInfo2Link;

    @FindBy(xpath = "//h2[contains(text(),'Please complete and submit your advanced profile b')]/a")
    private WebElement advancedMoreInfo3Link;

    @FindBy(xpath = "//a[normalize-space()='Advanced Services']")
    private WebElement advancedServicesLink;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlCountry")
    private WebElement countryDD;

    @FindBy(xpath = "//span[normalize-space()='Payments Type:']")
    private WebElement paymentTypeVisibleText;
    @FindBy(id = "ddlRemitterType")
    private WebElement paymentTypeDD;

    @FindBy(xpath = "(//input[@name='next'])[2]")
    private WebElement nextBtn;

    @FindBy(xpath = "//div[@class='divDomestic']")
    private WebElement USPagesCount;

    @FindBy(xpath = "//ul[@id='USprogressbar']/li[1]")
    private WebElement pageNumOne;

    @FindBy(xpath = "//ul[@id='USprogressbar']/li[2]")
    private WebElement pageNumTwo;

    @FindBy(xpath = "//ul[@id='USprogressbar']/li[3]")
    private WebElement pageNumThree;

    public WebElement getAdvancedProfileTabOption() {
        return advancedProfileTabOption;
    }

    public WebElement getAdvancedMoreInfoLink() {
        return advancedMoreInfoLink;
    }

    public WebElement getAdvancedMoreInfo1Link() {
        return advancedMoreInfo1Link;
    }

    public WebElement getAdvancedMoreInfo2Link() {
        return advancedMoreInfo2Link;
    }

    public WebElement getAdvancedMoreInfo3Link() {
        return advancedMoreInfo3Link;
    }

    public WebElement getAdvancedServicesLink() {
        return advancedServicesLink;
    }

    public WebElement getCountryDD() {
        return countryDD;
    }

    public WebElement getPaymentTypeVisibleText() {
        return paymentTypeVisibleText;
    }

    public WebElement getPaymentTypeDD() {
        return paymentTypeDD;
    }

    public WebElement getNextBtn() {
        return nextBtn;
    }

    public WebElement getUSPagesCount() {
        return USPagesCount;
    }

    public WebElement getPageNumOne() {
        return pageNumOne;
    }

    public WebElement getPageNumTwo() {
        return pageNumTwo;
    }

    public WebElement getPageNumThree() {
        return pageNumThree;
    }
}
