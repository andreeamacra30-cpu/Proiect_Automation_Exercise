package Pages;

import HelperMethods.ElementsMethods;
import HelperMethods.WaitMethods;
import Logger.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    private JavascriptExecutor js;
    private ElementsMethods elementsMethods;
    private WaitMethods waitMethods;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    private WebElement invalidLoginMessage;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.elementsMethods = new ElementsMethods(driver);
        this.waitMethods = new WaitMethods(driver);

        PageFactory.initElements(driver, this);
    }


    public void login(String email, String password) {

        LoggerUtility.info(
                "Se completeaza datele de autentificare"
        );

        elementsMethods.fillElement(emailField, email);
        elementsMethods.fillElement(passwordField, password);

        LoggerUtility.info(
                "Se asteapta ca butonul Login sa poata fi accesat"
        );

        waitMethods.waitForElementClickable(loginButton);

        LoggerUtility.info(
                "Se apasa butonul Login"
        );

        js.executeScript(
                "arguments[0].click();",
                loginButton
        );
    }


    public boolean isInvalidLoginMessageDisplayed() {

        LoggerUtility.info(
                "Se verifica mesajul pentru autentificare invalida"
        );

        return elementsMethods.isElementDisplayed(
                invalidLoginMessage
        );
    }
}