package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Highlight;

import java.util.List;

public class FeaturedSectionModule {

    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;

    private By moduleLocator = By.cssSelector("div[class*='featured-section-module-carousel component interactive-fsm']");
    private By carouselIndicatorsLocator = By.cssSelector("ol.carousel-indicators > li");
    private By cardCarouselLocator = By.cssSelector("div.carousel-inner > div[class*='active']");
    private By cardItemsLocator = By.cssSelector("div.featured-section-module.module-article.component");

    @FindBy (css = "div.media") private WebElement image;
    @FindBy (css = "a > h4") private WebElement title;
    @FindBy (css = "a[aria-label='Shares']") private WebElement share;
    @FindBy (css = "div.module-body") private WebElement description;
    @FindBy (tagName = "button") private WebElement cta;

    public FeaturedSectionModule(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * Selects a specific module from a list of all the modules in the page
     * based on the input integer with the first module at index 0.
     * All integer arguments follows the index-based system.
     * Also takes in a pagination index to give the user the option to choose
     * from which carousel he/she wishes to interact with.
     * Then from that carousel, the card index identifies the exact card
     * from the carousel.
     * @param moduleIndex The module you wish to interact with.
     * @param paginationIndex The exact carousel from that module.
     * @param cardIndex The card from the chosen carousel
     */
    public void selectModule(int moduleIndex, int paginationIndex, int cardIndex) {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(
                getCard(getModuleContainer().get(moduleIndex), paginationIndex, cardIndex),
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
     * Gets the exact card based on the module chosen and the exact carousel.
     * @param module The module from which the card is located.
     * @param paginationIndex The pagination index so you can choose to interact with any carousel.
     * @param cardIndex The particular card from your chosen carousel.
     * @return Returns the identified card
     */
    private WebElement getCard(WebElement module, int paginationIndex, int cardIndex) {
        clickPagination(module, paginationIndex);
        return module.findElement(cardCarouselLocator).findElements(cardItemsLocator).get(cardIndex);
    }

    /**
     * This method enables you to choose your card from whatever carousel from a FSM.
     * If non exists, a message will be printed to the console and life goes on as usual.
     * @param module The module your card is located at.
     * @param index The exact pagination dot you wish to click.
     */
    private void clickPagination(WebElement module, int index) {
        try {
            WebElement carouselIndicator = module.findElements(carouselIndicatorsLocator).get(index);
            carouselIndicator.click();
            wait.until(ExpectedConditions.attributeToBe(carouselIndicator, "class", "active"));
        } catch (Exception e) {
            System.out.println("No carousel indicators found. Not to worry, you may still proceed =)");
        }
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

    public void clickImage() {
        image.click();
    }

    public void clickTitle() {
        title.click();
    }

    public void clickShare() {
        share.click();
    }

    public void clickCTA() {
        cta.click();
    }

    public void clickViewAll() {
        // TODO View all is currently outside the scope of PageFactory.
    }
}
