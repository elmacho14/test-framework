package pageobjects.pageclasses;

import driversession.Instance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.RandomNumber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristian.g.maglasang on 6/30/2017.
 */
public class Registration extends Instance {

    {
        wait = new WebDriverWait(driver, 10);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @FindBy(id = "CareersRegistration_EmailAddress") private WebElement emailAddressField;
    @FindBy (id = "CareersRegistration_Password") private WebElement passwordField;
    @FindBy (id = "CareersRegistration_RetypePassword") private WebElement retypePasswordField;

    @FindBy (css = "div[aria-labelledby='ddBoxRegistrationSalutation-label']") private WebElement salutationDropdown;
    @FindBy (css = "div[aria-labelledby='ddBoxRegistrationSalutation-label'] > div.dropdown-menu") private WebElement salutationDropdownMenu;
    @FindAll(@FindBy (css = "#ddBoxRegistrationSalutation-ulid > li")) private List<WebElement> salutationDropdownMenuOptions;

    @FindBy (id = "CareerRegistration_FirstName") private WebElement firstNameField;
    @FindBy (id = "CareerRegistration_LastName") private WebElement lastNameField;

    @FindBy (css = "div[aria-labelledby='ddBoxRegistrationCountryRegion-label']") private WebElement countryRegionDropdown;
    @FindBy (css = "div[aria-labelledby='ddBoxRegistrationCountryRegion-label'] > div.dropdown-menu") private WebElement countryRegionDropdownMenu;
    @FindAll (@FindBy (css = "#ddBoxRegistrationCountryRegion-ulid > li")) private List<WebElement> countryRegionDropdownMenuOptions;

    @FindBy (css = "button[data-id='ddBoxRegistrationStateProvince']") private WebElement validateField;
    @FindBy (css = "div[aria-labelledby='ddBoxRegistrationStateProvince-label']") private WebElement stateProvinceDropdown;
    @FindBy (css = "div[aria-labelledby='ddBoxRegistrationStateProvince-label'] > div.dropdown-menu") private WebElement stateProvinceDropdownMenu;
    @FindAll (@FindBy (css = "#ddBoxRegistrationStateProvince-ulid > li")) private List<WebElement> stateProvinceDropdownMenuOptions;

    @FindBy (id = "CareerRegistration_City") private WebElement cityField;

    @FindBy (id = "chkJobAlert") private WebElement subscribeToEmailAlertsTextbox;

    @FindBy (css = "div[aria-labelledby='ddlPreferredCountryRegion-label']") private WebElement jobPreferenceCountryDropdown;
    @FindBy (css = "div[aria-labelledby='ddlPreferredCountryRegion-label'] > div.dropdown-menu") private WebElement jobPreferenceCountryDropdownMenu;
    @FindAll (@FindBy (css = "#ddlPreferredCountryRegion-ulid > li")) private List<WebElement> jobPreferenceCountryDropdownMenuOptions;

    @FindBy (css = "div[aria-labelledby='ddPreferredExperience-label']") private WebElement jobPreferenceExperienceDropdown;
    @FindBy (css = "div[aria-labelledby='ddPreferredExperience-label'] > div.dropdown-menu") private WebElement jobPreferenceExperienceDropdownMenu;
    @FindAll (@FindBy (css = "#ddPreferredExperience-ulid > li")) private List<WebElement> jobPreferenceExperienceDropdownMenuOptions;

    @FindBy (id = "RegCitiesLink") private WebElement jobPreferenceSelectCitiesLink;

    @FindBy (css = "button[data-id='ddBoxRegistrationCareersSkill'][title='Select']") private WebElement jobPreferenceCareersSkillDropdown;
    @FindBy (css = "div[aria-labelledby='ddBoxRegistrationCareersSkill-label'] > div.dropdown-menu") private WebElement jobPreferenceCareersSkillDropdownMenu;
    @FindAll (@FindBy (css = "#ddBoxRegistrationCareersSkill-ulid > li")) private List<WebElement> jobPreferenceCareersSkillDropdownMenuOptions;

    @FindBy (id = "RegIndustryLink") private WebElement jobPreferenceSelectIndustriesLink;
    @FindAll(@FindBy (css = "#ModalRegIndustries input")) private List<WebElement> jobPreferenceIndustriesOptions;
    @FindBy (id = "btnIndustryUpdate") private WebElement jobPreferenceIndustriesCloseButton;

    @FindBy (id = "chkOpt") private WebElement stayConnectedCheckbox;

    @FindBy (id = "btnRegister") private WebElement registerButton;

