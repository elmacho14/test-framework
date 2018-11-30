package pageobjects.pageclasses;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by kristian.g.maglasang on 7/3/2017.
 */
public class RegistrationConfirmation {

    private WebDriver driver;

    public RegistrationConfirmation(WebDriver driver) {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
        this.driver = driver;
    }

    public String getPageUrl() {
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        return driver.getCurrentUrl();
    }
}
