package appmanager;

import model.GroupData;
import model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GroupHelper extends HelperBase {

    private Groups groupCache = null;

    public GroupHelper(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    public Groups all() {
        if (groupCache != null){
            return  new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            GroupData group = new GroupData().withId(Integer.parseInt(id)).withName(name);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }

    public int count(){
        return driver.findElements(By.name("selected[]")).size();
    }

    public void create(GroupData group) {
        initCreation();
        fillForm(group);
        submitCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectById(group.getId());
        submitDeletion();
        groupCache = null;
        returnToGroupPage();
    }

    public void fillForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initCreation() {
        click(By.name("new"));
    }

    public void initModification() {
        click(By.name("edit"));
    }


    public void modify(GroupData group){
        selectById(group.getId());
        initModification();
        fillForm(group);
        submitModification();
        groupCache = null;
        returnToGroupPage();
    }

    private void selectById(int id) {
        driver.findElement(By.cssSelector("input[value='"+id+"']")).click();
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

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }
}
