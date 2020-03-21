package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver, WebDriverWait wait)
    { super(driver, wait); }

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//input[@type='submit']"));
    }


}
