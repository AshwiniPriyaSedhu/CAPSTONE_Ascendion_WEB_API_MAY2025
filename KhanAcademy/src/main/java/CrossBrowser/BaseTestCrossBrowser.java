package CrossBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.Point;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utility.Constant;
import org.openqa.selenium.Dimension;

// other imports...

public class BaseTestCrossBrowser {

    public WebDriver driver;

    @Parameters("br_info")
    @BeforeTest
    public void launchApp(String browser) throws Exception {
        System.out.println("Launching browser: " + browser);

        Dimension halfScreen = new Dimension(960, 1080); // width x height

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().setPosition(new Point(500, 0)); // Right half
            driver.manage().window().setSize(halfScreen);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            driver.manage().window().setPosition(new Point(0, 0)); // Left half
            driver.manage().window().setSize(halfScreen);
        } else {
            driver = new ChromeDriver(); // fallback
            driver.manage().window().setPosition(new Point(0, 0));
            driver.manage().window().setSize(halfScreen);
        }

        driver.get(Constant.demoblaze_appURL);
        Thread.sleep(3000);
    }

    @AfterTest
    public void closeApp() throws Exception {
        Thread.sleep(3000);
        driver.quit();
    }
}
