package experiments;

import pageobjects.modules.FeaturedInsightModule;
import pageobjects.modules.cookiebanner.CookieBanner;
import session.Instance;
import session.sessionutils.Environment;
import session.sessionutils.Geo;
import session.sessionutils.Page;
import utilities.Wait;

public class Headless {
    public static void main(String[] args) {
        Instance instance = new Instance();
        instance.getFirefoxOptionsInstance().setHeadless(true);
        instance.createFirefoxSessionAndNavigateTo(
                instance.urlBuilder(Environment.PRODUCTION, Geo.US_EN, Page.ARTIFICIALINDEX),
                false
        );

        // Close banner
        Wait.waitFor(3);
        new CookieBanner(instance.getDriver()).closeBanner();
        Wait.waitFor(3);

        FeaturedInsightModule featuredInsightModule = new FeaturedInsightModule(instance.getDriver());
        System.out.println("Module container count: " + featuredInsightModule.getModuleContainerCount());

        featuredInsightModule.selectModule(1);

        featuredInsightModule.clickArticleTitle();

        System.out.println("Current URL is: " + instance.getDriver().getCurrentUrl());
    }
}
