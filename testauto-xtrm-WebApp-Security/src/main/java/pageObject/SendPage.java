package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendPage {
    WebDriver driver;

    public SendPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/web/Common/AddFunds/Send.aspx']")
    private WebElement sendBtn;

    @FindBy(id = "btnGoAdvanced")
    private WebElement advanceGoBtn;

    @FindBy(id = "btnGoSimple")
    private WebElement simpleSendGoBtn;

    @FindBy(id = "btnGoMass")
    private WebElement massPaymentGoBtn;

    @FindBy(id = "btnGoEmbeddedMass")
    private WebElement embeddedPaymentGoBtn;

    @FindBy(id = "lblMsg")
    private WebElement errMsg;

    public WebElement getMassPaymentGoBtn() {
        return massPaymentGoBtn;
    }

    public WebElement getAdvanceGoBtn() {
        return advanceGoBtn;
    }

    public WebElement getSendBtn() {
        return sendBtn;
    }

    public WebElement getSimpleSendGoBtn() {
        return simpleSendGoBtn;
    }

    public WebElement getEmbeddedPaymentGoBtn() {
        return embeddedPaymentGoBtn;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }
}
