package tests;

import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {
    @Test
    public void addGoods() {
       loginPage.open();
       loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCard("Sauce Labs Backpack");
        }
}
