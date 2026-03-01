package BasicTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class BaseTestForPurchase {

    WebDriver driver;

    @Test
    public void LoginToNdosiWebsite()  {

        driver = new EdgeDriver();
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        driver.manage().window().maximize();

        //Targets the button, not just the span
        //Uses visible UI text
        //Less dependent on styling
        //Stable even if layout changes slightly
        driver.findElement(By.xpath("//button[.//span[text()='Login']]")).click();

        driver.findElement(By.id("login-email")).sendKeys("Dayne@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("@11712066");
        driver.findElement(By.id("login-submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement welcomeText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'Welcome back')]")));

        Assert.assertTrue(welcomeText.isDisplayed());

        driver.findElement(By.cssSelector(".nav-dropdown-trigger")).click();
        driver.findElement(By.xpath("//button[contains(.,'Learning Materials')]")).click();

        driver.findElement(By.id("tab-btn-web")).click();

        driver.findElement(By.id("deviceType"));
        Select device = new Select(driver.findElement(By.id("deviceType")));
        device.selectByVisibleText("Phone");

        driver.findElements(By.id("brand"));
        Select brand = new Select(driver.findElement(By.id("brand")));
        brand.selectByVisibleText("Apple");

        //Selenium cannot visually confirm images like a user, so the test verifies the image by checking its attributes such as alt or src.
        // This confirms that the correct image loads after selecting Apple.
        WebElement appleImage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@alt,'Apple')]")));
        Assert.assertTrue(appleImage.isDisplayed());

        String priceBefore = driver.findElement(By.id("unit-price-value")).getText();

        driver.findElement(By.id("storage-128GB")).click();

        String priceAfter = driver.findElement(By.id("unit-price-value")).getText();

        Assert.assertNotEquals(priceBefore, priceAfter);







    }

}