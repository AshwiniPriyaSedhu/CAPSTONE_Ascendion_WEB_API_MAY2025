package khanAcademyPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By cookieCloseButton = By.cssSelector("button.onetrust-close-btn-handler");
    private By loginButton = By.xpath("//a[contains(text(),'Log in')]");
    private By usernameField = By.id("username-field");
    private By passwordField = By.id("current-password-field");
    private By submitButton = By.cssSelector("button[data-testid='log-in-submit-button']");

    public void login(String username, String password) throws InterruptedException {
        try {
            WebElement closeCookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieCloseButton));
            closeCookieBtn.click();
            logger.info("Cookie banner closed.");
        } catch (Exception e) {
            logger.warn("Cookie popup not found or already dismissed.");
        }

        driver.findElement(loginButton).click();
        logger.info("Clicked login link.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        logger.info("Entered username.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        logger.info("Entered password.");

        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        logger.info("Clicked submit to log in.");
    }
}
