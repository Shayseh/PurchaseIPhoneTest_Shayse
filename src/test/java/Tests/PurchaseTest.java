package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PurchaseTest extends BaseTest {


    @Test
    public void PurchaseIPhoneUsingNdosiWebsite() {

        loginPage.clickLoginButton();
        loginPage.enterEmail("dayne@gmail.com");
        loginPage.enterPassword("@11712066");
        loginPage.clickSubmit();
        loginPage.verifyLoginSuccess("Welcome back, Dayne! \uD83D\uDC4B");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.findElement(By.cssSelector(".nav-dropdown-trigger")).click();
        driver.findElement(By.xpath("//span[contains(.,'💡')]")).click();

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
        Assert.assertEquals(priceAfter, "R480.00");

        driver.findElement(By.id("color"));
        Select color = new Select(driver.findElement(By.id("color")));
        color.selectByVisibleText("Blue");

        WebElement selectedColor = driver.findElement(By.xpath("//strong[text()='blue']"));
        Assert.assertTrue(selectedColor.isDisplayed());

        String priceBeforeQuantity = driver.findElement(By.id("subtotal-value")).getText();
        driver.findElement(By.id("quantity")).sendKeys("2");
        String priceAfterQuantity = driver.findElement(By.id("subtotal-value")).getText();
        Assert.assertEquals(priceAfterQuantity, "R960.00");

        driver.findElement(By.id("address")).sendKeys("123 Test Street, Test City, 12345");

        driver.findElement(By.id("inventory-next-btn")).click();

        String beforeTotalPrice = driver.findElement(By.id("breakdown-total-value")).getText();

        String beforeShippingPrice = driver.findElement(By.id("breakdown-shipping-value")).getText();
        driver.findElement(By.id("shipping-express")).click();
        String afterShippingPrice = driver.findElement(By.id("breakdown-shipping-value")).getText();
        Assert.assertEquals(afterShippingPrice, "R25.00");

        String beforeWarrantyPrice = driver.findElement(By.id("breakdown-warranty-value")).getText();
        driver.findElement(By.id("warranty-1yr")).click();
        String afterWarrantyPrice = driver.findElement(By.id("breakdown-warranty-value")).getText();
        Assert.assertEquals(afterWarrantyPrice, "R49.00");

        String afterTotalPrice = driver.findElement(By.id("breakdown-total-value")).getText();
        Assert.assertEquals(afterTotalPrice, "R1034.00");

        String beforeDiscountPrice = driver.findElement(By.id("breakdown-total-value")).getText();
        driver.findElement(By.id("discount-code")).sendKeys("SAVE10");
        driver.findElement(By.id("apply-discount-btn")).click();
        String afterDiscountPrice = driver.findElement(By.id("breakdown-total-value")).getText();
        Assert.assertEquals(afterDiscountPrice, "R930.60");

        driver.findElement(By.id("purchase-device-btn")).click();
        WebElement confirmationMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'Order Successful! \uD83C\uDF89')]")));
        Assert.assertTrue(confirmationMessage.isDisplayed());

        WebElement invoiceBtn =
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='view-history-btn']")));

        //Passed into JavaScriptExecutor to perform a click action when Selenium’s standard click method
        // could not interact with the dynamically loaded element.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", invoiceBtn);

        WebElement invoiceDetails = driver.findElement(By.xpath("//div[contains(.,'Dayne Harriparsad')]"));
        Assert.assertTrue(invoiceDetails.isDisplayed());

        String mainWindow = driver.getWindowHandle();

        WebElement viewInvoiceBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'\uD83D\uDC41\uFE0F View')]")));
        js.executeScript("arguments[0].click();", viewInvoiceBtn);

        //The invoice opened in a new browser window, so Selenium needed to switch control to the newly opened window
        // before locating and validating elements displayed on the invoice page.
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'123 Test Street, Test City, 12345')]")));

        Assert.assertEquals(address.getText(), "123 Test Street, Test City, 12345");

    }

}


