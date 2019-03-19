package utilities.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.AfterTest;
import utilities.Screenshot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kristian.g.maglasang on 9/29/2016.
 */

public class TestListener implements ITestListener, ISuiteListener {
    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test
                .get()
                .createNode(iTestResult.getMethod().getMethodName())
                .log(Status.PASS, MarkupHelper.createLabel("PASSED", ExtentColor.GREEN));
        onFinish();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
            String screenshotPath = new Screenshot(driver).captureScreenshot(iTestResult.getName());
            test
                    .get()
                    .createNode(iTestResult.getMethod().getMethodName())
                    .log(Status.FAIL, iTestResult.getThrowable().getMessage())
                    .fail("Snapshot of failure").addScreenCaptureFromPath("../" + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            onFinish();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test
                .get()
                .createNode(iTestResult.getMethod().getMethodName())
                .log(Status.SKIP, MarkupHelper.createLabel("SKIPPED", ExtentColor.ORANGE));
        onFinish();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // TODO
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentTest extentInstance = extent.createTest(iTestContext.getCurrentXmlTest().getName());
        test.set(extentInstance);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private void onFinish() {
        extent.flush();
    }

    @AfterTest
    public void closeExtent() {
        //extent.close();
    }

    @Override
    public void onStart(ISuite suite) {
        Date currentDateAndTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-hh.mm.ss");
        String formattedDateAndTime = dateFormat.format(currentDateAndTime);
        extent = ExtentManager.createInstance("reports/" + suite.getName() + "_" + formattedDateAndTime + ".html");
    }

    @Override
    public void onFinish(ISuite suite) {

    }
}
