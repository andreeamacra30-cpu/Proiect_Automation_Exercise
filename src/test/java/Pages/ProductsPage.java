package Pages;

import HelperMethods.WaitMethods;
import Logger.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    private WebDriver driver;
    private JavascriptExecutor js;
    private WaitMethods waitMethods;

    @FindBy(id = "search_product")
    private WebElement searchField;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(xpath = "(//div[@class='productinfo text-center']//a[contains(@class,'add-to-cart')])[1]")
    private WebElement firstProduct;

    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement viewCartButton;

    @FindBy(xpath = "//p[text()='Blue Top']")
    private WebElement blueTop;

    @FindBy(xpath = "(//a[contains(@href,'product_details')])[1]")
    private WebElement viewProductButton;

    @FindBy(id = "quantity")
    private WebElement quantityField;

    @FindBy(xpath = "//button[contains(@class,'cart')]")
    private WebElement addToCartDetailsButton;

    @FindBy(xpath = "//div[@class='product-information']//h2")
    private WebElement productDetailsName;

    @FindBy(xpath = "//div[@class='product-information']//span/span")
    private WebElement productPrice;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.waitMethods = new WaitMethods(driver);

        PageFactory.initElements(driver, this);
    }


    public void searchProduct(String productName) {

        LoggerUtility.info("Se cauta produsul: " + productName);

        js.executeScript(
                "arguments[0].value=arguments[1];",
                searchField,
                productName
        );

        js.executeScript(
                "arguments[0].click();",
                searchButton
        );

        LoggerUtility.info("Cautarea produsului a fost efectuata");
    }


    public boolean isBlueTopDisplayed() {

        LoggerUtility.info(
                "Se verifica daca produsul Blue Top este afisat"
        );

        return blueTop.isDisplayed();
    }


    public void addFirstProductToCart() {

        LoggerUtility.info("Se adauga primul produs in cos");

        js.executeScript(
                "arguments[0].click();",
                firstProduct
        );

        waitMethods.waitForElementVisible(viewCartButton);

        LoggerUtility.info("Produsul a fost adaugat in cos");
    }


    public void clickViewCart() {

        LoggerUtility.info("Se deschide cosul de cumparaturi");

        js.executeScript(
                "arguments[0].click();",
                viewCartButton
        );
    }


    public boolean isSearchedProductDisplayed(String productName) {

        LoggerUtility.info(
                "Se verifica daca produsul cautat este afisat: " + productName
        );

        return driver.getPageSource().contains(productName);
    }


    public void clickViewProduct() {

        LoggerUtility.info("Se deschid detaliile produsului");

        js.executeScript(
                "arguments[0].click();",
                viewProductButton
        );
    }


    public void setQuantity(String quantity) {

        LoggerUtility.info(
                "Se seteaza cantitatea produsului: " + quantity
        );

        js.executeScript(
                "arguments[0].value=arguments[1];",
                quantityField,
                quantity
        );
    }


    public void addToCartFromDetails() {

        LoggerUtility.info(
                "Se adauga produsul in cos din pagina de detalii"
        );

        js.executeScript(
                "arguments[0].click();",
                addToCartDetailsButton
        );

        waitMethods.waitForElementVisible(viewCartButton);

        LoggerUtility.info("Produsul a fost adaugat in cos");
    }


    public boolean isProductNameDisplayed() {

        LoggerUtility.info(
                "Se verifica daca numele produsului este afisat"
        );

        return productDetailsName.isDisplayed();
    }


    public boolean isProductPriceDisplayed() {

        LoggerUtility.info(
                "Se verifica daca pretul produsului este afisat"
        );

        return productPrice.isDisplayed();
    }



}