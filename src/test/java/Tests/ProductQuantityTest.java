package Tests;

import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import SharedData.TestBasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductQuantityTest extends TestBasePage {

    @Test
    public void productQuantityTest() {

        HomePage homePage = new HomePage(getDriver());
        homePage.clickProductsButton();

        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.clickViewProduct();

        // Verificam detaliile produsului
        Assert.assertTrue(
                productsPage.isProductNameDisplayed()
        );

        Assert.assertTrue(
                productsPage.isProductPriceDisplayed()
        );

        // Setam cantitatea
        productsPage.setQuantity("4");

        // Adaugam produsul in cos
        productsPage.addToCartFromDetails();
        productsPage.clickViewCart();

        CartPage cartPage = new CartPage(getDriver());

        // Verificam cantitatea din cos
        Assert.assertEquals(
                cartPage.getProductQuantity(),
                "4"
        );
    }
}