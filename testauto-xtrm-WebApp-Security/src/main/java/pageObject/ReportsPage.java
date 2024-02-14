package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.PaintEvent;
import java.util.List;

public class ReportsPage {
    WebDriver driver;

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_lnkbtnActionDownload")
    private WebElement downloadBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlAccountType")
    private WebElement accountDD;

    @FindBy(xpath = "//select[@id='ctl00_ctl00_TopMenu_MiniTab_ddlAccountType']/following-sibling::a")
    private WebElement accountDDMoreInfo;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlRewards")
    private WebElement paymentsDD;

    @FindBy(xpath = "//select[@id='ctl00_ctl00_TopMenu_MiniTab_ddlRewards']/following-sibling::a")
    private WebElement paymentsDDMoreInfo;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlReport")
    private WebElement reportDD;

    @FindBy(xpath = "//select[@id='ddlCurrency' or @id ='ctl00_ctl00_TopMenu_MiniTab_ddlCashAccount']")
    private WebElement walletDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlProgram")
    private WebElement programDD;

    @FindBy(id = "reportrange")
    private WebElement paymentDateRange;

    @FindBy(xpath = "//select[@id='ctl00_ctl00_TopMenu_MiniTab_ddlFundMethod' or @id='ctl00_ctl00_TopMenu_MiniTab_ddlPayMethod']")
    private WebElement paymentMethodDD;

    @FindBy(xpath = "//input[@id='ctl00_ctl00_TopMenu_MiniTab_txtSearch' or @id='ctl00_ctl00_TopMenu_MiniTab_txtKeyword' or @id='txtSearch']")
    private WebElement searchByTxt;

    @FindBy(xpath = "//input[@id='ctl00_ctl00_TopMenu_MiniTab_btnKeyword' or @id='btnGo' or @id='btnKeyword']")
    private WebElement goBtn;

    @FindBy(xpath = "//table[@id = 'ctl00_ctl00_TopMenu_MiniTab_dgReportByPerson' or @id='ctl00_ctl00_TopMenu_MiniTab_dgFundedReport' or @id='ctl00_ctl00_TopMenu_MiniTab_dgTransfersReport' or @id='ctl00_ctl00_TopMenu_MiniTab_dgReportbyCompanyCredits'  or @id='ctl00_ctl00_TopMenu_MiniTab_dgReportbyPartner' or @id='ctl00_ctl00_TopMenu_MiniTab_dgReportbyCamp' or @id='ctl00_ctl00_TopMenu_MiniTab_dgReportbyDate'] //tr[@class='GridItem']")
    private List<WebElement> showDetails;

    @FindBy(id = "lblTotalFilteredTransactions")
    private WebElement filteredTxnCount;

    @FindBy(xpath = "//table[@id='ctl00_ctl00_TopMenu_MiniTab_dgReportbyCamp']/tbody/tr[@class='GridItem']")
    private List<WebElement> txnCount;

    @FindBy(xpath = "//span[@id='ctl00_ctl00_TopMenu_MiniTab_lblGrid' or @id='ctl00_ctl00_TopMenu_MiniTab_lblbtmMsg']")
    private WebElement noTxnMsg;

    @FindBy(xpath = "//span[@id='ctl00_ctl00_TopMenu_MiniTab_lblbtmMsg' or @id='ctl00_ctl00_TopMenu_MiniTab_lblGrid']")
    private WebElement clearFilter;

    @FindBy(xpath = "//li[normalize-space()='This year']")
    private WebElement paymentDateThisYear;

    @FindBy(xpath = "//li[normalize-space()='This Month']")
    private WebElement paymentDateThisMonth;

    @FindBy(xpath = "//li[normalize-space()='Custom']")
    private WebElement paymentDateCustom;

    @FindBy(xpath = "//div[@class='calendar left']//select[@class='monthselect']")
    private WebElement paymentDateCustomStartMonthDD;

    @FindBy(xpath = "//div[@class='calendar left']//select[@class='yearselect']")
    private WebElement paymentDateCustomStartYearDD;

    @FindBy(xpath = "//div[@class='calendar right']//select[@class='monthselect']")
    private WebElement paymentDateCustomEndMonthDD;

    @FindBy(xpath = "//div[@class='calendar right']//select[@class='yearselect']")
    private WebElement paymentDateCustomEndYearDD;

    @FindBy(xpath = "//td[@class='weekend available'][normalize-space()='1']")
    private WebElement paymentDateCustomJan1;

    @FindBy(xpath = "//td[@class='weekend available'][normalize-space()='31']")
    private WebElement paymentDateCustomDec31;

    @FindBy(xpath = "(//td[contains(text(),'31')])[3]")
    private WebElement paymentDateCustomMar31;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlClaimStatus")
    private WebElement claimStatusDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlSubmissions")
    private WebElement submissionDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlBeneficiary")
    private WebElement beneficiariesDD;

    @FindBy(xpath = "//div[@id='divReport']/table/tbody/tr")
    private List<WebElement> benCompList;

    @FindBy(id = "ddlPayments")
    private WebElement txnDD;

    @FindBy(id = "ddlFundMethod")
    private WebElement fundMethodDD;

    @FindBy(id = "ddlFundSource")
    private WebElement fundSourceDD;

