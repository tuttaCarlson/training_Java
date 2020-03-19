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

    public void selectToDelete(int index){
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void submitDeletion(){
        click(By.xpath("//input[@value='Delete']"));
        submitAlert();
    }

    public void submitModification(){
        click(By.name("update"));
    }

    public void delete(int index) {
        selectToDelete(index);
        submitDeletion();
    }

    public void fillForm(ContactData contactData, boolean creation) {
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

    public void initCreation() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        initCreation();
        fillForm(contact, true);
        submitContactCreation();
        toHome();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
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

    public void selectToEdit(int index) {
        String url = "edit.php?id=" + index;
        driver.findElement(By.xpath("//a[@href='"+url+"']")).click();
    }

    public void toHome(){
        driver.findElement(By.linkText("home page")).click();
    }

    public void modify(ContactData contact, int id) {
        selectToEdit(id);
        fillForm(contact, false);
        submitModification();
        toHome();
    }
}

