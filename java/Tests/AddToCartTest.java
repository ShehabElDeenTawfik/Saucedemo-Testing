
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public class AddToCartTest {

    ChromeDriver driver;

    @BeforeTest
    void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--incognito");
        options.setExperimentalOption("prefs",Map.of("credentials_enable_service",false,"profile.password_manager_enabled",false));

        driver = new ChromeDriver(options);

        driver.get("https://www.saucedemo.com/");
    }

    @Test
    void TC009()
    {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-test=\"shopping-cart-badge\"]")));

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        String CartCount = cartBadge.getText().trim();
        Assert.assertEquals(CartCount,"1","Cart count should be 1 after adding the product");




    }

    @AfterTest
    void close()
    {
        driver.quit();
    }
}
