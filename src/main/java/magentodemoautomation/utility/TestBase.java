package magentodemoautomation.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;
    public static String browserName="chrome";

    public void browserSetUp(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        JenkinsBrowserSetup jenkinsBrowserSetup = new JenkinsBrowserSetup();
        boolean useHeadless = jenkinsBrowserSetup.serHeadlessModelIfLinux(chromeOptions);
        if (!useHeadless) {
            if ((driver) == null) {
                if (browserName.equalsIgnoreCase("Chrome")) {
                    WebDriverManager.chromedriver().setup();
                } else if (browserName.equalsIgnoreCase("FireFox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
            }
        }
        driver=new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(url);
    }
    public void closeBrowser(){
        //driver.close();
        driver.quit();
    }
    public static String readFromConfigProperties(String fileName, String key) {
        Properties properties=new Properties();
        String workingDirectory=System.getProperty("user.dir");
        String value;
        try {
            properties.load(new FileInputStream(workingDirectory+ File.separator+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        value=properties.getProperty(key);
        System.out.println(String.format("%s=%s",key,value));
        return value;
    }
}
