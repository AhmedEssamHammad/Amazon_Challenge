package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openURL(String url) {
        driver.navigate().to(url);
        System.out.println("Navigating to " + url);
    }

    public void visibilityWait(By element) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    public WebElement findElement(By element) {
        return driver.findElement(element);
    }


    protected void sendText(By element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
        findElement(element).sendKeys(text);
    }


    public String getElementText(By element) {
        return driver.findElement(element).getText();
    }


    public boolean checkElementIsDisplayed(By element) {
        try {
            visibilityWait(element);
            driver.findElement(element).isDisplayed();
        } catch (Exception e) {
            System.out.println("Couldn't find element" + element);
            return false;
        }
        return true;
    }


    public void clickDropDownItemByVisibleText(By element, String text) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(element));
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
        } catch (Exception e) {
            System.out.println("Couldn't find dropDown" + element);
        }
    }

    public void scrollToElement(By element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'nearest', inline: 'start'});",
                wait.until(ExpectedConditions.elementToBeClickable(element)));
    }

    public void scrollToElementThenClick(By element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", wait.until(ExpectedConditions.elementToBeClickable(element)));
        clickElement(element);
    }

    public void scrollToElementThenClickUsingJS(By element) {

        WebElement targetElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        js.executeScript("arguments[0].click();", targetElement);
    }


    public void clickElementUsingJS(By element) {
        WebElement targetElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetElement);
    }

    public int getPriceAsInteger(By itemPrice) throws ParseException {
        Number number = NumberFormat.getNumberInstance().parse(getElementText(itemPrice));
        return number.intValue();
    }

    public int getDecimalPriceAsInteger(By itemPrice) throws ParseException {

        String cleanedString = getElementText(itemPrice).replace("EGP", "").trim();
        cleanedString = cleanedString.replace(",", "").split("\\.")[0];
        return Integer.parseInt(cleanedString);
    }


}
