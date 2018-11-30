package tests;

import com.applitools.eyes.selenium.Eyes;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import modules.AdjacentTilesModule;
import modules.FeaturedSectionModule;
import modules.cookiebanner.CookieBanner;
import session.Instance;
import session.instanceutils.Environment;
import session.instanceutils.Geo;
import session.instanceutils.Page;
import utilities.Applitools;
import utilities.Wait;

public class Test1 {

    private Instance instance;
    private Applitools applitools;

    @BeforeClass
    public void setup() {
        instance = new Instance();
        instance.getChromeOptionsInstance().setHeadless(true);
        instance.createChromeSessionAndNavigateTo(
                Environment.PRODUCTION + Geo.US_EN + Page.STRATEGYINDEX,
                false
        );

        // Close banner
        Wait.waitFor(3);
        new CookieBanner(instance.getDriver()).closeBanner();
        Wait.waitFor(3);

        applitools = new Applitools(instance.getDriver());
        Eyes eyes = applitools
                .initialize("wctoHxCy61MvNPZAbrklsqORgWNk6wIeQa207J9Bl8s110")
                .startInstance("Accenture", "Strategy Index");
        eyes.setForceFullPageScreenshot(true);
        eyes.checkWindow();
    }

    @AfterClass
    public void teardown() {
        applitools.teardown();
    }

    @AfterMethod
    public void afterMethod() {
        instance.getDriver().navigate().back();
    }

    @Test(priority = 1, description = "This is the first test.")
    public void fsmTest() {
        FeaturedSectionModule featuredSectionModule = new FeaturedSectionModule(instance.getDriver());
        featuredSectionModule.selectModule(1, 0, 0);
        featuredSectionModule.clickTitle();
        Assert.assertTrue(instance.getDriver().getCurrentUrl().contains("accenture-ibb-consulting-group"));
    }

    @Test(priority = 2, description = "This is the second test.")
    public void atmTest() {
        AdjacentTilesModule adjacentTilesModule = new AdjacentTilesModule(instance.getDriver());
        adjacentTilesModule.selectModule(1, 2, 1);
        adjacentTilesModule.clickCTA();
        Assert.assertTrue(instance.getDriver().getCurrentUrl().contains("success-nokia-sustainability-management-solution"));
    }
}
