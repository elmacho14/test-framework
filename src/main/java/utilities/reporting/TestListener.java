package utilities.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;
import org.testng.annotations.AfterTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kristian.g.maglasang on 9/29/2016.
 */

public class TestListener implements ITestListener, ISuiteListener {
    private ExtentReports extent;
    private ExtentTest test;
    private String screenshotPath;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //test = extent.createTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test
                .createNode("This is a node at onTestSuccess")
                .log(Status.PASS, MarkupHelper.createLabel("PASSED", ExtentColor.GREEN));
        onFinish();

        /*try {
            screenshotPath = Screenshot.captureScreenshot(iTestResult.getName());
            test.pass("Results Snapshot").addScreenCaptureFromPath("../" + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            test.log(Status.PASS, MarkupHelper.createLabel("PASSED", ExtentColor.GREEN));
            onFinish();
        }*/
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            //screenshotPath = Screenshot.captureScreenshot(iTestResult.getName());
            test.fail("Snapshot of failure").addScreenCaptureFromPath("../" + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            test
                    .createNode("This is a node at onTestFailure")
                    .log(Status.FAIL, iTestResult.getThrowable().getMessage());
            onFinish();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP, MarkupHelper.createLabel("SKIPPED", ExtentColor.ORANGE));
        onFinish();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // TODO
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        /*Date currentDateAndTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-hh.mm.ss");
        String formattedDateAndTime = dateFormat.format(currentDateAndTime);
        extent = ExtentManager.createInstance("reports/" + iTestContext.getSuite().getName() + "_" + formattedDateAndTime + ".html");*/
        test = extent.createTest(iTestContext.getCurrentXmlTest().getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public void onFinish() {
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
