package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ViewInvoice {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js; // Store JavascriptExecutor instance
    String mainWindow;  // Store main window handle instead of WindowManager

    public ViewInvoice(WebDriver driver) { // Constructor to initialize WebDriver, WebDriverWait, and JavascriptExecutor
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Initialize WebDriverWait
        this.js = (JavascriptExecutor) driver; // Initialize JavascriptExecutor
        this.mainWindow = driver.getWindowHandle();  // Capture main window handle
    }

    @FindBy(xpath = "//*[contains(text(),'Order Successful!')]")
    WebElement orderConfirmationMessage;


    @FindBy(id = "view-history-btn")
    WebElement viewInvoiceButton;


    @FindBy(xpath = "//h3[contains(.,'Invoice History')]")
    WebElement invoiceDetails;

    @FindBy(xpath = "//button[contains(text(), 'View')]")
    WebElement viewInvoiceDetailsButton;

    @FindBy(xpath = "//span[contains(.,'Paid')]")
    WebElement invoiceStatusPaid;

    public void viewSuccessfulOrderInvoice(String expectedConfirmationMessage) {
        wait.until(ExpectedConditions.visibilityOf(orderConfirmationMessage));
        String actualConfirmationMessage = orderConfirmationMessage.getText();
        if (!actualConfirmationMessage.equals(expectedConfirmationMessage)) {
            throw new AssertionError("Expected confirmation message: " + expectedConfirmationMessage + ", but got: " + actualConfirmationMessage);
        }
    }

    public void clickViewInvoiceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewInvoiceButton));
        js.executeScript("arguments[0].scrollIntoView(true);", viewInvoiceButton);// Scroll to the button to ensure it's in view
        js.executeScript("arguments[0].click();", viewInvoiceButton);// Use JavaScript to click the button to avoid potential issues with element visibility or overlapping elements
    }

    public void verifyInvoiceDetailsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(invoiceDetails));
        if (!invoiceDetails.isDisplayed()) {
            throw new AssertionError("Invoice details are not displayed.");
        }
    }

    public void clickInvoiceDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(viewInvoiceDetailsButton));
        js.executeScript("arguments[0].scrollIntoView(true);", viewInvoiceDetailsButton);
        js.executeScript("arguments[0].click();", viewInvoiceDetailsButton);

        // Switch to the new invoice details window that just opened
        switchToInvoiceWindow();
    }

    // Switch to the invoice window that opened after clicking the button
    public void switchToInvoiceWindow() {
        // Wait for a new window to open
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Get all window handles and switch to the new one
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break; // Switch to the new window and exit the loop
            }
        }
    }


    public void verifyInvoiceStatusPaid() {
        wait.until(ExpectedConditions.visibilityOf(invoiceStatusPaid));
        String actualStatus = invoiceStatusPaid.getText();
        if (!actualStatus.equals("Paid")) {
            throw new AssertionError("Expected invoice status: Paid, but got: " + actualStatus);
        }
    }


}



