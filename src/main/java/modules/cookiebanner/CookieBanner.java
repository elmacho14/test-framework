package modules.cookiebanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CookieBanner {

    private WebDriverWait wait;
    private WebDriver driver;

    private By moduleLocator = By.cssSelector("div.optanon-alert-box-wrapper  ");

    @FindBy(css = "div.optanon-alert-box-body > p") private WebElement cookieStatement;
    @FindBy(css = "div.optanon-alert-box-body > p a:nth-of-type(1)") private WebElement privacyPolicyLink;
    @FindBy(css = "div.optanon-alert-box-body > p a:nth-of-type(2)']") private WebElement cookiePolicyLink;
    @FindBy(css = "a.optanon-toggle-display") private WebElement cookieSettings;
    @FindBy(css = "a.optanon-allow-all") private WebElement acceptCookies;
    @FindBy(css = "a.optanon-alert-box-close") private WebElement closeBanner;

    public CookieBanner(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(
                getModuleContainer(),
                10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    private WebElement getModuleContainer() {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(moduleLocator)));
    }

    public void clickPrivacyPolicyLink() {
        privacyPolicyLink.click();
    }

    public void clickCookiePolicyLink() {
        cookiePolicyLink.click();
    }

    public PrivacyPreferenceCenter clickCookieSettings() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieSettings)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PrivacyPreferenceCenter(driver);
    }

    public void clickAcceptCookies() {
        acceptCookies.click();
    }

    public void closeBanner() {
        closeBanner.click();
    }
}
