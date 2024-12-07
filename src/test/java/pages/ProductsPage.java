package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
      super(driver);
    }

    private final By tittle = By.cssSelector("[class=title]");
    private final By button = (By.xpath("//*[text()='Add to cart']"));
    private final By open = (By.xpath("//*[@class='shopping_cart_link']"));

    public String getTittle() {
       return driver.findElement(tittle).getText();
    }

    public ProductsPage addToCart(int index) {
        driver.findElements(button).get(index).click();
        return this;
    }

    public boolean tittleDisplayed() {
        driver.findElement(tittle).isDisplayed();
        return true;
    }

    public ProductsPage openCart() {
        driver.findElement(open).click();
        return this;
    }

    public ArrayList<String> getProductsNames() {
        List<WebElement> allProductsNames = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProductsNames) {
            names.add(product.getText());
        }
        return names;
    }

    public ProductsPage isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tittle));
        return this;
    }
}
