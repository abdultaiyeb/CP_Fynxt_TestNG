package pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class BasePage {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    private static final int RETRY_LIMIT = 3; // Retry limit

    // Constructor with customizable timeout and polling interval
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20)) // Default timeout of 10 seconds
                .pollingEvery(Duration.ofMillis(500)) // Poll every 500 milliseconds
                .ignoring(NoSuchElementException.class); // Ignore this exception during polling
        PageFactory.initElements(driver, this);
    }

    // Overloaded constructor to allow custom timeout and polling intervals
    public BasePage(WebDriver driver, long timeoutInSeconds, long pollingInMillis) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        PageFactory.initElements(driver, this);
    }

    // Wait for an element to be visible
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for an element to be clickable
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Generic method to click after waiting for the element to be clickable with retry
    public void waitAndClick(WebElement element) {
        for (int i = 0; i < RETRY_LIMIT; i++) {
            try {
                waitForElementToBeClickable(element);
                element.click();
                return; // Exit if click is successful
            } catch (Exception e) {
                System.out.println("Click failed on attempt " + (i + 1) + ". Retrying...");
                if (i == RETRY_LIMIT - 1) {
                    throw new RuntimeException("Unable to click on element: " + element + " due to " + e.getMessage());
                }
            }
        }
    }

    // Generic method to send keys after waiting for visibility with retry
    public void waitAndSendKeys(WebElement element, String text) {
        for (int i = 0; i < RETRY_LIMIT; i++) {
            try {
                waitForVisibility(element);
                element.clear(); // Clear the field before entering text (optional based on use case)
                element.sendKeys(text);
                return; // Exit if sending keys is successful
            } catch (Exception e) {
                System.out.println("Send keys failed on attempt " + (i + 1) + ". Retrying...");
                if (i == RETRY_LIMIT - 1) {
                    throw new RuntimeException("Unable to send keys to element: " + element + " due to " + e.getMessage());
                }
            }
        }
    }
}
