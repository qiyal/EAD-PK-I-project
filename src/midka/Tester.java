package midka;

import midka.builders.MotorbikeBuilder;
import midka.builders.director.Director;
import midka.motorbikes.Motorbike;
import midka.observers.EventManager;
import midka.observers.listeners.AddMotorbikeListener;
import midka.observers.listeners.ChangePriceMotorbike;
import midka.observers.listeners.EventListener;
import midka.singleton.DBBike;
import midka.singleton.DBCredit;
import midka.singleton.DBOrder;
import midka.singleton.DBUser;
import midka.strategies.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int chose, op;

        DBBike dbBike = DBBike.getInstance();
        DBUser dbUser = DBUser.getInstance();
        DBOrder dbOrder = DBOrder.getInstance();
        DBCredit dbCredit = DBCredit.getInstance();

        ArrayList<String> nameBikes;
        PayStrategy payStrategy;
        EventManager manager = new EventManager();

        String user = null;

        // TEST
        dbUser.showAdmin();
        dbUser.showCustomer();
        // EXIT TEST

        while(true) {
            nameBikes = dbBike.getBikesCodeName();

            if(user == null) {
                System.out.println("\nenter 1 --- Sign In");
                System.out.println("enter 2 --- Sign Up");
                System.out.println("enter 0 --- EXIT");
                System.out.print("enter: ");
                op = sc.nextInt();

                // Sing Up
                if(op == 2) {
                    String firstname, lastname, email, password;
                    CreditCard card = null;

                    System.out.print("\nFirstname: ");
                    firstname = sc.next();

                    System.out.print("Lastname: ");
                    lastname = sc.next();

                    System.out.println("\nYou are Client --- 1");
                    System.out.println("You are Admin --- 2");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    if(op == 1) {
                        System.out.print("\nEmail: ");
                        email = sc.next();

                        System.out.println("\nWant to add credit card?\nenter 1 --- Yes\nenter 0 --- No");
                        System.out.print("enter: ");
                        op = sc.nextInt();

                        if (op == 1) {
                            System.out.print("\nEnter the card number: ");
                            String number = sc.next();

                            System.out.print("Enter the card expiration date 'mm/yy': ");
                            String date = sc.next();

                            System.out.print("Enter the CVV code: ");
                            String cvv = sc.next();

                            card = new CreditCard(number, date, cvv);
                        }

                        if (!dbUser.addUser(new Customer(firstname, lastname, email, card))) {
                            System.err.println("Email " + email + "already exist!!!\n Again!");
                        }
                    } else {
                        System.out.print("\nLogin: ");
                        email = sc.next();

                        System.out.print("Password: ");
                        password = sc.next();

                        if (!dbUser.addUser(new Admin(firstname, lastname, email, password))) {
                            System.err.println("\nLogin " + email + "already exist!!!\n Again!");
                        }
                    }
                    System.out.println("You created account!");
                }
                // Sing In
                else if (op == 1) {
                    System.out.println("\nYou are Client --- 1");
                    System.out.println("You are Admin --- 2");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    if(op == 1) {
                        System.out.print("\nEmail: ");
                        String email = sc.next();

                        if (dbUser.checkUser(email)) {
                            user = email;
                        } else {
                            System.out.println("\nLogin or Password is not correct!!!");
                        }
                    } else {
                        System.out.print("\nLogin: ");
                        String email = sc.next();

                        System.out.print("Password: ");
                        String password = sc.next();
                        if (dbUser.checkUser(email, password)) {
                            user = email;
                            System.out.println("User: " + user);
                        } else {
                            System.out.println("\nLogin or Password is not correct!!!");
                        }
                    }
                } else if (op == 0) {
                    System.out.println("\n--- EXIT ---");
                    break;
                } else {
                    System.out.println("\nInvalid argument!");
                }
            }
            // Admins Panel
            else if (dbUser.isAdmin(user)) {
                System.out.println("\nAdmin: " + user);

                System.out.println("\nenter 1 --- Add new Motorbike");
                System.out.println("enter 2 --- Show orders");
                System.out.println("enter 3 --- Show credits");
                System.out.println("enter 4 --- Show garage");
                System.out.println("enter 5 --- Change motorbike price");
                System.out.println("enter -1 --- Sign Out");
                System.out.println("enter 0 --- EXIT");
                System.out.print("enter: ");
                op = sc.nextInt();

                if(op == -1) {
                    user = null;
                } else if (op == 1) {
                    Director director = new Director();
                    System.out.println("\nAdd Yamaha --- 1");
                    System.out.println("Add Harley Davidson --- 2");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    if (op == 1) {
                        dbBike.addMotorbike(director.constructYamaha(new MotorbikeBuilder()));
                        manager.notifyUsers("Star Bolt R Spec XVS95CEGY/C", "add");
                    } else {
                        dbBike.addMotorbike(director.constructHarleyDavidson(new MotorbikeBuilder()));
                        manager.notifyUsers("V-Rod Muscle VRSCF 1250", "add");
                    }

                } else if (op == 2) {
                    dbOrder.showOrders();
                } else if (op == 3) {
                    dbCredit.showCredit();
                } else if (op == 4) {
                    dbBike.showAllMotorBikes();
                } else if (op == 5) {
                    System.out.println();
                    for (int i = 0; i < nameBikes.size(); i++) {
                        Motorbike motorbike = dbBike.getMotorBike(nameBikes.get(i));
                        System.out.println(i + ") " + motorbike.getModelCode() + " --- " + motorbike.getPrice());
                    }
                    System.out.print("chose motorbike (number): ");
                    chose = sc.nextInt();

                    int newPrice;
                    System.out.print("\nenter new price: ");
                    newPrice = sc.nextInt();
                    dbBike.setNewPrice(nameBikes.get(chose), newPrice);
                    manager.notifyUsers(nameBikes.get(chose), "changePrice");
                } else if (op == 0) {
                    break;
                } else {
                    System.err.println("Invalid argument!");
                }
            }
            // Users Panel
            else {
                System.out.println("\nCustomer: " + user);

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
                    user = null;
                }
                // Buy
                else if (op == 1) {

                    System.out.println();
                    for (int i = 0; i < nameBikes.size(); i++) {
                        Motorbike motorbike = dbBike.getMotorBike(nameBikes.get(i));
                        System.out.println(i + ") " + motorbike.getModelCode() + " --- " + motorbike.getPrice());
                    }

                    System.out.print("chose motorbike (number): ");
                    chose = sc.nextInt();

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

                    payStrategy.collectPaymentDetails(nameBikes.get(chose), user);

                    if (payStrategy.pay(dbBike.getMotorBike(nameBikes.get(chose)).getPrice())) {
                        System.out.println("\nPayment has been successful.");
                    } else {
                        System.out.println("\nFAIL! Please, check your data.");
                    }
                } else if (op == 2) {
                    dbBike.showAllMotorBikes();
                } else if (op == 3) {
                    dbOrder.showCustomerOrders(user);
                } else if(op == 4) {
                    System.out.println();
                    for (int i = 0; i < nameBikes.size(); i++) {
                        Motorbike motorbike = dbBike.getMotorBike(nameBikes.get(i));
                        System.out.println(i + ") " + motorbike.getModelCode() + " --- " + motorbike.getPrice());
                    }

                    System.out.print("chose motorbike (number): ");
                    chose = sc.nextInt();

                    System.out.println("\nChose subscribe method:\nenter 1 --- Add motorbike\nenter 2 --- Change motorbike price");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    EventListener eventListener;
                    if (op == 1) {
                        System.out.println("Chose: " + nameBikes.get(chose) + "value: " + chose);
                        eventListener = new AddMotorbikeListener(nameBikes.get(chose), user);
                        manager.subscribe("add", eventListener);
                    } else {
                        eventListener = new ChangePriceMotorbike(nameBikes.get(chose), user, dbBike.getMotorBike(nameBikes.get(chose)).getPrice());
                        manager.subscribe("changePrice", eventListener);
                    }
                } else if (op == 5) {
                    System.out.println("\nChose subscribe method:\nenter 1 --- Add motorbike\nenter 2 --- Change motorbike price");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    System.out.println("\n0) V-Rod Muscle VRSCF 1250\n1) Star Bolt R Spec XVS95CEGY/C");
                    System.out.print("chose motorbike (number): ");
                    chose = sc.nextInt();

                    String nameBike;
                    if (chose == 0) {
                        nameBike = "V-Rod Muscle VRSCF 1250";
                    } else {
                        nameBike = "Star Bolt R Spec XVS95CEGY/C";
                    }

                    EventListener eventListener;
                    if (op == 1) {
                        eventListener = new AddMotorbikeListener(nameBike, user);
                        manager.unsubscribe("add", eventListener);
                    } else {
                        eventListener = new ChangePriceMotorbike(nameBike, user, dbBike.getMotorBike(nameBike).getPrice());
                        manager.unsubscribe("changePrice", eventListener);
                    }
                } else if (op == 0) {
                  break;
                } else {
                    System.out.println("\n*** Invalid argument! ***");
                }
            }
        }



    }
}
