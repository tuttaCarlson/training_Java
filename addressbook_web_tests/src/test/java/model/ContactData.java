package model;

import java.util.Objects;

public class ContactData {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneMobile;
    private String group;

    public ContactData(int id, String firstName, String lastName, String address,
                            String email, String phoneMobile, String group){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneMobile = phoneMobile;
        this.group = group;
    }

    public ContactData(String firstName, String lastName, String address,
                       String email, String phoneMobile, String group){
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneMobile = phoneMobile;
        this.group = group;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getGroup() { return group; }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public int getId() {return id; }
}
