package utilities;

import driversession.Instance;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

public class OpenInNewTab extends Instance {

    private static String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

    public static void openInNewTab (WebElement element){
        element.sendKeys(openInNewTab);
    }
}
