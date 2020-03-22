package appmanager;

import model.ContactData;
import model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    private Contacts contactCache = null;

    public ContactHelper(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element: elements){
            List<WebElement> colValues = element.findElements(By.tagName("td"));
            String id = colValues.get(0).findElement(By.tagName("input")).getAttribute("id");
            String lastName = colValues.get(1).getText();
            String firstName = colValues.get(2).getText();
            String address = colValues.get(3).getText();
            String allEmails = colValues.get(4).getText();
            String allPhones = colValues.get(5).getText();
            ContactData contact = new ContactData().
                    withId(Integer.parseInt(id)). withFirstName(firstName)
                    .withLastName(lastName).withAddress(address)
                    .withAllPhones(allPhones).withAllEmails(allEmails);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public int count(){
        return driver.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact) {
        initCreation();
        fillForm(contact, true);
        submitContactCreation();
        toHome();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        submitDeletion();
        contactCache = null;
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getPhoneMobile());
        type(By.name("email"), contactData.getEmail());
        if (creation){
            new Select(driver.findElement(By.name("new_group")))
                    .selectByVisibleText(contactData.getGroup());
        } else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initCreation() {
        click(By.linkText("add new"));
    }

    public void modify(ContactData contact) {
        selectToEdit(contact.getId());
        fillForm(contact, false);
        submitModification();
        contactCache = null;
        toHome();
    }

    private void selectById(int id) {
        driver.findElement(By.cssSelector("input[id='"+id+"']")).click();
    }

    public void selectToEdit(int id) {
        String url = "edit.php?id=" + id;
        driver.findElement(By.xpath("//a[@href='"+url+"']")).click();
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }


    public void submitDeletion(){
        click(By.xpath("//input[@value='Delete']"));
        submitAlert();

    }

    public void submitModification(){
        click(By.name("update"));
    }


    public void toHome(){
        driver.findElement(By.linkText("home page")).click();
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
            ContactData contact = new ContactData().
                    withId(Integer.parseInt(id)). withFirstName(firstName)
                    .withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        selectToEdit(contact.getId());

        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");

        String address = driver.findElement(By.name("address")).getText();

        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");

        String phoneHome = driver.findElement(By.name("home")).getAttribute("value");
        String phoneMobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String phoneWork = driver.findElement(By.name("work")).getAttribute("value");

        return new ContactData().withFirstName(firstName).withLastName(lastName)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
                .withPhoneHome(phoneHome).withPhoneMobile(phoneMobile).withPhoneWork(phoneWork);

    }
}

