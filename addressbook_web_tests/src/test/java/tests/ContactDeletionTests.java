package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        before.remove(deletedContact);
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(before, after);
    }


}
