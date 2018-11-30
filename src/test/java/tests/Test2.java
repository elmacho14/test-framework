package tests;

import com.applitools.eyes.selenium.Eyes;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.modules.FeaturedInsightModule;
import pageobjects.modules.cookiebanner.CookieBanner;
import session.Instance;
import session.sessionutils.Environment;
import session.sessionutils.Geo;
import session.sessionutils.Page;
import utilities.Applitools;
import utilities.Wait;

public class Test2 {

    private Instance instance;
    private Applitools applitools;

    @BeforeClass
    public void setup() {
        instance = new Instance();
        instance.getFirefoxOptionsInstance().setHeadless(true);
        instance.createFirefoxSessionAndNavigateTo(
                Environment.PRODUCTION + Geo.US_EN + Page.ARTIFICIALINDEX,
                false
        );

        // Close banner
        Wait.waitFor(3);
        new CookieBanner(instance.getDriver()).closeBanner();
        Wait.waitFor(3);

        applitools = new Applitools(instance.getDriver());
        Eyes eyes = applitools
                .initialize("wctoHxCy61MvNPZAbrklsqORgWNk6wIeQa207J9Bl8s110")
                .startInstance("Accenture", "Artificial Intelligence Index");
        eyes.setForceFullPageScreenshot(true);
        eyes.checkWindow();
    }

    @AfterClass
    public void teardown() {
        applitools.teardown();
    }

    @Test(priority = 1)
    public void fimTest() {
        FeaturedInsightModule featuredInsightModule = new FeaturedInsightModule(instance.getDriver());
        featuredInsightModule.selectModule(0);
        featuredInsightModule.clickArticleTitle();
        Assert.assertTrue(instance.getDriver().getCurrentUrl().contains("insight-process-reimagined"));
    }
}
