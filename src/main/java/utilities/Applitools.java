package utilities;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
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
        eyes.setApiKey(apiKey);
        return this;
    }

    /**
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
     */
    public void startInstance(String appName, String testName) {
        eyes.open(driver, appName, testName);
    }

    /**
     * This creates the test.
     * This is an overloaded method that takes in 1 additional parameter of instance RectangleSize.
     * @param appName Provide the application name, i.e. Accenture
     * @param testName Provide a test name, i.e. Login Test
     * @param rectangleSize Takes in an instance of RectangleSize that determines the browser's viewport.
     */
    public void startInstance(String appName, String testName, RectangleSize rectangleSize) {
        eyes.open(driver, appName, testName, rectangleSize);
    }

    /**
     * Returns a unique instance of Eyes per instantiation.
     * @return Returns the Eyes instance.
     */
    public Eyes getEyes() {
        return eyes;
    }

    /**
     * Closes the current instance of Eyes.
     * This method needs to be called everytime you conclude you UI tests
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
