package midka.singleton;

import midka.users.Admin;
import midka.users.Customer;
import midka.strategies.CreditCard;
import midka.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBUser {
    private static DBUser instance;
    private Map<String, User> users = new HashMap<>();
//    private Map<String, Admin> admins = new HashMap<>();
//    private Map<String, Customer> users = new HashMap<>();

    {
        CreditCard creditCard = new CreditCard("0123", "01/22","123");
        users.put("nico", new Admin("Yerbolat", "Pazyl", "nico", "123"));
        users.put("nico@gmail.com", new Customer("Yerbolat", "Pazyl", "nico@gmail.com", "123", null));
        users.put("nico10@gmail.com", new Customer("Yerbolat", "Pazyl", "nico10@gmail.com", "123", creditCard));
    }

    private DBUser(){}

    public static DBUser getInstance() {
        if (instance == null)
            instance = new DBUser();
        return instance;
    }

    public boolean isAdmin(String login) {
        return (users.get(login) instanceof Admin);
//        return admins.containsKey(login);
    }

    public User getUser(String login) {
        return users.getOrDefault(login, null);
    }

//    public Customer getCustomer(String email) {
//       return users.get(email);
//    }

    public boolean addUser(User user) {
        if (user instanceof Admin && !users.containsKey( ( (Admin) user).getLogin() ) ) {
            users.put( ( (Admin) user).getLogin(), user);
            return true;
        } else if (user instanceof Customer && !users.containsKey( ( (Customer) user ).getEmail() ) ) {
            users.put( ( (Customer) user).getEmail(), user);
            return true;
        } else {
            return false;
        }
    }

//    public boolean addUser(Admin user) {
//        if(admins.containsKey(user.getLogin())) {
//            return false;
//        } else {
//            admins.put(user.getLogin(), user);
//            return true;
//        }
//    }

//    public boolean addUser(Customer user) {
//        if(users.containsKey(user.getEmail())) {
//            return false;
//        } else {
//            users.put(user.getEmail(), user);
//            return true;
//        }
//    }

    public void showCustomer() {
        for (User user : users.values()) {
            if(user instanceof Customer)
                System.out.println( ( (Customer) user).getEmail());
        }

//        if (!users.isEmpty()) {
//            System.out.println();
//            for (String key : users.keySet())
//                System.out.println(key);
//        } else {
//            System.err.println("\nDB User is Empty!!!");
//        }
    }

    public void showAdmin() {

        for (User user : users.values()) {
            if(user instanceof Admin)
                System.out.println( ( (Admin) user).getLogin());
        }
//        if (!admins.isEmpty()) {
//            System.out.println();
//            for (String key : admins.keySet())
//                System.out.println(key);
//        } else {
//            System.err.println("\nDB User is Empty!!!");
//        }
    }

    public boolean checkUser(String login, String password) {
        boolean valid = false;
        if (users.containsKey(login)) {
            User user = users.get(login);
            if(user instanceof Customer) {
                valid = ( ( (Customer) user).getPassword().equals(password));
            } else {
                valid = ( ( (Admin) user).getPassword().equals(password));
            }
        }
        return valid;
//        return (admins.containsKey(login) && admins.get(login).getPassword().equals(password));
    }

//    public boolean checkUser(String email) {
//        return (users.containsKey(email));
//    }
}
