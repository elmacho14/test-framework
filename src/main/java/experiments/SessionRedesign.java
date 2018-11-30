package experiments;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Eyes;
import session.Instance;
import utilities.Applitools;
import utilities.browsermob.NetworkRequests;

import java.util.HashMap;

public class SessionRedesign {
    public static void main(String[] args) {

        BatchInfo batchInfo = new BatchInfo("Anaalytics Test");

        Instance instance = new Instance();
        NetworkRequests networkRequests = NetworkRequests.startProxyServer(instance.getDesiredCapabilitiesInstance());
        instance.createChromeSessionAndNavigateTo("https://www.accenture.com/us-en/careers", false);

        Applitools applitools = new Applitools(instance.getDriver());

        Eyes eyes = applitools
                .initialize("wctoHxCy61MvNPZAbrklsqORgWNk6wIeQa207J9Bl8s110")
                .setBatch(batchInfo)
                .startInstance("Accenture", "Analytics");

        eyes.checkWindow("First check");
        eyes.checkWindow("Second check");

        applitools.teardown();

        HashMap<String, String> data = networkRequests
                .getAllRequestsByRequestUrl("https://somni.accenture.com/b/ss")
                .pageViewRequest()
                .getVariables("v49");

        System.out.println(data.get("v49"));
        NetworkRequests.stopProxyServer();
    }
}
