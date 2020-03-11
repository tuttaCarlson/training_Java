package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(ChromeDriver driver){
        super(driver);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(){
        click(By.name("selected[]"));
    }

    public void submitContactDeletion(){
        click(By.xpath("//input[@value='Delete']"));
        submitAlert();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getPhoneMobile());
        type(By.name("email"),contactData.getEmail());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }
}
