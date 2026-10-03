package Tests;

import ObjectData.LoginObject;
import Pages.HomePage;
import Pages.LoginPage;
import SharedData.TestBasePage;
import XmlData.XmlDataLoader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest extends TestBasePage {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {

        Map<String, LoginObject> data =
                XmlDataLoader.loadData(
                        "src/test/resources/ValidLoginData.xml",
                        LoginObject.class
                );

        return new Object[][]{
                {data.get("dataSet1")}
        };
    }


    @Test(dataProvider = "validLoginData")
    public void loginTest(LoginObject data) {

        HomePage homePage =
                new HomePage(getDriver());

        homePage.clickSignupLoginButton();

        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.login(
                data.getEmail(),
                data.getPassword()
        );

        // Verificam daca utilizatorul este autentificat
        Assert.assertTrue(
                homePage.isUserLoggedIn()
        );

        // Facem logout
        homePage.clickLogoutButton();

        // Verificam daca utilizatorul a fost delogat
        Assert.assertTrue(
                homePage.isUserLoggedOut()
        );
    }
}