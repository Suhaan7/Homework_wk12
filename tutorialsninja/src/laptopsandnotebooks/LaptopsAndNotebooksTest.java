package laptopsandnotebooks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Utility;

import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php?";

    public void selectMenu(String menu) {
        List<WebElement> topMenuList = driver.findElements(By.xpath("//nav[@id='menu']//ul/li[contains(@class, 'open')]/div/child::*"));
        try {
            for (WebElement element : topMenuList) {
                if (element.getText().equalsIgnoreCase(menu)) {
                    element.click();
                }
            }
        } catch (StaleElementReferenceException e) {
            topMenuList = driver.findElements(By.xpath("//nav[@id='menu']//ul/li[contains(@class, 'open')]/div/child::*"));
        }
    }

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {

        //Currency change to £
        clickOnElement(By.xpath("//*[@id=\"form-currency\"]/div/button/span"));
        clickOnElement(By.xpath("//*[@id=\"form-currency\"]/div/ul/li[2]/button"));

        // 1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHover(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[2]"));

        //1.2 Click on “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");

        //1.3  Select Sort By "Price (High > Low)"
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='input-sort']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (High > Low)");

        //1.4 Verify the Product price will arrange in High to Low order
        String actualVerifyText = getTextFromElement(By.xpath("//select[@id='input-sort']/option[5]"));
        String expectedVerifyText = "Price (High > Low)";
        Assert.assertEquals("Verify High to Low order", expectedVerifyText, actualVerifyText);
    }

        @Test
        public void verifyThatUserPlaceOrderSuccessfully() {
            // 2.1 Mouse hover on Laptops & Notebooks Tab.and click
            mouseHover(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[2]"));

            //2.2 Click on “Show All Laptops & Notebooks”
            selectMenu("Show AllLaptops & Notebooks");

            //2.3  Select Sort By "Price (High > Low)"
            WebElement dropdown = driver.findElement(By.xpath("//select[@id='input-sort']"));
            Select select = new Select(dropdown);
            select.selectByVisibleText("Price (High > Low)");

            // 2.4 Select product Macbook
            clickOnElement(By.xpath("//a[normalize-space()='MacBook']"));

            // 2.5 Verify the Text 'Macbook'
            String actualMacbookText = getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']"));
            String expectedMacbookText = "MacBook";
            Assert.assertEquals("Verify Macbook", expectedMacbookText, actualMacbookText);

            //2.6 Click on Add to Cart
            clickOnElement(By.xpath("//button[@id='button-cart']"));

            //2.7 Verify Success message of shopping cart
            String actualVerifyShoppingCart = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
            String expectedVerifyShoppingCart = "Success: You have added MacBook to your shopping cart!\n" + "×";
            Assert.assertEquals("Verify Shopping Cart alert message", expectedVerifyShoppingCart, actualVerifyShoppingCart);

            //2.8 Click on 'shopping cart' link into success message
            clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

            //2.9 Verify the text "Shopping Cart"
            String actualShoppingCartText = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
            String expectedShoppingCartText = "Shopping Cart  (0.00kg)";
            Assert.assertEquals("Verify Shopping Cart Text", expectedShoppingCartText, actualShoppingCartText);

            //2.10 Verify the Product name "Macbook"
            String actualProductName = getTextFromElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
            String expectedProductName = "MacBook";
            Assert.assertEquals("Verify text MacBook", expectedProductName, actualProductName);

            //2.11 Change quantity "2"
            WebElement quantity = driver.findElement(By.xpath("//input[@value='1']"));
            quantity.clear();
            quantity.sendKeys("2");

            //2.12 Click on Update tab
            clickOnElement(By.xpath("//i[@class='fa fa-refresh']"));

            //2.13 Verify the message "Success: You have modified your shopping cart!"
            String actualSuccessMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
            String expectedSuccessMessage = "Success: You have modified your shopping cart!\n" + "×";
            Assert.assertEquals("Verify Success Message", expectedSuccessMessage, actualSuccessMessage);

            //2.14
            String actualTotal = getTextFromElement(By.xpath("//tbody//tr//td[6]"));
            String expectedTotal = "£737.45";
            Assert.assertEquals("Verify Total", expectedTotal, actualTotal);

            //2.15 Click on Checkout button
            clickOnElement(By.xpath("//a[@class='btn btn-primary']"));

            //2.16 Verify the text Checkout
            String actualCheckoutText = getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']"));
            String expectedCheckoutText = "Checkout";
            Assert.assertEquals("Verify text Checkout", expectedCheckoutText, actualCheckoutText);

            //2.17 Verify the text "New Customer"
            String actualNewCustomer = getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']"));
            String expectedNewCustomer = "New Customer";
            Assert.assertEquals("Verify New Customer Text", expectedNewCustomer, actualNewCustomer);

            //2.18 Click on “Guest Checkout” radio button
            clickOnElement(By.xpath("//label[normalize-space()='Guest Checkout']"));

            //2.19 Click on “Continue” tab
            clickOnElement(By.xpath("//input[@id='button-account']"));

            //2.20 Fill the mandatory fields
            driver.findElement(By.xpath("//input[@id='input-payment-firstname']")).sendKeys("Rudra");
            driver.findElement(By.xpath("//input[@id='input-payment-lastname']")).sendKeys("Shivyaa");
            driver.findElement(By.xpath("//input[@id='input-payment-email']")).sendKeys("rudrashivaay312@gmail.com");
            driver.findElement(By.xpath("//input[@id='input-payment-telephone']")).sendKeys("0123456789");
            driver.findElement(By.xpath("//input[@id='input-payment-address-1']")).sendKeys("213 Stratford Road");
            driver.findElement(By.xpath("//input[@id='input-payment-city']")).sendKeys("London");
            driver.findElement(By.xpath("//input[@id='input-payment-postcode']")).sendKeys("NS23SH");
            WebElement dropDown = driver.findElement(By.xpath("//select[@id='input-payment-country']"));
            Select select1 = new Select(dropDown);
            select1.selectByVisibleText("United Kingdom");
            WebElement dropDown2 = driver.findElement(By.xpath("//select[@id='input-payment-zone']"));
            Select select2 = new Select(dropDown2);
            select2.selectByVisibleText("Northamptonshire");

            //2.21 Click on “Continue” Button
            clickOnElement(By.xpath("//input[@id='button-guest']"));

            //2.22 Add Comments About your order into text area
            driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("Very Good Shopping experience!");

            //2.23  Check the Terms & Conditions check box
            clickOnElement(By.xpath("//input[@type='checkbox']"));

            //2.24  Click on “Continue” button
            clickOnElement(By.xpath("//input[@id='button-payment-method']"));

            //2.25  Verify the message “Warning: Payment method required!”
            String actualPaymentWarning = getTextFromElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
            String expectedPaymentWarning = "Warning: Payment method required!\n" + "×";
            Assert.assertEquals("Verify Payment Warning message", expectedPaymentWarning, actualPaymentWarning);

        }

        @Test
        public void tearDown(){closeBrowser();}

    }

