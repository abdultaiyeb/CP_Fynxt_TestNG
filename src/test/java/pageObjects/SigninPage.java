package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SigninPage extends BasePage {

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id='btnLoginSignupFlip']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailTxt;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordTxt;

    @FindBy(xpath = "//button[@id='btnCustomerLogin']")
    private WebElement signInBtn;

    
    @FindBy(xpath="//div[@aria-live='assertive']//div") 
    private WebElement errorTxt;
    
    
    // Method to click the sign-up button
    public void clickSignUpBtn() {
        waitAndClick(signUpBtn);
    }

    // Method to enter email or username
    public void enterEmailTxt(String username) {
        waitAndSendKeys(emailTxt, username);
    }

    // Method to enter password
    public void enterPasswordTxt(String password) {
        waitAndSendKeys(passwordTxt, password);
    }

    // Method to click the sign-in button
    public void clickSignInBtn() {
        waitAndClick(signInBtn);
    }
    
    public boolean isErrorMessageVisible() {
        try {
            waitForVisibility(errorTxt);
            return errorTxt.isDisplayed();
        } catch (Exception e) {
            // Handle any exceptions (e.g., element not found, not visible)
            return false;
        }
    }
}
