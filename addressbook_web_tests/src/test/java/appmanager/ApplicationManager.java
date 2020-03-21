package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    WebDriver driver;
    WebDriverWait wait;
    private String browser;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        }
        wait = new WebDriverWait(driver, 10);
        groupHelper = new GroupHelper(driver, wait);
        navigationHelper = new NavigationHelper(driver, wait);
        sessionHelper = new SessionHelper(driver, wait);
        contactHelper = new ContactHelper(driver, wait);
        driver.get("http://localhost/addressbook/");
        sessionHelper.login("admin", "secret");
    }


    public ContactHelper contact() {
        return contactHelper;
    }

    public void stop() {
        driver.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() { return navigationHelper; }
}
