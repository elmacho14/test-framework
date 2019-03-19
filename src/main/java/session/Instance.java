package session;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class Instance {

    private WebDriver driver;
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private ChromeOptions chromeOptions = new ChromeOptions();
    private FirefoxOptions firefoxOptions = new FirefoxOptions();
    private FirefoxProfile firefoxProfile = new FirefoxProfile();

    /**
     * Passes by reference the DesiredCapabilities object
     * @return Returns the DesiredCapabilities object
     */
    public DesiredCapabilities getDesiredCapabilitiesInstance() {
        return capabilities;
    }

    /**
     * Passes by reference the ChromeOptions object
     * @return Returns the ChromeOptions object
     */
    public ChromeOptions getChromeOptionsInstance() {
        return chromeOptions;
    }

    /**
     * Passes by reference the FirefoxOptions object
     * @return Returns the FirefoxOptions object
     */
    public FirefoxOptions getFirefoxOptionsInstance() {
        return firefoxOptions;
    }

    /**
     * Passes by reference the FirefoxProfile object
     * @return Returns the FirefoxProfile object
     */
    public FirefoxProfile getFirefoxProfileInstance() {
        return firefoxProfile;
    }

    /**
     * Passes by reference the WebDriver object
     * @return Returns the WebDriver object
     */
    public WebDriver getDriver() {
        return driver;
    }

    public void createChromeSessionAndNavigateTo(String url, boolean maxWindow) {
        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/main/resources/drivers/chromedriver-v2.44.exe"))
                .usingAnyFreePort()
                .build();

        chromeOptions.merge(capabilities);

        driver = new ChromeDriver(chromeDriverService, chromeOptions);
        maxWindow(maxWindow);
        driver.get(url);
    }

    public void createFirefoxSessionAndNavigateTo(String url, boolean maxWindow) {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver-v0.23.0.exe");
        firefoxProfile.setAcceptUntrustedCertificates(true);

        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.merge(capabilities);

        driver = new FirefoxDriver(firefoxOptions);
        maxWindow(maxWindow);
        driver.get(url);
    }

    public void quitDriverSession() {
        driver.quit();
    }

    private void maxWindow(boolean maxWindow) {
        if (maxWindow) {
            driver.manage().window().maximize();
        }
    }
}
