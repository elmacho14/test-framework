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

public class AdjacentTilesModule {

    private WebDriver driver;
    private Actions actions;

    private By moduleLocator = By.cssSelector("section.container-block > .col-sm-12 > .col-sm-12 > div.adjacent-tiles-container");
    private By columnLocator = By.cssSelector("div[class='col-sm-12'][style='display: block;']");
    private By rowLocator = By.cssSelector("div.articles");

    @FindBy (css = "p[class*='cardTitle'] > a") private WebElement header;
    @FindBy (css = "h2[class*='headline'] > a") private WebElement title;
    @FindBy (css = "p.subHeadline") private WebElement description;
    @FindBy (css = "a[role='button'][title='Shares']") private WebElement share;
    @FindBy (css = "div[class*='cta-container'] > a") private WebElement cta;
    //@FindBy (css = "") private WebElement ctaArrow;

    public AdjacentTilesModule(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    /**
     * Selects a module based on the block it belongs to, column, and row.
     * The column and row serves like a coordinate, e.g. (x, y) -> (row, column)
     * All integer arguments follows the index-based system.
     * @param blockIndex The block to which the list of modules belongs to.
     * @param column The column index within the block from which the module is located at.
     * @param row The row index within the column.
     */
    public void selectModule(int blockIndex, int column, int row) {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(
                getTile(getModuleContainer().get(blockIndex), column, row), 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    /**
     * Gets the block container from which all the modules are located at.
     * @return Returns a list of all the block containers within the page.
     */
    private List<WebElement> getModuleContainer() {
        return driver.findElements(moduleLocator);
    }

    /**
     * Gets the tile/module based on the block from which it is located, and the row and column from that block.
     * @param module This is the block from which the target module will be located from.
     * @param column The column index identifies from which column you want to look into to get your module.
     * @param row The row index identifies the exact row the module will be located at from a particular column.
     * @return Returns the target module.
     */
    private WebElement getTile(WebElement module, int column, int row) {
        return module.findElements(columnLocator).get(column).findElements(rowLocator).get(row);
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
            Highlight.highlightElement(driver, module, durationPerContainer);
        });
    }

    public void clickHeader() {
        header.click();
    }

    public void clickTitle() {
        title.click();
    }

    public void clickCTA() {
        cta.click();
    }

    public void clickCTAArrow() {
        //ctaArrow.click();
    }

    public void clickShare() {
        share.click();
    }
}

