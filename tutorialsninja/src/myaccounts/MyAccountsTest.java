package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utility;

public class MyAccountsTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php?";

    //1.1 Method with parameter
    //1.2 s Method should click on the options whatever name is passed as parameter
    public void selectMyAccountOptions(By by, String option) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //1.1 1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        // 1.2  Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions(By.xpath("//a[normalize-space()='Register']"), "Register");
        //1.3 Verify the text “Register Account”.
        String actualRegisterText = getTextFromElement(By.xpath("//h1[normalize-space()='Register Account']"));
        String expectedRegisterText = "Register Account";
        Assert.assertEquals("Verify Register Account text", expectedRegisterText, actualRegisterText);
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //2.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        // 2.2  Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']"), "Login");
        //2.3 Verify the text “Returning Customer".
        String actualReturningCustomerText = getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']"));
        String expectedReturningCustomerText = "Returning Customer";
        Assert.assertEquals("Verify Returning Customer Text", expectedReturningCustomerText, actualReturningCustomerText);
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        //3.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions(By.xpath("//a[normalize-space()='Register']"), "Register");
        //3.3 Enter First Name
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Rudy");
        //3.4 Enter Last Name
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Shivy");
        //3.5 Enter Email
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("rudyshivy@gmail.com");
        //3.6 Enter Telephone
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("0123456789");
        //3.7 Enter Password
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Rush312");
        //3.8 Enter Password Confirm
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Rush312");
        //3.9 Select Subscribe Yes radio button
        clickOnElement(By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']"));
        // 3.10 Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        // 3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@value='Continue']"));
        // 3.12 Verify the message “Your Account Has Been Created!”
        String actualVerifyMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"));
        String expectedVerifyMessage = "Your Account Has Been Created!";
        Assert.assertEquals("Verify Account Creation Success Message",expectedVerifyMessage,actualVerifyMessage);
        // 3.13 Click on Continue button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
        // 3.14 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        // 3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']"),"Logout");
        // 3.16 Verify the text “Account Logout”
        String actualVerifyLogout = getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']"));
        String expectedVerifyLogout = "Account Logout";
        Assert.assertEquals("Verify Account Logout Text ",expectedVerifyLogout,actualVerifyLogout);
        // 3.17 Click on Continue button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully(){
        //4.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']"), "Login");
        //4.3 Enter Email address
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("rudyshivy@gmail.com");
        //4.5 Enter Password
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Rush312");
        //4.6 Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));
        //4.7 Verify text “My Account”
        String actualMyAccountText = getTextFromElement(By.xpath("//h2[normalize-space()='My Account']"));
        String expectedMyAccountText = "My Account";
        Assert.assertEquals("Verify My Account Text",expectedMyAccountText,actualMyAccountText);
        //4.8 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']"),"Logout");
        //4.10 Verify the text “Account Logout”
        String actualVerifyLogout = getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']"));
        String expectedVerifyLogout = "Account Logout";
        Assert.assertEquals("Verify Account Logout Text ",expectedVerifyLogout,actualVerifyLogout);
        //4.11 Click on Continue button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
    }

    @After
    public void tearDown (){closeBrowser();}
}
