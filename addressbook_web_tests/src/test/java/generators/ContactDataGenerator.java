package generators;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "count of contacts")
    private int count;

    @Parameter (names = "-f", description = "target file")
    public String file;

    @Parameter(names = "-d", description = "data format")
    private String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander.newBuilder().addObject(generator).build().parse(args);
        generator.run();
    }

    private void run() throws IOException{
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        }
        else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        }
        else {
            System.out.println("Unrecognized format");
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation()
                .create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact: contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName()
                    , contact.getLastName(), contact.getAddress(), contact.getEmail()
                    , contact.getPhoneMobile(), contact.getPhoto(), contact.getGroup()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i=0; i<count; i++){
            contacts.add(new ContactData().withFirstName(String.format("name %s", i ))
                    .withLastName(String.format("last %s", i)).withAddress(String.format("address %s", i))
                    .withEmail(String.format("email %s", i)).withPhoneMobile(String.format("phone %s", i))
                    .withPhoto(new File("src/test/resources/sunflower.jpg")).withGroup("test"));
        }
        return contacts;
    }
}
