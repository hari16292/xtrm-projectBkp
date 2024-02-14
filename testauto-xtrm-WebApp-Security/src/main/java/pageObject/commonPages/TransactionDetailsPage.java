package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TransactionDetailsPage {
    WebDriver driver;
    public TransactionDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr[@class='GridItem']/td[@align='right']/a")
    private List<WebElement> showTxnDetails;

    @FindBy(xpath = "//td[normalize-space()='Amount:']/following-sibling::td //td")
    private WebElement txnDetailsAmount;

    @FindBy(xpath = "//label[normalize-space()='Amount :']/../following-sibling::td/label")
    private WebElement userTxnDetailsAmount;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnDone")
    private WebElement txnDetailsDoneBtn;

    @FindBy(xpath = "//table[@id='Table4'] //td")
    private WebElement txnDetailsType;

    @FindBy(xpath = "//table[@id='Table7'] //td")
    private WebElement txnDetailsMethod;

    @FindBy(xpath = "//label[normalize-space()='Wallet']")
    private WebElement userTxnDetailsMethod;

    @FindBy(xpath = "//table[@id='Table2'] //td")
    private WebElement emailID;

    @FindBy(xpath = "//label[normalize-space()='To :']/../following-sibling::td/label")
    private WebElement userEmailId;

    @FindBy(xpath = "//td[normalize-space()='Settlement Amount :']/following-sibling::td")
    private WebElement settlementAmount;

    @FindBy(xpath = "//td[normalize-space()='Settlement Currency :']/following-sibling::td")
    private WebElement settlementCurrency;

    @FindBy(xpath = "//td[normalize-space()='Transfer Currency :']/following-sibling::td")
    private WebElement transferCurrency;

    @FindBy(xpath = "//td[normalize-space()='Transfer Amount :']/following-sibling::td")
    private WebElement transferAmount;

    @FindBy(xpath = "//input[@id='btnContinue']")
    private WebElement giftCardDoneBtn;

    @FindBy(xpath = "//input[@value='Done']")
    private WebElement doneBtn;

    @FindBy(xpath = "//a[normalize-space()='Done']")
    private WebElement rapidDoneBtn;

    @FindBy(xpath = "//label[contains(normalize-space(),' Type :')]/../following-sibling::td/label")
    private WebElement exchangeTransactionType;

    @FindBy(xpath = "//label[normalize-space()='Type :']/../following-sibling::td/label")
    private WebElement exchangeType;

    @FindBy(xpath = "//label[normalize-space()='From Amount :']/../following-sibling::td/label")
    private WebElement exchangeFromAmount;

    @FindBy(xpath = "//label[normalize-space()='Description :']/../../td[2]")
    private WebElement descriptionFund;

    @FindBy(xpath = "//label[normalize-space()='Amount :']/../../td[2]")
    private WebElement amountFund;



    public WebElement getAmountFund() {
        return amountFund;
    }

    public WebElement getDescriptionFund() {
        return descriptionFund;
    }

    public WebElement getExchangeFromAmount() {
        return exchangeFromAmount;
    }

    public WebElement getExchangeTransactionType() {
        return exchangeTransactionType;
    }

    public WebElement getExchangeType() {
        return exchangeType;
    }

    public WebElement getTransferCurrency() {
        return transferCurrency;
    }

    public WebElement getTransferAmount() {
        return transferAmount;
    }

    public WebElement getGiftCardDoneBtn() {
        return giftCardDoneBtn;
    }

    public WebElement getRapidDoneBtn() {
        return rapidDoneBtn;
    }

    public WebElement getDoneBtn() {
        return doneBtn;
    }

    public WebElement getSettlementCurrency() {
        return settlementCurrency;
    }

    public WebElement getSettlementAmount() {
        return settlementAmount;
    }

    public WebElement getEmailID() {
        return emailID;
    }

    public WebElement getUserEmailId() {
        return userEmailId;
    }

    public List<WebElement> getShowTxnDetails() {
        return showTxnDetails;
    }

    public WebElement getTxnDetailsAmount() {
        return txnDetailsAmount;
    }

    public WebElement getUserTxnDetailsAmount() {
        return userTxnDetailsAmount;
    }

    public WebElement getTxnDetailsDoneBtn() {
        return txnDetailsDoneBtn;
    }

    public WebElement getTxnDetailsType() {
        return txnDetailsType;
    }

    public WebElement getTxnDetailsMethod() {
        return txnDetailsMethod;
    }

    public WebElement getUserTxnDetailsMethod() {
        return userTxnDetailsMethod;
    }
}
