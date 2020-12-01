package midka.facades;

import midka.builders.MotorbikeBuilder;
import midka.builders.director.Director;
import midka.handlers.BaseAuthHandler;
import midka.handlers.RoleCheckHandler;
import midka.motorbikes.Motorbike;
import midka.observers.EventManager;
import midka.observers.listeners.AddMotorbikeListener;
import midka.observers.listeners.ChangePriceMotorbike;
import midka.observers.listeners.EventListener;
import midka.services.AuthService;
import midka.singleton.DBBike;
import midka.singleton.DBCredit;
import midka.singleton.DBOrder;
import midka.singleton.DBUser;
import midka.strategies.*;
import midka.users.Admin;
import midka.users.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class MotoBikeShowroomApp {
    private DBBike dbBike;
    private DBUser dbUser;
    private DBOrder dbOrder;
    private DBCredit dbCredit;
    private AuthService authService;
    private PayStrategy payStrategy;
    private EventManager manager;
    private ArrayList<String> nameBikes;
    private Scanner sc;
    private int op;
    private int chose;

    private void initDataBases() {
        dbBike = DBBike.getInstance();
        dbUser = DBUser.getInstance();
        dbOrder = DBOrder.getInstance();
        dbCredit = DBCredit.getInstance();
    }

    private void initAuthService() {
        authService = AuthService.getInstance();
    }

    private void initHandler() {
        BaseAuthHandler baseAuthHandler = new BaseAuthHandler();
        RoleCheckHandler roleCheckHandler = new RoleCheckHandler();
        baseAuthHandler.setNext(roleCheckHandler);
        authService.setHandler(baseAuthHandler);
    }

    private void initEventManager() {
        manager = new EventManager();
    }

    private void createCustomer() {
        String firstname, lastname, email, password;
        CreditCard card = null;

        while (true) {
            System.out.print("\nEmail: ");
            email = sc.next();

            if (dbUser.hasLogin(email)) {
                System.out.println("\n[" + email + "] already exist!!!");
            } else {
                break;
            }
        }

        System.out.print("Password: ");
        password = sc.next();

        System.out.print("Firstname: ");
        firstname = sc.next();

        System.out.print("Lastname: ");
        lastname = sc.next();

        System.out.println("\nWant to add credit card?\nenter 1 --- Yes\nenter 0 --- No");
        System.out.print("enter: ");
        op = sc.nextInt();

        if(op == 1) {
            System.out.print("\nEnter the card number: ");
            String number = sc.next();

            System.out.print("Enter the card expiration date 'mm/yy': ");
            String date = sc.next();

            System.out.print("Enter the CVV code: ");
            String cvv = sc.next();

            card = new CreditCard(number, date, cvv);
        }

        if (dbUser.addUser(new Customer(firstname, lastname, email, password, card))) {
            System.out.println("\nYou created account!");
        } else {
            System.out.println("\nERROR!!!\nOne more again!");
        }
    }

    private void createAdmin() {
        String firstname, lastname, login, password;

        while (true) {
            System.out.print("\nLogin: ");
            login = sc.next();

            if (dbUser.hasLogin(login)) {
                System.out.println("\n[" + login + "] already exist!!!");
            } else {
                break;
            }
        }

        System.out.print("Password: ");
        password = sc.next();

        System.out.print("Firstname: ");
        firstname = sc.next();

        System.out.print("Lastname: ");
        lastname = sc.next();

        if (dbUser.addUser(new Admin(firstname, lastname, login, password))) {
            System.out.println("\nYou created account!");
        } else {
            System.out.println("\nERROR!!!\nOne more again!");
        }
    }

    private void signUp() {
        System.out.println("\nYou are Client --- 1");
        System.out.println("You are Admin --- 2");
        System.out.print("enter: ");
        op = sc.nextInt();

        if(op == 1) {
            createCustomer();
        } else {
            createAdmin();
        }
    }

    private void signIn() {
        System.out.print("\nLogin: ");
        String login = sc.next();

        System.out.print("Password: ");
        String password = sc.next();

        if(!authService.doAuth(login, password)) {
            System.out.println("\nLogin or Password is not correct!!!");
        }
    }

    private void addNewMotorbike() {
        Director director = new Director();
        System.out.println("\nADD [Star Bolt R Spec XVS95CEGY/C] Yamaha --- 1");
        System.out.println("ADD [V-Rod Muscle VRSCF 1250] Harley Davidson --- 2");
        System.out.print("enter: ");
        op = sc.nextInt();

        if (op == 1) {
            dbBike.addMotorbike(director.constructYamaha(new MotorbikeBuilder()));
            manager.notifyUsers("Star Bolt R Spec XVS95CEGY/C", "add");
        } else {
            dbBike.addMotorbike(director.constructHarleyDavidson(new MotorbikeBuilder()));
            manager.notifyUsers("V-Rod Muscle VRSCF 1250", "add");
        }
    }

    private void choseMotorbike() {
        System.out.println();
        for (int i = 0; i < nameBikes.size(); i++) {
            Motorbike motorbike = dbBike.getMotorBike(nameBikes.get(i));
            System.out.println("\n" + i + ") " + motorbike.getModelCode());
            System.out.println("   Manufacturer: " + motorbike.getManufacturer());
            System.out.println("   Price: " + motorbike.getPrice());
        }
        System.out.print("chose motorbike (number): ");
        chose = sc.nextInt();
    }

    private void changeMotorbikePrice() {
        choseMotorbike();

        int newPrice;
        System.out.print("\nenter new price: ");
        newPrice = sc.nextInt();

        dbBike.setNewPrice(nameBikes.get(chose), newPrice);
        manager.notifyUsers(nameBikes.get(chose), "changePrice");
    }

    private void buyMotorbike() {
        choseMotorbike();

        System.out.println("\nChose payment method:\nenter 1 --- Cash\nenter 2 --- Credit Card\nenter 3 -- Take Credit");
        System.out.print("enter: ");
        op = sc.nextInt();

        if (op == 1) {
            payStrategy = new PayByCash();
        } else if (op == 2){
            payStrategy = new PayByCreditCard();
        } else {
            payStrategy = new PayByCredit();
        }

        payStrategy.collectPaymentDetails(nameBikes.get(chose), authService.getAuthUserLogin());

        if (payStrategy.pay(dbBike.getMotorBike(nameBikes.get(chose)).getPrice())) {
            System.out.println("\nPayment has been successful.");
        } else {
            System.out.println("\nFAIL! Please, check your data.");
        }
    }

    private String collectInfoOfEventListener() {
        System.out.println("\n0) V-Rod Muscle VRSCF 1250\n1) Star Bolt R Spec XVS95CEGY/C");
        System.out.print("chose motorbike (number): ");
        chose = sc.nextInt();

        System.out.println("\nChose subscribe method:\nenter 1 --- Add motorbike\nenter 2 --- Change motorbike price");
        System.out.print("enter: ");
        op = sc.nextInt();

        String nameBike;
        if (chose == 0) {
            nameBike = "V-Rod Muscle VRSCF 1250";
        } else {
            nameBike = "Star Bolt R Spec XVS95CEGY/C";
        }
        return nameBike;
    }

    private void doSubscribes() {
        String nameBike = collectInfoOfEventListener();

        EventListener eventListener;
        if (op == 1) {
            eventListener = new AddMotorbikeListener(nameBike, authService.getAuthUserLogin());
            manager.subscribe("add", eventListener);
        } else {
            eventListener = new ChangePriceMotorbike(nameBike, authService.getAuthUserLogin());
            manager.subscribe("changePrice", eventListener);
        }
    }

    private void doUnsubscribes() {
        String nameBike = collectInfoOfEventListener();

        EventListener eventListener;
        if (op == 1) {
            eventListener = new AddMotorbikeListener(nameBike, authService.getAuthUserLogin());
            manager.unsubscribe("add", eventListener);
        } else {
            eventListener = new ChangePriceMotorbike(nameBike, authService.getAuthUserLogin());
            manager.unsubscribe("changePrice", eventListener);
        }
    }

    public void run() {
        sc = new Scanner(System.in);

        initDataBases();
        initAuthService();
        initHandler();
        initEventManager();

        while(true) {
            nameBikes = dbBike.getBikesCodeName();

            // isAuth() -> false
            if(!authService.isAuth()) {
                System.out.println("\nenter 1 --- Sign In");
                System.out.println("enter 2 --- Sign Up");
                System.out.println("enter 0 --- EXIT");
                System.out.print("enter: ");
                op = sc.nextInt();

                // Sign In
                if (op == 1) {
                    signIn();
                }
                // Sign Up
                else if (op == 2) {
                    signUp();
                }
                // Exit
                else if (op == 0) {
                    System.out.println("\n--- EXIT ---");
                    break;
                } else {
                    System.out.println("\nInvalid argument!");
                }
            }
            // Admins Panel [isAuth() -> true as admin]
            else if (authService.isAuth() && authService.getRole().equals("Admin")) {
                System.out.println("\nAdmin: " + authService.getAuthUserLogin());

                System.out.println("\nenter 1 --- Add new Motorbike");
                System.out.println("enter 2 --- Show orders");
                System.out.println("enter 3 --- Show credits");
                System.out.println("enter 4 --- Show garage");
                System.out.println("enter 5 --- Change motorbike price");
                System.out.println("enter -1 --- Sign Out");
                System.out.println("enter 0 --- EXIT");
                System.out.print("enter: ");
                op = sc.nextInt();

                if (op == -1) {
                    authService.logOut();
                } else if (op == 1) {
                    addNewMotorbike();
                } else if (op == 2) {
                    dbOrder.showOrders();
                } else if (op == 3) {
                    dbCredit.showCredit();
                } else if (op == 4) {
                    dbBike.showAllMotorBikes();
                } else if (op == 5) {
                    changeMotorbikePrice();
                } else if (op == 0) {
                    break;
                } else {
                    System.err.println("Invalid argument!");
                }
            }
            // Users Panel
            else {
                System.out.println("\nCustomer: " + authService.getAuthUserLogin());

                System.out.println("\nenter 1 --- Buy motorbike");
                System.out.println("enter 2 --- Show motorbikes");
                System.out.println("enter 3 --- Show your orders");
                System.out.println("enter 4 --- Subscribes");
                System.out.println("enter 5 --- Unsubscribes");
                System.out.println("enter -1 --- Sign Out");
                System.out.println("enter 0 --- EXIT");
                System.out.print("enter: ");
                op = sc.nextInt();

                // Sign Out
                if(op == -1) {
                    authService.logOut();
                }
                // Buy
                else if (op == 1) {
                   buyMotorbike();
                } else if (op == 2) {
                    dbBike.showAllMotorBikes();
                } else if (op == 3) {
                    dbOrder.showCustomerOrders(authService.getAuthUserLogin());
                } else if(op == 4) {
                    doSubscribes();
                } else if (op == 5) {
                    doUnsubscribes();
                } else if (op == 0) {
                    break;
                } else {
                    System.out.println("\n*** Invalid argument! ***");
                }
            }
        }

    }
}
