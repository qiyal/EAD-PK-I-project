package midka.singleton;

import midka.users.Admin;
import midka.users.Customer;
import midka.strategies.CreditCard;
import midka.users.User;
import java.util.HashMap;
import java.util.Map;

public class DBUser {
    private static DBUser instance;
    private Map<String, User> users = new HashMap<>();

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
    }

    public User getUser(String login) {
        return users.getOrDefault(login, null);
    }

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

    public void showCustomer() {
        for (User user : users.values()) {
            if(user instanceof Customer)
                System.out.println( ( (Customer) user).getEmail());
        }
    }

    public void showAdmin() {
        for (User user : users.values()) {
            if(user instanceof Admin)
                System.out.println( ( (Admin) user).getLogin());
        }
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
    }
}
