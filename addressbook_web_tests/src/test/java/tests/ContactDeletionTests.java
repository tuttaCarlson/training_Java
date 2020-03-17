package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
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
        app.getContactHelper().submitContactDeletion();
        //TODO add wait here (remove implicit wait from ApplicationManager)
        app.getNavigationHelper().openHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
