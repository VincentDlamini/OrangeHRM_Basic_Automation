package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;   //UI of the report.
    public ExtentReports extent;    //Populates common info on the report.
    public ExtentTest test;     //Creating test case entries in the report and updates status of the test methods.

    public void onStart(ITestContext context) {

        sparkReporter = new ExtentSparkReporter("C:\\Users\\Lebo\\Desktop\\Test Automation University\\OrangeHRM Automation Test\\OrangeHRMBasicAutomation\\src\\test\\reports\\report.html");

        sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report"); //Title of report
        sparkReporter.config().setReportName("OrangeHRM Functional Testing"); //Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OrangeHRM");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Employee");
        //extent.setSystemInfo("UserName", System.getProperty("user.name")); //To dynamically get uses. Used in the work environment.
        extent.setSystemInfo("Test Name", "Vincent");
        extent.setSystemInfo("Environment", "Quality Assurance");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());     //Creates a new entry of the test name on the report.
        test.log(Status.PASS, result.getName() + "Test case Passed!");   //Update status P/F/S
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, result.getName() + " Test case Failed.");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, result.getName() + "Test case Skipped.");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
