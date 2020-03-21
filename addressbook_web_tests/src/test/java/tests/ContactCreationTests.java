package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add (contact);
        Assert.assertEquals(after, before);
    }




}
