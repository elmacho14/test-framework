package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import session.Instance;

public abstract class TestHelper {

    public Instance instance;
    protected String pageUrl;
    protected Applitools applitools;
    protected PageTestability pageTestability;
    protected ITestContext context;

    /**
     * Created this abstract setup() method so all tests that inherit this class
     * must have an implementation of this method.
     * @param iTestContext Need this for a passing a driver object as attribute to ITestContext
     */
    public abstract void setup(ITestContext iTestContext);

    /**
     * This method simply passes the driver object over to our test listener
     * so that we can use the captureScreenshot method.
     *
     * Call this inside the setup() method, after the driver object is no longer null
     * @param iTestContext Just pass an ITestContext object
     * @param driver WebDriver driver via instance.getDriver()
     * @return Returns ITestContext with the driver object added as an attribute
     */
    protected synchronized ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
        iTestContext.setAttribute("driver", driver);
        return iTestContext;
    }

    @Test(priority = 1, groups = {"pageTestability"})
    public void statusCodeCheck() {
        Assert.assertEquals(pageTestability.getPageStatusResponseCode(pageUrl), 200);
    }

    @Test(priority = 2, groups = {"pageTestability"})
    public void renderingErrorCheck() {
        Assert.assertFalse(pageTestability.areRenderingErrorsPresent());
    }

    @Test(priority = 3, groups = {"pageTestability"})
    public void glassmapperCheck() {
        Assert.assertFalse(pageTestability.areGlassmapperErrorsPresent());
    }

    @AfterTest
    public void teardown() {
        instance.getDriver().quit();
    }
}
