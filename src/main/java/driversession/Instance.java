package driversession;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.util.List;

public abstract class Instance {

    public static WebDriver driver;
    public static WebDriverWait wait;
    protected static JavascriptExecutor jsExecutor;
    protected static Actions actions;
    protected static AjaxElementLocatorFactory ajaxElementLocatorFactory;

    protected static List<String> windowHandles;

    protected static DesiredCapabilities capabilities = new DesiredCapabilities();

    static ChromeOptions chromeOptions = new ChromeOptions();
    static ChromeDriverService chromeDriverService;

    static FirefoxOptions firefoxOptions = new FirefoxOptions();
    static FirefoxProfile firefoxProfile = new FirefoxProfile();

    public static void createChromeSessionAndNavigateTo(String url, boolean maxWindow) {
        chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/main/resources/drivers/chromedriver-v2.43.exe"))
                .usingAnyFreePort()
                .build();

        chromeOptions.merge(capabilities);

        driver = new ChromeDriver(chromeDriverService, chromeOptions);
        maxWindow(maxWindow);
        driver.get(url);
    }

    public static void createFirefoxSessionAndNavigateTo(String url, boolean maxWindow) {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver-v0.23.0.exe");
        firefoxProfile.setAcceptUntrustedCertificates(true);

        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.merge(capabilities);

        driver = new FirefoxDriver(firefoxOptions);
        maxWindow(maxWindow);
        driver.get(url);
    }

    public static void createIESessionAndNavigateTo(String url, boolean maxWindow) {
    }

    public static void createSafariSessionAndNavigateTo(String url, boolean maxWindow) {
    }

    protected static void quitDriverSession() {
        driver.quit();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    private static void maxWindow(boolean maxWindow) {
        if (maxWindow) {
            driver.manage().window().maximize();
        }
    }
}
