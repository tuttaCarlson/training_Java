package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationManager {
    ChromeDriver driver;
    private GroupHelper groupHelper;

    public void init() {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        groupHelper = new GroupHelper(driver);
        driver.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        driver.findElement(By.name("user")).click();
        driver.findElement(By.name("user")).sendKeys(username);
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void submitContactCreation(){
        driver.findElement(By.name("submit")).click();
    }

    public void fillContactForm(ContactData contactData){
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(contactData.getPhoneMobile());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void initContactCreation(){
        driver.findElement(By.linkText("add new")).click();
    }

    public void stop() {
        driver.quit();
    }

    public void openGroupPage() {
        driver.findElement(By.linkText("groups")).click();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }
}
