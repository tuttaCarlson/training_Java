package tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().submitGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
    }
}
