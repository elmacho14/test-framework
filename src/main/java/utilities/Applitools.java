package utilities;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import org.openqa.selenium.WebDriver;

public class Applitools {

    private WebDriver driver;
    private Eyes eyes;

    public Applitools(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Creates a new instance of Eyes.
     * And sets the API key to your unique key taken from your Applitools dashboard.
     * @param apiKey Takes in a key of type String.
     * @return Return the current instance of Applitools.
     */
    public Applitools initialize(String apiKey) {
        eyes = new Eyes();
        eyes.setStitchMode(StitchMode.CSS);
        eyes.setMatchLevel(MatchLevel.STRICT);
        eyes.setHideScrollbars(false);
        eyes.setWaitBeforeScreenshots(3);
        eyes.setApiKey(apiKey);
        return this;
    }

    /**
     * This is optional
     * Call this method if you want to group tests together under 1 batch.
     * Make sure to pass in the same instance of BatchInfo to group the tests together.
     * @param batchInfo Takes in an instance of BatchInfo.
     * @return Return the current instance of Applitools.
     */
    public Applitools setBatch(BatchInfo batchInfo) {
        eyes.setBatch(batchInfo);
        return this;
    }

    /**
     * This creates the test.
     * @param appName Provide the application name, i.e. Accenture
     * @param testName Provide a test name, i.e. Login Test
     * @return Returns the Eyes instance
     */
    public Eyes startInstance(String appName, String testName) {
        eyes.open(driver, appName, testName);
        return eyes;
    }

    /**
     * This creates the test.
     * This is an overloaded method that takes in 1 additional parameter of instance RectangleSize.
     * @param appName Provide the application name, i.e. Accenture
     * @param testName Provide a test name, i.e. Login Test
     * @param rectangleSize Takes in an instance of RectangleSize that determines the browser's viewport.
     * @return Returns the Eyes instance
     */
    public Eyes startInstance(String appName, String testName, RectangleSize rectangleSize) {
        eyes.open(driver, appName, testName, rectangleSize);
        return eyes;
    }

    /**
     * Closes the current instance of Eyes.
     * This method needs to be called everytime you conclude your UI tests
     * so that the results will be reflected in your Applitools dashboard.
     */
    public void teardown() {
        try {
            eyes.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eyes.abortIfNotClosed();
        }
    }
}
