package pageobjects.pageclasses;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kristian.g.maglasang on 8/15/2017.
 */
public class JobSearch {

    private JavascriptExecutor jsExecutor;
    private WebDriverWait wait;

    @FindBy(id = "search-input-txt")
    private WebElement searchField;
    @FindBy(id = "search-btn")
    private WebElement searchButton;

    public JobSearch(WebDriver driver) {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 20);
    }

    public void enterKeyword(String keyword) {
        searchField.sendKeys(keyword);
    }

    public void clickFindButton() {
        searchButton.click();
    }

}
