package tests;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().openGroupPage();
        if (! app.getGroupHelper().isThereGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test", "null", "null"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().submitGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
    }
}
