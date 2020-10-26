package midka.strategies;

import midka.Credit;
import midka.Customer;
import midka.Order;
import midka.motorbikes.Motorbike;
import midka.singleton.DBBike;
import midka.singleton.DBCredit;
import midka.singleton.DBOrder;
import midka.singleton.DBUser;

public class PayByCredit implements PayStrategy {
    private DBUser dbUser = DBUser.getInstance();
    private DBBike dbBike = DBBike.getInstance();
    private DBOrder dbOrder = DBOrder.getInstance();
    private DBCredit dbCredit = DBCredit.getInstance();
    private Customer customer;
    private String motorbikeName;

    @Override
    public boolean pay(int paymentAmount) {
        Motorbike motorbike = dbBike.getMotorBike(motorbikeName);
        Order order = new Order(motorbike.getPrice(), customer.getEmail(), motorbikeName);
        dbOrder.addOrder(order);
        dbBike.deleteMotorbike(motorbikeName);
        dbCredit.addCredit(new Credit(motorbike.getPrice(), 1.25, order));
        System.out.println("\nYou have issued a loan for 5 years.");
        return true;
    }

    @Override
    public void collectPaymentDetails(String motorbikeName, String customerName) {
        customer = dbUser.getCustomer(customerName);
        this.motorbikeName = motorbikeName;
    }


}
