package modules.cookiebanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivacyPreferenceCenter {

    private WebDriverWait wait;
    private WebDriver driver;

    private By moduleLocator = By.id("optanon-popup-wrapper");

    @FindBy(css = "#optanon-menu li:nth-of-type(3)") private WebElement firstPartyAnalyticsCookies;
    @FindBy(css = "#optanon-menu li:nth-of-type(4)") private WebElement performanceAndFunctionalCookies;
    @FindBy(css = "#optanon-menu li:nth-of-type(5)") private WebElement advertisingAndSocialMediaCookies;
    @FindBy(css = "#optanon-popup-bottom > div:nth-of-type(2) > div > a") private WebElement allowAllButton;
    @FindBy(css = "#optanon-popup-bottom > div:nth-of-type(1) > div > a") private WebElement saveSettingsButton;
    @FindBy(css = "#optanon-popup-body-right > h3") private WebElement categoryIndicator;
    @FindBy(css = "#optanon-popup-more-info-bar form > fieldset > p > label") private WebElement statusToggle;

    public PrivacyPreferenceCenter(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(getModuleContainer(), 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    private WebElement getModuleContainer() {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(moduleLocator)));
    }

    /**
     * This is a helper method that makes sure that the script waits until
     * the clicked element's text matches the h3 tag text
     * @param element           The category that was clicked
     * @param categoryIndicator The h3 tag text value
     */
    private void helperMethod(WebElement element, WebElement categoryIndicator) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, categoryIndicator.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickFirstPartyAnalyticsMenuItem() {
        firstPartyAnalyticsCookies.click();
        helperMethod(firstPartyAnalyticsCookies, categoryIndicator);
    }

    public void clickPerformanceAndFunctionalMenuItem() {
        performanceAndFunctionalCookies.click();
        helperMethod(performanceAndFunctionalCookies, categoryIndicator);
    }

    public void clickAdvertisingAndSocialMediaMenuItem() {
        advertisingAndSocialMediaCookies.click();
        helperMethod(advertisingAndSocialMediaCookies, categoryIndicator);
    }

    public void clickStatusToggle() {
        statusToggle.click();
    }

    public void clickAllowAllButton() {
        allowAllButton.click();
    }

    public void clickSaveSettingsButton() {
        saveSettingsButton.click();
    }
}
