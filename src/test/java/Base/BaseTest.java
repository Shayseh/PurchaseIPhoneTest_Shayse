package Base;

import Pages.LoginPage;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTest {

    BrowserFactory browserFactory = new BrowserFactory();

    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "edge";

    public final WebDriver driver = browserFactory.startBrowser(browserChoice, url);

    public LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
}
