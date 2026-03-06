package Tests;

import Base.BaseTest;
import Utilities.ReadXSLdata;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {


    @Test(dataProviderClass = ReadXSLdata.class, dataProvider = "testData")
    public void PurchaseIPhoneUsingNdosiWebsite(String email, String password, String address) {

        loginPage.clickLoginButton();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
        loginPage.verifyLoginSuccess("Welcome back, Dayne! \uD83D\uDC4B");


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
        fillInInventoryForm.fillInAddress(address);
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


        viewInvoice.viewSuccessfulOrderInvoice("ORDER SUCCESSFUL! \uD83C\uDF89");
        viewInvoice.clickViewInvoiceButton();
        viewInvoice.verifyInvoiceDetailsDisplayed();
        viewInvoice.clickInvoiceDetails();
        viewInvoice.switchToInvoiceWindow();
        viewInvoice.verifyInvoiceStatusPaid();
    }

}




