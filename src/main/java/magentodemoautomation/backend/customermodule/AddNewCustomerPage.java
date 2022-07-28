package magentodemoautomation.backend.customermodule;

import magentodemoautomation.utility.FunctionPage;
import magentodemoautomation.utility.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCustomerPage extends TestBase {
    WebDriver driver;
    FunctionPage functionPage;
    String configFile = "config-qa.properties";
    public AddNewCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionPage=new FunctionPage(driver);
    }
    @FindBy(id="_accountfirstname")
    WebElement firstNameField;

    @FindBy(id = "_accountlastname")
    WebElement lastNameField;

    @FindBy(id="_accountemail")
    WebElement emailField;

    @FindBy(id = "_accountpassword")
    WebElement managementPassword;

    @FindBy(xpath = " //div[@id='anchor-content']//p/button[3]")
    WebElement saveButton;
    @FindBy(xpath = "//*[text()='The customer has been saved.']")
    WebElement successfullyMessages;

    public void addNewCustomer(String firstName,String lastName,String emailAddress){
        functionPage.waitForElement(firstNameField);
        firstNameField.click();
        firstNameField.sendKeys(firstName);
        functionPage.waitForElement(lastNameField);
        lastNameField.click();
        lastNameField.sendKeys(lastName);
        functionPage.waitForElement(emailField);
        emailField.click();
        emailField.sendKeys(emailAddress);
        functionPage.waitForElement(managementPassword);
        managementPassword.click();
        managementPassword.sendKeys( readFromConfigProperties(configFile,"customer_password"));
        functionPage.waitForElement(saveButton);
        saveButton.click();
    }
    public boolean verifyAddNewCustomer(){
        functionPage.waitForElement(successfullyMessages);
        if (successfullyMessages.isDisplayed()){
            return true;
        }else
            return false;

    }

}
