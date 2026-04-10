package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Screenshots {

    static final String screenshotDir = System.getProperty("user.dir") + "/target/screenshots";

    public static void takesSnapShot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotDir, screenshotName + ".jpeg");

        try {
            FileUtils.copyFile(file, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
