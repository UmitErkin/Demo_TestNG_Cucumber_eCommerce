package runner.testng;

import magentodemoautomation.backend.customermodule.AddNewCustomerPage;
import magentodemoautomation.backend.customermodule.CustomerDashboardPage;
import magentodemoautomation.backend.customermodule.CustomerManagerLoginPage;
import magentodemoautomation.utility.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestNGResultListener.class)
public class CustomerRunner extends TestBase {
    String configFile = "config-qa.properties";
    static CustomerManagerLoginPage customerManagerLoginPage;
    AddNewCustomerPage addNewCustomerPage;
    CustomerDashboardPage customerDashboardPage;
    FunctionPage functionPage;
    TestDataHolder info = new TestDataHolder();


    @BeforeSuite()
    public void setUp(ITestContext context) {
        browserSetUp(readFromConfigProperties(configFile, "backend_url"));
        Log4j.startTestCase("Magento_Customer_Module_Automation_Test_Start");
        context.setAttribute("driver", driver);
        customerManagerLoginPage = new CustomerManagerLoginPage(driver);
        TestDataHolder info = new TestDataHolder();
        addNewCustomerPage=new AddNewCustomerPage(driver);
        customerDashboardPage=new CustomerDashboardPage(driver);
        functionPage=new FunctionPage(driver);
    }
    @BeforeMethod
    public void backToDashboard(){
        customerDashboardPage.clickOnMagentoLogoBackDashboard();
    }
    @Test(description = "Customer Manager can add a new customer ", dataProvider = "NewCustomerInfo")
    public void addCustomer(String firstName, String lastName, String emailAddress) {
        customerDashboardPage.clickOnAddNewCustomerButton();
        addNewCustomerPage.addNewCustomer(firstName,lastName,emailAddress);
        Assert.assertTrue(addNewCustomerPage.verifyAddNewCustomer());
    }



    @DataProvider
    public Object[][] NewCustomerInfo() {

        info.setCustomerFirstName(functionPage.generateFirstName());
        info.setCustomerLastName(functionPage.generateLastName());
        info.setCustomerEmailAddress(functionPage.generateEmail());
        System.out.println("in the data first:  " + info.getCustomerFirstName());
        Object[][] customerInfo = new Object[][]{
                {"Team1" + info.getCustomerFirstName(), info.getCustomerLastName(),
                        info.getCustomerEmailAddress()}
        };
        return customerInfo;
    }
    @DataProvider
    public Object[][] customerFirstName() {
        System.out.println("in the second Data:  " + info.getCustomerFirstName());
        Object[][] customerName = new Object[][]{
                {"Team1" + info.getCustomerFirstName(),functionPage.generateMiddleName()}
        };
        return customerName;
    }

    @DataProvider
    public Object[][] customerEmail() {
        Object[][] customerEmail = new Object[][]{
                {info.getCustomerEmailAddress()}
        };
        return customerEmail;
    }
}