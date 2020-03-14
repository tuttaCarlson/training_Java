package model;

public class ContactData {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneMobile;
    private String group;

    public ContactData(String firstName, String lastName, String address,
                       String email, String phoneMobile, String group){
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
}
