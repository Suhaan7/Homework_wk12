package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utils.Utility;

public class LoginTest extends Utility {
    String baseUrl = "https://parabank.parasoft.com/parabank/index.htm";
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        sendTextToElement(By.xpath("//input[@name='username']"), "RudraShivaay312");
        sendTextToElement(By.xpath("//input[@name='password']"), "312312");
        clickOnElement(By.xpath("//input[@value='Log In']"));
        String actualVerifyText = getTextFromElement(By.xpath("//h1[@class='title']"));
        String expectedVerifyText = "Accounts Overview";
        Assert.assertEquals("Verify Account Login Text", expectedVerifyText, actualVerifyText);
    }
    @Test
    public void verifyTheErrorMessage() {
        sendTextToElement(By.xpath("//input[@name='username']"), "RudraShivaay");
        sendTextToElement(By.xpath("//input[@name='password']"), "312");
        clickOnElement(By.xpath("//input[@value='Log In']"));
        String actualErrorMessage = getTextFromElement(By.xpath("//p[@class='error']"));
        String expectedErrorMessage = "The username and password could not be verified.";
        Assert.assertEquals("Verify Error Message", expectedErrorMessage, actualErrorMessage);
    }
    @Test
    public void userShouldLogOutSuccessfully() {
        sendTextToElement(By.xpath("//input[@name='username']"), "RudraShivaay312");
        sendTextToElement(By.xpath("//input[@name='password']"), "312312");
        clickOnElement(By.xpath("//input[@value='Log In']"));
        clickOnElement(By.xpath("//div[@id='leftPanel']/ul/li[8]/a"));
        String actualVerifyText = getTextFromElement(By.xpath("//div[@id='leftPanel']/h2"));
        String expectedVerifyText = "Customer Login";
        Assert.assertEquals("Verify Customer Login Text", expectedVerifyText, actualVerifyText);
    }

    @After
    public void tearDown(){closeBrowser();}

}
