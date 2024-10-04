package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SigninPage extends BasePage {

    @FindBy(xpath = "//button[@id='btnLoginSignupFlip']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailTxt;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordTxt;

    @FindBy(xpath = "//button[@id='btnCustomerLogin']")
    private WebElement signInBtn;

    @FindBy(xpath = "//div[@aria-live='assertive']//div")
    private WebElement errorTxt;

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    // Method to click the sign-up button
    public void clickSignUp() {
        waitAndClick(signUpBtn);
    }

    // Method to enter email or username
    public void enterEmail(String username) {
        waitAndClearAndSendKeys(emailTxt, username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        waitAndClearAndSendKeys(passwordTxt, password);
    }

    // Method to click the sign-in button
    public void clickSignIn() {
        waitAndClick(signInBtn);
    }

    // Method to check if the error message is visible
    public boolean isErrorVisible() {
        return isElementVisible(errorTxt);
    }

    // Helper method to check if an element is visible
    private boolean isElementVisible(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
           
            return false;
        }
    }
}
