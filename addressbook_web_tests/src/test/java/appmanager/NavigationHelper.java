package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void homePage() {
        click(By.linkText("home"));
    }
}
