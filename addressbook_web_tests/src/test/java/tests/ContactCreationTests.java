package tests;

import model.ContactData;
import model.GroupData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        //TODO: nice to have a check if the group with this name exists
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test","header", "footer"));
        app.getNavigationHelper().openHomePage();
        app.getContactHelper().createContact(new ContactData("Rabbit", "Nice", "SPB Russia",
                "rabbit.nice.works@msailk.nu", "+79220002323", "test"));
    }

}
