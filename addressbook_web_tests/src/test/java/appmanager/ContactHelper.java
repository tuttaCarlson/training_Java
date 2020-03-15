package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver){
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

    public void submitContactModification(){
        click(By.name("update"));
    }

    public void initContactModification(){
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getPhoneMobile());
        type(By.name("email"), contactData.getEmail());
        if (creation){
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData
                    .getGroup());
        } else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
