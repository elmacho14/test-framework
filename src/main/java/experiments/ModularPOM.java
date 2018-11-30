package experiments;

import pageobjects.modules.AdjacentTilesModule;
import pageobjects.modules.FeaturedSectionModule;
import pageobjects.modules.cookiebanner.CookieBanner;
import session.Instance;
import session.sessionutils.Environment;
import session.sessionutils.Geo;
import session.sessionutils.Page;
import utilities.Wait;

public class ModularPOM {
    public static void main(String[] args) {
        Instance instance = new Instance();
        instance.createFirefoxSessionAndNavigateTo(
                /*Environment.PRODUCTION + Geo.US_EN + Page.STRATEGYINDEX,*/
                "https://www.accenture.com/us-en/success-carve-out-for-competitive-agility",
                true);

        FeaturedSectionModule fsm = new FeaturedSectionModule(instance.getDriver());
        fsm.selectModule(0, 1, 0);

        fsm.clickShare();
    }
}