package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver){
        super(driver);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index){
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void submitContactDeletion(){
        click(By.xpath("//input[@value='Delete']"));
        submitAlert();
    }

    public void submitContactModification(){
        click(By.name("update"));
    }

    public void initContactModification(int id){
        String url = "http://localhost/addressbook/edit.php?id=" + id;
        driver.get(url);
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element: elements){
            List<WebElement> colValues = element.findElements(By.tagName("td"));
            String id = colValues.get(0).findElement(By.tagName("input")).getAttribute("id");
            String lastName = colValues.get(1).getText();
            String firstName = colValues.get(2).getText();
            ContactData contact = new ContactData(Integer.parseInt(id), firstName
                    , lastName, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

    public void selectContactToModify(int index) {
        String url = "edit.php?id=" + index;
        driver.findElement(By.xpath("//a[@href='"+url+"']")).click();
    }
}

