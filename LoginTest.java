package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginTest {

ChromeDriver driver = new ChromeDriver();

@BeforeTest
    void setup()

{
    driver.get("https://www.saucedemo.com/");
}


    @Test
    void TC001()
 {
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();

    String actual = driver.findElement(By.className("app_logo")).getText();
     Assert.assertEquals(actual,"Swag Labs");
 }

 @Test
 void TC002()
 {
     driver.findElement(By.id("user-name")).sendKeys("invalid_user");
     driver.findElement(By.id("password")).sendKeys("wrong_pass");
     driver.findElement(By.id("login-button")).click();

     String ActualError = driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
     String ExpetedError = "Epic sadface: Username and password do not match any user in this service";
     Assert.assertEquals(ActualError,ExpetedError,"Error message mismatch!");
 }
  @Test
  void TC003()
  {
      driver.findElement(By.id("login-button")).click();

   String ActualError =  driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
   String ExpectedError = "Epic sadface: Username is required";
   Assert.assertEquals(ActualError,ExpectedError,"Error message mismatch!");
  }

  @Test
  void TC004()
  {
      driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
      driver.findElement(By.id("password")).sendKeys("secret_sauce");
      driver.findElement(By.id("login-button")).click();

     String ActualError = driver.findElement(By.cssSelector("h3[data-test=\"error\"]")).getText();
     String ExpectedError = "Epic sadface: Sorry, this user has been locked out.";
     Assert.assertEquals(ActualError,ExpectedError,"Error message mismatch!");
  }

  @Test
  void TC005()
  {
      driver.findElement(By.id("user-name")).sendKeys("problem_user");
      driver.findElement(By.id("password")).sendKeys("secret_sauce");
      driver.findElement(By.id("login-button")).click();

      WebDriverWait  wait = new WebDriverWait(driver,Duration.ofSeconds(10));

      wait.until(ExpectedConditions.urlContains("/inventory.html"));

      Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),"TC005 FAILED - problem_user did not reach inventory page");
      boolean inventoryVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))).isDisplayed();
      Assert.assertTrue(inventoryVisible,"TC005 FAILED - inventory container not visible after login");

  }
  @Test
  void TC006()
  {
      driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
      driver.findElement(By.id("password")).sendKeys("secret_sauce");
      driver.findElement(By.id("login-button")).click();

      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
      wait.until(ExpectedConditions.urlContains("/inventory.html"));

      Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),"TC006 FAILED - performance_glitch_user did not reach inventory page");
           boolean inventoryVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))).isDisplayed();

      Assert.assertTrue(inventoryVisible,"TC006 FAILED - performance_glitch_user did not reach inventory page");

  }
  @Test
  void TC007()
  {
      driver.findElement(By.id("user-name")).sendKeys("error_user");
      driver.findElement(By.id("password")).sendKeys("secret_sauce");
      driver.findElement(By.id("login-button")).click();

      //انتظار تحميل الصفحه
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
      wait.until(ExpectedConditions.urlContains("/inventory.html"));

      //التحقق من الوصول للصفحه
      Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),"TC007 FAILED - error_user did not reach inventory page");
      //التحقق من ظهور المنتجات
      List<WebElement> items = driver.findElements(By.id("inventory_container"));
      Assert.assertTrue(items.size()>0,"TC007 FAILED - No items displayed for error_user");
  }
  @Test
  void TC008()
  {
      driver.findElement(By.id("user-name")).sendKeys("visual_user");
      driver.findElement(By.id("password")).sendKeys("secret_sauce");
      driver.findElement(By.id("login-button")).click();

      //التأكد على ان ال url يحتوى على /inventory.html
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
      wait.until(ExpectedConditions.urlContains("/inventory.html"));
      Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),"TC008 FAILED - visual_user did not reach inventory page");

      //التاكد من وجود ال logo
      Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class=\"app_logo\"]"))).isDisplayed(),"TC008 FAILED - app logo not visible");

      //التاكد من وجود عناصر المنتجات
      List<WebElement> items = driver.findElements(By.cssSelector("div[class=\"inventory_item\"]"));
      Assert.assertTrue(items.size()> 0 ,"TC008 FAILED - no inventory items found");
  }





@AfterTest
    void  close()
  {
 driver.quit();
  }





}
