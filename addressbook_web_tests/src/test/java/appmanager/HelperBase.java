package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    WebDriver driver;

    public HelperBase(WebDriver driver){
        this.driver = driver;
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

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void submitAlert(){
        driver.switchTo().alert().accept();
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
