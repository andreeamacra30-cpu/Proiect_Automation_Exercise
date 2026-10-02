package Tests;

import ObjectData.SearchProductObject;
import Pages.HomePage;
import Pages.ProductsPage;
import SharedData.TestBasePage;
import XmlData.XmlDataLoader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class SearchProductTest extends TestBasePage {

    @DataProvider(name = "searchProductData")
    public Object[][] searchProductData() {

        Map<String, SearchProductObject> data =
                XmlDataLoader.loadData(
                        "src/test/resources/SearchProductData.xml",
                        SearchProductObject.class
                );

        return new Object[][]{
                {data.get("dataSet1")},
                {data.get("dataSet2")},
                {data.get("dataSet3")}
        };
    }


    @Test(dataProvider = "searchProductData")
    public void searchProductTest(SearchProductObject data) {

        HomePage homePage =
                new HomePage(getDriver());

        homePage.clickProductsButton();

        ProductsPage productsPage =
                new ProductsPage(getDriver());

        productsPage.searchProduct(
                data.getProductName()
        );

        Assert.assertTrue(
                productsPage.isSearchedProductDisplayed(
                        data.getProductName()
                )
        );
    }
}