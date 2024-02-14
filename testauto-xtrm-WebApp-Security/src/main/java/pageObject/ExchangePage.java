package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExchangePage {
    WebDriver driver;

    public ExchangePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='txtAmt' or @id='ctl00_ctl00_TopMenu_MiniTab_txtAmt']")
    private WebElement amountTxt;

    @FindBy(xpath = "//select[contains(@id,'ddlCashAccount')]/option")
    private List<WebElement> fromWalletOption;

    @FindBy(xpath = "//select[@id='ddlCurrencyList']/option")
    private List<WebElement> toCurrencyOption;

    @FindBy(id = "ddlCurrencyList")
    private WebElement toCurrency;

    @FindBy(xpath = "(//i[@class='fa fa-question-circle'])[1]")
    private WebElement fromWalletMouseHover;

    @FindBy(xpath = "(//i[@class='fa fa-question-circle'])[2]")
    private WebElement toCurrencyMouseHover;

    @FindBy(xpath = "//div[@class='divExchange']")
    private WebElement exchangeValue;

    @FindBy(xpath = "//input[contains(@id,'btnContinue')]")
    private WebElement continueBtn;

    public WebElement getContinueBtn() {
        return continueBtn;
    }

    public WebElement getExchangeValue() {
        return exchangeValue;
    }

    public WebElement getAmountTxt() {
        return amountTxt;
    }

    public List<WebElement> getFromWalletOption() {
        return fromWalletOption;
    }

    public List<WebElement> getToCurrencyOption() {
        return toCurrencyOption;
    }

    public WebElement getToCurrency() {
        return toCurrency;
    }

    public WebElement getFromWalletMouseHover() {
        return fromWalletMouseHover;
    }

    public WebElement getToCurrencyMouseHover() {
        return toCurrencyMouseHover;
    }
}
