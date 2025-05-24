package khanAcademyPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchPage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchButton = By.xpath("//*[@id=\"top-header-container\"]/nav/div[1]/div[1]/div[2]/button");
    private By searchInput = By.xpath("//*[contains(@aria-label, 'Search for courses, skills, and videos')]");
    private By playVideoButton = By.xpath("//button[@aria-label='Play video']");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void openSearch() throws InterruptedException {
        logger.info("Clicking on search button.");
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBtn.click();
        Thread.sleep(1500);
    }

    public void searchForCourse(String courseName) throws InterruptedException {
        logger.info("Typing course name: {}", courseName);
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box is not visible.");
        searchBox.sendKeys(courseName + Keys.ENTER);
        Thread.sleep(2000);
    }

    public void selectSearchResult(int resultIndex) throws InterruptedException {
        logger.info("Selecting course result at index {}", resultIndex);
        String resultXPath = String.format("//*[@id='indexed-search-results']/div/ul/li[%d]/div/a", resultIndex);
        WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resultXPath)));
        Assert.assertTrue(courseLink.isDisplayed(), "Expected search result is not visible.");
        courseLink.click();
        Thread.sleep(3000);
    }

    public void playCourseVideo() throws InterruptedException {
        logger.info("Attempting to play the course video.");
        WebElement playBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(playVideoButton));
        Assert.assertTrue(playBtn.isDisplayed(), "Play video button is not visible — course page may not have loaded.");
        playBtn.click();
        Thread.sleep(3000);
    }
}
