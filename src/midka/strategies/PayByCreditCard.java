package midka.strategies;

import midka.Customer;
import midka.Order;
import midka.singleton.DBBike;
import midka.singleton.DBOrder;
import midka.singleton.DBUser;

import java.util.Scanner;

public class PayByCreditCard implements PayStrategy {
    private Scanner sc = new Scanner(System.in);
    private DBUser dbUser = DBUser.getInstance();
    private DBBike dbBike = DBBike.getInstance();
    private DBOrder dbOrder = DBOrder.getInstance();
    private Customer customer;
    private String motorbikeName;

    @Override
    public boolean pay(int paymentAmount) {
        if (customer.getCard() == null) {
            System.out.println("\nCard not present!");
            return false;
        } else if (paymentAmount > customer.getCard().getAmount()) {
            System.out.println("\nYour deposit smaller than payment amount");
            return false;
        } else {
            System.out.println("\nPaying " + paymentAmount + " using Credit Card.");
            customer.getCard().setAmount(customer.getCard().getAmount() - paymentAmount);
            dbOrder.addOrder(new Order(paymentAmount, customer.getEmail(), motorbikeName));
            dbBike.deleteMotorbike(motorbikeName);
            return true;
        }
    }

    @Override
    public void collectPaymentDetails(String motorbikeName, String customerName) {
        customer = dbUser.getCustomer(customerName);
        this.motorbikeName = motorbikeName;

        if(customer.getCard() == null) {
            System.out.print("\nEnter the card number: ");
            String number = sc.next();
            System.out.print("Enter the card expiration date 'mm/yy': ");
            String date = sc.next();
            System.out.print("Enter the CVV code: ");
            String cvv = sc.next();

            customer.setCard(new CreditCard(number, date, cvv));
        }
    }
}
