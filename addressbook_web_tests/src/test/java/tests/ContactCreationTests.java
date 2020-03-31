package tests;

import com.thoughtworks.xstream.XStream;
import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @BeforeClass
    public void performPreconditions(){
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("test")
                .withHeader("header").withFooter("footer");
        app.group().create(group);
    }

    @DataProvider
    public Iterator<Object []> validContacts() throws IOException {
        BufferedReader reader = new BufferedReader
                (new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while(line!=null){
        xml+=line;
        line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>)xStream.fromXML(xml);
        return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList())
                .iterator();

    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
            app.goTo().homePage();
            Contacts before = app.contact().all();
            app.contact().create(contact);
            assertEquals(app.contact().count(), before.size() + 1);
            Contacts after = app.contact().all();
            assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                    .mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
