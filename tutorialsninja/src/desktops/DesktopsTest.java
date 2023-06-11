package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utils.Utility;

import java.time.Duration;
import java.util.List;

public class DesktopsTest extends Utility {

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
    public void verifyProductArrangeInAlphaBaticalOrder() {
        Actions actions = new Actions(driver);
        WebElement desktops =
                driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]/a"));//lcoating 'Desktops' for mouse hover
        WebElement showAllDesktopsLink = driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]/div/a"));
        actions.moveToElement(desktops).moveToElement(showAllDesktopsLink).click().build().perform();

        WebElement dropDown = driver.findElement(By.xpath("//select[@id='input-sort']"));//locating sort-by dropdown box
        Select select = new Select(dropDown);//select class
        select.selectByVisibleText("Name (Z - A)");//selecting from dropdown
        String actualVerifyText = getTextFromElement(By.xpath("//select[@id='input-sort']/option[3]"));//locating sortby dropdown
        String expectedVerifyText = "Name (Z - A)";//verifying the text Z-A sorting
        Assert.assertEquals("Verify Z to A Order", expectedVerifyText, actualVerifyText);//comparing actual and expexted texts
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {

        clickOnElement(By.xpath("//*[@id=\"form-currency\"]/div/button/span"));
        clickOnElement(By.xpath("//*[@id=\"form-currency\"]/div/ul/li[2]/button"));

        mouseHover(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]/a"));//locating 'Desktops' for mousehover
        selectMenu("Show AllDesktops");

        //Sort-By dropdown 'A-Z'
        WebElement dropDown = driver.findElement(By.xpath("//select[@id='input-sort']"));//locating sort-by drop down box
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name (A - Z)");// selecting sort by dropdown
        String actualVerifyText = getTextFromElement(By.xpath("//select[@id='input-sort']/option[2]"));//locating sort-by dropdown
        String expectedVerifyText = "Name (A - Z)";//verifying by A-Z text
        Assert.assertEquals("Verify A to Z Order", expectedVerifyText, actualVerifyText);

        //Verifying Text 'HP LP3065'
        mouseHover(By.xpath("//a[normalize-space()='HP LP3065']"));//div[@id='content']/div[4]/div[3]/div/div[2]/div/h4"))
        String actualHPLaptopText = getTextFromElement(By.xpath("//*[@id='content']/div/div[2]/h1"));
        String expectedHPLaptopText = "HP LP3065";
        Assert.assertEquals("Verify HP Laptop", expectedHPLaptopText, actualHPLaptopText);

        // Delivery date
        String year = "2023";
        String month = "August";

        driver.findElement(By.xpath("//div[@class='input-group date']/span/button")).click();

        while (true) {
            String monthyear = driver.findElement(By.xpath("//div[3]/div/div[1]/table/thead/tr[1]/th[2]")).getText();
            String arr[] = monthyear.split(" ");
            String mon = arr[0];
            String year1 = arr[1];

            if (mon.equalsIgnoreCase(month) && year1.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[3]/div/div[1]/table/thead/tr[1]/th[3]"));
            }
        }
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/table/tbody/tr[4]/td[3]")).click();

        //Entering Qty 1
        WebElement quantity = driver.findElement(By.xpath("//input[@id='input-quantity']"));// locating input box for quantity
        quantity.clear();
        quantity.sendKeys("1");
        clickOnElement(By.xpath("//button[@id='button-cart']"));// Add to cart button for shopping

        //Verifying alert message
        String actualVerifyShoppingCart = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));//locating actual alert text
        String expectedVerifyShoppingCart = "Success: You have added HP LP3065 to your shopping cart!\n" + "×";//expected alert text
        Assert.assertEquals("Verify shopping added to cart", expectedVerifyShoppingCart, actualVerifyShoppingCart);//comparing alert texts

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        //2.10 Click on link 'shopping cart' in success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));// clicking on alert message 'shopping cart' link

        //Verify the Text 'Shopping Cart'
        String actualShoppingCartText = getTextFromElement(By.xpath("//a[contains(text(),'Shopping Cart')]"));
        String expectedShoppingCartText = "Shopping Cart";
        Assert.assertEquals("Verify Shopping Cart Text", expectedShoppingCartText, actualShoppingCartText);

        //Verifying Product Name 'HP LP3065'
        String actualProductName = getTextFromElement(By.xpath("//table[@class='table table-bordered']/tbody/tr/td/a[text()='HP LP3065']"));
        String expectedProductName = "HP LP3065";
        Assert.assertEquals("Verify Product name", expectedProductName, actualProductName);

        //Verify Delivery Date
        String actualDeliveryDate = getTextFromElement(By.xpath("//small[normalize-space()='Delivery Date:2011-04-22']"));
        String expectedDeliveryDate = "Delivery Date:2011-04-22";
        Assert.assertEquals("Verify Delivery Date",expectedDeliveryDate,actualDeliveryDate);

        //Verifying Model 'Product 21'
        String actualModel = getTextFromElement(By.xpath("//div[@id='checkout-cart']/div//form//tbody//td[3]"));
        String expectedModel = "Product 21";
        Assert.assertEquals("Verify Model", expectedModel, actualModel);

        //Verifying Total
        String actualTotal = getTextFromElement(By.xpath("//tbody//tr//td[6]"));
        String expectedTotal = "£74.73";
        Assert.assertEquals("Verify Total", expectedTotal, actualTotal);

    }

    @After
    public void tearDown(){closeBrowser();}

}
