package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
      super(driver);
    }

    private final String ADD_TO_CART_PATTERN = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    private final By TITTLE = By.cssSelector("[class=title]");
    private final By TITTLE2 = By.xpath("//h3 [text()='Epic sadface: Sorry, this user has been locked out.']");
    private final By TITTLE3 = By.xpath("//*[@name='remove-sauce-labs-backpack']");

    public String getTittle() {
       return driver.findElement(TITTLE).getText();

    }
    public boolean tittleDisplayed1() {
        driver.findElement(TITTLE2).isDisplayed();
        return true;
       }

    public void addToCard(String product)  {
        By addToCard = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCard).click();
    }

}
