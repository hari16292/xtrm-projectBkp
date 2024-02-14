package pageObject.advancedSendPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdvanceSendStep2Page {
    WebDriver driver;
    public AdvanceSendStep2Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "txtSearch")
    private WebElement recipientSearch;

    @FindBy(xpath = "//tbody/tr/td[@class='ng-scope']/a")
    private List<WebElement> recentPayeeAddBtn;

    @FindBy(xpath = "//span[normalize-space()='Add']")
    private WebElement addUser;

    @FindBy(xpath = "//span[contains(@class,'Missing')]")
    private List<WebElement> checkMissing;

    @FindBy(xpath = "//select[contains(@class,'ListBox ddlBank ddlBank')]")
    private List<WebElement> selectBank;

    @FindBy(xpath = "//table[@class='Grid tblBankList']/tbody/tr[contains(@class,'GridItem div_1')]")
    private List<WebElement> selectedRecipientList;

    @FindBy(xpath = "//tbody/tr/td[@class='ng-scope']/preceding-sibling::td[@class='ng-binding']")
    private List<WebElement> recentPayeeEmail;

    public List<WebElement> getRecentPayeeEmail() {
        return recentPayeeEmail;
    }

    public WebElement getRecipientSearch() {
        return recipientSearch;
    }

    public List<WebElement> getRecentPayeeAddBtn() {
        return recentPayeeAddBtn;
    }

    public WebElement getAddUser() {
        return addUser;
    }

    public List<WebElement> getCheckMissing() {
        return checkMissing;
    }

    public List<WebElement> getSelectBank() {
        return selectBank;
    }

    public List<WebElement> getSelectedRecipientList() {
        return selectedRecipientList;
    }
}
