package Base;

import Pages.FillInInventoryForm;
import Pages.LoginPage;
import Pages.NavigateToInventoryFormPage;
import Pages.ViewInvoice;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

public class BaseTest {

    BrowserFactory browserFactory = new BrowserFactory();

    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "edge";

    public WebDriver driver;
    public LoginPage loginPage;
    public NavigateToInventoryFormPage navigateToInventoryFormPage;
    public FillInInventoryForm fillInInventoryForm;
    public ViewInvoice viewInvoice;

    @BeforeClass// This method will run before any of the test methods in the class
    public void setUp() {
        driver = browserFactory.startBrowser(browserChoice, url);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        navigateToInventoryFormPage = PageFactory.initElements(driver, NavigateToInventoryFormPage.class);
        fillInInventoryForm = PageFactory.initElements(driver, FillInInventoryForm.class);
        viewInvoice = PageFactory.initElements(driver, ViewInvoice.class);
    }

    @AfterClass// This method will run after all the test methods in the class have run
    public void tearDown(){
        browserFactory.closeBrowser();
    }
}
