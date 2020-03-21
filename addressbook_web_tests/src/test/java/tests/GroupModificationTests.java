package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("Old").withHeader("Old_header")
                            .withFooter("Old_footer"));
        }
    }

    @Test
    public void testGroupModification(){
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withName("New").withHeader("New_header").withFooter("New_footer");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size(), after.size());
        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(before, after);
    }
}
