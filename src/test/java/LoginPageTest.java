import PageObjects.LoginPage;
import PageObjects.ProductPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class LoginPageTest {

   WebDriver driver;
   LoginPage loginPage = new LoginPage();
   ProductPage productPage = new ProductPage();

   @BeforeSuite
   public void goToSauceDemo() {
      driver = Driver.getDriver();
      driver.get("http://practice.automationtesting.in/shop/?min_price=150&max_price=450");
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      driver.manage().window().maximize();
   }

//   @Test
//   public void TC001_verifyLoginElementsArePresent() {
//      Assert.assertTrue(loginPage.usernameInput.isDisplayed());
//      Assert.assertTrue(loginPage.passwordInput.isDisplayed());
//      Assert.assertTrue(loginPage.loginBtn.isDisplayed());
//   }

   // login then verify page header == Products
   @Test
   public void TC002_verifyPageHeaderOfProductPage() {
      loginPage.doLogin(ConfigReader.getProperty("standardUsername"),
         ConfigReader.getProperty("standardPassword"));
      Assert.assertEquals(productPage.pageHeader.getText(), "PRODUCTS");
   }

   // invalid login, verify warning/error msg
   @Test
   public void TC003_verifyErrorMessageWhenLoginWithoutPassword() {
      loginPage.doLogin(ConfigReader.getProperty("standardUsername"));
      Assert.assertTrue(loginPage.errorMsg.isDisplayed());
   }

   // verify the product page have 6 products
   @Test
   public void TC004_verifyNumberOfProducts() {
      loginPage.doLogin(ConfigReader.getProperty("standardUsername"),
         ConfigReader.getProperty("standardPassword"));
      Assert.assertEquals(productPage.productList.size(), 6);
   }

   @Test
   public void verifySliderFilter() {
      int filterToBeSet = 450;
//      productPage.setLeftSlider(filterToBeSet);
//      productPage.filterBtn.click();
//      try {
//         Thread.sleep(2000);
//      } catch (InterruptedException e) {
//         e.printStackTrace();
//      }
      List<WebElement> productPrice = productPage.productPrices;
      for (WebElement e : productPrice) {
         double price = productPage.getPriceAsInt(e);
         assertThat(price).isLessThanOrEqualTo(filterToBeSet);
      }
   }
}
