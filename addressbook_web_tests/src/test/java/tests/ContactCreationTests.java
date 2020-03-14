package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Rabbit", "Nice", "SPB Russia",
                "rabbit.nice.works@msailk.nu", "+79220002323", "Test"), true);
        app.getContactHelper().submitContactCreation();
    }

}
