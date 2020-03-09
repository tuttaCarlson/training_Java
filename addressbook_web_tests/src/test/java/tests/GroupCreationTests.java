package tests;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation(){
        app.openGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("Test", "Test_header", "Test_footer"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
