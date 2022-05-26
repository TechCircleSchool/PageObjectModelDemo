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
   public WebElement rightSlider;
   @FindBy(css = "div.price_label > span.to")
   public WebElement priceTo;

   public void setLeftSlider(int maxPrice) {
      int price;
      do {
         price = Integer.parseInt(priceTo.getText().substring(1)); // $450 ---> 450
         rightSlider.sendKeys(Keys.ARROW_LEFT);
      } while (price > maxPrice + 1);
   }
}