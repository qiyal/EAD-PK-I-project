package midka.services;

import midka.decorators.*;
import midka.singleton.DBUser;
import midka.users.Customer;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NotifierService {
    private static NotifierService instance = new NotifierService();
    private DBUser dbUser = DBUser.getInstance();
    private Set<NotifierEnum> notifierEnums = new HashSet<>();
    private String userLogin;
    private Scanner sc = new Scanner(System.in);
    private int op;
    private int chose;

    private NotifierService() {}

    public static NotifierService getInstance() {
        return instance;
    }

    private void setNotifierEnums(String login) {
        notifierEnums = ((Customer)dbUser.getUser(login)).getNotifiers();
    }

    public void runMenu(String login) {
        userLogin = login;
        System.out.println("\nenter 1 --- Add new notifier");
        System.out.println("enter 2 --- Delete notifier");
        System.out.println("enter 3 --- Set default notifier");
        System.out.print("enter: ");
        op = sc.nextInt();

        setNotifierEnums(login);

        if (op == 1) {
            addNotifier();
        } else if (op == 2) {
            deleteNotifier();
        } else if (op == 3) {
            setDefaultNotifier();
        } else {
            System.out.println("Invalid argument!");
        }
    }

    public void sendMessage(String login, String message) {
        setNotifierEnums(login);
        Customer customer = (Customer) dbUser.getUser(login);

        if(notifierEnums.contains(NotifierEnum.EMAIL)) {
            Notifier notifier = new EmailNotifier(customer.getEmail());

            if (notifierEnums.contains(NotifierEnum.SMS)) {
                notifier = new SMSDecorator(notifier, customer.getTelephoneNumber());
            }
            if (notifierEnums.contains(NotifierEnum.FACEBOOK)) {
                notifier = new FacebookDecorator(notifier, customer.getFacebookLogin());
            }
            notifier.send(message);
        }
        else {
            NotifierDecorator notifierDecorator = new NotifierDecorator();

            if (notifierEnums.contains(NotifierEnum.SMS)) {
                notifierDecorator = new SMSDecorator(notifierDecorator, customer.getTelephoneNumber());
            }
            if (notifierEnums.contains(NotifierEnum.FACEBOOK)) {
                notifierDecorator = new FacebookDecorator(notifierDecorator, customer.getFacebookLogin());
            }
            notifierDecorator.send(message);
        }
    }

    private void addNotifier() {
        if (notifierEnums.size() >= 3) {
            System.out.println("\nYou can't add a new notifier.");
            System.out.println("Because you have all the notifiers.");
        } else {
            Customer customer = (Customer) dbUser.getUser(userLogin);

            String menu = "";
            if (!notifierEnums.contains(NotifierEnum.SMS)) {
                menu += "Add SMS notifier - 1\n";
            }
            if (!notifierEnums.contains(NotifierEnum.EMAIL)) {
                menu += "Add Email notifier - 2\n";
            }
            if (!notifierEnums.contains(NotifierEnum.FACEBOOK)) {
                menu += "Add Facebook notifier - 3\n";
            }

            System.out.println("\n" + menu);
            System.out.print("enter: ");
            chose = sc.nextInt();

            if(chose == 1) {
                if(customer.getTelephoneNumber() == null) {
                    System.out.print("\nYour phone number: ");
                    customer.setTelephoneNumber(sc.next());
                }
                notifierEnums.add(NotifierEnum.SMS);
            } else if (chose == 2) {
                notifierEnums.add(NotifierEnum.EMAIL);
            } else if (chose == 3) {
                if(customer.getFacebookLogin() == null) {
                    System.out.print("\nYour facebook login: ");
                    customer.setFacebookLogin(sc.next());
                }
                notifierEnums.add(NotifierEnum.FACEBOOK);
            } else {
                System.out.println("\nInvalid argument!");
            }

            if (chose == 1 || chose == 2 || chose == 3) {
                System.out.println("\nNew notifier added!");
                updateNotifiers();
            }
        }
    }

    private void deleteNotifier() {
        if (notifierEnums.size() == 0) {
            System.out.println("\nYour notifier list is empty.");
        } else {
            String menu = "";
            if (notifierEnums.contains(NotifierEnum.SMS)) {
                menu += "Delete SMS notifier - 1\n";
            }
            if (notifierEnums.contains(NotifierEnum.EMAIL)) {
                menu += "Delete Email notifier - 2\n";
            }
            if (notifierEnums.contains(NotifierEnum.FACEBOOK)) {
                menu += "Delete Facebook notifier - 3\n";
            }

            System.out.println("\n" + menu);
            System.out.print("enter: ");
            chose = sc.nextInt();

            if(chose == 1) {
                notifierEnums.remove(NotifierEnum.SMS);
            } else if (chose == 2) {
                notifierEnums.remove(NotifierEnum.EMAIL);
            } else if (chose == 3) {
                notifierEnums.remove(NotifierEnum.FACEBOOK);
            } else {
                System.out.println("\nInvalid argument!");
            }

            if (chose == 1 || chose == 2 || chose == 3) {
                System.out.println("\nNotifier deleted!");
                updateNotifiers();
            }
        }
    }

    private void setDefaultNotifier() {
        Set<NotifierEnum> set = new HashSet<>(3);
        set.add(NotifierEnum.EMAIL);
        ((Customer)dbUser.getUser(userLogin)).setNotifiers(set);
        System.out.println("\nDefault notifier was set!");
    }

    private void updateNotifiers() {
        ((Customer)dbUser.getUser(userLogin)).setNotifiers(notifierEnums);
    }
}
