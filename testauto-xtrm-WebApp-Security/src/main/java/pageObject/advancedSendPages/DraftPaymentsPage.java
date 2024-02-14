package pageObject.advancedSendPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy( xpath = "//div[@class='NaviDropDown']/a")
    private List<WebElement> continueBtn;

    @FindBy(xpath = "(//a[contains(@class,'aEdit')])[1]")
    private WebElement editDescription;

    @FindBy(xpath = "(//input[contains(@class,'TextBox txtDesc')])[1]")
    private WebElement editDescriptionTxt;

    @FindBy(xpath = "(//a[contains(@class,'aSave')])[1]")
    private WebElement editDescriptionSave;

    @FindBy(xpath = "(//span[contains(@class,'spnDesc')])[1]")
    private WebElement descriptionMsgValue;

    public WebElement getEditDescriptionTxt() {
        return editDescriptionTxt;
    }

    public WebElement getEditDescriptionSave() {
        return editDescriptionSave;
    }

    public WebElement getDescriptionMsgValue() {
        return descriptionMsgValue;
    }

    public WebElement getEditDescription() {
        return editDescription;
    }

    public List<WebElement> getContinueBtn() {
        return continueBtn;
    }

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
