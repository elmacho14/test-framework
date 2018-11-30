package pageobjects.modules.cookiebanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.pageclasses.CookiesPolicy;
import pageobjects.pageclasses.PrivacyPolicy;


public class CookieBanner {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy(css = "div.optanon-alert-box-wrapper  ")
    public WebElement cookieBanner;
    @FindBy(css = "div.optanon-alert-box-body > p")
    public WebElement cookieStatement;
    @FindBy(css = "div.optanon-alert-box-body a[href*='privacy-policy']")
    public WebElement privacyPolicyLink;
    @FindBy(css = "div.optanon-alert-box-body a[href*='company-cookies-similar-technology']")
    public WebElement cookiePolicyLink;
    @FindBy(css = "a.optanon-toggle-display")
    public WebElement cookieSettings;
    @FindBy(css = "a.optanon-allow-all")
    public WebElement acceptCookies;
    @FindBy(css = "a[title='Close Banner']")
    public WebElement closeBanner;

    public CookieBanner(WebDriver driver) {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
        wait = new WebDriverWait(driver, 10);
        this.driver = driver;
    }

    public PrivacyPolicy clickPrivacyPolicyLink() {
        privacyPolicyLink.click();
        return new PrivacyPolicy();
    }

    public CookiesPolicy clickCookiePolicyLink() {
        cookiePolicyLink.click();
        return new CookiesPolicy();
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
