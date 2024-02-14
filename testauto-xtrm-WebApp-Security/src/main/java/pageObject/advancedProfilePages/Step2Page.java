package pageObject.advancedProfilePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Step2Page {
    WebDriver driver;

    public Step2Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "businesstxtFictitiousName")
    private WebElement companyNameTxt;

    @FindBy(id = "businesstxtWebsite")
    private WebElement websiteTxt;

    @FindBy(id = "businessSelUSCompanyType")
    private WebElement companyTypeDD;

    @FindBy(id = "businessSelCountryOfIncorporation")
    private WebElement companyCountryDD;

    @FindBy(id = "businessSelSubDivisionOfIncorporation")
    private WebElement companyStateDD;

    @FindBy(id = "businesstxtTaxIdentificationNumber")
    private WebElement taxNumberTxt;

    @FindBy(id = "businesstxtAddressLine1")
    private WebElement address1Txt;

    @FindBy(id = "businesstxtAddressLine2")
    private WebElement address2Txt;

    @FindBy(id = "businesstxtAddressLine3")
    private WebElement address3Txt;

    @FindBy(id = "businesstxtCity")
    private WebElement cityTxt;

    @FindBy(id = "businessSelCountry")
    private WebElement businessCountryDD;

    @FindBy(id = "businessSelState")
    private WebElement businessStateDD;

    @FindBy(id = "businesstxtPostalCode")
    private WebElement businessZipcodeTxt;

    @FindBy(id = "businesstxtPurposeOfTransactions")
    private WebElement purposeOfTxnTxt;

    @FindBy(xpath = "(//input[@name='previous'])[2]")
    private WebElement previousBtn;

    @FindBy(xpath = "(//input[@name='SaveExit'])[1]")
    private WebElement saveExitBtn;

    @FindBy(xpath = "(//input[@name='next'])[4]")
    private WebElement saveContinueBtn;

    @FindBy(xpath = "//div[@class='divNonUS']/form/div[@class='clsRequired']/span")
    private WebElement errMsg;

    public WebElement getCompanyNameTxt() {
        return companyNameTxt;
    }

    public WebElement getWebsiteTxt() {
        return websiteTxt;
    }

    public WebElement getCompanyTypeDD() {
        return companyTypeDD;
    }

    public WebElement getCompanyCountryDD() {
        return companyCountryDD;
    }

    public WebElement getCompanyStateDD() {
        return companyStateDD;
    }

    public WebElement getTaxNumberTxt() {
        return taxNumberTxt;
    }

    public WebElement getAddress1Txt() {
        return address1Txt;
    }

    public WebElement getAddress2Txt() {
        return address2Txt;
    }

    public WebElement getAddress3Txt() {
        return address3Txt;
    }

    public WebElement getCityTxt() {
        return cityTxt;
    }

    public WebElement getBusinessCountryDD() {
        return businessCountryDD;
    }

    public WebElement getBusinessStateDD() {
        return businessStateDD;
    }

    public WebElement getBusinessZipcodeTxt() {
        return businessZipcodeTxt;
    }

    public WebElement getPurposeOfTxnTxt() {
        return purposeOfTxnTxt;
    }

    public WebElement getPreviousBtn() {
        return previousBtn;
    }

    public WebElement getSaveExitBtn() {
        return saveExitBtn;
    }

    public WebElement getSaveContinueBtn() {
        return saveContinueBtn;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }
}
