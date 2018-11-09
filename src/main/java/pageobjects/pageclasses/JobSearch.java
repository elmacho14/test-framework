package pageobjects.pageclasses;


import driversession.Instance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.RandomNumber;

import java.util.ArrayList;
import java.util.List;

import static utilities.Wait.waitFor;

/**
 * Created by kristian.g.maglasang on 8/15/2017.
 */
public class JobSearch extends Instance {

    {
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 20);
    }

    @FindBy (id = "search-input-txt") private WebElement searchField;
    @FindBy (id = "search-btn") private WebElement searchButton;


    public JobSearch() {
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void enterKeyword(String keyword) {
        searchField.sendKeys(keyword);
    }

    public void clickFindButton() {
        searchButton.click();
    }

}
