package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ...existing code...
    //Uses visible UI text
    //Less dependent on styling
    //Stable even if layout changes slightly
    @FindBy(xpath = "//button[.//span[text()='Login']]")
    WebElement loginButton;

    @FindBy(id = "login-email")
    WebElement loginEmailField;

    @FindBy(id = "login-password")
    WebElement loginPasswordField;

    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;

    @FindBy(xpath = "//h2[contains(text(), 'Welcome back')]")
    WebElement welcomeBackMessage;

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(loginEmailField))
                .sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(loginPasswordField))
                .sendKeys(password);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton))
                .click();
    }

    public void verifyLoginSuccess(String expectedMessage) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(welcomeBackMessage));
        String actualMessage = element.getText();
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Login failed. Expected: " + expectedMessage + ", but got: " + actualMessage);
        }
    }


}
