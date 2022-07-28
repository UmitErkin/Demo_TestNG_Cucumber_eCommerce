package magentodemoautomation.backend.customermodule;

import magentodemoautomation.utility.FunctionPage;
import magentodemoautomation.utility.Log4j;
import magentodemoautomation.utility.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerManagerLoginPage {
    WebDriver driver;
    FunctionPage functionPage;
    String configFile = "config-qa.properties";

    public CustomerManagerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionPage = new FunctionPage(driver);

    }

    @FindBy(id = "username")
    WebElement usernameField;
    @FindBy(css = "#login")
    WebElement passwordField;
    @FindBy(css = "[type='submit']")
    WebElement loginButton;
    @FindBy(css = ".link-logout")
    WebElement logOutButton;


    public boolean verifyLoginPageOpened() {
        functionPage.waitForElement(loginButton);
        if (loginButton.isDisplayed()) {
            Log4j.info("Login Page Opened Successfully");
            return true;
        } else {
            Log4j.error("Login Page Opened Fail");
            return false;
        }
    }

    public boolean login() {
        functionPage.waitForElement(usernameField);
        usernameField.click();
        usernameField.sendKeys(TestBase.readFromConfigProperties(configFile,"customer_username"));
        functionPage.waitForElement(passwordField);
        passwordField.click();
        passwordField.sendKeys(TestBase.readFromConfigProperties(configFile,"customer_password"));
        functionPage.waitForElement(loginButton);
        loginButton.click();
        functionPage.waitForElement(logOutButton);
        if (logOutButton.isDisplayed()){
            return true;
        }else
            return false;

    }
}
