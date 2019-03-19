package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareersSignInModule {

    private WebDriverWait wait;
    private WebDriver driver;

    private By frameLocator = By.id("ADFSLogOnModal");
    private By moduleLocator = By.id("ADFSLoginPage");

    @FindBy(id = "ContentPlaceHolder1_UsernameTextBox") private WebElement emailAddressTextField;
    @FindBy(id = "ContentPlaceHolder1_PasswordTextBox") private WebElement passwordTextField;
    @FindBy(css = "div.checkbox.Remember input") private WebElement checkbox;
    @FindBy(id = "ForgotPasswordLabel") private WebElement forgotPasswordLink;
    @FindBy(id = "ContentPlaceHolder1_SubmitButton") private WebElement cta;

    public CareersSignInModule(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(
                getModuleContainer(),
                10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    private WebElement getModuleContainer() {
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(frameLocator)))
                .findElement(moduleLocator);
    }

    public void enterEmailAddress(String emailAddress) {
        emailAddressTextField.sendKeys(emailAddress);
    }

    public void enterPassword(String password) {
        passwordTextField.sendKeys(password);
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    public void clickRememberMe() {
        checkbox.click();
    }

    public void clickSignInButton() {
        cta.click();
    }
}
