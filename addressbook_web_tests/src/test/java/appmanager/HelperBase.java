package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class HelperBase {
    WebDriver driver;
    WebDriverWait wait;

    public HelperBase(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null){
            String existingText = driver.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    public void attach(By locator, File file) {
        if (file != null){
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void submitAlert(){
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div[id='nav']")));
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }
}
