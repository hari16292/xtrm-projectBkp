package functionalTestCases;

import resource.RetryAnalyzer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.ReportsPage;
import pageObject.commonPages.CommonMenu;
import resource.BaseClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

@Listeners(resource.Listeners.class)
public class ReportsTestCase extends BaseClass {

    WebDriver driver = BaseClass.driver;
    CommonMenu cm;
    ReportsPage rp;

    public ReportsTestCase() {
        cm = new CommonMenu(driver);
    }

    @BeforeSuite(groups = {"Reports", "CompanyRegression", "Security"})
    public void openBrowser() throws Exception {
        if (driver == null) {
            driver = initializeBrowser();
        }
        cm = new CommonMenu(driver);
        rp = new ReportsPage(driver);
    }

    @AfterSuite(groups = {"Reports", "CompanyRegression", "Security"})
    public void closeBrowser() {
        driver.quit();
    }

    @Test(groups = {"Reports", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_homePage() throws Exception {
        LoginTestCase l = new LoginTestCase();
        l.tc02_verificationPage("");
        waitUntilLoaderDisable();
        waitForElementClickable(cm.getReportsMenu(), 5);
        cm.getReportsMenu().click();
        waitUntilLoaderDisable();
        Assert.assertTrue(driver.getCurrentUrl().contains("Common/Report/View"), "Issue in Reports menu click");
    }

    @Test(dataProvider = "tc02_data", groups = {"Reports", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_reports(String account, String payments, String moreInfo, String report, String wallet, String program, String paymentDate, String method, String searchParam, String txn, String fundMethod, String fundSource) throws Exception {
        tc01_homePage();
        String downloadedFileName = null;
        waitForElementClickable(rp.getDownloadBtn(), 5);
        Select accountDD = new Select(rp.getAccountDD());
        switch (account) {
            case "This Account":
                accountDD.selectByValue("1");
                waitForElementClickable(rp.getDownloadBtn(), 5);
                Select paymentsDD = new Select(rp.getPaymentsDD());
                List<WebElement> paymentsOptions = paymentsDD.getOptions();
                for (WebElement option : paymentsOptions) {
                    if (option.getText().equalsIgnoreCase(payments)) {
                        option.click();
                        waitUntilLoaderDisable();
                        break;
                    }
                }
                if (report.equals("By Payment") || report.equals("By Mass Pay Submission")) {
                    waitForElementClickable(rp.getDownloadBtn(), 5);
                    Select paymentMethodDD = new Select(rp.getPaymentMethodDD());
                    List<WebElement> paymentMethodOptions = paymentMethodDD.getOptions();
                    for (WebElement option : paymentMethodOptions) {
                        if (option.getText().equals(method)) {
                            option.click();
                            waitUntilLoaderDisable();
                            break;
                        }
                    }
                }
                if (!report.equals("By Mass Pay Submission")) {
                    waitForElementClickable(rp.getDownloadBtn(), 5);
                    Select walletDD = new Select(rp.getWalletDD());
                    if (wallet.equals("All Wallets"))
                        walletDD.selectByValue("0");
                    else if (wallet.isEmpty()) {
                        //Nothing to do if value is empty
                    } else {
                        walletDD.selectByValue(getWalletID(wallet));
                    }
                }
                if (!program.isEmpty() && !payments.equals("Funded")) {
                    waitUntilLoaderDisable();
                    waitForElementClickable(rp.getDownloadBtn(), 5);
                    Select programDD = new Select(rp.getProgramDD());
                    List<WebElement> programOptions = programDD.getOptions();
                    for (WebElement option : programOptions) {
                        if (option.getText().equals(program)) {
                            option.click();
                            waitUntilLoaderDisable();
                            break;
                        }
                    }
                }
                break;
            case "Beneficiary Company Account":
                accountDD.selectByValue("2");
                waitUntilLoaderDisable();
                waitForElementClickable(rp.getDownloadBtn(), 5);
                Select beneficiaryDD = new Select(rp.getBeneficiariesDD());
                List<WebElement> beneficiaryOptions = beneficiaryDD.getOptions();
                for (WebElement option : beneficiaryOptions) {
                    if (option.getText().equalsIgnoreCase(report)) {
                        option.click();
                        waitUntilLoaderDisable();
                        break;
                    }
                }
                break;
            case "Connected Company Account":
                accountDD.selectByValue("3");
                waitUntilLoaderDisable();
                break;
        }
        if (moreInfo.equals("Yes")) {
            waitForElementVisible(rp.getAccountDDMoreInfo(), 5);
            rp.getAccountDDMoreInfo().click();
            waitUntilLoaderDisable();
            driver.switchTo().window(windowHandle(driver, "Child"));
            Assert.assertTrue(driver.getTitle().contains("COMPANY004"), "Redirect to Account more info page failed");
            driver.close();
            driver.switchTo().window(windowHandle(driver, "Parent"));
            Assert.assertEquals(driver.getTitle(), "XTRM Performance", "Issue in Reports module");
            if (account.equals("This Account")) {
                rp.getPaymentsDDMoreInfo().click();
                waitUntilLoaderDisable();
                driver.switchTo().window(windowHandle(driver, "Child"));
                Assert.assertTrue(driver.getTitle().contains("COMPANY023"), "Redirect to Payments more info page failed");
                driver.close();
                driver.switchTo().window(windowHandle(driver, "Parent"));
                Assert.assertEquals(driver.getTitle(), "XTRM Performance", "Issue in Reports module");
            }
        }
        waitUntilLoaderDisable();
        waitForElementClickable(rp.getDownloadBtn(), 5);
        Select reportDD = new Select(rp.getReportDD());
        reportDD.selectByVisibleText(report);
        switch (report) {
            case "By Payment":
                switch (payments) {
                    case "Sent":
                        downloadedFileName = "By_Payments.csv";
                        break;
                    case "Received":
                        downloadedFileName = "By_Company_Credits.csv";
                        break;
                    case "Funded":
                        downloadedFileName = "By_Funded.csv";
                        break;
                }
                break;
            case "By Mass Pay Submission":
                if (account.equals("Beneficiary Company Account")) downloadedFileName = ".csv";
                else {
                    downloadedFileName = ".csv";
                    Select paymentMethodDD = new Select(rp.getPaymentMethodDD());
                    paymentMethodDD.selectByVisibleText(method);
                    waitUntilLoaderDisable();
                }
                break;
            case "Claims":
                downloadedFileName = ".csv";
                break;
            case "By Date":
                downloadedFileName = "By_Date.csv";
                break;
            case "By Beneficiary Company":
                downloadedFileName = "By_Partner.csv";
                break;
            case "By Beneficiary Individual":
                downloadedFileName = "By_Person.csv";
                break;
            case "By Beneficiary Individual - Consolidated":
                downloadedFileName = "By_Consolidated_Person.csv";
                break;
            case "Admins":
                downloadedFileName = "BeneficiaryCompanyAdminsReport.csv";
                break;
            case "Wallets":
                if (account.equals("Beneficiary Company Account"))
                    downloadedFileName = "BeneficiariesWalletsReport.csv";
                else if (account.equals("Connected Company Account"))
                    downloadedFileName = "ConnectedCompaniesWalletsReport.csv";
                break;
            case "Transactions":
                if (!txn.equals("Pending Fund")) downloadedFileName = "ConnectedAccountsReport_TestAPICompany.csv";
                else downloadedFileName = "CAPendingFundReport_TestAPICompany.csv";
                waitUntilLoaderDisable();
                waitForElementClickable(rp.getDownloadBtn(), 5);
                Select txnDD = new Select(rp.getTxnDD());
                txnDD.selectByVisibleText(txn);
                waitUntilLoaderDisable();
                if (txn.equals("Pending Fund")) {
                    waitUntilLoaderDisable();
                    waitForElementClickable(rp.getDownloadBtn(), 5);
                    Select fundMethodDD = new Select(rp.getFundMethodDD());
                    fundMethodDD.selectByVisibleText(fundMethod);
                    waitUntilLoaderDisable();
                    waitForElementClickable(rp.getDownloadBtn(), 5);
                    Select fundSourceDD = new Select(rp.getFundSourceDD());
                    fundSourceDD.selectByVisibleText(fundSource);
                    waitUntilLoaderDisable();
                }
                waitUntilLoaderDisable();
                waitForElementClickable(rp.getDownloadBtn(), 5);
                Select currencyDD = new Select(rp.getWalletDD());
                if (!wallet.equalsIgnoreCase("All"))
                    currencyDD.selectByValue(wallet);
                else currencyDD.selectByVisibleText("All");
                break;
        }
        if (!paymentDate.isEmpty()) {
            waitForElementClickable(rp.getPaymentDateRange(), 5);
            rp.getPaymentDateRange().click();
            waitForElementClickable(rp.getPaymentDateCustom(), 5);
            switch (paymentDate) {
                case "This Month":
                    rp.getPaymentDateThisMonth().click();
                    waitUntilLoaderDisable();
                    break;
                case "This Year":
                    rp.getPaymentDateThisYear().click();
                    waitUntilLoaderDisable();
                    break;
                case "Custom":
                    rp.getPaymentDateCustom().click();
                    waitForElementClickable(rp.getPaymentDateCustomStartMonthDD(), 5);
                    Select startMonth = new Select(rp.getPaymentDateCustomStartMonthDD());
                    startMonth.selectByValue("0");
                    Select startYear = new Select(rp.getPaymentDateCustomStartYearDD());
                    startYear.selectByValue("2022");
                    waitForElementClickable(rp.getPaymentDateCustomJan1(), 5);
                    rp.getPaymentDateCustomJan1().click();
                    Select endMonth = new Select(rp.getPaymentDateCustomEndMonthDD());
                    endMonth.selectByValue("2");
                    Select endYear = new Select(rp.getPaymentDateCustomStartYearDD());
                    endYear.selectByValue("2022");
                    waitForElementClickable(rp.getPaymentDateCustomMar31(), 5);
                    rp.getPaymentDateCustomMar31().click();
                    waitUntilLoaderDisable();
                    break;
            }
        }
        if (!searchParam.isEmpty()) {
            waitForElementVisible(rp.getSearchByTxt(), 5);
            rp.getSearchByTxt().sendKeys(searchParam);
            rp.getGoBtn().click();
            waitUntilLoaderDisable();
        }
        if (account.equals("This Account")) {
            try {
                waitForElementVisible(rp.getShowDetails().get(0), 2);
                Assert.assertTrue(rp.getShowDetails().size() >= 1, "Transaction details list not available");
                Assert.assertTrue(Integer.parseInt(rp.getFilteredTxnCount().getText()) > 0, "Filtered Transaction count is 0");
                if (Integer.parseInt(rp.getFilteredTxnCount().getText()) > Integer.parseInt(getPropertyValue("recordsToDownloadAttachment", "config"))) {
                    rp.getDownloadBtn().click();
                    verifyDownloadedFile(downloadedFileName, rp.getFilteredTxnCount().getText(), report);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                Assert.assertFalse(rp.getNoTxnMsg().isDisplayed(), rp.getNoTxnMsg().getText());
            }
        } else {
            try {
                waitForElementVisible(rp.getBenCompList().get(0), 2);
                if (account.equals("Beneficiary Company Account"))
                    Assert.assertTrue(rp.getBenCompList().size() >= 1, "Beneficiary Company List has no records");
                else Assert.assertTrue(rp.getBenCompList().size() >= 1, "Connected Company List has no records");
                rp.getDownloadBtn().click();
                verifyDownloadedFile(downloadedFileName, "", report);
            } catch (Exception e) {
                Assert.assertFalse(rp.getNoTxnMsg().isDisplayed(), rp.getNoTxnMsg().getText());
            }
        }
    }

    @DataProvider
    public Object[][] tc02_data() {
        return new Object[][]{
                /*{"account", "payments", "moreInfo", "report", "wallet/currency", "program/beneficiaries/connectedCompanies", "paymentDate", "method", "searchParam", "txn", "fundMethod", "fundSource"}*/
                {"This Account", "Sent", "Yes", "By Payment", "", "", "", "", "", "", "", ""},
                /*{"This Account", "Sent", "No", "By Payment", "All Wallets", "", "", "", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "USD", "", "", "", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "", "", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "Campaign", "", "", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "This Month", "", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Wallet", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Bank", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Check", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Prepaid Virtual Card", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Digital Gift Card", "", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "TestAPICompany", "", "", ""},
                {"This Account", "Sent", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "17754645", "", "", ""},
                {"This Account", "Sent", "No", "By Mass Pay Submission", "", "All Programs", "Custom", "All", "", "", "", ""},
                {"This Account", "Sent", "No", "By Date", "All Wallets", "All Programs", "Custom", "", "Test Wallet", "", "", ""},
                {"This Account", "Sent", "No", "By Beneficiary Company", "All Wallets", "All Programs", "Custom", "", "Hari Test Company", "", "", ""},
                {"This Account", "Sent", "No", "By Beneficiary Individual", "All Wallets", "All Programs", "Custom", "", "Hari Test", "", "", ""},
                {"This Account", "Sent", "No", "By Beneficiary Individual - Consolidated", "All Wallets", "All Programs", "Custom", "", "Hari Test", "", "", ""},
                {"This Account", "Sent", "No", "Claims", "All Wallets", "All Programs", "Custom", "All", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "", "", "", "", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "", "", "", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "USD", "", "", "", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "", "", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "Campaign", "", "", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "This Month", "", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Wallet", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Bank", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Check", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Prepaid Virtual Card", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Direct to Digital Gift Card", "", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "Selenium", "", "", ""},
                {"This Account", "Received", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "6.00", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "", "", "", "", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "", "", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "USD", "", "", "", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "This Month", "", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "Custom", "", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "Custom", "All", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "Custom", "Bank Account", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "Custom", "Check", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "Custom", "Prepaid Virtual Card", "", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "Custom", "All", "Transfer to", "", "", ""},
                {"This Account", "Transferred", "No", "By Payment", "All Wallets", "", "Custom", "All", "10655675", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "", "", "", "", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "", "", "", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "USD", "", "", "", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "", "", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "Campaign", "", "", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "This Month", "", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "Custom", "", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "Custom", "ACH Debit", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "Custom", "Bank Transfer", "", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "Fund Request - Bank Transfer", "", "", ""},
                {"This Account", "Funded", "No", "By Payment", "All Wallets", "All Programs", "Custom", "All", "TestAPICompany", "", "", ""},
                {"Beneficiary Company Account", "", "No", "Admins", "", "All Company Beneficiaries", "", "", "", "", "", ""},
                {"Beneficiary Company Account", "", "No", "Admins", "", "All Company Beneficiaries", "", "", "Adlin Infotech", "", "", ""},
                {"Beneficiary Company Account", "", "No", "Admins", "", "Adlin Infotech", "", "", "test2706_2@xtrm.com", "", "", ""},
                {"Beneficiary Company Account", "", "No", "Wallets", "", "All Company Beneficiaries", "", "", "", "", "", ""},
                {"Beneficiary Company Account", "", "No", "Wallets", "", "All Company Beneficiaries", "", "", "AnyPay - FJD", "", "", ""},
                {"Beneficiary Company Account", "", "No", "Wallets", "", "Adlin Infotech", "", "", "3127", "", "", ""},
                {"Connected Company Account", "", "No", "Transactions", "All", "", "Custom", "", "", "Summary", "", ""},
                {"Connected Company Account", "", "No", "Transactions", "USD", "", "Custom", "", "", "Summary", "", ""},
                {"Connected Company Account", "", "No", "Transactions", "All", "", "Custom", "", "", "Pending Fund", "All", "All"},
                {"Connected Company Account", "", "No", "Transactions", "USD", "", "Custom", "", "", "Pending Fund", "All", "All"},
                {"Connected Company Account", "", "No", "Transactions", "All", "", "Custom", "", "", "Pending Fund", "Bank Transfer", "All"},
                {"Connected Company Account", "", "No", "Transactions", "All", "", "Custom", "", "", "Pending Fund", "Bank Transfer", "Platform"},
                {"Connected Company Account", "", "No", "Transactions", "All", "", "Custom", "", "", "Sent", "", ""},
                {"Connected Company Account", "", "No", "Transactions", "USD", "", "Custom", "", "", "Sent", "", ""},
                {"Connected Company Account", "", "No", "Transactions", "All", "", "Custom", "", "", "Received", "", ""},
                {"Connected Company Account", "", "No", "Transactions", "USD", "", "Custom", "", "", "Received", "", ""},
                {"Connected Company Account", "", "No", "Wallets", "", "All Connected Companies", "", "", "", "", "", ""},
                {"Connected Company Account", "", "No", "Wallets", "", "Hari Test Company", "", "", "", "", "", ""},
                {"Connected Company Account", "", "No", "Wallets", "", "All Connected Companies", "", "", "86512", "", "", ""},
                {"Connected Company Account", "", "No", "By Mass Pay Submission", "", "All Programs", "Custom", "All", "", "", "", ""},
                {"Connected Company Account", "", "No", "By Mass Pay Submission", "", "All Programs", "Custom", "Wallet", "", "", "", ""},
                {"Connected Company Account", "", "No", "By Mass Pay Submission", "", "All Programs", "Custom", "Direct to Bank", "", "", "", ""},
                {"Connected Company Account", "", "No", "By Mass Pay Submission", "", "All Programs", "Custom", "Direct to Check", "", "", "", ""},
                {"Connected Company Account", "", "No", "By Mass Pay Submission", "", "All Programs", "Custom", "Direct to Prepaid Virtual Card", "", "", "", ""},
                {"Connected Company Account", "", "No", "By Mass Pay Submission", "", "All Programs", "Custom", "Direct to Digital Gift Card", "", "", "", ""},*/
        };
    }

    @Test(dataProvider = "tc03_data", groups = {"Statements", "CompanyRegression", "Security"}, retryAnalyzer = RetryAnalyzer.class)
    public void tc03_statements(String walletCurrency, String downloadType, String year, String month, String rangeType, String fileSize) throws Exception {
        tc01_homePage();
        waitForElementClickable(rp.getStatementOption(), 5);
        rp.getStatementOption().click();
        waitUntilLoaderDisable();
        waitForElementClickable(rp.getStatementYearDownloadBtn(), 5);
        Assert.assertTrue(driver.getCurrentUrl().contains("Report/Statements"), "Redirected to wrong URL in statement option click - " + driver.getCurrentUrl());
        Select walletDD = new Select(rp.getStatementWalletDD());
        walletDD.selectByValue(getWalletID(walletCurrency));
        waitUntilLoaderDisable();
        waitForElementClickable(rp.getStatementYearDownloadBtn(), 5);
        if (downloadType.equals("Range")) {
            rp.getStatementDateRange().click();
            waitForElementClickable(rp.getRangeLast7DaysOption(), 5);
            switch (rangeType) {
                case "Last 7 Days":
                    rp.getRangeLast7DaysOption().click();
                    break;
                case "Last 30 Days":
                    rp.getRangeLast30DaysOption().click();
                    break;
                case "Last Month":
                    rp.getRangeLastMonthOption().click();
                    break;
                case "This Year":
                    rp.getRangeThisYearOption().click();
                    break;
                case "Last Year":
                    rp.getRangeLastYearOption().click();
                    break;
            }
            rp.getRangeDownloadBtn().click();
            waitUntilLoaderDisable();
            waitForElementVisible(rp.getErrMsg(), 5);
            Assert.assertEquals(rp.getErrMsg().getText(), "Statement downloaded successfully");
            verifyDownloadedFileSize(getDownloadedFileName("Statement_"), fileSize);
        } else {
            Select yearDD = new Select(rp.getStatementYearDD());
            yearDD.selectByValue(year);
            Select monthDD = new Select(rp.getStatementMonthDD());
            monthDD.selectByValue(month);
            rp.getStatementYearDownloadBtn().click();
            waitUntilLoaderDisable();
            waitForElementVisible(rp.getErrMsg(), 5);
            Assert.assertEquals(rp.getErrMsg().getText(), "Statement downloaded successfully");
            verifyDownloadedFileSize("HTML.pdf", fileSize);
        }

    }

    @DataProvider
    public Object[][] tc03_data() {
        return new Object[][]{
                /*{"walletCurrency", "downloadType", "year", "monthNo", "rangeType", "fileSize"}*/
                {"USD", "Year", "2022", "8", "", "greaterThan"},
                /*{"BWP", "Year", "2022", "6", "", "lessThan"},
                {"USD", "Range", "", "", "Last 7 Days", "greaterThan"},
                {"USD", "Range", "", "", "Last 30 Days", "greaterThan"},
                {"USD", "Range", "", "", "Last Month", "greaterThan"},
                {"USD", "Range", "", "", "This Year", "greaterThan"},
                {"USD", "Range", "", "", "Last Year", "greaterThan"},*/

        };
    }

    /******************************************************************************************************************/

    public void verifyDownloadedFile(String fileName, String txnCount, String report) throws Exception {
        String home = System.getProperty("user.home");
        String fileLocation = home + "\\Downloads\\" + fileName;
        File file = new File(fileLocation);
        Thread.sleep(1000); //wait for the downloaded file to appear in the downloads folder
        Assert.assertTrue(file.exists(), "File - " + fileName + " not available in downloads folder");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
        ArrayList<String[]> lines = new ArrayList<>();
        String line;
        int rowCount = 0, sum = 0;
        if (report.equals("By Beneficiary Individual - Consolidated")) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] distance = line.replace("\"", "").split("\t");
                lines.add(distance);
            }
            int i = 0;
            for (String[] lineA : lines) {
                if (i == 0) i++;
                else if (i < lines.size() - 1) {
                    StringJoiner joiner = new StringJoiner("");
                    Stream.of(lineA[1].split("\0")).forEach(joiner::add);
                    sum = sum + Integer.parseInt(String.valueOf(joiner));
                    i++;
                }
            }
            Assert.assertEquals(sum, Integer.parseInt(txnCount), "Count mismatched in downloaded file");
        } else {
            while (bufferedReader.readLine() != null) {
                rowCount++;
            }
            if (!txnCount.isEmpty())
                Assert.assertEquals(rowCount - 2, Integer.parseInt(txnCount), "Count mismatched in downloaded file");
            else Assert.assertTrue(rowCount >= 2, "No transaction data available in downloaded file");
        }
        bufferedReader.close();
        file.delete();
        Assert.assertFalse(file.exists(), "File - " + fileName + " not deleted and still available in downloads folder");
    }

    public void verifyDownloadedFileSize(String fileName, String fileSize) throws InterruptedException {
        String home = System.getProperty("user.home");
        String fileLocation = home + "\\Downloads\\" + fileName;
        File file = new File(fileLocation);
        Thread.sleep(1000); //wait for the downloaded file to appear in the downloads folder
        Assert.assertTrue(file.exists(), "File - " + fileName + " not available in downloads folder");
        if (fileSize.equals("greaterThan"))
            Assert.assertTrue(file.length() > 62000, "File Size is less than minimum, need to check the file content. File size - " + file.length());
        else Assert.assertTrue(file.length() < 62000, "File Size is less than minimum, need to check the file content");
        file.delete();
        Assert.assertFalse(file.exists(), "File - " + fileName + " not deleted and still available in downloads folder");
    }

    public String getDownloadedFileName(String fileName) throws Exception {
        String home = System.getProperty("user.home");
        String fileLocation = home + "\\Downloads\\";
        File downloads = new File(fileLocation);
        File[] allFile = downloads.listFiles();
        String name = null;
        for (File file : allFile) {
            if (file.getName().contains(fileName)) {
                name = file.getName();
                break;
            }
        }
        return name;
    }
}
