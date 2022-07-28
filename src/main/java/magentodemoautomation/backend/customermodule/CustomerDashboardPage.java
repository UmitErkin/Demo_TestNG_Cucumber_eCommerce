package magentodemoautomation.backend.customermodule;

import magentodemoautomation.utility.FunctionPage;
import magentodemoautomation.utility.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerDashboardPage  extends TestBase {
    WebDriver driver;
    FunctionPage functionPage;
    String configFile = "config-qa.properties";
    String webSite;
    String countryName;
    Actions actions;

    public CustomerDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        functionPage = new FunctionPage(driver);
        actions=new Actions(driver);
    }

    @FindBy(css = "#messages+.content-header>table>tbody>tr>td+td>button")
    WebElement aadNewCustomerButton;
    @FindBy(xpath = "//img[@alt='Magento Logo']")
    WebElement customerMagentoDashboard;
    @FindBy(id = "anchor-content")
    WebElement manageCustomersTable;


    public void clickOnAddNewCustomerButton() {
        functionPage.waitForElement(aadNewCustomerButton);
        aadNewCustomerButton.click();
    }

    public boolean clickOnMagentoLogoBackDashboard() {
        functionPage.waitForElement(customerMagentoDashboard);
        customerMagentoDashboard.click();
        functionPage.waitForElement(manageCustomersTable);
        if (manageCustomersTable.isDisplayed()) {
            return true;
        } else
            return false;
    }

}
