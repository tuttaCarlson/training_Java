package appmanager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void create(GroupData group) {
        initCreation();
        fillForm(group);
        submitCreation();
        returnToGroupPage();
    }

    public void delete(int index) {
        select(index);
        submitDeletion();
        returnToGroupPage();
    }

    public void fillForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            GroupData group = new GroupData().withId(Integer.parseInt(id)).withName(name);
            groups.add(group);
        }
        return groups;
    }


    public void initCreation() {
        click(By.name("new"));
    }

    public void initModification() { click(By.name("edit")); }


    public void modify(int index, GroupData group){
        select(index);
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
