package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        if ( app.contact().all().size() == 0){
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
    public void testContactDeletion(){
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size()-1);
        MatcherAssert.assertThat(after, equalTo(before.without(deletedContact)));
    }


}
