package pageObject.advancedSendPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdvanceSendStep4Page {
    WebDriver driver;
    public AdvanceSendStep4Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='spnTotal ng-binding']")
    private WebElement summaryAmount;

    @FindBy(xpath = "//table[@class='Grid']/tbody/tr")
    private List<WebElement> selectedRecipient;

    @FindBy(xpath = "//span[@class='spnDesc ng-binding']")
    private WebElement descriptionValue;

    public WebElement getDescriptionValue() {
        return descriptionValue;
    }

    public List<WebElement> getSelectedRecipient() {
        return selectedRecipient;
    }

    public WebElement getSummaryAmount() {
        return summaryAmount;
    }
}
