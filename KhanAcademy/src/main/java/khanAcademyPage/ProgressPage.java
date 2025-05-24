package khanAcademyPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(ProgressPage.class);

    private By userMenu = By.xpath("//button[contains(@aria-label, 'ascendion')]");
    private By learnerHomeLink = By.xpath("//a[@data-testid='learner-home-button']");
    private By progressButton = By.xpath("//span[contains(text(),'Progress')]");

    public ProgressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToProgressPage() {
        wait.until(ExpectedConditions.elementToBeClickable(userMenu)).click();
        logger.info("Opened user menu.");

        wait.until(ExpectedConditions.elementToBeClickable(learnerHomeLink)).click();
        logger.info("Clicked learner home link.");

        wait.until(ExpectedConditions.elementToBeClickable(progressButton)).click();
        logger.info("Navigated to Progress page.");
    }
}
