package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MailinatorPage {
    WebDriver driver;
    public MailinatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[@class='table-striped jambo_table']/tbody/tr/td[contains(@onclick,'showTheMessage')][2]")
    private List<WebElement> mailList;

    @FindBy(xpath = "//table[@class='table-striped jambo_table']/tbody/tr/td[contains(@onclick,'showTheMessage')][2]/following-sibling::td")
    private List<WebElement> mailTime;

    @FindBy(xpath = "/html/body/table/tbody/tr/td[contains(text(), 'Your verification code is:')]/span")
    private WebElement mailOTP;

    @FindBy(xpath="//button[@value='Search for public inbox for free']")
    private WebElement gomailButton;

    @FindBy(id = "html_msg_body")
    private WebElement iframe;

    @FindBy(xpath="//input[@id='search']")
    private WebElement searchMail;

    @FindBy(xpath = "//tr[8]/td")
    private WebElement mailBody;

    @FindBy(xpath = "(//p)[1]/following-sibling::p[1]")
    private WebElement pvvMailAmount;

    public WebElement getPvvMailAmount() {
        return pvvMailAmount;
    }

    public WebElement getIframe() {
        return iframe;
    }

    public WebElement getMailBody() {
        return mailBody;
    }

    public WebElement getMailOTP() {
        return mailOTP;
    }
    public List<WebElement> getMailList() {
        return mailList;
    }

	public WebElement getgoButton() {
    	return gomailButton;
    }

     public WebElement getsearchMail() {
    	return searchMail;
    }

    public List<WebElement> getMailTime() {
        return mailTime;
    }
}
