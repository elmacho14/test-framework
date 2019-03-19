package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Highlight;

import java.util.List;

public class FeaturedInsightModule {

    private WebDriver driver;
    private Actions actions;

    private By moduleLocator = By.cssSelector("div[class*='featured-insight module']");

    @FindBy (css = "h2.section-title") private WebElement featuredInsightTitle;
    @FindBy (css = "h3 > a") private WebElement articleTitle;
    @FindBy (tagName = "p") private WebElement description;
    @FindBy (css = ".cta-container > a") private WebElement readMore;

    public FeaturedInsightModule(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    /**
     * Selects a specific module from a list of all the modules in the page
     * based on the input integer with the first module at index 0.
     * Call this method first prior to performing any actions (i.e. clicking).
     * @param moduleIndex Takes in an integer, the index of the module to interact with.
     */
    public void selectModule(int moduleIndex) {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(
                getModuleContainer().get(moduleIndex),
                10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    /**
     * Gets all the module containers in the page.
     * @return Returns a WebElement list of all the identified module containers
     */
    private List<WebElement> getModuleContainer() {
        return driver.findElements(moduleLocator);
    }

    /**
     * Call this method to give you an idea about how many modules are in the page
     * @return Returns an integer, the total module count in the page
     */
    public int getModuleContainerCount() {
        return getModuleContainer().size();
    }

    /**
     * Call this method if you need help identifying all the modules in the page
     * @param durationPerContainer Specifies the highlight duration (how long the module is highlighted)
     */
    public void highlightEachContainer(int durationPerContainer) {
        getModuleContainer().forEach(module -> {
            actions.moveToElement(module).build().perform();
            Highlight.highlightElement(driver, module, durationPerContainer
            );
        });
    }

    public void clickArticleTitle() {
        articleTitle.click();
    }

    public void clickCTA() {
        readMore.click();
    }
}
