package Tests;

import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import SharedData.TestBasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends TestBasePage {

    @Test
    public void cartTest() {

        HomePage homePage = new HomePage(getDriver());
        homePage.clickProductsButton();

        ProductsPage productsPage = new ProductsPage(getDriver());

        // Adaugam primul produs in cos
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        CartPage cartPage = new CartPage(getDriver());

        // Verificam daca pagina Cart este afisata
        Assert.assertTrue(
                cartPage.isCartDisplayed()
        );

        // Stergem produsul din cos
        cartPage.deleteProduct();

        // Verificam daca cosul este gol
        Assert.assertTrue(
                cartPage.isCartEmpty()
        );
    }
}