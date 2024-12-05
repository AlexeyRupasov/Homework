package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;
import untils.PropertyReader;
import untils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    String user1;
    String user2;
    String user3;
    String user4;
    String user5;
    String password;

    @Parameters({"browser"})
    @BeforeMethod
    @Description("Открытие браузера")
    public void setup(@Optional("firefox") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        System.setProperty("BASE_URL", PropertyReader.getProperty("HomeWork.url"));
        user1 = PropertyReader.getProperty("HomeWork.user1");
        user2 = PropertyReader.getProperty("HomeWork.user2");
        user3 = PropertyReader.getProperty("HomeWork.user3");
        user4 = PropertyReader.getProperty("HomeWork.user4");
        user5 = PropertyReader.getProperty("HomeWork.user5");
        password = PropertyReader.getProperty("HomeWork.password");
    }

    @AfterMethod
    @Description("Закрытие браузера")
    public void close() {
        driver.quit();
    }
}
