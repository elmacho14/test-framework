package utilities.browsermob;

import driversession.Instance;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;

import java.net.URLDecoder;
import java.util.*;

public class NetworkRequests extends Instance {

    public static BrowserMobProxy proxy;
    public static HashMap<String, String> varData;
    private HarEntry harEntry;
    private List<HarEntry> requests;
    private List<HarEntry> filteredRequests = new ArrayList<>();
    private String requestUrl;

    public static void startProxyServer() {
        varData = new HashMap<>();

        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.RESPONSE_CONTENT, CaptureType.REQUEST_CONTENT);

        // create a new HAR
        proxy.newHar();
    }

    public static void stopProxyServer() {
        proxy.stop();
    }

    /**
     * Sets the value of requestUrl. Call this method if
     * you don't need to specify multiple request URLs
     *
     * Note: The requestUrl global variable will be set to null if calling
     * getAllRequestsByRequestUrl(String requestUrl) method.
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * Gets all the requests, no filters used.
     */
    private void getAllRequests() {
        requests = proxy.getHar().getLog().getEntries();
    }

    /**
     * Filters all requests that contains a specific request URL.
     * Call this method if you need to specify multiple request URLs.
     *
     * Note: The requestUrl global variable will be set to null
     * when calling this method as the rule for using the same requestUrl
     * as filter across all triggered requests is no longer honored.
     *
     * Call the setRequestUrl() method again if you wish to use 1
     * request URL, e.g., obj.setRequestUrl()
     * @param requestUrl
     */
    public NetworkRequests getAllRequestsByRequestUrl(String requestUrl) {
        this.requestUrl = null;
        getAllRequests();
        requests.forEach(request -> {
            if (request.getRequest().getUrl().contains(requestUrl)) {
                filteredRequests.add(request);
            }
        });
        return this;
    }

    /**
     * This is called when the setRequestUrl() method is used.
     */
    private void getAllRequestsByRequestUrl() {
        getAllRequests();
        requests.forEach(request -> {
            if (request.getRequest().getUrl().contains(requestUrl)) {
                filteredRequests.add(request);
            }
        });
    }

    /**
     * Returns a HarEntry list of all the requests filtered
     * by the request url provided to the setRequestUrl() method
     * @return
     */
    public List<HarEntry> getAllRequestsAsList() {
        getAllRequestsByRequestUrl();
        return filteredRequests;
    }

    /**
     * Gets image request at page load
     * @return
     */
    public NetworkRequests pageViewRequest() {
        if (requestUrl != null) {
            getAllRequestsByRequestUrl();
        }
        harEntry = filteredRequests.get(0);
        return this;
    }

    /**
     * Always gets the latest request triggered after performing some action (e.g., click)
     * @return
     */
    public NetworkRequests trackLinkRequest() {
        if (requestUrl != null) {
            getAllRequestsByRequestUrl();
        }
        Collections.reverse(filteredRequests);
        harEntry = filteredRequests.get(0);
        return this;
    }

    /**
     * Gets all the variables for a specific request - no filters, no extra steps, no drama
     */
    public void getAllVariables() {
        HarRequest request = harEntry.getRequest();
        String methodType = request.getMethod();

        if (methodType.equals("GET")) {
            request.getQueryString().forEach(data -> varData.put(data.getName(), data.getValue()));

        } else if (methodType.equals("POST")) {
            String text = request.getPostData().getText();
            String decoded = "";
            try {
                decoded = URLDecoder.decode(text, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String[] parsedString = decoded.split("&");

            Arrays.asList(parsedString).forEach(str -> {
                String[] splitStr = str.split("=", 2);
                int arraySize = splitStr.length;
                if (arraySize > 1) {
                    varData.put(splitStr[0], splitStr[1]);
                } else {
                    varData.put(splitStr[0], null);
                }
            });
        }
    }

    /**
     * Gets specific variables as defined by the user.
     * @param variables
     */
    public void getVariables(String... variables) {
        HarRequest request = harEntry.getRequest();
        String methodType = request.getMethod();

        if (methodType.equals("GET")) {
            Arrays.asList(variables).forEach(variable -> request.getQueryString().forEach(data -> {
                if (data.getName().equals(variable)) {
                    varData.put(data.getName(), data.getValue());
                }
            }));

        } else if (methodType.equals("POST")) {
            Arrays.asList(variables).forEach(variable -> {
                String text = request.getPostData().getText();
                String decoded = "";
                try {
                    decoded = URLDecoder.decode(text, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String[] parsedString = decoded.split("&");

                Arrays.asList(parsedString).forEach(str -> {
                    String[] splitStr = str.split("=", 2);
                    int arraySize = splitStr.length;
                    if (arraySize > 1) {
                        if (variable.equals(splitStr[0])) {
                            varData.put(splitStr[0], splitStr[1]);
                        }
                    } else {
                        if (variable.equals(splitStr[0])) {
                            varData.put(splitStr[0], null);
                        }
                    }
                });
            });
        }
    }
}
