package Tests;

import Base.BaseTest;
import Utilities.ReadXSLdata;
import Utilities.Screenshots;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseTest extends BaseTest {


    //@Test(dataProviderClass = ReadXSLdata.class, dataProvider = "testData")
    //public void PurchaseIPhoneUsingNdosiWebsite(String email, String password, String address) {


    @Test(enabled = false)
    public void testDataRead() throws IOException {

        ReadXSLdata reader = new ReadXSLdata();

        Object[][] loginData = reader.getData("enterLoginDetails");
        Object[][] addressData = reader.getData("fillInAddress");

        // Print the retrieved data
        System.out.println("enterLoginDetails");
        for (Object[] row : loginData) {
            System.out.println("Email: " + row[0] + ", Password: " + row[1]);
        }
        System.out.println("fillInAddress");
        for (Object[] row : addressData) {
            System.out.println("Address: " + row[0]);
        }
    }

    @Test(priority = 0)
    public void clickLoginButton() {
        loginPage.clickLoginButton();
        Screenshots.takesSnapShot(driver, "clickLoginButton");
    }

    @Test(dataProviderClass = ReadXSLdata.class, dataProvider = "loginData", priority = 1)
    public void enterLoginDetails(String email, String password) {

        System.out.println(email);
        System.out.println(password);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
        loginPage.verifyLoginSuccess("Welcome back, Dayne! \uD83D\uDC4B");
        Screenshots.takesSnapShot(driver, "enterLoginDetails");

    }

    @Test(priority = 2)
    public void navigateToInventoryFormAndPurchaseIPhone() {

        navigateToInventoryFormPage.clickLearnDropdown();
        navigateToInventoryFormPage.clickLearningMaterials();
        navigateToInventoryFormPage.clickWebAutomationAdvance();
        Screenshots.takesSnapShot(driver, "navigateToInventoryFormAndPurchaseIPhone");
    }

    @Test(priority = 3)
    public void selectIPhone() {
        fillInInventoryForm.selectDeviceType("Phone");
        Screenshots.takesSnapShot(driver, "selectIPhone");
    }

    @Test(priority = 4)
    public void selectAppleBrandAndVerifyBrandIsDisplayed() {
        fillInInventoryForm.selectBrandType("Apple");
        fillInInventoryForm.verifyAppleBrandOptionDisplayed();
        Screenshots.takesSnapShot(driver, "selectAppleBrandAndVerifyBrandIsDisplayed");
    }

    //fillInInventoryForm.verifyUnitPrice("R400.00");
    @Test(priority = 5)
    public void select128GBStorageAndVerifyUnitPrice() {
        fillInInventoryForm.select128GBStorage();
        fillInInventoryForm.verifyUnitPrice("R480.00");
        Screenshots.takesSnapShot(driver, "select128GBStorageAndVerifyUnitPrice");
    }

    @Test(priority = 6)
    public void selectBlueColorAndVerifySelectedColor() {
        fillInInventoryForm.selectColor("Blue");
        fillInInventoryForm.verifySelectedColorDisplayed("Blue");
        Screenshots.takesSnapShot(driver, "selectBlueColorAndVerifySelectedColor");
    }

    @Test(priority = 7)
    public void enterQuantityAndVerifySubTotal() {
        fillInInventoryForm.enterQuantity("2");
        fillInInventoryForm.verifySubTotalValue("R960.00");
        Screenshots.takesSnapShot(driver, "enterQuantityAndVerifySubTotal");
    }

    @Test(dataProviderClass = ReadXSLdata.class, dataProvider = "addressData", priority = 8)
    public void fillInAddress(String address) {
        fillInInventoryForm.fillInAddress(address);
        Screenshots.takesSnapShot(driver, "fillInAddress");
    }


    @Test(priority = 9)
    public void selectShippingAndWarrantyOptionsAndVerifyBreakdownValues() {
        fillInInventoryForm.clickNextButton();
        fillInInventoryForm.clickShippingExpressOption();
        fillInInventoryForm.verifyBreakdownShippingValue("R25.00");
        fillInInventoryForm.clickWarranty1yrOption();
        fillInInventoryForm.verifyBreakdownWarrantyValue("R49.00");
        fillInInventoryForm.verifyBreakdownTotalValue("R1034.00");
        Screenshots.takesSnapShot(driver, "selectShippingAndWarrantyOptionsAndVerifyBreakdownValues");
    }

    @Test(priority = 10)
    public void applyDiscountCodeAndVerifyTotal() {
        fillInInventoryForm.enterDiscountCode("SAVE10");
        fillInInventoryForm.clickApplyDiscountButton();
        fillInInventoryForm.verifyBreakdownTotalValue("R930.60");
        Screenshots.takesSnapShot(driver, "applyDiscountCodeAndVerifyTotal");
    }

    @Test(priority = 11)
    public void purchaseDeviceAndVerifyInvoice() {
        fillInInventoryForm.clickPurchaseDeviceButton();
        viewInvoice.viewSuccessfulOrderInvoice("ORDER SUCCESSFUL! \uD83C\uDF89");
        viewInvoice.clickViewInvoiceButton();
        viewInvoice.verifyInvoiceDetailsDisplayed();
        Screenshots.takesSnapShot(driver, "purchaseDeviceAndVerifyInvoice");
    }

    @Test(priority = 12)
    public void verifyInvoiceStatusPaid() {
        viewInvoice.clickInvoiceDetails();
        viewInvoice.switchToInvoiceWindow();
        viewInvoice.verifyInvoiceStatusPaid();
        Screenshots.takesSnapShot(driver, "verifyInvoiceStatusPaid");
    }
}







