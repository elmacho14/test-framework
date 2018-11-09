package tests;

import driversession.Instance;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.commonmodules.cookiebanner.CookieBanner;
import pageobjects.pageclasses.JobSearch;
import utilities.Wait;
import utilities.browsermob.NetworkRequests;

import java.util.Set;

public class AnalyticsTest extends Instance {

    private NetworkRequests networkRequests;

    @AfterMethod
    public void teardown() {
        NetworkRequests.stopProxyServer();
        quitDriverSession();
    }

    @Test
    public void pageViewRequestIsWorking() {
        NetworkRequests.startProxyServer();
        createChromeSessionAndNavigateTo("https://www.accenture.com/us-en/careers", false);
        networkRequests = new NetworkRequests();
        networkRequests.setRequestUrl("https://somni.accenture.com/b/ss");

        networkRequests.pageViewRequest().getAllVariables();
        Set<String> keys = NetworkRequests.varData.keySet();
        System.out.println("PAGE VIEW REQUEST\n");
        keys.forEach(key -> System.out.printf("%s = %s\n", key, NetworkRequests.varData.get(key)));
        System.out.println("\n");
    }

    @Test
    public void trackLinkRequest() {
        NetworkRequests.startProxyServer();
        createChromeSessionAndNavigateTo("https://www.accenture.com/us-en/careers/jobsearch", false);
        networkRequests = new NetworkRequests();
        networkRequests.setRequestUrl("https://somni.accenture.com/b/ss");

        CookieBanner cookieBanner = new CookieBanner();
        Wait.waitFor(2);
        cookieBanner.closeBanner();
        Wait.waitFor(2);

        JobSearch jobSearch = new JobSearch();
        jobSearch.enterKeyword("developer");
        jobSearch.clickFindButton();
        Wait.waitFor(3);

        networkRequests.trackLinkRequest().getAllVariables();
        Set<String> keys = NetworkRequests.varData.keySet();
        System.out.println("TRACK LINK REQUEST\n");
        keys.forEach(key -> System.out.printf("%s = %s\n", key, NetworkRequests.varData.get(key)));
        System.out.println("\n");
    }
}
