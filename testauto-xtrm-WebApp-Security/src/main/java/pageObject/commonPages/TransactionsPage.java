package pageObject.commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TransactionsPage {

    WebDriver driver;
    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[@class='tblTransactions Grid hLines'] //td[@class='Balance']")
    private List<WebElement> txnBalAmt;

    @FindBy(xpath = "//a[normalize-space()='Details']")
    private List<WebElement> txnDetailsBtn;

    @FindBy(xpath = "//tr[@class='GridItem']/td[4]")
    private List<WebElement> txnPayMethod;

    @FindBy(id = "reportrange")
    private WebElement txnDateRange;

    @FindBy(xpath = "//li[normalize-space()='Last 7 Days']")
    private WebElement txnDateLast7DaysBtn;

    @FindBy(xpath = "//select[contains(@id,'ddlFilter')]")
    private WebElement txnFilterDD;

    public WebElement getTxnFilterDD() {
        return txnFilterDD;
    }

    public WebElement getTxnDateLast7DaysBtn() {
        return txnDateLast7DaysBtn;
    }

    public WebElement getTxnDateRange() {
        return txnDateRange;
    }

    public List<WebElement> getTxnBalAmt() {
        return txnBalAmt;
    }

    public List<WebElement> getTxnDetailsBtn() {
        return txnDetailsBtn;
    }

    public List<WebElement> getTxnPayMethod() {
        return txnPayMethod;
    }

}
