package khanAcademyPage;

import Utils.ConfigReader;
import Utils.ScreenshotUtil;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUpReport() {
        String reportPath = "test-output" + File.separator + "ExtentReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "Khan Academy Automation");
        extent.setSystemInfo("Tested By", "Ashwini Priya");
        extent.setSystemInfo("Date", "26/05/2025");
        logger.info("✅ ExtentReport initialized at: {}", reportPath);
    }

    //@Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, ITestResult result) {
        logger.info("Launching browser: {}", browser);
        ChromeOptions options = new ChromeOptions();

        // Use config file to read headless setting
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.khanacademy.org/");
        test = extent.createTest(result.getMethod().getMethodName());
        test.info("Navigated to Khan Academy");
        logger.info("✅ Test started: {}", result.getMethod().getMethodName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.capture(driver, result.getMethod().getMethodName());
            test.fail("❌ Test Failed: " + result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
            logger.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("✅ Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("⚠️ Test Skipped");
        }

        if (driver != null) {
            driver.quit();
            logger.info("🔻 Browser closed");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
        logger.info("📄 ExtentReport flushed and saved.");
    }
}
