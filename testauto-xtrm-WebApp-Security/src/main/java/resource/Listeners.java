package resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseClass implements ITestListener {
    ExtentReporterNG reportNG = new ExtentReporterNG();
    ExtentReports report = reportNG.getReport();
    ThreadLocal<ExtentTest> extentReport = new ThreadLocal<>();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = report.createTest(result.getMethod().getMethodName());
        extentReport.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentReport.get().log(Status.PASS, "Test Completed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentReport.get().log(Status.FAIL, "Test execution failed");
        extentReport.get().fail(result.getThrowable());
        String tcMethodName = result.getMethod().getMethodName();
        try {
            extentReport.get().addScreenCaptureFromPath("file:///" + getScreenshot(tcMethodName), result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        report.removeTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        File filePath = new File(System.getProperty("user.dir") + "/extentReport");
        File securityReportFilePath = new File(System.getProperty("user.dir") + "/securityReport");
        securityReportFilePath.mkdir();
        try {
            FileUtils.cleanDirectory(filePath);
            FileUtils.cleanDirectory(securityReportFilePath);
            //VideoRecorderUtility.startRecord("XTRM");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
        /*try {
            VideoRecorderUtility.stopRecord();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        if (zapAPI != null) {
            String title = "XTRM Web-Application";
            String reportName = "xtrm-zap-report";
            String template = "modern";
            String theme = "corporate";
            String description = "XTRM Web Application Security Test Report";
            String targetFolder = System.getProperty("user.dir") + "/securityReport";
            try {
                zapAPI.reports.generate(title, template, theme, description, null, null, null, null, null, reportName+"-"+template, null, targetFolder, null);
                //zapAPI.reports.generate(title, "traditional-pdf", null, description, null, null, null, null, null, reportName,null,targetFolder, null);
                zapAPI.reports.generate(title, "risk-confidence-html", null, description, null, null, null, null, null, reportName+"-risk-confidence", null, targetFolder, null);
            } catch (ClientApiException e) {
                e.printStackTrace();
            }
        }
    }
}
