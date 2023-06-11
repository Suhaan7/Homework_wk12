package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    public void selectMenu(By by, String menu) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        Actions actions = new Actions(driver);
        WebElement desktops = driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]/a"));
        actions.moveToElement(desktops).click().build().perform();
        selectMenu(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]/div/a"), "Show All Desktops");
        String actualVerifyDesktopText = getTextFromElement(By.xpath("//div[@id='content']/h2"));
        String expectedVerifyDesktopText = "Desktops";
        Assert.assertEquals("Verify Desktops Text", expectedVerifyDesktopText, actualVerifyDesktopText);
    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        Actions actions = new Actions(driver);
        WebElement laptopAndNotebooks = driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[2]"));
        actions.moveToElement(laptopAndNotebooks).click().build().perform();
        selectMenu(By.xpath("//ul[@class='nav navbar-nav']/li[2]/div/a"), "Show AllLaptops & Notebooks");
        String actualVerifyLaNText = getTextFromElement(By.xpath("//div[@id='content']/h2"));
        String expectedVerifyLaNText = "Laptops & Notebooks";
        Assert.assertEquals("Verify Laptops & Notebooks Text", expectedVerifyLaNText, actualVerifyLaNText);
    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        Actions actions = new Actions(driver);
        WebElement components = driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[3]"));
        actions.moveToElement(components).click().build().perform();
        selectMenu(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[3]/div/a"), "Show All Components");
        String actualComponentsText = getTextFromElement(By.xpath("//div[@id='content']/h2"));
        String expectedComponentsText = "Components";
        Assert.assertEquals("Verify Components Text", expectedComponentsText, actualComponentsText);
    }

@After
    public void tearDown (){closeBrowser();}

}
