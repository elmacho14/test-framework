package utilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by kristian.g.maglasang on 8/24/2016.
 */
public class Highlight {

    public static void highlightElement(WebDriver driver, WebElement element, int duration) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String original_style = element.getAttribute("style");

        //js.executeScript("arguments[0].scrollIntoView();", element);

        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                "border: 6px solid red; border-style: solid;");

        if (duration > 0) {
            Wait.waitFor(duration);
            js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                    original_style);
        }
    }
}
