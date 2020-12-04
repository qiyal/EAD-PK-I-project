package midka.singleton;

import midka.files.Order;
import midka.iterators.DBOrderIterator;
import midka.iterators.ICustomIterableCollection;
import midka.iterators.ICustomIterator;

import java.util.ArrayList;

public class DBOrder implements ICustomIterableCollection {
    private static DBOrder instance;
    private ArrayList<Order> orders = new ArrayList<>();

    private DBOrder() {}

    public static DBOrder getInstance() {
        if (instance == null)
            instance = new DBOrder();
        return instance;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("\n[ DB Order is Empty! ]");
        } else {
            System.out.println("\n[ ORDERS ]");
            ICustomIterator iterator = createIterator();
            while (iterator.hasNext()) {
                Order order = (Order) iterator.next();
                System.out.println("\nMotorbike: " + order.getMotorbikeId());
                System.out.println("Customer email: " + order.getCustomerEmail());
                System.out.println("Cots: " + order.getTotalCost());
            }
        }
    }

    public void showCustomerOrders(String email) {
        if (orders.isEmpty()) {
            System.out.println("\n[ Your list of orders is Empty! ]");
        } else {
            int k = 0;
            ICustomIterator iterator = createIterator();
            while (iterator.hasNext()) {
                Order order = (Order) iterator.next();
                if (email.equals(order.getCustomerEmail())) {
                    k++;
                    System.out.println("\nMotorbike: " + order.getMotorbikeId());
                    System.out.println("Customer email: " + order.getCustomerEmail());
                    System.out.println("Cots: " + order.getTotalCost());
                }
            }
            if (k == 0) {
                System.out.println("\n[ Your list of orders is Empty! ]");
            }
        }
    }

    @Override
    public ICustomIterator createIterator() {
        return new DBOrderIterator(orders);
    }
}
