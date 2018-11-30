package tests.shakeout;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Eyes;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.modules.cookiebanner.CookieBanner;
import pageobjects.pageclasses.LoginPage;
import pageobjects.pageclasses.Registration;
import session.Instance;
import session.sessionutils.Environment;
import session.sessionutils.Geo;
import session.sessionutils.Page;
import utilities.Applitools;
import utilities.Wait;

public class LoginTest {

    private Instance instance;
    private LoginPage loginPage;
    private Applitools applitools;
    private BatchInfo batchInfo = new BatchInfo("Login Test");
    private Eyes eyes;

    @BeforeClass
    public void setup() {
        instance = new Instance();
        instance
                .createChromeSessionAndNavigateTo(
                        Environment.PRODUCTION + Geo.US_EN + Page.LOGIN,
                        true);
        loginPage = new LoginPage(instance.getDriver());
        applitools = new Applitools(instance.getDriver());
        eyes = applitools
                .initialize("wctoHxCy61MvNPZAbrklsqORgWNk6wIeQa207J9Bl8s110")
                .setBatch(batchInfo)
                .startInstance("Accenture", "Login");
    }

    @BeforeMethod
    public void closeCookieBanner() {
        CookieBanner cookieBanner = new CookieBanner(instance.getDriver());
        Wait.waitFor(3);
        cookieBanner.closeBanner();
        Wait.waitFor(2);
    }

    @AfterMethod
    public void eyesTeardown() {
        applitools.teardown();
    }

    @Test
    public void checkIfUserCanLogin() {
        eyes.setForceFullPageScreenshot(true);
        eyes.checkWindow("Before login.");

        loginPage.switchToFrame();
        loginPage
                .enterEmailAddress("xz.gylenhaal@yahoo.com")
                .enterPassword("Accenture03")
                .clickSignInButton(Registration.class);

        eyes.checkWindow("After login.");
    }
}
