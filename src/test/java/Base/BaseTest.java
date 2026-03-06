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

    @BeforeMethod// This method will run before each test method in the class
    public void setUp() {
        driver = browserFactory.startBrowser(browserChoice, url);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        navigateToInventoryFormPage = PageFactory.initElements(driver, NavigateToInventoryFormPage.class);
        fillInInventoryForm = PageFactory.initElements(driver, FillInInventoryForm.class);
        viewInvoice = PageFactory.initElements(driver, ViewInvoice.class);
    }

    @AfterMethod// This method will run after each test method in the class
    public void tearDown(){
        browserFactory.closeBrowser();
    }
}
