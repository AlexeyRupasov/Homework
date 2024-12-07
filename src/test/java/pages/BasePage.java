package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import untils.PropertyReader;

import java.time.Duration;

public abstract class BasePage {
    String BASE_URL = PropertyReader.getProperty("HomeWork.url");
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
