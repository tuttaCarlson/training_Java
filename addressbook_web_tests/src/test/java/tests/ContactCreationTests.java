package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("test")
                .withHeader("header").withFooter("footer");
        ContactData contact = new ContactData().withFirstName("Rabbit").withLastName("Nice")
                .withAddress("SPB Russia"). withEmail("rabbit.nice.works@msailk.nu")
                .withPhoneMobile("+79220002323").withGroup("test");
        app.group().create(group);
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add (contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }




}
