package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ApplicationManager {
    WebDriver driver;
    WebDriverWait wait;
    private final Properties properties;
    private String browser;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(
                new File(String.format("src/test/resources/%s.properties", target))));
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
        driver.get(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.adminLogin")
                ,properties.getProperty("web.adminPassword"));
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
