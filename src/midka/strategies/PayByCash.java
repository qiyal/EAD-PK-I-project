package midka.strategies;

import midka.users.Customer;
import midka.files.Order;
import midka.singleton.DBBike;
import midka.singleton.DBOrder;
import midka.singleton.DBUser;

public class PayByCash implements PayStrategy {
    private DBUser dbUser = DBUser.getInstance();
    private DBBike dbBike = DBBike.getInstance();
    private DBOrder dbOrder = DBOrder.getInstance();
    private String motorbikeName;
    private Customer customer;

    @Override
    public boolean pay(int paymentAmount) {
        dbOrder.addOrder(new Order(paymentAmount, customer.getEmail(), motorbikeName));
        dbBike.deleteMotorbike(motorbikeName);
        System.out.println("\nPaying " + paymentAmount + " by cash");
        return true;
    }

    @Override
    public void collectPaymentDetails(String motorbikeName, String customerName) {
        this.motorbikeName = motorbikeName;
        customer = (Customer)dbUser.getUser(customerName);
    }




}
