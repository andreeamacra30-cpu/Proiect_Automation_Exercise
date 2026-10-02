package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import SharedData.TestBasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBasePage {

    @Test
    public void loginTest() {

        HomePage homePage =
                new HomePage(getDriver());

        homePage.clickSignupLoginButton();

        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.login(
                "macra.a@gmail.com",
                "Test@1234"
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