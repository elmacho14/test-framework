package utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class OpenInNewTab {

    private static String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

    public static void openInNewTab(WebElement element) {
        element.sendKeys(openInNewTab);
    }
}
