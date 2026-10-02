package Pages;

import HelperMethods.ElementsMethods;
import HelperMethods.WaitMethods;
import Logger.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private JavascriptExecutor js;
    private ElementsMethods elementsMethods;
    private WaitMethods waitMethods;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signupLoginButton;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsButton;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logoutButton;

    @FindBy(id = "susbscribe_email")
    private WebElement subscriptionEmail;

    @FindBy(id = "subscribe")
    private WebElement subscriptionButton;

    @FindBy(id = "success-subscribe")
    private WebElement subscriptionSuccessMessage;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInUser;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.elementsMethods = new ElementsMethods(driver);
        this.waitMethods = new WaitMethods(driver);

        PageFactory.initElements(driver, this);
    }


    public void clickSignupLoginButton() {

        LoggerUtility.info("Se deschide pagina de Login/Signup");

        js.executeScript(
                "arguments[0].click();",
                signupLoginButton
        );
    }


    public void clickProductsButton() {

        LoggerUtility.info("Se deschide pagina Products");

        js.executeScript(
                "arguments[0].click();",
                productsButton
        );
    }


    public void clickLogoutButton() {

        LoggerUtility.info("Se efectueaza logout");

        js.executeScript(
                "arguments[0].click();",
                logoutButton
        );
    }


    public void subscribe(String email) {

        LoggerUtility.info("Se completeaza emailul pentru abonare");

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                subscriptionEmail
        );

        js.executeScript(
                "arguments[0].value=arguments[1];",
                subscriptionEmail,
                email
        );

        js.executeScript(
                "arguments[0].click();",
                subscriptionButton
        );

        LoggerUtility.info("Cererea de abonare a fost trimisa");
    }


    public boolean isSubscriptionSuccessful() {

        LoggerUtility.info(
                "Se verifica mesajul de abonare cu succes"
        );

        return elementsMethods.isElementDisplayed(
                subscriptionSuccessMessage
        );
    }


    public boolean isUserLoggedIn() {

        LoggerUtility.info(
                "Se verifica daca utilizatorul este autentificat"
        );

        waitMethods.waitForElementVisible(loggedInUser);

        return elementsMethods.isElementDisplayed(
                loggedInUser
        );
    }


    public boolean isUserLoggedOut() {

        LoggerUtility.info(
                "Se verifica daca utilizatorul a fost delogat"
        );

        waitMethods.waitForElementVisible(signupLoginButton);

        return elementsMethods.isElementDisplayed(
                signupLoginButton
        );
    }
}