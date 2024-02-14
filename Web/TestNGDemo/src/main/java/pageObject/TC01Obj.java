package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TC01Obj {
    WebDriver driver;

    public TC01Obj(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "1")
    private WebElement element1;

    public WebElement getElement1() {
        return element1;
    }
}
