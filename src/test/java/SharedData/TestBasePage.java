package SharedData;

import Logger.LoggerUtility;
import SharedData.Browser.BrowserFactory;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBasePage {

    private WebDriver driver;
    public String testName;

    @BeforeMethod
    public void initialiseBrowser() {

        ThreadContext.put(
                "testName",
                this.getClass().getSimpleName()
        );

        testName = this.getClass().getSimpleName();

        LoggerUtility.info(
                "Aici a inceput testul: " + testName
        );

        driver =
                new BrowserFactory().getBrowserFactory();

        driver.get(
                "https://automationexercise.com/"
        );
    }


    @AfterMethod
    public void clearBrowser(ITestResult result) {

        LoggerUtility.info(
                "S-a terminat testul: " + testName
        );

        if (driver != null) {
            driver.quit();
        }

        ThreadContext.clearAll();
    }


    public WebDriver getDriver() {
        return driver;
    }
}