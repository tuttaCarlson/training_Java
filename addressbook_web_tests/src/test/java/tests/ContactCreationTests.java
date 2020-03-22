package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.contact().all();
        app.contact().create(contact);
        assertEquals(app.contact().count(), before.size()+1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt((c)->c.getId()).max().getAsInt()))));
    }




}
