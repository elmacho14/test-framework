package tests.shakeout;

import com.applitools.eyes.selenium.Eyes;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.modules.cookiebanner.CookieBanner;
import pageobjects.pageclasses.Registration;
import pageobjects.pageclasses.RegistrationConfirmation;
import session.sessionutils.Environment;
import session.sessionutils.Geo;
import session.Instance;
import session.sessionutils.Page;
import utilities.Applitools;
import utilities.Wait;

import static org.testng.Assert.assertTrue;

public class RegistrationTest {

    private Registration registration;
    private Instance instance;

    @BeforeClass
    public void setup() {
        instance = new Instance();
        instance.createFirefoxSessionAndNavigateTo(
                Environment.PRODUCTION + Geo.US_EN + Page.REGISTRATION,
                true);
        registration = new Registration(instance.getDriver());
    }

    @BeforeMethod
    public void closeCookieBanner() {
        CookieBanner cookieBanner = new CookieBanner(instance.getDriver());
        Wait.waitFor(3);
        cookieBanner.closeBanner();
        Wait.waitFor(2);
    }

    @Test
    public void registerUser() {
        registration.enterEmailAddress("ponchonito.el.de.guanzo_1126@mexicano.com");
        registration.enterPassword("qwe123!@#");
        registration.retypePassword("qwe123!@#");
        registration.enterFirstName("Ponchonito");
        registration.enterLastName("El De Guanzo");
        registration.selectCountry();
        registration.selectProvince();
        registration.enterCity("Mexico City");
        registration.selectJobPreferenceCountry();
        registration.selectExperience();
        registration.selectIndustries();
        registration.selectCareersSkill();
        registration.enterCaptcha();
        RegistrationConfirmation registrationConfirmation = registration.clickRegisterButton();

        Applitools applitools = new Applitools(instance.getDriver());
        Eyes eyes = applitools
                .initialize("wctoHxCy61MvNPZAbrklsqORgWNk6wIeQa207J9Bl8s110")
                .startInstance("Accenture Registration", "parallel-tests Testing ITIPT");

        eyes.setForceFullPageScreenshot(true);
        eyes.checkWindow();
        applitools.teardown();

        assertTrue(registrationConfirmation.getPageUrl().contains("confirmation-thankyou?"));
    }
}
