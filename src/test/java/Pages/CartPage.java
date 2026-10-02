package Pages;

import HelperMethods.ElementsMethods;
import Logger.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;
    private JavascriptExecutor js;
    private ElementsMethods elementsMethods;

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    private WebElement deleteProductButton;

    @FindBy(xpath = "//button[@class='disabled']")
    private WebElement productQuantity;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.elementsMethods = new ElementsMethods(driver);

        PageFactory.initElements(driver, this);
    }


    public boolean isCartDisplayed() {

        LoggerUtility.info(
                "Se verifica daca pagina Cart este afisata"
        );

        return driver.getCurrentUrl().contains("view_cart");
    }


    public void deleteProduct() {

        LoggerUtility.info(
                "Se sterge produsul din cos"
        );

        js.executeScript(
                "arguments[0].click();",
                deleteProductButton
        );

        LoggerUtility.info(
                "Produsul a fost sters din cos"
        );
    }


    public boolean isCartEmpty() {

        LoggerUtility.info(
                "Se verifica daca cosul este gol"
        );

        return driver.getPageSource().contains("Cart is empty!");
    }


    public String getProductQuantity() {

        LoggerUtility.info(
                "Se verifica cantitatea produsului din cos"
        );

        return elementsMethods.getTextFromElement(productQuantity);
    }
}