package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test").withHeader("header")
                    .withFooter("footer"));
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Check")
                    .withLastName("Macheck").withAddress("12345 Dibo")
                    .withEmail("mah@mah.com").withPhoneMobile("123543").withGroup("test"));
            app.goTo().homePage();
        }
    }
    
    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        int id = before.get(index).getId();
        ContactData contact = new ContactData().withId(id).withFirstName("Heilko")
                .withLastName("Kopeilko").withAddress("29437 Espame")
                .withEmail("fjfjf@mail.ce").withPhoneMobile("123456");
        app.contact().modify(contact, id);
        List<ContactData> after = app.contact().list();
        before.remove(index);
        before.add(contact);
        Assert.assertEquals(before.size(), after.size());
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }




}
