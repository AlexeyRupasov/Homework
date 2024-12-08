package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import untils.AllureUtils;
import untils.PropertyReader;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Epic("Модуль логина интернет магазина")
    @Feature("HomeWork")
    @Story("Final")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Alexey Rupasov")
    @TmsLink("Homework")
    @Description("Проверка входа в интеренет-магазин")

    @DataProvider
    public Object[][] loginCorrectData() {
        String standard = PropertyReader.getProperty("HomeWork.standard");
        String problemUser = PropertyReader.getProperty("HomeWork.problemUser");
        String performanceGlitchUser = PropertyReader.getProperty("HomeWork.performanceGlitchUser");
        String visualUser = PropertyReader.getProperty("HomeWork.visualUser");
        String errorUser = PropertyReader.getProperty("HomeWork.errorUser");
        String password = PropertyReader.getProperty("HomeWork.password");
        return new Object[][]{
                {standard, password},
                {problemUser, password},
                {performanceGlitchUser, password},
                {visualUser, password},
                {errorUser, password}
        };
    }

    @Test(dataProvider = "loginCorrectData", description = "Валидная авторизация пользователя")
    public void correctLogin(String user, String password) {
        loginPage
                .open()
                .login(user, password);
        assertEquals(productsPage.getTittle(), "Products");
        AllureUtils.takeScreenshot(driver);
    }

    @DataProvider
    public Object[][] loginIncorrectData() {
        String standard = PropertyReader.getProperty("HomeWork.standard");
        String password = PropertyReader.getProperty("HomeWork.password");
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"Русский", "Алфавит", "Epic sadface: Username and password do not match any user in this service"},
                {"!!!!!", "!!!!!", "Epic sadface: Username and password do not match any user in this service"},
                {"No_user", password, "Epic sadface: Username and password do not match any user in this service"},
                {"", password, "Epic sadface: Username is required"},
                {standard, "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "loginIncorrectData", description = "Невалидная авторизация пользователя")
    public void incorrectLoginCheck(String user, String password, String errorMessage) {
        loginPage
                .open()
                .login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
        AllureUtils.takeScreenshot(driver);
    }
}