    @FindBy(linkText = "Statements")
    private WebElement statementOption;

    @FindBy(linkText = "Tax")
    private WebElement taxOption;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnDownload")
    private WebElement statementYearDownloadBtn;

    @FindBy(id = "selWallet")
    private WebElement statementWalletDD;

    @FindBy(id = "selYear")
    private WebElement statementYearDD;

    @FindBy(id = "selMonth")
    private WebElement statementMonthDD;

    @FindBy(id = "reportrange")
    private WebElement statementDateRange;

    @FindBy(id = "spnErrorMsg")
    private WebElement errMsg;

    @FindBy(xpath = "//li[normalize-space()='Last 7 Days']")
    private WebElement rangeLast7DaysOption;

    @FindBy(xpath = "//li[normalize-space()='Last 30 Days']")
    private WebElement rangeLast30DaysOption;

    @FindBy(xpath = "//li[normalize-space()='Last Month']")
    private WebElement rangeLastMonthOption;

    @FindBy(xpath = "//li[normalize-space()='This year']")
    private WebElement rangeThisYearOption;

    @FindBy(xpath = "//li[normalize-space()='Last year']")
    private WebElement rangeLastYearOption;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnDownload1")
    private WebElement rangeDownloadBtn;

    public WebElement getTxnDD() {
        return txnDD;
    }

    public WebElement getFundMethodDD() {
        return fundMethodDD;
    }

    public WebElement getFundSourceDD() {
        return fundSourceDD;
    }

    public List<WebElement> getBenCompList() {
        return benCompList;
    }

    public WebElement getBeneficiariesDD() {
        return beneficiariesDD;
    }

    public WebElement getClaimStatusDD() {
        return claimStatusDD;
    }

    public WebElement getSubmissionDD() {
        return submissionDD;
    }

    public WebElement getPaymentDateCustomMar31() {
        return paymentDateCustomMar31;
    }

    public WebElement getPaymentDateCustomJan1() {
        return paymentDateCustomJan1;
    }

    public WebElement getPaymentDateCustomDec31() {
        return paymentDateCustomDec31;
    }

    public WebElement getPaymentDateCustomStartMonthDD() {
        return paymentDateCustomStartMonthDD;
    }

    public WebElement getPaymentDateCustomStartYearDD() {
        return paymentDateCustomStartYearDD;
    }

    public WebElement getPaymentDateCustomEndMonthDD() {
        return paymentDateCustomEndMonthDD;
    }

    public WebElement getPaymentDateCustomEndYearDD() {
        return paymentDateCustomEndYearDD;
    }

    public WebElement getPaymentDateCustom() {
        return paymentDateCustom;
    }

    public WebElement getPaymentDateThisMonth() {
        return paymentDateThisMonth;
    }

    public WebElement getPaymentDateThisYear() {
        return paymentDateThisYear;
    }

    public WebElement getDownloadBtn() {
        return downloadBtn;
    }

    public WebElement getAccountDD() {
        return accountDD;
    }

    public WebElement getAccountDDMoreInfo() {
        return accountDDMoreInfo;
    }

    public WebElement getPaymentsDD() {
        return paymentsDD;
    }

    public WebElement getPaymentsDDMoreInfo() {
        return paymentsDDMoreInfo;
    }

    public WebElement getReportDD() {
        return reportDD;
    }

    public WebElement getWalletDD() {
        return walletDD;
    }

    public WebElement getProgramDD() {
        return programDD;
    }

    public WebElement getPaymentDateRange() {
        return paymentDateRange;
    }

    public WebElement getPaymentMethodDD() {
        return paymentMethodDD;
    }

    public WebElement getSearchByTxt() {
        return searchByTxt;
    }

    public WebElement getGoBtn() {
        return goBtn;
    }

    public List<WebElement> getShowDetails() {
        return showDetails;
    }

    public WebElement getFilteredTxnCount() {
        return filteredTxnCount;
    }

    public List<WebElement> getTxnCount() {
        return txnCount;
    }

    public WebElement getNoTxnMsg() {
        return noTxnMsg;
    }

    public WebElement getClearFilter() {
        return clearFilter;
    }

    public WebElement getStatementOption() {
        return statementOption;
    }

    public WebElement getTaxOption() {
        return taxOption;
    }

    public WebElement getStatementYearDownloadBtn() {
        return statementYearDownloadBtn;
    }

    public WebElement getStatementWalletDD() {
        return statementWalletDD;
    }

    public WebElement getStatementYearDD() {
        return statementYearDD;
    }

    public WebElement getStatementMonthDD() {
        return statementMonthDD;
    }

    public WebElement getStatementDateRange() {
        return statementDateRange;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }

    public WebElement getRangeLast7DaysOption() {
        return rangeLast7DaysOption;
    }

    public WebElement getRangeLast30DaysOption() {
        return rangeLast30DaysOption;
    }

    public WebElement getRangeLastMonthOption() {
        return rangeLastMonthOption;
    }

    public WebElement getRangeThisYearOption() {
        return rangeThisYearOption;
    }

    public WebElement getRangeLastYearOption() {
        return rangeLastYearOption;
    }

    public WebElement getRangeDownloadBtn() {
        return rangeDownloadBtn;
    }
}
