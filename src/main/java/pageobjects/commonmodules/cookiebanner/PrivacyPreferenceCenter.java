package pageobjects.commonmodules.cookiebanner;

import driversession.Instance;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivacyPreferenceCenter extends Instance {

    {
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy (css = "li[title='First Party Analytics Cookies']") public WebElement firstPartyAnalyticsCookies;
    @FindBy (css = "li[title='Performance and Functional Cookies']") public WebElement performanceAndFunctionalCookies;
    @FindBy (css = "li[title='Advertising and Social Media Cookies']") public WebElement advertisingAndSocialMediaCookies;
    @FindBy (linkText = "Allow All") public WebElement allowAllButton;
    @FindBy (linkText = "Save Settings") public WebElement saveSettingsButton;
    @FindBy (css = "#optanon-popup-body-right > h3") public WebElement categoryIndicator;
    @FindBy (css = "#optanon-popup-more-info-bar form > fieldset > p > label") public WebElement statusToggle;

    public PrivacyPreferenceCenter() {
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    /**
     * This is a helper method that makes sure that the script waits until the clicked element's text matches the h3 tag text
     * @param element The category that was clicked
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
