package magentodemoautomation.utility;

import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FunctionPage {
    WebDriver driver;
    static  String  configFile="config-qa.properties";
    public static int timeout=Integer.parseInt(TestBase.readFromConfigProperties(configFile,"timeout"));
    Faker faker=new Faker();

    public FunctionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForElement(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void implicitlyWait(){
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
    public  void fluentWait(WebElement element){
        Wait<WebDriver> wait=new FluentWait<>(driver)
                //     Wait<WebDriver> wait=new FluentWait<>(driver)
                .withTimeout(20,TimeUnit.SECONDS)
                .pollingEvery(100,TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void sleep(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String generateFirstName(){
        String firstName=faker.name().firstName();
        return firstName;
    }
    public String generateLastName(){
        String lastName=faker.name().lastName();
        return lastName;
    }
    public String generateEmail(){
        String email=faker.internet().emailAddress();
        return email;
    }
    public String generateCityName(){
        String cityName=faker.address().city();
        return cityName;
    }

    public String generateStreetName(){
        String streetName=faker.address().streetName();
        return streetName;
    }


    public String generateZipCode(){
        String zipCode=faker.address().zipCode();
        return zipCode;
    }

    public String  generateTelephoneNumber(){
        String telephoneNumber=faker.phoneNumber().cellPhone();
        return telephoneNumber;
    }


    public  String generateMiddleName(){
        Faker faker=new Faker();
        String  middleName=faker.name().username();
        return middleName;
    }


    public void waitForAlertPresent(){
        WebDriverWait wai=new WebDriverWait(driver,timeout);
        wai.until(ExpectedConditions.alertIsPresent());
    }

    //alertAccept
    public void alertAccept(){
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageName(String pageName) {
        WebElement name = driver.findElement(By.xpath("//*[contains(text(),'" + pageName + "')]"));
        return name.getText();
    }

    public void hoverToClick(String text) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"))).click().perform();
    }

    public List<String> getElementsText(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elements) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public  int getSectionCount(By locator) {
        return driver.findElements(locator).size();
    }
}
