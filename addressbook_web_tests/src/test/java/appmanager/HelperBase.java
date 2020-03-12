package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    private WebDriver driver;

    public HelperBase(WebDriver driver){
        this.driver = driver;
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void submitAlert(){
        driver.switchTo().alert().accept();
    }
}
