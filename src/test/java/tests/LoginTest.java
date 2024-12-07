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
        loginPage
                .open()
                .login(user, password);
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация проблемного пользователя")
    public void correctLogin2() {
        loginPage
                .open()
                .login(problemUser, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация проблемного пользователя 2")
    public void correctLogin3() {
        loginPage
                .open()
                .login(performanceGlitchUser, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация при сбое производительности")
    public void correctLogin4() {
        loginPage
                .open()
                .login(visualUser, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация ошибочного пользователя")
    public void correctLogin5() {
        loginPage
                .open()
                .login(errorUser, password);
        assertTrue(productsPage.tittleDisplayed());
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"Русский", "Алфавит", "Epic sadface: Username and password do not match any user in this service"},
                {"!!!!!", "!!!!!", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test(dataProvider = "loginData", description = "Авторизация невалидного пользователя")
    public void incorrectLoginCheck(String user, String password, String errorMessage) {
        loginPage
                .open()
                .login(user, password);
        assertTrue(loginPage.getErrorMessage(), errorMessage);
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация невалидного пользователя с невалидным логином")
    public void incorrectUserCheck() {
        loginPage.open();
        loginPage.login("No_user", password);
        assertTrue(loginPage.getErrorMessage());
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация невалидного пользователя с невалидным паролем")
    public void incorrectPasswordCheck() {
        loginPage.open();
        loginPage.login(user, "No_password");
        assertTrue(loginPage.getErrorMessage());
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация невалидного пользователя с невведенным логином")
    public void incorrectEmptyLoginCheck() {
        loginPage.open();
        loginPage.login("", password);
        assertTrue(loginPage.getErrorMessage());
        AllureUtils.takeScreenshot(driver);
    }

    @Test(description = "Авторизация невалидного пользователя с невведенным паролем")
    public void incorrectEmptyPasswordCheck() {
        loginPage.open();
        loginPage.login(user, "");
        assertTrue(loginPage.getErrorMessage());
        AllureUtils.takeScreenshot(driver);
    }
}