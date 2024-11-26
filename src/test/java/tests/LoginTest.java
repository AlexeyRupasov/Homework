package tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin1() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTittle(),"Products");
    }

    @Test
    public void correctLogin2() {
        loginPage.open();
        loginPage.login("problem_user", "secret_sauce");
        assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@class='title']")).getText(), "Products");
    }

    @Test
    public void correctLogin3() {
        loginPage.open();
        loginPage.login("performance_glitch_user", "secret_sauce");
        assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@class='title']")).getText(), "Products");
    }

    @Test
    public void correctLogin4() {
        loginPage.open();
        loginPage.login("visual_user", "secret_sauce");
        assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@class='title']")).getText(), "Products");
    }

    @Test
    public void correctLogin5() {
        loginPage.open();
        loginPage.login("error_user", "secret_sauce");
        assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@class='title']")).getText(), "Products");
    }

    @Test
    public void locked_out_user() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
         assertTrue(productsPage.tittleDisplayed1() ,"Epic sadface: Sorry, this user has been locked out.");
       assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void incorrectLoginCheck() {
        loginPage.open();
        loginPage.login("No_user", "secret_sauce");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void incorrectPasswordCheck() {
        loginPage.open();
        loginPage.login("standard_user", "No_password");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void NoLoginNoPasswordCheck() {
        loginPage.open();
        loginPage.login("", "");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Username is required");
    }

    @Test
    public void YesLoginNoPasswordCheck() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Password is required");
    }

    @Test
    public void NoLoginYesPasswordCheck() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Username is required");
    }

}