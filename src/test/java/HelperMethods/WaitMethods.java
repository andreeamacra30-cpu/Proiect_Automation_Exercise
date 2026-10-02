package HelperMethods;

import Logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitMethods {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(5)
        );
    }


    public void waitForElementVisible(WebElement element) {

        LoggerUtility.info(
                "Se asteapta ca elementul sa fie vizibil"
        );

        wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }


    public void waitForElementClickable(WebElement element) {

        LoggerUtility.info(
                "Se asteapta ca elementul sa poata fi accesat"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }
}