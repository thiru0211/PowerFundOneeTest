package allPages;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports extends PropertyFileReader{
	private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeMethod
    public void setup() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (extentTest != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.fail("Test failed: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Test passed");
            } else {
                extentTest.skip("Test skipped");
            }

            extentReports.flush();
        }
    }

    protected void startTest(String testName) {
        extentTest = extentReports.createTest(testName);
    }
}
