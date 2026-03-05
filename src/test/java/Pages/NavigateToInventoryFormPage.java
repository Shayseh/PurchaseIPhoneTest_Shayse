package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigateToInventoryFormPage {

    WebDriver driver;
    WebDriverWait wait;

    public NavigateToInventoryFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(., '\uD83D\uDCDALearn')]")
    WebElement learnDropdownButton;

    @FindBy(xpath = "//button[contains(@class,'nav-dropdown-item') and contains(.,'Learning Materials')]")
    WebElement learningMaterialsOption;

    @FindBy(id = "tab-btn-web")
    WebElement webAutomationAdvanceButton;

    public void clickLearnDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(learnDropdownButton)).click();
    }

    public void clickLearningMaterials() {
        wait.until(ExpectedConditions.elementToBeClickable(learningMaterialsOption)).click();
    }

    public void clickWebAutomationAdvance() {
        wait.until(ExpectedConditions.elementToBeClickable(webAutomationAdvanceButton)).click();
    }

}
