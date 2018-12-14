package utilities;

import org.openqa.selenium.WebDriver;

public class PageTestability {

    private WebDriver driver;

    public PageTestability(WebDriver driver) {
        this.driver = driver;
    }

    public int getPageStatusResponseCode(String url) {
        return HttpResponseCode.httpResponseViaGet(url);
    }

    public boolean areRenderingErrorsPresent() {
        return driver.getPageSource().contains("Rendering");
    }

    public boolean areGlassmapperErrorsPresent() {
        return driver.getPageSource().contains("Glassmapper");
    }
}
