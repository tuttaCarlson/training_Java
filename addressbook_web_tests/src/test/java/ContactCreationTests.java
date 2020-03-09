import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        initContactCreation();
        fillContactForm(new ContactData("Rabbit", "Nice", "SPB Russia",
                "rabbit.nice.works@msailk.nu", "+79220002323"));
        submitContactCreation();
    }

}
