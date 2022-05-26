import PageObjects.LoginPage;
import PageObjects.ProductPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

   WebDriver driver;
   LoginPage loginPage = new LoginPage();
   ProductPage productPage = new ProductPage();

   @BeforeSuite
   public void goToSauceDemo() {
      driver = Driver.getDriver();
      driver.get("http://practice.automationtesting.in/shop/");
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
   public void verifySlider() {
      productPage.setLeftSlider(450);
//      Assert.assertEquals(450, 450);
   }
}
