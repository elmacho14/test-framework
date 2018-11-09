package pageobjects.pageclasses;


import driversession.Instance;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by kristian.g.maglasang on 7/3/2017.
 */
public class RegistrationConfirmation extends Instance {

    public RegistrationConfirmation() {
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    public String getPageUrl() {
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        return driver.getCurrentUrl();
    }
}