    By stayConnectedCheckboxAttribute = By.id("chkOpt");

    public Registration() {
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void enterEmailAddress(String emailAddress) {
        emailAddressField.sendKeys(emailAddress);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void retypePassword(String password) {
        retypePasswordField.sendKeys(password);
    }

    public void selectSalutation() {
        salutationDropdown.click();
        if (salutationDropdownMenu.isDisplayed()) {
            int randomNumber = RandomNumber.generateRandomNumber(salutationDropdownMenuOptions);
            if (randomNumber == 0) {
                randomNumber++;
            }
            salutationDropdownMenuOptions.get(randomNumber).click();
        }
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void selectCountry() {
        countryRegionDropdown.click();
        if (countryRegionDropdownMenu.isDisplayed()) {
            int randomNumber = RandomNumber.generateRandomNumber(countryRegionDropdownMenuOptions);
            if (randomNumber == 0) {
                randomNumber++;
            }
            countryRegionDropdownMenuOptions.get(randomNumber).click();
        }
    }

    public void selectProvince() {
        if (validateField.getAttribute("title").equals("Not Applicable")) {
            // SKIP
        }
        else {
            stateProvinceDropdown.click();
            if (stateProvinceDropdownMenu.isDisplayed()) {
                int randomNumber = RandomNumber.generateRandomNumber(stateProvinceDropdownMenuOptions);
                if (randomNumber == 0) {
                    randomNumber++;
                }
                stateProvinceDropdownMenuOptions.get(randomNumber).click();
            }
        }
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void clickJobAlertsCheckbox(boolean subscribe) {
        if (subscribe) {
            subscribeToEmailAlertsTextbox.click();
        }
    }

    public void selectJobPreferenceCountry() {
        jobPreferenceCountryDropdown.click();
        if (jobPreferenceCountryDropdownMenu.isDisplayed()) {
            int randomNumber = RandomNumber.generateRandomNumber(jobPreferenceCountryDropdownMenuOptions);
            if (randomNumber == 0) {
                randomNumber++;
            }
            jobPreferenceCountryDropdownMenuOptions.get(randomNumber).click();
        }
    }

    public void selectExperience() {
        jobPreferenceExperienceDropdown.click();
        if (jobPreferenceExperienceDropdownMenu.isDisplayed()) {
            int randomNumber = RandomNumber.generateRandomNumber(jobPreferenceExperienceDropdownMenuOptions);
            if (randomNumber == 0) {
                randomNumber++;
            }
            jobPreferenceExperienceDropdownMenuOptions.get(randomNumber).click();
        }
    }

    //TODO: Insert selectCities() here

    public void selectCareersSkill() {
        try {
            jsExecutor.executeScript("arguments[0].click();", jobPreferenceCareersSkillDropdown);
            //jobPreferenceCareersSkillDropdown.click();
            if (jobPreferenceCareersSkillDropdownMenu.isDisplayed()) {
                int randomNumber = RandomNumber.generateRandomNumber(jobPreferenceCareersSkillDropdownMenuOptions);
                if (randomNumber == 0) {
                    randomNumber++;
                }
                jobPreferenceCareersSkillDropdownMenuOptions.get(randomNumber).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectIndustries() {
        jobPreferenceSelectIndustriesLink.click();
        List<Integer> randomNumbers = new ArrayList<>();
        int flag = 0;

        do {
            int randomNumber = RandomNumber.generateRandomNumber(jobPreferenceIndustriesOptions);
            if (!randomNumbers.contains(randomNumber)) {
                randomNumbers.add(randomNumber);
                jobPreferenceIndustriesOptions.get(randomNumber).click();
                flag ++;
            }

            if (flag == 3) {
                break;
            }
        } while (true);

        jobPreferenceIndustriesCloseButton.click();
    }

    public void clickStayConnectCheckbox(boolean stayConnected) {
        wait.until(ExpectedConditions.elementToBeClickable(stayConnectedCheckboxAttribute));
        if (stayConnected){
            stayConnectedCheckbox.click();
        }
    }

    public void enterCaptcha() {
        while (true) {
            JFrame frame = new JFrame();
            int confirmation = JOptionPane.showConfirmDialog(frame,
                    "Enter CAPTCHA text. Click Yes once done.");
            if (confirmation == JOptionPane.YES_OPTION) {
                break;
            }
        }
        //TODO: Need to handle valid and invalid inputs to Captcha
    }

    public RegistrationConfirmation clickRegisterButton() {
        registerButton.click();
        return new RegistrationConfirmation();
    }
}