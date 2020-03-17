package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    
    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereContact()){
            //TODO: nice to have a check if the group with this name exists
            app.getNavigationHelper().openGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test","header", "footer"));
            app.getNavigationHelper().openHomePage();
            app.getContactHelper().createContact(new ContactData("Check"
                    , "Macheck", "12345 Dibo", "mah@mah.com"
                    , "123543", "test"));
            app.getNavigationHelper().openHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "Heilko", "Kopeilko",
                "29437 Espame", "fjfjf@mail.ce", "123456", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().openHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(before.size(), after.size());
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);




    }
}
