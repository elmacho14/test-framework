package system.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modules.CareersSignInModule;
import modules.cookiebanner.CookieBanner;
import modules.cookiebanner.PrivacyPreferenceCenter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import session.Instance;
import utilities.Wait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RMT9975Test {

    private Instance instance;
    private WebDriver driver;
    private PrivacyPreferenceCenter privacyPreferenceCenter;
    private ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/rmt9975_result.html");
    private ExtentTest scenario;
    private List<String> results;
    private ExtentReports extent;

    @Before
    public void setup() {
        extent = new ExtentReports();
        htmlReporter.setAppendExisting(true);
        extent.attachReporter(htmlReporter);
        ExtentTest feature = extent.createTest(Feature.class, "RMT9975 Cookie Blocking");
        scenario = feature.createNode(
                Scenario.class,
                "First Party analytics is enabled");
    }

    @Given("^I am at the Accenture page \"([^\"]*)\"$")
    public void i_am_at_the_Accenture_page(String url) {
        instance = new Instance();
        instance.getChromeOptionsInstance().setHeadless(true);
        instance.createChromeSessionAndNavigateTo(url, true);
        driver = instance.getDriver();
        scenario.createNode(com.aventstack.extentreports.gherkin.model.Given.class, "I am at the " + url + " page").pass("");
    }

    @When("^I click cookie settings$")
    public void i_click_cookie_settings() {
        CookieBanner cookieBanner = new CookieBanner(driver);
        Wait.waitFor(3);
        cookieBanner.clickCookieSettings();
        scenario.createNode(com.aventstack.extentreports.gherkin.model.When.class, "I click cookie Settings").pass("");
    }

    @And("^I disable the first party analytics cookie category \"([^\"]*)\"$")
    public void i_disable_the_first_party_analytics_cookie_category(String arg1) {
        privacyPreferenceCenter = new PrivacyPreferenceCenter(driver);
        privacyPreferenceCenter.clickFirstPartyAnalyticsMenuItem();
        privacyPreferenceCenter.clickStatusToggle();
        scenario.createNode(com.aventstack.extentreports.gherkin.model.And.class, "I disable the first party analytics cookie category").pass("");
    }

    @And("^I click save settings$")
    public void i_click_save_settings() {
        privacyPreferenceCenter.clickSaveSettingsButton();
        scenario.createNode(com.aventstack.extentreports.gherkin.model.And.class, "I click save settings").pass("");
    }

    @When("^I login using careers credentials$")
    public void i_login_using_careers_credentials(DataTable loginCredentials) {
        CareersSignInModule careersSignInModule = new CareersSignInModule(driver);
        careersSignInModule.enterEmailAddress(loginCredentials.raw().get(0).get(0));
        careersSignInModule.enterPassword(loginCredentials.raw().get(0).get(1));
        careersSignInModule.clickSignInButton();
        scenario.createNode(com.aventstack.extentreports.gherkin.model.When.class, "I login using careers credentials").pass("");
    }

    @Then("^Cookies produced by AcnCacheManager is blocked \"([^\"]*)\"$")
    public void cookies_produced_by_AcnCacheManager_is_blocked(String cookies) {
        WebStorage webStorage = (WebStorage) driver;
        LocalStorage localStorage = webStorage.getLocalStorage();
        results = new ArrayList<>();
        results.clear();
        localStorage.keySet().forEach(key -> {
            if (Arrays.asList(cookies.split(";")).contains(key)) {
                System.out.println(key);
                results.add(key);
            }

        });

        if (results.size() == 0)
            scenario.createNode(com.aventstack.extentreports.gherkin.model.Then.class, "Cookies produced by AcnCacheManager is blocked").pass("");
        else
            scenario.createNode(com.aventstack.extentreports.gherkin.model.Then.class, "Cookies produced by AcnCacheManager is blocked")
                    .log(Status.FAIL, "Found " + results.size() + " cookies.");

        Assert.assertEquals(results.size(), 0);
    }

    @And("^I close the cookie banner$")
    public void iCloseTheCookieBanner() {
        Wait.waitFor(3);
        new CookieBanner(driver).closeBanner();
    }

    @Then("^Cookies produced by AcnCacheManager is no longer blocked \"([^\"]*)\"$")
    public void cookiesProducedByAcnCacheManagerIsNoLongerBlocked(String cookies) {
        WebStorage webStorage = (WebStorage) driver;
        LocalStorage localStorage = webStorage.getLocalStorage();
        results = new ArrayList<>();
        results.clear();
        localStorage.keySet().forEach(key -> {
            if (Arrays.asList(cookies.split(";")).contains(key)) {
                System.out.println(key);
                results.add(key);
            }
        });

        if (results.size() >= 3)
            scenario.createNode(com.aventstack.extentreports.gherkin.model.Then.class, "Cookies produced by AcnCacheManager is no longer blocked").pass("");
        else
            scenario.createNode(com.aventstack.extentreports.gherkin.model.Then.class, "Cookies produced by AcnCacheManager is no longer blocked")
                    .log(Status.FAIL, "Found " + results.size() + " cookies.");

        Assert.assertTrue(results.size() >= 3);
    }

    @After
    public void teardown() {
        extent.flush();
        //instance.quitDriverSession();
    }
}
