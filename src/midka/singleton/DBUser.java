package midka.singleton;

import midka.Admin;
import midka.Customer;
import midka.User;
import midka.strategies.CreditCard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DBUser {
    private static DBUser instance;
    private Map<String, Admin> admins = new HashMap<>();
    private Map<String, Customer> users = new HashMap<>();

    {
        admins.put("nico", new Admin("Yerbolat", "Pazyl", "nico", "123"));
        users.put("nico@gmail.com", new Customer("Yerbolat", "Pazyl", "nico@gmail.com", null));
        CreditCard creditCard = new CreditCard("0123", "01/22","123");
        users.put("nico10@gmail.com", new Customer("Yerbolat", "Pazyl", "nico10@gmail.com", creditCard));
    }

    private DBUser(){}

    public static DBUser getInstance() {
        if (instance == null)
            instance = new DBUser();
        return instance;
    }

    public boolean isAdmin(String login) {
        return admins.containsKey(login);
    }

    public Customer getCustomer(String email) {
        return users.get(email);
    }

    public boolean addUser(Admin user) {
        if(admins.containsKey(user.getLogin())) {
            return false;
        } else {
            admins.put(user.getLogin(), user);
            return true;
        }
    }

    public boolean addUser(Customer user) {
        if(users.containsKey(user.getEmail())) {
            return false;
        } else {
            users.put(user.getEmail(), user);
            return true;
        }
    }

    public void showCustomer() {

        if (!users.isEmpty()) {
            System.out.println();
            for (String key : users.keySet())
                System.out.println(key);
        } else {
            System.err.println("\nDB is Empty!!!");

        }
    }

    public void showAdmin() {
        if (!admins.isEmpty()) {
            System.out.println();
            for (String key : admins.keySet())
                System.out.println(key);
        } else {
            System.err.println("\nDB is Empty!!!");
        }
    }

    public boolean checkUser(String login, String password) {
        return (admins.containsKey(login) && admins.get(login).getPassword().equals(password));
    }

    public boolean checkUser(String email) {
        return (users.containsKey(email));
    }
}
