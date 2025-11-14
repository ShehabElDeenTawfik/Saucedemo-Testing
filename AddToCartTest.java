package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest {
    ChromeDriver driver = new ChromeDriver();
    @BeforeTest
    void setup ()
    {
     driver.get("https://www.saucedemo.com/");
     driver.findElement(By.id("user-name")).sendKeys("standard_user");
     driver.findElement(By.id("password")).sendKeys("secret_sauce");
     driver.findElement(By.id("login-button")).click();
    }
    public void handleAlertIfPresent () {
        try {
            driver.switchTo().alert().accept();
            System.out.println("Alert acceted");
        }
        catch (NoAlertPresentException e)
        {
            System.out.println("No alert present");

        }
    }
@Test
    void TC009 ()
    {
        handleAlertIfPresent();

        driver.findElement(By.cssSelector("button[id=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();


        String CartCount = driver.findElement(By.cssSelector("span[class=\"shopping_cart_badge\"]")).getText();
        Assert.assertEquals(CartCount,"1","Cart count should be 1 after adding Sauce Labs Fleece Jacket.");

        String buttontext= driver.findElement(By.cssSelector("button[id=\"remove-sauce-labs-fleece-jacket\"]")).getText();
        Assert.assertEquals(buttontext,"Remove","Button should change to 'Remove' after adding the product");

    }



@AfterTest
    void close ()
{
    driver.quit();
}







}
