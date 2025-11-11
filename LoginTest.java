package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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





@AfterTest
    void  close()
  {
 driver.quit();
  }





}
