package pageObject.advancedSendPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvanceSendStep6Page {
    WebDriver driver;
    public AdvanceSendStep6Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[normalize-space()='Your payment has been processed.']")
    private WebElement paymentConfirmation;

    @FindBy(id = "btnNext")
    private WebElement viewTxnBtn;

    public WebElement getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public WebElement getViewTxnBtn() {
        return viewTxnBtn;
    }
}
