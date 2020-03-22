package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Heilko").withLastName("Kopeilko")
                .withAddress("29437 Espame").withEmail("fjfjf@mail.ce")
                .withPhoneMobile("123456");
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before
                .without(modifiedContact).withAdded(contact)));
    }




}
