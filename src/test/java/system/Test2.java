package system;

import com.applitools.eyes.selenium.Eyes;
import modules.FeaturedInsightModule;
import modules.cookiebanner.CookieBanner;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import session.Instance;
import session.Environment;
import session.Geo;
import session.Page;
import utilities.Applitools;
import utilities.PageTestability;
import utilities.Wait;

public class Test2 {

    private Instance instance;
    private Applitools applitools;
    private String pageUrl = Environment.PRODUCTION + Geo.US_EN + Page.ARTIFICIALINDEX;
    private PageTestability pageTestability;

    @BeforeClass
    public void setup() {
        instance = new Instance();
        instance.getChromeOptionsInstance().setHeadless(true);
        instance.createChromeSessionAndNavigateTo(pageUrl, false);
        pageTestability = new PageTestability(instance.getDriver());
    }

    @Test (priority = 1, groups = {"pageTestability"})
    public void statusCodeCheck() {
        Assert.assertEquals(pageTestability.getPageStatusResponseCode(pageUrl), 200);
    }

    @Test (priority = 2, groups = {"pageTestability"})
    public void renderingErrorCheck() {
        Assert.assertFalse(pageTestability.areRenderingErrorsPresent());
    }

    @Test (priority = 3, groups = {"pageTestability"})
    public void glassmapperCheck() {
        Assert.assertFalse(pageTestability.areGlassmapperErrorsPresent());
    }

    @AfterGroups(value = "pageTestability")
    public void afterPageTestability() {
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

    @Test(
            priority = 1,
            description = "This is the first test.",
            dependsOnGroups = "pageTestability")
    public void featuredInsightModuleTest() {
        FeaturedInsightModule featuredInsightModule = new FeaturedInsightModule(instance.getDriver());
        featuredInsightModule.selectModule(0);
        featuredInsightModule.clickArticleTitle();
        Assert.assertTrue(instance.getDriver().getCurrentUrl().contains("insight-process-reimagined"));
    }

    @AfterClass
    public void teardown() {
        applitools.teardown();
    }
}
