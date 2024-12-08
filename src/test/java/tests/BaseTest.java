package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    String standard;
    String problemUser;
    String performanceGlitchUser;
    String visualUser;
    String errorUser;
    String password;

    @Parameters({"browser"})

    @BeforeMethod
    @Description("Открытие браузера")
    public void setup(@Optional("firefox") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("firefox")) {
            driver=new FirefoxDriver();
            driver.manage().window().fullscreen();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        System.setProperty("BASE_URL", PropertyReader.getProperty("HomeWork.url"));
        standard = PropertyReader.getProperty("HomeWork.standard");
        problemUser = PropertyReader.getProperty("HomeWork.problemUser");
        performanceGlitchUser = PropertyReader.getProperty("HomeWork.performanceGlitchUser");
        visualUser = PropertyReader.getProperty("HomeWork.visualUser");
        errorUser = PropertyReader.getProperty("HomeWork.errorUser");
        password = PropertyReader.getProperty("HomeWork.password");
    }

    @AfterMethod
    @Description("Закрытие браузера")
    public void close() {
        driver.quit();
    }
}