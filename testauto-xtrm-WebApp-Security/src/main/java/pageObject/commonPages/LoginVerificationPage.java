package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginVerificationPage {
    WebDriver driver;

    public LoginVerificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='nice-select form-control ddlVerification']")
    private WebElement verificationDD;

    @FindBy(xpath = "//li[text()='Email']")
    private WebElement selectEmail;

    @FindBy(id = "ctl00_MainContent_btnGo")
    private WebElement goBtn;

    @FindBy(id = "ctl00_MainContent_txtActivationCode")
    private WebElement otpTxt;

    @FindBy(id = "btnActivate")
    private WebElement verifyBtn;

    public WebElement getVerifyBtn() {
        return verifyBtn;
    }

    public WebElement getOtpTxt() {
        return otpTxt;
    }

    public WebElement getSelectEmail() {
        return selectEmail;
    }

    public WebElement getGoBtn() {
        return goBtn;
    }

    public WebElement getVerificationDD() {
        return verificationDD;
    }
}
