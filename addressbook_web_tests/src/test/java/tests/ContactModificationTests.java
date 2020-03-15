package tests;

import model.ContactData;
import model.GroupData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    
    @Test
    public void testContactModification(){
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
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Heilko", "Kopeilko",
                "29437 Espame", "fjfjf@mail.ce", "123456", null), false);
        app.getContactHelper().submitContactModification();

    }
}
