package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageObjects.SigninPage;
import pageObjects.SignupPage;

public class TC003_AccountRegistrationTest extends BaseClass {

    @Test
    public void account_registration() throws InterruptedException {
        logger.info("*** Starting TC003_AccountRegistrationTest ***");
        try {
            Faker faker = new Faker(); // Initialize Faker for random data generation
            
            SigninPage signinPage = new SigninPage(driver);
            signinPage.clickSignUpBtn();
            logger.info("Clicked on Sign-Up button");
            
            SignupPage signupPage = new SignupPage(driver);
            signupPage.enterUserName(faker.name().username());
            logger.info("Entered username");
            
            signupPage.enterFirstName(faker.name().firstName());
            logger.info("Entered first name");
            
            signupPage.enterLastName(faker.name().lastName());
            logger.info("Entered last name");
            
            String email = faker.internet().emailAddress(); // Random email
            signupPage.enterEmailAddress(email);
            logger.info("Entered email address");
            
            signupPage.enterConfirmEmailAddress(email);
            logger.info("Entered confirm email address");
            
            signupPage.enterPassword(generatePassword());
            logger.info("Entered password.");
            
            signupPage.enterPhoneNumber(generatePhoneNumber());
            logger.info("Entered phone number");
            
            signupPage.clickSignUpButton();
            logger.info("Clicked on Sign-Up button");
            Thread.sleep(3000);
            
        
            
        } catch (Exception e) {
            logger.error("An error occurred during account registration test: ", e);
            Assert.fail("Test failed due to an exception.");
        }

        logger.info("*** Finished TC003_AccountRegistrationTest ***");
    }

	
}
