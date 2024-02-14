package resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    ExtentReports extent;
    String reportPath = System.getProperty("user.dir") + "/extentReport/TestReport.html";

    public ExtentReports getReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("XTRM WebApp Automation Test Result");
        reporter.config().setDocumentTitle("XTRM");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Hari");
        return extent;
    }
}
