package pageobjects.commonmodules;


import driversession.Instance;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by kristian.g.maglasang on 7/21/2017.
 */
public class TypeToSearch extends Instance {

    {
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(id = "search-label-type") private WebElement typeToSearch;
    @FindBy (id = "keywords") private WebElement typeToSearchTextField;
    @FindBy (id = "recommended-contents-section") private WebElement recommendedContentsSection;
    @FindBy (id = "recommended-content") private WebElement recommendedContent;
    @FindBy (id = "close-smart-search") private WebElement typeToSearchCloseButton;
    @FindAll(@FindBy (css = "div#recommended-content div[id*='search-results']")) private List<WebElement> searchResults;

    public TypeToSearch() {
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    public void clickTypeToSearch() {
        typeToSearch.click();
    }

    public void searchForKeyword(String keyword) {
        try {
            wait.until(ExpectedConditions.visibilityOf(typeToSearchTextField));
            typeToSearchTextField.sendKeys(keyword);
            wait.until(ExpectedConditions.visibilityOf(recommendedContentsSection));
            wait.until(ExpectedConditions.visibilityOf(recommendedContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getResultsCount() {
        if (recommendedContent.isDisplayed()) {
            return searchResults.size();
        }
        else {
            return 0;
        }
    }

    public void clickTypeToSearchCloseButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(typeToSearchCloseButton));
            typeToSearchCloseButton.click();
        } catch (Exception e) {
            // TODO
        }
    }
}
