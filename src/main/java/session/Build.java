package session;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import session.sessionutils.Environment;
import utilities.browsermob.NetworkRequests;

public class Build {

    private static Instance instance;

    /**
     * All tests that are run via XML will be getting the driver instance from this method.
     * Otherwise, you may directly invoke the same method from an Instance object.
     *
     * @return
     */
    public static WebDriver getDriver() {
        return instance.getDriver();
    }

    @BeforeTest
    @Parameters({
            "enableNetworkRequests",
            "browser",
            "environment",
            "geo",
            "page"
    })
    public void setup(
            boolean enableNetworkRequests,
            String browser,
            String environment,
            String geo,
            String page
    ) {
        instance = new Instance();
        if (enableNetworkRequests) {
            NetworkRequests.startProxyServer(instance.getDesiredCapabilitiesInstance());
        }

        switch (browser) {
            case "chrome":
                instance.createChromeSessionAndNavigateTo(
                        urlBuilder(environment, geo, page), false); //won't handle (for now) in XML the maxWindow arg
                break;
            case "firefox":
                instance.createFirefoxSessionAndNavigateTo(
                        urlBuilder(environment, geo, page), false); //won't handle (for now) in XML the maxWindow arg
                break;
        }
    }

    private String urlBuilder(String environment, String geo, String page) {
        String url = null;
        switch (environment) {
            case "production":
                url = Environment.PRODUCTION + "/" + geo + page;
                break;
            case "stage1":
                url = Environment.STAGE1 + "/" + geo + page;
                break;
            case "itipt":
                url = Environment.ITIPT + "/" + geo + page;
        }
        return url;
    }
}
