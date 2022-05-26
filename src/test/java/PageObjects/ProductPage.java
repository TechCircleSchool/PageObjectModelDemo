package PageObjects;

import Utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
   WebDriver driver = Driver.getDriver();

   public ProductPage() {
      PageFactory.initElements(driver, this);
   }

   @FindBy(className = "title")
   public WebElement pageHeader;
   @FindBy(xpath = "//*[@class='inventory_item']")
   public List<WebElement> productList;
   @FindBy(xpath = "//*[contains(@class,'price_slider')]/span[2]")
   private WebElement rightSlider;
   @FindBy(css = "div.price_label > span.to")
   private WebElement priceTo;
   @FindBy(xpath = "//ins/span[contains(@class,'amount')] | //span/span[contains(@class,'amount')]")
   public List<WebElement> productPrices;
   @FindBy(css = "button.button")
   public WebElement filterBtn;

   public void setLeftSlider(int maxPrice) {
      double price;
      do {
         price = getPriceAsInt(priceTo); // $450 ---> 450
         rightSlider.sendKeys(Keys.ARROW_LEFT);
      } while (price > maxPrice + 1);
   }

   public double getPriceAsInt(WebElement element) {
      return Integer.parseInt(element.getText().substring(1));
   }
}
