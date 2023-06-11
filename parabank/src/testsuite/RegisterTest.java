package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utils.Utility;

public class RegisterTest extends Utility {

    String baseUrl = "https://parabank.parasoft.com/parabank/index.htm";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyThatSigningUpPageDisplay() {
        clickOnElement(By.xpath("//div[@id='loginPanel']/p[2]/a"));
        String actualVerifyText = getTextFromElement(By.xpath("//div[@id='rightPanel']/h1"));
        String expectedVerifyText = "Signing up is easy!";
        Assert.assertEquals("Verify Signing-up Text", expectedVerifyText, actualVerifyText);
    }

    @Test
    public void userSholdRegisterAccountSuccessfully() {
        clickOnElement(By.xpath("//div[@id='loginPanel']/p[2]/a"));
        sendKeysToElement(By.xpath("//input[@id='customer.firstName']"), "Rudra");
        sendKeysToElement(By.xpath("//input[@id='customer.lastName']"), "Shivaay");
        sendKeysToElement(By.xpath("//input[@id='customer.address.street']"), "157A, Sidebrook Road");
        sendKeysToElement(By.xpath("//input[@id='customer.address.city']"), "Manchaster");
        sendKeysToElement(By.xpath("//input[@id='customer.address.state']"), "Hemel Hampstead");
        sendKeysToElement(By.xpath("//input[@id='customer.address.zipCode']"), "HM1012");
        sendKeysToElement(By.xpath("//input[@id='customer.phoneNumber']"), "012345678");
        sendKeysToElement(By.xpath("//input[@id='customer.ssn']"), "abcd");
        sendKeysToElement(By.xpath("//input[@id='customer.username']"), "RudraShivaay312");
        sendTextToElement(By.xpath("//input[@id='customer.password']"), "312312");
        sendKeysToElement(By.xpath("//input[@id='repeatedPassword']"), "312312");
        clickOnElement(By.xpath("//td[@colspan='2']/input[1]"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}