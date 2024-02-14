package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ControllerAdminPage {
    WebDriver driver;

    public ControllerAdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ddlRole")
    private WebElement filterDD;

    @FindBy(xpath = "//a[normalize-space()='Edit']")
    private List<WebElement> editBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_ddlAccessLevel")
    private WebElement accessLevelDD;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnUpdateEmp")
    private WebElement doneBtn;

    @FindBy(id = "lblMsg")
    private WebElement errMsg;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnUpdateAccess")
    private WebElement setCustomAccessBtn;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkCanCreateNewEntities")
    private WebElement createNewEntitiesCB;

    @FindBy(xpath = "//table[@id='chkEntities'] //input")
    private List<WebElement> entityManagementCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkFundWallets")
    private WebElement fundWalletCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkFundWalletsViaACHDebit")
    private WebElement achDebitCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkFundWalletsViaCards")
    private WebElement creditCardsCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkFundWalletsViaEI")
    private WebElement electronicInvoiceCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkMoveFunds")
    private WebElement moveFundCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkSendFunds")
    private WebElement sendFundCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkSimplePaymentsSend")
    private WebElement simpleSendCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkCreateDraftPaymentsSend")
    private WebElement advanceSendCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkApproveDraftPaymentsSend")
    private WebElement draftPaymentCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkMassPaymentsSend")
    private WebElement massPaymentCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkEmbeddedPaymentsSend")
    private WebElement embeddedPaymentCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkTransferFunds")
    private WebElement transferFundsPaymentCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkTransferMethodsBankAccount")
    private WebElement transferBankAccountCB;

    @FindBy(xpath = "//div[@id='pnlTransferBanks'] //input")
    private List<WebElement> listOfBanksCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkTransferMethodsBankCheck")
    private WebElement bankCheckCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkTransferMethodsVisaDebit")
    private WebElement visaDebitCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkCurrencyExchange")
    private WebElement currencyExchangeCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkCreateprograms")
    private WebElement createProgramsCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkLinkBanks")
    private WebElement linkBanksCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkLinkBankTransfer")
    private WebElement linkBankTransferCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkLinkBankFund")
    private WebElement linkBankFundCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkConnectedManagerServices")
    private WebElement connectedManagerCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkEditClaims")
    private WebElement editClaimCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkEditBeneficiaries")
    private WebElement editBeneficiariesCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkEditConnected")
    private WebElement editConnectedCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkEditWallets")
    private WebElement editWalletCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkViewClaims")
    private WebElement viewClaimCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkViewReports")
    private WebElement viewReportCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkViewBeneficiaries")
    private WebElement viewBeneficiariesCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkViewMassPayments")
    private WebElement viewMassPaymentCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkViewConnected")
    private WebElement viewConnectedCB;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_chkViewWallets")
    private WebElement viewWalletsCB;

    @FindBy(xpath = "//table[@id='chkWallets'] //input")
    private List<WebElement> listOfWallets;

    @FindBy(xpath = "//table[@id='chkWallets'] //label")
    private List<WebElement> listOfWalletsId;

    @FindBy(id = "ctl00_ctl00_TopMenu_MiniTab_btnDone")
    private WebElement customAccessDoneBtn;


    public WebElement getFilterDD() {
        return filterDD;
    }

    public List<WebElement> getEditBtn() {
        return editBtn;
    }

    public WebElement getAccessLevelDD() {
        return accessLevelDD;
    }

    public WebElement getDoneBtn() {
        return doneBtn;
    }

    public WebElement getErrMsg() {
        return errMsg;
    }

    public WebElement getSetCustomAccessBtn() {
        return setCustomAccessBtn;
    }

    public WebElement getCreateNewEntitiesCB() {
        return createNewEntitiesCB;
    }

    public List<WebElement> getEntityManagementCB() {
        return entityManagementCB;
    }

    public WebElement getFundWalletCB() {
        return fundWalletCB;
    }

    public WebElement getAchDebitCB() {
        return achDebitCB;
    }

    public WebElement getCreditCardsCB() {
        return creditCardsCB;
    }

    public WebElement getElectronicInvoiceCB() {
        return electronicInvoiceCB;
    }

    public WebElement getMoveFundCB() {
        return moveFundCB;
    }

    public WebElement getSendFundCB() {
        return sendFundCB;
    }

    public WebElement getSimpleSendCB() {
        return simpleSendCB;
    }

    public WebElement getAdvanceSendCB() {
        return advanceSendCB;
    }

    public WebElement getDraftPaymentCB() {
        return draftPaymentCB;
    }

    public WebElement getMassPaymentCB() {
        return massPaymentCB;
    }

    public WebElement getEmbeddedPaymentCB() {
        return embeddedPaymentCB;
    }

    public WebElement getTransferFundsPaymentCB() {
        return transferFundsPaymentCB;
    }

    public WebElement getTransferBankAccountCB() {
        return transferBankAccountCB;
    }

    public List<WebElement> getListOfBanksCB() {
        return listOfBanksCB;
    }

    public WebElement getBankCheckCB() {
        return bankCheckCB;
    }

    public WebElement getVisaDebitCB() {
        return visaDebitCB;
    }

    public WebElement getCurrencyExchangeCB() {
        return currencyExchangeCB;
    }

    public WebElement getCreateProgramsCB() {
        return createProgramsCB;
    }

    public WebElement getLinkBanksCB() {
        return linkBanksCB;
    }

    public WebElement getLinkBankTransferCB() {
        return linkBankTransferCB;
    }

    public WebElement getLinkBankFundCB() {
        return linkBankFundCB;
    }

    public WebElement getConnectedManagerCB() {
        return connectedManagerCB;
    }

    public WebElement getEditClaimCB() {
        return editClaimCB;
    }

    public WebElement getEditBeneficiariesCB() {
        return editBeneficiariesCB;
    }

    public WebElement getEditConnectedCB() {
        return editConnectedCB;
    }

    public WebElement getEditWalletCB() {
        return editWalletCB;
    }

    public WebElement getViewClaimCB() {
        return viewClaimCB;
    }

    public WebElement getViewReportCB() {
        return viewReportCB;
    }

    public WebElement getViewBeneficiariesCB() {
        return viewBeneficiariesCB;
    }

    public WebElement getViewMassPaymentCB() {
        return viewMassPaymentCB;
    }

    public WebElement getViewConnectedCB() {
        return viewConnectedCB;
    }

    public WebElement getViewWalletsCB() {
        return viewWalletsCB;
    }

    public List<WebElement> getListOfWallets() {
        return listOfWallets;
    }

    public List<WebElement> getListOfWalletsId() {
        return listOfWalletsId;
    }

    public WebElement getCustomAccessDoneBtn() {
        return customAccessDoneBtn;
    }
}
