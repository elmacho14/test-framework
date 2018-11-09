package utilities;

import driversession.Instance;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ESOLogin extends Instance {

    private static By esoTitle = By.id("signOnTitle");

    public static void waitUntilUserIsLoggedIn() {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(esoTitle));
        boolean flag = false;

        int n = JOptionPane.showConfirmDialog(null,
                "Login using your ESO credentials. Press OK once done. CANCEL to quit session.", "ESO Login",
                JOptionPane.OK_CANCEL_OPTION);

        while (true) {
            if (n == JOptionPane.OK_OPTION) {
                flag = true;
                break;
            } else if (n == JOptionPane.CANCEL_OPTION) {
                quitDriverSession();
                break;
            }
        }
        if (flag) {
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
    }
}
