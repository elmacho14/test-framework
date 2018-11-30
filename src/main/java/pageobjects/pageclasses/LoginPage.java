package pageobjects.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriverWait wait;
    private WebDriver driver;

    @FindBy(css = "iframe[src='/Authentication/LogOnModal']")
    private WebElement iframe;
    @FindBy(id = "ContentPlaceHolder1_UsernameTextBox")
    private WebElement emailAddressTextField;
    @FindBy(id = "ContentPlaceHolder1_PasswordTextBox")
    private WebElement passwordTextField;
    @FindBy(id = "ForgotPasswordLabel")
    private WebElement forgotPasswordLink;
    @FindBy(id = "ContentPlaceHolder1_SubmitButton")
    private WebElement signInButton;
    @FindBy(css = "a[data-linktype = 'careers parallel-tests']")
    private WebElement registerLink;
    @FindBy(id = "signInClose")
    private WebElement signInCloseButton;

    public LoginPage(WebDriver driver) {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
        wait = new WebDriverWait(driver, 10);
        this.driver = driver;
    }

    public void switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
    }

    public LoginPage enterEmailAddress(String emailAddress) {
        emailAddressTextField.sendKeys(emailAddress);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordTextField.sendKeys(password);
        return this;
    }

    public ForgotMyPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new ForgotMyPasswordPage();
    }

    @SuppressWarnings("unchecked")
    public <T> T clickSignInButton(Class<T> pageClass) {
        signInButton.click();
        return (T) pageClass;
    }

    public Registration clickRegisterButton() {
        registerLink.click();
        return new Registration(driver);
    }

    public void clickSignInCloseButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signInCloseButton));
            signInCloseButton.click();
        } catch (Exception e) {
            // TODO
        }
    }
}
