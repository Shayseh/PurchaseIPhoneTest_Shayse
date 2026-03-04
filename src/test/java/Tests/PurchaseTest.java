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

        navigateToInventoryFormPage.clickLearnDropdown();
        navigateToInventoryFormPage.clickLearningMaterials();
        navigateToInventoryFormPage.clickWebAutomationAdvance();

        fillInInventoryForm.selectDeviceType("Phone");
        fillInInventoryForm.selectBrandType("Apple");
        fillInInventoryForm.verifyAppleBrandOptionDisplayed();
        fillInInventoryForm.verifyUnitPrice("R400.00");
        fillInInventoryForm.select128GBStorage();
        fillInInventoryForm.verifyUnitPrice("R480.00");
        fillInInventoryForm.selectColor("Blue");
        fillInInventoryForm.verifySelectedColorDisplayed("Blue");
        fillInInventoryForm.enterQuantity("2");
        fillInInventoryForm.verifySubTotalValue("R960.00");
        fillInInventoryForm.fillInAddress("123 Test Street, Test City, 12345");
        fillInInventoryForm.clickNextButton();
        fillInInventoryForm.clickShippingExpressOption();
        fillInInventoryForm.verifyBreakdownShippingValue("R25.00");
        fillInInventoryForm.clickWarranty1yrOption();
        fillInInventoryForm.verifyBreakdownWarrantyValue("R49.00");
        fillInInventoryForm.verifyBreakdownTotalValue("R1034.00");
        fillInInventoryForm.enterDiscountCode("SAVE10");
        fillInInventoryForm.clickApplyDiscountButton();
        fillInInventoryForm.verifyBreakdownTotalValue("R930.60");
        fillInInventoryForm.clickPurchaseDeviceButton();


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


