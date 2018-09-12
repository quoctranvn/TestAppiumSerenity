package core;

import core.driver.AndroidDriverFactory;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import jxl.common.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    private static final AndroidDriver driver = new AndroidDriverFactory().getDriver();
    private static final int waitTime = 15;

    public static void clickJS(WebElement webElement) {
        waitForElementClickable(webElement);
        driver.executeScript("arguments[0].click();", webElement);
    }

    public static void clickByTextValue(String text, Boolean isUsingJS) {
        String xpath = "//*[text()=\"" + text + "\"]";
        WebElement e = driver.findElement(By.xpath(xpath));
        if(isUsingJS)
            clickJS(e);
        else e.click();
    }

    public static void doubleClick(WebElement webElement) {
        Actions action = new Actions(driver);
        action.moveToElement(webElement).doubleClick().build().perform();
    }

    public static void clickAction(WebElement webElement) {
        Actions action = new Actions(driver);
        action.moveToElement(webElement).perform();
        action.click().perform();
    }

    public static void verifyElementPresent(WebElement webElement) {
        Assert.verify(webElement.isDisplayed());
    }

    public static void setText(WebElement webElement, String value) {
        waitForElementVisible(webElement);
        webElement.clear();
        webElement.sendKeys(value);
    }

    public static void verifyTextPresent(String textValue) {
        String xpath = "//*[contains(text(),\"" + textValue + "\")]";
        WebElement e = driver.findElement(By.xpath(xpath));
        verifyElementPresent(e);
    }

    public static void verifyAlertText(String expectedValue) {
        Alert alert = driver.switchTo().alert();
        Boolean checkEqual = alert.getText().trim().equals(String.valueOf(expectedValue.trim()));
        Assert.verify(checkEqual);
    }

    public static void verifyTextContainedByTableRowValue(String rowValue, String expectedText) {
        String xpath = "//tr[td[contains(text(), \"" + rowValue + "\")]]/td[contains(., \""
                                                      + expectedText + "\")]";
        WebElement e = driver.findElement(By.xpath(xpath));
        verifyElementPresent(e);
    }

    public static String getTextByTableRowValue(String rowValue) {
        String xpath = "//tr[td[contains(text(),\"" + rowValue + "\")]]/td[not(contains(.,\"" + rowValue + "\"))]";
        WebElement e = driver.findElement(By.xpath(xpath));
        return e.getText().trim();
    }

    public static void waitForElementClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitForElementVisible(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForAlert(int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (NoAlertPresentException e) {
            e.getMessage();
        }
    }
}
