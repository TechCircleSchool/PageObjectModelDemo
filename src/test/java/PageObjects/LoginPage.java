package PageObjects;

import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

   WebDriver driver = Driver.getDriver();
   // initialize page factory
   public LoginPage() {
      PageFactory.initElements(driver, this);
   }
   // store page's elements
   @FindBy (css = "#user-name")
   private WebElement usernameInput;
   @FindBy (xpath = "//input[@id='password']")
   private WebElement passwordInput;
   @FindBy (id = "login-button")
   private WebElement loginBtn;
   @FindBy (css = "[data-test=\"error\"]")
   public WebElement errorMsg;

   // page's action
   public void doLogin(String username, String password) {
      usernameInput.sendKeys(username);
      passwordInput.sendKeys(password);
      loginBtn.click();
   }

   public void doLogin(String username) {
      usernameInput.sendKeys(username);
      loginBtn.click();
   }
}
