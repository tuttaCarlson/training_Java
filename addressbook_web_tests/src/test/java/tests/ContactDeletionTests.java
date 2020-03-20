package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        if ( app.contact().list().size() == 0){
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
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);
        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
