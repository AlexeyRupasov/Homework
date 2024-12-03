package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import untils.AllureUtils;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class LoginTest extends BaseTest {

    @Epic("Модуль логина интернет магазина")
    @Feature("HomeWork")
    @Story("Final")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Alexey Rupasov")
    @TmsLink("Homework")
    @Description("Проверка входа в интеренет-магазин")
    @Test(description = "Валидная авторизация пользователя")
    public void correctLogin1() {
        loginPage.open();
        loginPage.login(user1, password);
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация проблемного пользователя")
    public void correctLogin2() {
        loginPage.open();
        loginPage.login(user2, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
    }

    @Test(description = "Авторизация проблемного пользователя 2")
    public void correctLogin3() {
        loginPage.open();
        loginPage.login(user3, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация при сбое производительности")
    public void correctLogin4() {
        loginPage.open();
        loginPage.login(user4, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация ошибочного пользователя")
    public void correctLogin5() {
        loginPage.open();
        loginPage.login(user5, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"No_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "No_password", "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "loginData", description = "Авторизация невалидного пользователя")
    public void incorrectLoginCheck(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
        AllureUtils.takeScreenshot(driver);
    }
}