import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation(){
        openGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("Test", "Test_header", "Test_footer"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
