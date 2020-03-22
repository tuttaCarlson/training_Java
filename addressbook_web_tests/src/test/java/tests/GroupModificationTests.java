package tests;

import model.GroupData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withName("New").withHeader("New_header").withFooter("New_footer");
        app.group().modify(group);
        assertEquals(app.group().count(), before.size());
        Groups after = app.group().all();
        assertThat(after, equalTo(before
                .without(modifiedGroup).withAdded(group)));
    }
}
