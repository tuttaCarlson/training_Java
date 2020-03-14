package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    
    @Test
    public void testContactModification(){
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Heilko", "Kopeilko",
                "29437 Espame", "fjfjf@mail.ce", "123456", null), false);
        app.getContactHelper().submitContactModification();

    }
}
