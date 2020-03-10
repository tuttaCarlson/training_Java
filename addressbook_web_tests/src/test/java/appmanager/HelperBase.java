package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelperBase {
    private ChromeDriver driver;

    public HelperBase(ChromeDriver driver){
        this.driver = driver;
    }

    public void type(By locator, String name) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(name);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }
}
