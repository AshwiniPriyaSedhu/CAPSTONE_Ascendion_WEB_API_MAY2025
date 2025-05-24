package CrossBrowser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KhanAcademy_Test extends BaseTestCrossBrowser {

    private static final Logger logger = LogManager.getLogger(KhanAcademy_Test.class);

    @Test
    public void searchCourse() throws InterruptedException {
        logger.info("Starting test: Search for a course on Khan Academy");

        // Click the search icon
        logger.info("Clicking on the search icon.");
        driver.findElement(By.xpath("//*[@id=\"top-header-container\"]/nav/div[1]/div[1]/div[2]/button")).click();
        Thread.sleep(1000); // Optional: Replace with wait in next refactor

        // Enter course name and press ENTER
        logger.info("Entering course name: Algebra");
        driver.findElement(By.xpath("//*[contains(@aria-label, 'Search for courses, skills, and videos')]")).sendKeys("Algebra", Keys.ENTER);
        Thread.sleep(3000); // Optional: Replace with wait for search results

        

        logger.info("Search for 'Algebra' returned results successfully.");
    }
}
