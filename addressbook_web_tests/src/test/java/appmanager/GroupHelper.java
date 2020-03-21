package appmanager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            GroupData group = new GroupData().withId(Integer.parseInt(id)).withName(name);
            groups.add(group);
        }
        return groups;
    }

    public void create(GroupData group) {
        initCreation();
        fillForm(group);
        submitCreation();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectById(group.getId());
        submitDeletion();
        returnToGroupPage();
    }

    private void selectById(int id) {
        driver.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void fillForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initCreation() {
        click(By.name("new"));
    }

    public void initModification() { click(By.name("edit")); }


    public void modify(GroupData group){
        selectById(group.getId());
        initModification();
        fillForm(group);
        submitModification();
        returnToGroupPage();
    }


    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void select(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void submitCreation() {
        click(By.name("submit"));
    }

    public void submitDeletion() {
        click(By.name("delete"));
    }


    public void submitModification() {
        click(By.name("update"));
    }


}
