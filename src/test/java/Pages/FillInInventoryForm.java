package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FillInInventoryForm {

    WebDriver driver;
    WebDriverWait wait;

    public FillInInventoryForm(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "deviceType")
    WebElement deviceTypeDropdown;

    @FindBy(id = "brand")
    WebElement brandTypeDropdown;

    @FindBy(xpath = "//img[contains(@alt,'Apple')]")
    WebElement appleBrandOptionDisplayed;

    @FindBy(id = "unit-price-value")
    WebElement unitPrice;

    @FindBy(id = "storage-128GB")
    WebElement selectStorageOption128GB;

    @FindBy(id = "color")
    WebElement colorDropdown;

    @FindBy(xpath = "//strong[text()='blue']")
    WebElement selectedColorDisplayed;

    @FindBy(id = "subtotal-value")
    WebElement subtotalValue;

    @FindBy(id = "quantity")
    WebElement quantityInputField;

    @FindBy(id = "address")
    WebElement addressInputField;

    @FindBy(id = "inventory-next-btn")
    WebElement nextButton;

    @FindBy(id = "breakdown-total-value")
    WebElement breakdownTotalValue;

    @FindBy(id = "breakdown-shipping-value")
    WebElement breakdownShippingValue;
    @FindBy(id = "shipping-express")
    WebElement shippingExpressOption;

    @FindBy(id = "breakdown-warranty-value")
    WebElement breakdownWarrantyValue;
    @FindBy(id = "warranty-1yr")
    WebElement warranty1yrOption;

    @FindBy(id = "discount-code")
    WebElement discountCodeInputField;
    @FindBy(id = "apply-discount-btn")
    WebElement applyDiscountButton;

    @FindBy(id = "purchase-device-btn")
    WebElement purchaseDeviceButton;

    // Selects an option from the device type <select> by visible text
    public void selectDeviceType(String deviceType) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(deviceTypeDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(deviceType);
    }

    public void selectBrandType(String brandType) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(brandTypeDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(brandType);
    }

    public void verifyAppleBrandOptionDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(appleBrandOptionDisplayed));
        if (!appleBrandOptionDisplayed.isDisplayed()) {
            throw new AssertionError("Apple brand option is not displayed.");
        }
    }

    // Get the unit price value - returns the actual price text
    public String getUnitPrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOf(unitPrice));
        return priceElement.getText();
    }

    // Verify unit price matches expected value
    public void verifyUnitPrice(String expectedPrice) {
        String actualPrice = getUnitPrice();
        if (!actualPrice.equals(expectedPrice)) {
            throw new AssertionError(String.format("Unit price mismatch. Expected: %s, but got: %s", expectedPrice, actualPrice));
        }
    }

    // Select 128GB storage option
    public void select128GBStorage() {
        WebElement storageOption = wait.until(ExpectedConditions.elementToBeClickable(selectStorageOption128GB));
        storageOption.click();
    }

    public void selectColor(String color) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(colorDropdown));
                Select select = new Select(dropdown);
                select.selectByVisibleText(color);
    }

    public void verifySelectedColorDisplayed(String color) {
        WebElement selectedColor = wait.until(ExpectedConditions.visibilityOf(selectedColorDisplayed));
        if (!selectedColor.isDisplayed()) {
            throw new AssertionError("Selected color " + color + " is not displayed.");
        }
    }

    public void verifySubTotalValue(String expectedSubtotal) {
        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOf(subtotalValue));
        String actualSubtotal = subtotalElement.getText();
        if (!actualSubtotal.equals(expectedSubtotal)) {
            throw new AssertionError(String.format("Subtotal value mismatch. Expected: %s, but got: %s", expectedSubtotal, actualSubtotal));
        }
    }

        public void enterQuantity(String quantity) {
            wait.until(ExpectedConditions.elementToBeClickable(quantityInputField))
                    .sendKeys(quantity);
        }

        public void fillInAddress(String address) {
            wait.until(ExpectedConditions.elementToBeClickable(addressInputField))
                    .sendKeys(address);
        }

        public void clickNextButton() {
            wait.until(ExpectedConditions.elementToBeClickable(nextButton))
                    .click();
        }

        public void verifyBreakdownTotalValue(String expectedTotal) {
            WebElement totalElement = wait.until(ExpectedConditions.visibilityOf(breakdownTotalValue));
            String actualTotal = totalElement.getText();
            if (!actualTotal.equals(expectedTotal)) {
                throw new AssertionError(String.format("Total price mismatch. Expected: %s, but got: %s", expectedTotal, actualTotal));
            }
        }

        public void verifyBreakdownShippingValue(String expectedShipping) {
            WebElement shippingElement = wait.until(ExpectedConditions.visibilityOf(breakdownShippingValue));
            String actualShipping = shippingElement.getText();
            if (!actualShipping.equals(expectedShipping)) {
                throw new AssertionError(String.format("Shipping price mismatch. Expected: %s, but got: %s", expectedShipping, actualShipping));
            }
        }

        public void clickShippingExpressOption() {
            wait.until(ExpectedConditions.elementToBeClickable(shippingExpressOption)).click();
        }

        public void verifyBreakdownWarrantyValue(String expectedWarranty) {
            WebElement warrantyElement = wait.until(ExpectedConditions.visibilityOf(breakdownWarrantyValue));
            String actualWarranty = warrantyElement.getText();
            if (!actualWarranty.equals(expectedWarranty)) {
                throw new AssertionError(String.format("Warranty price mismatch. Expected: %s, but got: %s", expectedWarranty, actualWarranty));
            }
        }

        public void clickWarranty1yrOption() {
            wait.until(ExpectedConditions.elementToBeClickable(warranty1yrOption)).click();
        }

        public void enterDiscountCode(String discountCode) {
            wait.until(ExpectedConditions.elementToBeClickable(discountCodeInputField))
                    .sendKeys(discountCode);
        }

        public void clickApplyDiscountButton() {
            wait.until(ExpectedConditions.elementToBeClickable(applyDiscountButton)).click();
        }

        public void clickPurchaseDeviceButton() {
            wait.until(ExpectedConditions.elementToBeClickable(purchaseDeviceButton)).click();
        }

}

