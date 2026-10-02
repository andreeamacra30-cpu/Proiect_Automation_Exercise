package HelperMethods;

import Logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementsMethods {

    private WebDriver driver;

    public ElementsMethods(WebDriver driver) {
        this.driver = driver;
    }


    public void clickElement(WebElement element) {

        LoggerUtility.info("Se executa click pe element");

        element.click();
    }


    public void fillElement(WebElement element, String text) {

        LoggerUtility.info("Se completeaza elementul");

        element.sendKeys(text);
    }


    public String getTextFromElement(WebElement element) {

        LoggerUtility.info("Se preia textul elementului");

        return element.getText();
    }


    public boolean isElementDisplayed(WebElement element) {

        LoggerUtility.info("Se verifica daca elementul este afisat");

        return element.isDisplayed();
    }
}