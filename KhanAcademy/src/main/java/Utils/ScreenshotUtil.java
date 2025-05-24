package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}  // Utility class ⇒ no public constructor

    /** Captures a screenshot and returns the absolute path of the saved file. */
    public static String capture(WebDriver driver, String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName  = testName + "_" + timeStamp + ".png";
        String dirPath   = "test-output" + File.separator + "screenshots";
        File screenshot  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest        = new File(dirPath + File.separator + fileName);
        
        try {
            dest.getParentFile().mkdirs();  // Ensure directory exists
            FileUtils.copyFile(screenshot, dest);
            System.out.println("✅ Screenshot saved to: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Could not save screenshot: " + e.getMessage());
        }

        return dest.getAbsolutePath(); // Use this path in ExtentReports
    }

	public static String captureScreenshot(WebDriver driver, String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
