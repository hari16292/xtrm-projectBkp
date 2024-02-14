package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    WebDriver driver;
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "login")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[normalize-space()='Learn More']")
    private WebElement learnMoreBtn;

    public WebElement getLearnMoreBtn() {
        return learnMoreBtn;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }
}
