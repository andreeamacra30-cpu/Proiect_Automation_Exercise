package Tests;

import ObjectData.SearchProductObject;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import SharedData.TestBasePage;
import XmlData.XmlDataLoader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class CartTest extends TestBasePage {

    @DataProvider(name = "cartData")
    public Object[][] cartData() {

        Map<String, SearchProductObject> data =
                XmlDataLoader.loadData(
                        "src/test/resources/CartData.xml",
                        SearchProductObject.class
                );

        return new Object[][]{
                {data.get("dataSet1")},
                {data.get("dataSet2")},
                {data.get("dataSet3")}
        };
    }


    @Test(dataProvider = "cartData")
    public void cartTest(SearchProductObject data) {

        HomePage homePage = new HomePage(getDriver());
        homePage.clickProductsButton();

        ProductsPage productsPage = new ProductsPage(getDriver());

        // Cautam produsul primit din fisierul XML
        productsPage.searchProduct(
                data.getProductName()
        );

        // Adaugam produsul gasit in cos
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