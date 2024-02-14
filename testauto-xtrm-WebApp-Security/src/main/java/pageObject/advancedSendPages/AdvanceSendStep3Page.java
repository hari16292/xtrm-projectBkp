package pageObject.advancedSendPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdvanceSendStep3Page {
    WebDriver driver;
    public AdvanceSendStep3Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='aRemoveUser']")
    private List<WebElement> dltRecipientsPayment;

    @FindBy(xpath = "//input[@placeholder='0.00']")
    private List<WebElement> amountTxt;

    @FindBy(xpath = "//input[@placeholder='Optional']")
    private List<WebElement> additionalCommentsTxt;

    public List<WebElement> getAdditionalCommentsTxt() {
        return additionalCommentsTxt;
    }

    public List<WebElement> getAmountTxt() {
        return amountTxt;
    }

    public List<WebElement> getDltRecipientsPayment() {
        return dltRecipientsPayment;
    }
}
