package pageObject.advancedSendPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvanceSendStep5Page {
    WebDriver driver;

    public AdvanceSendStep5Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[normalize-space()='Amount:']/following-sibling::td")
    private WebElement amount;

    @FindBy(xpath = "//td[normalize-space()='Remaining Balance:']/following-sibling::td")
    private WebElement remainingBalAmt;

    @FindBy(xpath = "//label[contains(normalize-space(),'Prepare to process to')]")
    private WebElement sendNowTitle;

    @FindBy(id = "btnSendPayment")
    private WebElement sendNowBtn;

    public WebElement getAmount() {
        return amount;
    }

    public WebElement getSendNowTitle() {
        return sendNowTitle;
    }

    public WebElement getSendNowBtn() {
        return sendNowBtn;
    }

    public WebElement getRemainingBalAmt() {
        return remainingBalAmt;
    }
}
