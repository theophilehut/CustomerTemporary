package polito.mad.drawer.Model;

public class User {
    private String Name;
    private String Email;
    private String Address;
    private String Password;


    public User() {
    }

    public User(String name, String email, String address, String password) {
        Name = name;
        Email = email;
        Address = address;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
