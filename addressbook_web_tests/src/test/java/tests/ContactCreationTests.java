package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object []> validContacts() throws IOException {
        List<Object []> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader
                (new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while(line!=null){
        String[] split = line.split(";");
        ContactData contact = new ContactData().withFirstName(split[0]).withLastName(split[1])
                .withAddress(split[2]).withEmail(split[3]).withPhoneMobile(split[4])
                .withPhoto(new File(split[5])).withGroup(split[6]);
        list.add(new Object[]{contact});
        line = reader.readLine();
        }
        return list.iterator();

    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
            app.goTo().groupPage();
            GroupData group = new GroupData().withName("test")
                    .withHeader("header").withFooter("footer");
            app.group().create(group);
            app.goTo().homePage();
            Contacts before = app.contact().all();
            app.contact().create(contact);
            assertEquals(app.contact().count(), before.size() + 1);
            Contacts after = app.contact().all();
            assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                    .mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
