package system;

import com.applitools.eyes.selenium.Eyes;
import modules.AdjacentTilesModule;
import modules.FeaturedSectionModule;
import modules.cookiebanner.CookieBanner;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import session.Instance;
import session.Environment;
import session.Geo;
import session.Page;
import utilities.Applitools;
import utilities.PageTestability;
import utilities.TestHelper;
import utilities.Wait;

public class Test1 extends TestHelper {

    @BeforeClass
    public void setup(ITestContext iTestContext) {
        instance = new Instance();
        instance.getFirefoxOptionsInstance().setHeadless(true);
        pageUrl = Environment.PRODUCTION + Geo.US_EN + Page.STRATEGYINDEX;
        instance.createFirefoxSessionAndNavigateTo(pageUrl, false);
        context = setContext(iTestContext, instance.getDriver());
        pageTestability = new PageTestability(instance.getDriver());
    }

    @AfterGroups (value = "pageTestability")
    public void afterPageTestability() {
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

    @AfterMethod (dependsOnGroups = "test")
    public void afterMethod() {
        System.out.println("after method");
        instance.getDriver().navigate().back();
    }

    @Test(
            priority = 1,
            description = "This is the first test.",
            dependsOnGroups = "pageTestability",
            groups = {"test"})
    public void featuredSectionModuleTest() {
        System.out.println("Featured Section Module");
        FeaturedSectionModule featuredSectionModule = new FeaturedSectionModule(instance.getDriver());
        featuredSectionModule.selectModule(1, 0, 0);
        featuredSectionModule.clickTitle();
        Assert.assertTrue(instance.getDriver().getCurrentUrl().contains("accenture-ibb-consulting-group"));
    }

    @Test(
            priority = 2,
            description = "This is the second test.",
            dependsOnGroups = "pageTestability",
            groups = {"test"})
    public void adjacentTilesModuleTest() {
        System.out.println("Adjacent Tiles Module");
        AdjacentTilesModule adjacentTilesModule = new AdjacentTilesModule(instance.getDriver());
        adjacentTilesModule.selectModule(1, 2, 1);
        adjacentTilesModule.clickCTA();
        Assert.assertTrue(instance.getDriver().getCurrentUrl().contains("success-nokia-sustainability-management-solution"));
    }
}
