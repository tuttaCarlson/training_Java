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
            app.group().create(new GroupData("test","header", "footer"));
            app.goTo().homePage();
            app.contact().create(new ContactData("Check"
                    , "Macheck", "12345 Dibo", "mah@mah.com"
                    , "123543", "test"));
            app.goTo().homePage();
        }
    }
    
    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        int id = before.get(index).getId();
        ContactData contact = new ContactData(id, "Heilko", "Kopeilko",
                "29437 Espame", "fjfjf@mail.ce", "123456", null);
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
