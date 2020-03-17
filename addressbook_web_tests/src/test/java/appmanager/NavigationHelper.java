package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void openGroupPage() {
        click(By.linkText("groups"));
    }

    public void openHomePage() { click(By.linkText("home")); }
}
