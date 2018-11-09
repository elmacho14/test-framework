package pageobjects.pageclasses;


import driversession.Instance;

public class ForgotMyPasswordPage extends Instance {

    public String getPageTitle() {
        return driver.getTitle();
    }
}
