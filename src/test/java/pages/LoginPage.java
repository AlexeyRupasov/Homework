package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
 
    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем браузер")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вводим данные {user} и {password}")
    public void login(String user, String password) {
        fillUserInput(user);
        fillPasswordInput(password);
        clickSubmitButton();
    }

    @Step("Вводим {user}")
    public void fillUserInput(String user) {
        driver.findElement(USERNAME_INPUT).sendKeys(user);
    }

    @Step("Вводим {password}")
    public void fillPasswordInput(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @Step("Нажимаем кнопку {Login}")
    public void clickSubmitButton() {
        driver.findElement(LOGIN_BUTTON).submit();
    }

    @Step("Получаем текст из сообщения об ощибке")
    public boolean getErrorMessage() {
        driver.findElement(errorMessage).isDisplayed();
        return true;
    }
}
