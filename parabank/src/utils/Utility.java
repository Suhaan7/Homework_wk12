package utils;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Utility extends BaseTest {

    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public void sendTextToElement(By by, String text) {
        WebElement element = driver.findElement(by);
        element.sendKeys(text);
    }

    public void sendKeysToElement(By by, CharSequence keys) {
        driver.findElement(by).sendKeys(keys);
    }
}
