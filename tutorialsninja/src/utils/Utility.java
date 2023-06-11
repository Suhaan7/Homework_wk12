package utils;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    public void mouseHover(By by) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(by);
        actions.moveToElement(element).moveToElement(element).click().build().perform();

        }

        public void clearTextFromField(By by){
        driver.findElement(by).sendKeys(Keys.DELETE);
        }
        }