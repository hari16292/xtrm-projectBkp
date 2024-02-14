package pageObject.advancedProfilePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Step3Page {
    WebDriver driver;

    public Step3Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//input[@value='Finish and Submit'])[2]")
    private WebElement finishBtn;

    @FindBy(xpath = "(//input[@name='previous'])[4]")
    private WebElement previousBtn;

    @FindBy(id = "primarySelJobTitle")
    private WebElement jobTitleTxt;

    @FindBy(id = "primarytxtFirstName")
    private WebElement firstNameTxt;

    @FindBy(id = "primarytxtLastName")
    private WebElement lastNameTxt;

    @FindBy(id = "primarytxtEmail")
    private WebElement emailTxt;

    @FindBy(id = "primaryselDOBMonth")
    private WebElement birthMonthDD;

    @FindBy(id = "primarytxtDOBDay")
    private WebElement birthDateTxt;

    @FindBy(id = "primarytxtDOBYear")
    private WebElement birthYearTxt;

    @FindBy(id = "primarySelCitizenship")
    private WebElement citizenshipDD;

    @FindBy(id = "primarySelIdentificationType")
    private WebElement signatoryTypeDD;

    @FindBy(id = "primarytxtIdentificationNumber")
    private WebElement signatoryNumberTxt;

    @FindBy(id = "primarytxtAddress1")
    private WebElement addr1Txt;

    @FindBy(id = "primarytxtCity")
    private WebElement cityTxt;

    @FindBy(id = "primarySelRegion")
    private WebElement stateDD;

    @FindBy(id = "primarytxtZip")
    private WebElement zipcodeTxt;

    @FindBy(id = "primarytxtPhone")
    private WebElement phoneTxt;

    @FindBy(xpath = "(//label[contains(text(),'Signatory/Director Identification Type')]) //a")
    private WebElement signatoryMoreInfoLink;

    @FindBy(xpath = "(//input[@value='Done'])[1]")
    private WebElement doneBtn;

    @FindBy(xpath = "(//input[@value='Done'])[1]/../../../h2[@class='fs-title']")
    private WebElement successMsg;

    public WebElement getFinishBtn() {
        return finishBtn;
    }

    public WebElement getPreviousBtn() {
        return previousBtn;
    }

    public WebElement getJobTitleTxt() {
        return jobTitleTxt;
    }

    public WebElement getFirstNameTxt() {
        return firstNameTxt;
    }

    public WebElement getLastNameTxt() {
        return lastNameTxt;
    }

    public WebElement getEmailTxt() {
        return emailTxt;
    }

    public WebElement getBirthMonthDD() {
        return birthMonthDD;
    }

    public WebElement getBirthDateTxt() {
        return birthDateTxt;
    }

    public WebElement getBirthYearTxt() {
        return birthYearTxt;
    }

    public WebElement getCitizenshipDD() {
        return citizenshipDD;
    }

    public WebElement getSignatoryTypeDD() {
        return signatoryTypeDD;
    }

    public WebElement getSignatoryNumberTxt() {
        return signatoryNumberTxt;
    }

    public WebElement getAddr1Txt() {
        return addr1Txt;
    }

    public WebElement getCityTxt() {
        return cityTxt;
    }

    public WebElement getStateDD() {
        return stateDD;
    }

    public WebElement getZipcodeTxt() {
        return zipcodeTxt;
    }

    public WebElement getPhoneTxt() {
        return phoneTxt;
    }

    public WebElement getSignatoryMoreInfoLink() {
        return signatoryMoreInfoLink;
    }

    public WebElement getDoneBtn() {
        return doneBtn;
    }

    public WebElement getSuccessMsg() {
        return successMsg;
    }
}
