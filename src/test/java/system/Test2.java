package system;

import com.applitools.eyes.selenium.Eyes;
import modules.FeaturedInsightModule;
import modules.cookiebanner.CookieBanner;
import org.testng.Assert;
import org.testng.ITestContext;
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
import utilities.TestHelper;
import utilities.Wait;

public class Test2 extends TestHelper {

    @BeforeClass
    public void setup(ITestContext iTestContext) {
        instance = new Instance();
        instance.getChromeOptionsInstance().setHeadless(true);
        pageUrl = Environment.PRODUCTION + Geo.US_EN + Page.ARTIFICIALINDEX;
        instance.createChromeSessionAndNavigateTo(pageUrl, false);
        context = setContext(iTestContext, instance.getDriver());
        pageTestability = new PageTestability(instance.getDriver());
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
}
