package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgramsPage {

    WebDriver driver;

    public ProgramsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "btnCreate")
    private WebElement createPgbBtn;

    @FindBy(xpath = "//li[@id='li8']/a")
    private WebElement programBtn;

    @FindBy(xpath = "//li[@id='li13']/a")
    private WebElement programsTab;

    public WebElement getCreatePgbBtn() {
        return createPgbBtn;
    }

    public WebElement getProgramBtn() {
        return programBtn;
    }

    public WebElement getProgramsTab() {
        return programsTab;
    }
}
