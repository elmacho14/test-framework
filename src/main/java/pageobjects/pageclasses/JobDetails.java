package pageobjects.pageclasses;


import driversession.Instance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.OpenInNewTab;

import java.util.ArrayList;

/**
 * Created by kristian.g.maglasang on 8/15/2017.
 */
public class JobDetails extends Instance {

    {
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public String href;

    @FindBy(css = "span.top-links a:nth-of-type(1)") public WebElement topApplyButton;
    @FindBy (css = "div.bottom-links a:nth-of-type(1)") public WebElement bottomApplyButton;

    @FindBy (css = "input[type='checkbox']") private WebElement privacyStatementCheckbox;

    @FindBy (css = "span.top-links a:nth-of-type(2)") public WebElement topGetReferredButton;
    @FindBy (css = "div.bottom-links a:nth-of-type(2)") public WebElement bottomGetReferredButton;

    @FindBy (css = "span.top-links a:nth-of-type(3)") public WebElement topAbacusSaveJobButton;
    @FindBy (css = "div.bottom-links a:nth-of-type(3)") public WebElement bottomAbacusSaveJobButton;

    @FindBy (css = "span.top-links a:nth-of-type(2)") public WebElement topSaveJobButton;
    @FindBy (css = "div.bottom-links a:nth-of-type(2)") public WebElement bottomSaveJobButton;

    @FindBy (css = "span.top-links > div") public WebElement topShareButton;
    @FindBy (css = "div.bottom-links > div") public WebElement bottomShareButton;


    @FindBy (css = "a.collapse-toggle span:nth-of-type(2)") public WebElement linkJobDescription;
    @FindBy (id = "0-collapsible") public WebElement jobDescription;

    @FindBy (css ="a[href='#1-collapsible']") public WebElement linkBasicQualification;
    @FindBy (id = "1-collapsible") public WebElement basicQualification;

    @FindBy (id = "maincontent-hero") public WebElement pageTitle;
    @FindBy (css = ".job-title > h1") public WebElement jobTitle;
    @FindBy (css = ".job-location.color-black") public WebElement jobLocation;
    @FindBy (css = ".job-number.color-black") public WebElement jobID;

    @FindBy (css = "div[class='display-boxshadow bg-color-white floatcontainer ']") public WebElement blockJobOpenings;



    public JobDetails() {
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    public void clickPrivacyStatementCheckbox() {
        privacyStatementCheckbox.click();
    }

    public void clickTopApplyButton() {
        jsExecutor.executeScript("arguments[0].click();", topApplyButton);
        windowHandles = new ArrayList<>(driver.getWindowHandles());
        href = topApplyButton.getAttribute("href");
    }

    public void clickBottomApplyButton() {
        jsExecutor.executeScript("arguments[0].click();", bottomApplyButton);
        windowHandles = new ArrayList<>(driver.getWindowHandles());
        href = bottomApplyButton.getAttribute("href");
    }

    public void switchToConsentAndAcknowledgementTab() {
        driver.switchTo().window(windowHandles.get(1));
    }

    public void closeConsentAndAcknowledgementTab() {
        driver.switchTo().window(windowHandles.get(1)).close();
        driver.switchTo().window(windowHandles.get(0));
    }

    public void clickGetReferred(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("get-referred-button-abacus")));
        OpenInNewTab.openInNewTab(topGetReferredButton);
        href = topGetReferredButton.getAttribute("href");
    }

    public void clickSaveButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='SAVE THIS JOB'][role='Button']")));
        jsExecutor.executeScript("arguments[0].click();", topSaveJobButton);
    }

    public void expandBasicQualition(){
        jsExecutor.executeScript("arguments[0].click();", linkBasicQualification);
    }

}
