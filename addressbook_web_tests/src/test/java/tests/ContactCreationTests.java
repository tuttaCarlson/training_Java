package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        //TODO: nice to have a check if the group with this name exists
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test","header", "footer"));
        app.getNavigationHelper().openHomePage();
        ContactData contact = new ContactData("Rabbit", "Nice", "SPB Russia",
                "rabbit.nice.works@msailk.nu", "+79220002323", "test");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().openHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add (contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }




}
