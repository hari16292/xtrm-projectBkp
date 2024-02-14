package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinkedCardsPage {
    WebDriver driver;

    public LinkedCardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnAddLinkCard")
    private WebElement linkCardBtn;

    @FindBy(xpath = "//tr/td[@class='col4']")
    private List<WebElement> cardType;

    @FindBy(xpath = "//tr/td[@class='col6']/a")
    private List<WebElement> deleteCardOption;

    @FindBy(xpath = "//li[@id='li10']/a")
    private WebElement linkedCardsTab;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lblMessage")
    private WebElement errMsg;

    public WebElement getLinkCardBtn() {
        return linkCardBtn;
    }

    public List<WebElement> getCardType() {
        return cardType;
    }

    public List<WebElement> getDeleteCardOption() {
        return deleteCardOption;
    }

    public WebElement getLinkedCardsTab() {
        return linkedCardsTab;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }
}
