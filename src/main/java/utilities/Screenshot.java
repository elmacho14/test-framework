package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    private WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public String captureScreenshot(String screenshotName) throws IOException {
        // Note: In Chrome, the screenshot is taken only on the visible portion
        // of the page, not the entire page.
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshots/" + screenshotName + ".png";
        File destination = new File(screenshotPath);
        FileUtils.copyFile(srcFile, destination);
        return screenshotPath;
    }
}
