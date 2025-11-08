package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
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

 }
@AfterTest
    void  close()
  {
 driver.quit();
  }





}
