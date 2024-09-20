package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends BasePage{


public SignupPage(WebDriver driver) {
		super(driver);
	}


@FindBy(xpath="//input[@id='Username']")  private WebElement userNameTxt;
@FindBy(xpath="//input[@id='FirstName']")  private WebElement firstNameTxt;
@FindBy(xpath="//input[@id='LastName']")  private WebElement lastNameTxt;
@FindBy(xpath="//input[@id='Email']")  private WebElement emailAddressTxt;
@FindBy(xpath="//input[@id='ConfirmEmail']")  private WebElement confirmEmailAddressTxt;
@FindBy(xpath="//input[@id='Password']")  private WebElement passwordTxt;
@FindBy(xpath="//input[@id='MobileNumber']")  private WebElement phoneNumberTxt;
@FindBy(xpath="//button[@id='btnCustomerSignup']")  private WebElement signUpBtn;


public void enterUserName(String userName) {
    waitAndSendKeys(userNameTxt, userName);
}

public void enterFirstName(String firstName) {
    waitAndSendKeys(firstNameTxt, firstName);
}

public void enterLastName(String lastName) {
    waitAndSendKeys(lastNameTxt, lastName);
}

public void enterEmailAddress(String email) {
    waitAndSendKeys(emailAddressTxt, email);
}

public void enterConfirmEmailAddress(String confirmEmail) {
    waitAndSendKeys(confirmEmailAddressTxt, confirmEmail);
}

public void enterPassword(String password) {
    waitAndSendKeys(passwordTxt, password);
}

public void enterPhoneNumber(String phoneNumber) {
    waitAndSendKeys(phoneNumberTxt, phoneNumber);
}

public void clickSignUpButton() {
    waitAndClick(signUpBtn);
}


}
