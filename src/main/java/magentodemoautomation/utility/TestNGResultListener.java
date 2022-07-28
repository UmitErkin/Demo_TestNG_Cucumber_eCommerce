package magentodemoautomation.utility;


import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGResultListener implements ITestListener {
    ScreenshotUtility screenshotUtility=new ScreenshotUtility();

    public void onTestSuccess(ITestResult result){
        Log4j.info(result.getMethod().getMethodName()+"  Passed");

    }
    public void onTestFailure(ITestResult result) {
        Log4j.error(result.getMethod().getMethodName()+"    Failed");

        screenshotUtility.takeScreenshot("image",result.getMethod().getMethodName(),
                (WebDriver) result.getTestContext().getAttribute("driver"));
    }
}
