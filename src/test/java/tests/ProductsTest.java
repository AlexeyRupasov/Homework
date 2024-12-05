package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {
    @Test
    public void addGoods() {
        loginPage.open()
                .login(user1, password);
        productsPage.isOpened()
        .addToCart(1)
        .addToCart(2)
        .openCart();
        assertTrue(productsPage.getProductsNames().contains("Sauce Labs Bike Light"));
    }
}
