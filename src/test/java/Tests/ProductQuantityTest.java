package Tests;

import ObjectData.ProductQuantityObject;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import SharedData.TestBasePage;
import XmlData.XmlDataLoader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductQuantityTest extends TestBasePage {

    @DataProvider(name = "productQuantityData")
    public Object[][] productQuantityData() {

        Map<String, ProductQuantityObject> data =
                XmlDataLoader.loadData(
                        "src/test/resources/ProductQuantityData.xml",
                        ProductQuantityObject.class
                );

        return new Object[][]{
                {data.get("dataSet1")},
                {data.get("dataSet2")},
                {data.get("dataSet3")}
        };
    }


    @Test(dataProvider = "productQuantityData")
    public void productQuantityTest(ProductQuantityObject data) {

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

        // Setam cantitatea din fisierul XML
        productsPage.setQuantity(
                data.getQuantity()
        );

        // Adaugam produsul in cos
        productsPage.addToCartFromDetails();
        productsPage.clickViewCart();

        CartPage cartPage = new CartPage(getDriver());

        // Verificam cantitatea din cos
        Assert.assertEquals(
                cartPage.getProductQuantity(),
                data.getQuantity()
        );
    }
}