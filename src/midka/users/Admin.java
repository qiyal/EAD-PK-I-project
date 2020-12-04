package midka.users;

import midka.users.User;
import midka.visitor.Visitor;

public class Admin extends User {
    private String login;
    private String password;

    public Admin() {}

    public Admin(String firstName, String lastName, String login, String password) {
        super(firstName, lastName);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLogin: " + login + "\nPassword: " + password;
    }
}
