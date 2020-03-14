package tests;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation(){
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().createGroup(new GroupData("Test", "Test_header", "test_footer"));
    }

}
