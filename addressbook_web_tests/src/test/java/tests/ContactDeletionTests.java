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
            app.group().create(new GroupData("test","header", "footer"));
            app.goTo().homePage();
            app.contact().create(new ContactData("Check"
                    , "Macheck", "12345 Dibo", "mah@mah.com"
                    , "123543", "test"));
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
