package tests;

import driversession.Instance;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.commonmodules.cookiebanner.CookieBanner;
import pageobjects.pageclasses.Registration;
import pageobjects.pageclasses.RegistrationConfirmation;
import utilities.Wait;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends Instance {

    private Registration registration;
    private RegistrationConfirmation registrationConfirmation;
    private CookieBanner cookieBanner;

    @BeforeClass
    public void setup() {
        createFirefoxSessionAndNavigateTo("https://acnpi-pt.ciotest.accenture.com/us-en/careers/registration/register",
                true);
        registration = new Registration();
    }

    @BeforeMethod
    public void closeCookieBanner() {
        cookieBanner = new CookieBanner();
        Wait.waitFor(3);
        cookieBanner.closeBanner();
        Wait.waitFor(2);
    }

    @Test
    public void registerUser() {
        registration.enterEmailAddress("ponchonito.el.de.guanzo_1@mexicano.com");
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
        registrationConfirmation = registration.clickRegisterButton();
        assertTrue(registrationConfirmation.getPageUrl().contains("confirmation-thankyou?"));
    }
}
