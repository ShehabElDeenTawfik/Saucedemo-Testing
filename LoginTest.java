package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
@AfterTest
    void  close()
  {
 driver.quit();
  }





}
