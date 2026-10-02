package Tests;

import Logger.LoggerUtility;
import ObjectData.LoginObject;
import Pages.HomePage;
import Pages.LoginPage;
import SharedData.TestBasePage;
import XmlData.XmlDataLoader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class InvalidLoginTest extends TestBasePage {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        Map<String, LoginObject> data =
                XmlDataLoader.loadData(
                        "src/test/resources/LoginData.xml",
                        LoginObject.class
                );

        return new Object[][]{
                {data.get("dataSet1")},
                {data.get("dataSet2")},
                {data.get("dataSet3")}
        };
    }

    @Test(dataProvider = "loginData")
    public void invalidLoginTest(LoginObject data) {

        LoggerUtility.info("Invalid Login Test a inceput");

        HomePage homePage =
                new HomePage(getDriver());

        homePage.clickSignupLoginButton();

        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.login(
                data.getEmail(),
                data.getPassword()
        );

        Assert.assertTrue(
                loginPage.isInvalidLoginMessageDisplayed()
        );

        LoggerUtility.info(
                "Invalid Login Test s-a terminat cu succes"
        );
    }
}