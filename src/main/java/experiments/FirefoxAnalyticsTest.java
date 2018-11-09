package experiments;

import driversession.Instance;
import pageobjects.commonmodules.cookiebanner.CookieBanner;
import pageobjects.pageclasses.JobSearch;
import utilities.Wait;
import utilities.browsermob.NetworkRequests;

public class FirefoxAnalyticsTest extends Instance {
    public static void main(String[] args) {
        try {
            NetworkRequests.startProxyServer();

            //createChromeSessionAndNavigateTo("https://www.accenture.com/us-en/careers/jobsearch", false);
            createFirefoxSessionAndNavigateTo("https://www.accenture.com/us-en/careers/jobsearch", false);

            NetworkRequests networkRequests = new NetworkRequests();
            JobSearch jobSearch = new JobSearch();
            CookieBanner cookieBanner = new CookieBanner();

            Wait.waitFor(3);
            cookieBanner.closeBanner();
            Wait.waitFor(3);

            jobSearch.clickFindButton();
            Wait.waitFor(3);

            networkRequests
                    .getAllRequestsByRequestUrl("https://somni.accenture.com/b/ss")
                    .trackLinkRequest()
                    .getVariables("pageName");

            System.out.println(NetworkRequests.varData.get("pageName"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            NetworkRequests.stopProxyServer();
        }
    }
}
