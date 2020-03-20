package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("Old").withHeader("Old_header")
                            .withFooter("Old_footer"));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData group = new GroupData().withId(before.get(index).getId())
                .withName("New").withHeader("New_header").withFooter("New_footer");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(), after.size());
        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
