package khanAcademyTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import khanAcademyPage.BaseTest;
import khanAcademyPage.LoginPage;
import khanAcademyPage.LogoutPage;
import khanAcademyPage.ProgressPage;
import khanAcademyPage.SearchPage;
import Utils.ScreenshotUtil;

import java.time.Duration;

public class CourseAutomationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CourseAutomationTest.class);

    @Test
    public void testCourseFlow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.info("Test started: testCourseFlow");
        test.info("Starting testCourseFlow for Khan Academy");

        try {
            // Login
            test.info("Attempting to log in");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("ashwiniascendion@gmail.com", "ashwini15");
            Thread.sleep(3000);
            wait.until(ExpectedConditions.urlContains("/courses"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/courses"), "Login failed or did not redirect properly.");
            logger.info("Login successful. URL: {}", driver.getCurrentUrl());
            test.pass("Login successful");

            // Search and play course
            SearchPage searchPage = new SearchPage(driver);
            test.info("Opening search input");
            searchPage.openSearch();
            Thread.sleep(2000);

            test.info("Searching for course: 'art'");
            searchPage.searchForCourse("art");
            Thread.sleep(2000);

            test.info("Selecting the 3rd course from search results");
            searchPage.selectSearchResult(3);
            Thread.sleep(3000);

            test.info("Interacting with course video");
            searchPage.playCourseVideo();
            Thread.sleep(5000);
            test.pass("Course video interaction successful");

            // Navigate to Progress
            ProgressPage progressPage = new ProgressPage(driver);
            test.info("Navigating to Progress page");
            progressPage.goToProgressPage();
            Thread.sleep(3000);
            Assert.assertTrue(driver.getCurrentUrl().contains("progress"), "Progress page not loaded.");
            test.pass("Progress page loaded successfully");
            logger.info("Progress page verified");

            // Logout
            LogoutPage logoutPage = new LogoutPage(driver);
            test.info("Logging out of application");
            logoutPage.logout();
            Thread.sleep(2000);
            test.pass("Logout successful");
            logger.info("Logout completed");

            test.pass("Test case testCourseFlow passed successfully");

        } catch (Exception e) {
            test.fail("Test case failed: " + e.getMessage());
            String screenshotPath = ScreenshotUtil.capture(driver, "testCourseFlow");
            test.addScreenCaptureFromPath(screenshotPath);
            logger.error("Test case failed", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("Test completed: testCourseFlow");
    }
}
