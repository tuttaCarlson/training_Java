package model;

public class ContactData {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneMobile;

    public ContactData(String firstName, String lastName, String address,
                       String email, String phoneMobile){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneMobile = phoneMobile;
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
}
