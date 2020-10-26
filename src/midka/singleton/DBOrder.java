package midka.singleton;

import midka.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBOrder {
    private static DBOrder instance;
    private ArrayList<Order> orders = new ArrayList<>();

    private DBOrder() {}

    public static DBOrder getInstance() {
        if (instance == null)
            instance = new DBOrder();
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("\nDB is Empty!");
        } else {
            System.out.println("\nORDERS");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void showCustomerOrders(String email) {
        for (Order order : orders) {
            if(email.equals(order.getCustomerEmail()))
                System.out.println(order);
        }
    }
 }
