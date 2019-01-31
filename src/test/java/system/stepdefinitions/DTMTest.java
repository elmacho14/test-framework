package system.stepdefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modules.cookiebanner.CookieBanner;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import session.Instance;
import utilities.browsermob.NetworkRequests;

import java.util.*;

import static utilities.Wait.waitFor;

public class DTMTest extends Instance {

    private Instance instance = new Instance();
    private String testEnvi;
    private NetworkRequests networkRequests;
    private List<HarEntry> requests;
    private CookieBanner cookieBanner;
    private Map<String, String> map;
    private List<Map> requestArr;
    private String[] keyValPair;
    private String separator1 = "=========================================================";
    private String separator2 = "---------------------------------------------------------";
    private String[] euGeos = {"gb-en", "it-it", "de-de", "fr-fr", "es-es"};
    private String geo;
    private String[] parameters;

    @Before
    public void setup() {
        map = new HashMap<>();
        requestArr = new ArrayList<>();
        networkRequests = NetworkRequests.startProxyServer(instance.getDesiredCapabilitiesInstance());
    }

    @After
    public void teardown() {
        NetworkRequests.stopProxyServer();
        instance.quitDriverSession();

    }

    @Given("^I'm using the environment$")
    public void i_use_the_environment(DataTable dataTable) {
        switch (dataTable.raw().get(0).get(0)) {
            case "production":
                testEnvi = "https://www.accenture.com/";
                break;
            case "stage":
                testEnvi = "https://acnstg.accenture.com/";
                break;
        }
    }

    @And("^I launch a browser and navigate to page \"([^\"]*)\" using geo \"([^\"]*)\"$")
    public void i_launch_a_browser_and_navigate_to_page_using_geo(String page, String geo, DataTable dataTable) {
        this.geo = geo;

        switch (dataTable.raw().get(0).get(0)) {
            case "chrome":
                instance.createChromeSessionAndNavigateTo(testEnvi + geo + "/" + page, false);
                break;
            case "firefox":
                instance.createFirefoxSessionAndNavigateTo(testEnvi + geo + "/" + page, false);
        }
    }

    @And("^I accept cookies and refresh page if geo is eu$")
    public void i_accept_cookies_and_refresh_page_if_geo_is_eu() {
        cookieBanner = new CookieBanner(instance.getDriver());
        for (String geo : euGeos) {
            if (geo.equals(this.geo)) {
                acceptCookiesAndRefresh();
            }
        }
    }

    @When("^I open developer tools and check under network panel$")
    public void i_open_developer_tools_and_check_under_network_panel() {
        //networkRequests = new NetworkRequests();
    }

    @And("^I filter using id \"([^\"]*)\"$")
    public void i_filter_using_tag(String tag) {
        networkRequests.setRequestUrl(tag);
        requests = networkRequests.getAllRequestsAsList();
    }

    @Then("^I should see an image request whose domain and parameter values should be \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_should_see_an_image_request_whose_domain_and_parameter_values_should_be(String domain, String params) {
        parameters = params.split(", ");
        requests.forEach(request -> {
            if (request.getResponse().getStatus() == 200) {
                map = new HashMap<>();
                Arrays.stream(request.getRequest().getUrl().split(";"))
                        .forEach(item -> {
                            if (!item.contains("https://")) {
                                if (StringUtils.endsWith(item, "=")) {
                                    keyValPair = new String[]{item.split("=")[0], "no value"};
                                } else {
                                    keyValPair = item.split("=");
                                }
                                map.put(keyValPair[0], keyValPair[1]);
                            } else {
                                map.put("domain", item);
                            }
                        });
                requestArr.add(map);
            }
        });

        for (String parameter : parameters) {
            requestArr.removeIf(map -> map.get(parameter) == null || !map.get("domain").toString().contains(domain));
        }

        System.out.printf("Size of remaining map: %d\n", requestArr.size());

        System.out.printf("%s\n%s\n%s\n", separator1, this.geo.toUpperCase(), separator1);
        requestArr.forEach(map -> {
            System.out.println(separator2);
            for (Object key : map.keySet()) {
                System.out.printf("%s = %s\n", String.valueOf(key), map.get(key));
            }
            System.out.printf("%s\n", separator2);
        });
        Assert.assertTrue(requestArr.size() == 1);
    }

    @Then("^I should not be able to trigger any image request$")
    public void i_should_not_be_able_to_trigger_any_image_request() {
        Assert.assertTrue(requests.size() == 0);
    }

    private void acceptCookiesAndRefresh() {
        // This is not an ideal way of waiting for an element (explicit wait is the way to go),
        // but will have to do for now.
        waitFor(3);
        cookieBanner.clickAcceptCookies();
        instance.getDriver().navigate().refresh();
        waitFor(3);
    }
}
