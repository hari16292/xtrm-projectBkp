package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //get login text box webElement
    @FindBy(id = "inputEmail")
    private WebElement loginTxt;

    //get password text box webElement
    @FindBy(id = "inputPassword")
    private WebElement passwordTxt;

    //get sign in button webElement
    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInBtn;

    @FindBy(xpath = "//p[@role='alert']")
    private WebElement notificationMsg;

    public WebElement getNotificationMsg() {
        return notificationMsg;
    }

    public WebElement getLoginTxt() {
        return loginTxt;
    }

    public WebElement getPasswordTxt() {
        return passwordTxt;
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }
}
