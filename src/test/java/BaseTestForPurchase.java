import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLInputElement;

import java.time.Duration;

public class BaseTestForPurchase {

    WebDriver driver;

    @Test
    public void LoginToNdosiWebsite()  {

        driver = new EdgeDriver();
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")).click();
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


    }

}