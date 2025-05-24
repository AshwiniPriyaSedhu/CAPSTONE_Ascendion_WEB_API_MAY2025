package khanAcademyPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(LogoutPage.class);

    private By profileButton = By.xpath("//button[contains(@aria-label, 'ascendion')]");
    private By logoutLink = By.xpath("//a[@data-testid='user-settings-logout-link']");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        logger.info("Opened profile menu.");

        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        logger.info("Clicked logout link.");
    }
}
