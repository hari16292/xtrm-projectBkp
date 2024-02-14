package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftPaymentsPage {
    WebDriver driver;
    public DraftPaymentsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='dropdown dropdown-bubble']//button[@id='dLabel']")
    private WebElement dltDropDownBtn;

    @FindBy(xpath = "//a[normalize-space()='Delete']")
    private WebElement dltOption;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblMsgsuccess")
    private WebElement confirmationMsg;

    public WebElement getConfirmationMsg() {
        return confirmationMsg;
    }

    public WebElement getDltOption() {
        return dltOption;
    }

    public WebElement getDltDropDownBtn() {
        return dltDropDownBtn;
    }
}
