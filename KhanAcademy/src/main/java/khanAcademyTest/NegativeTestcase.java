package khanAcademyTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import khanAcademyPage.BaseTest;
import khanAcademyPage.LoginPage;
import khanAcademyPage.SearchPage;
import Utils.ScreenshotUtil;

import java.time.Duration;

public class NegativeTestcase extends BaseTest {

    private static final Logger logger = LogManager.getLogger(NegativeTestcase.class);

    @Test
    public void testInvalidLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.info("Test started: testInvalidLogin");
        test = extent.createTest("testInvalidLogin").info("Testing login with invalid credentials");

        try {
            LoginPage loginPage = new LoginPage(driver);
            test.info("Entering invalid credentials");
            loginPage.login("invalid_user@example.com", "wrongpassword");
            Thread.sleep(3000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("/courses"), "Login should have failed but redirected to /courses");
            test.pass("Invalid login handled correctly, stayed on login page or showed error");
            logger.info("Invalid login test passed, current URL: {}", currentUrl);

        } catch (Exception e) {
            test.fail("Test case failed: " + e.getMessage());
            String screenshotPath = ScreenshotUtil.capture(driver, "testInvalidLogin");
            test.addScreenCaptureFromPath(screenshotPath);
            logger.error("Test case failed", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("Test completed: testInvalidLogin");
    }
    @Test
    public void testSpecialCharacterSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("Test started: testSpecialCharacterSearch");
        test = extent.createTest("testSpecialCharacterSearch").info("Testing search with special characters");

        try {
            LoginPage loginPage = new LoginPage(driver);
            test.info("Logging in with valid credentials");
            loginPage.login("ashwiniascendion@gmail.com", "ashwini15");
            Thread.sleep(3000);
            wait.until(ExpectedConditions.urlContains("/courses"));
            test.pass("Login successful");

            SearchPage searchPage = new SearchPage(driver);
            test.info("Opening search input");
            searchPage.openSearch();
            Thread.sleep(2000);

            String specialKeyword = "@#$%^&*";
            test.info("Searching with special characters: " + specialKeyword);
            searchPage.searchForCourse(specialKeyword);
            Thread.sleep(3000);

            // Optional: validate the search result behavior
            test.pass("Special character search performed. Validation depends on actual result handling.");
            logger.info("Special character search test passed with keyword: {}", specialKeyword);

        } catch (Exception e) {
            test.fail("Test case failed: " + e.getMessage());
            String screenshotPath = ScreenshotUtil.capture(driver, "testSpecialCharacterSearch");
            test.addScreenCaptureFromPath(screenshotPath);
            logger.error("Test case failed", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("Test completed: testSpecialCharacterSearch");
    }

}
