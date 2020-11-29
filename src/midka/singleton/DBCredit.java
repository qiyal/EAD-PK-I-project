package midka.singleton;

import midka.Credit;
import midka.iterators.DBCreditIterator;
import midka.iterators.ICustomIterableCollection;
import midka.iterators.ICustomIterator;

import java.util.ArrayList;

public class DBCredit implements ICustomIterableCollection {
    private static DBCredit instance;
    private ArrayList<Credit> credits = new ArrayList<>();

    private DBCredit() {}

        public static DBCredit getInstance() {
        if (instance == null)
            instance = new DBCredit();
        return instance;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    public void showCredit() {
        if (credits.isEmpty()) {
            System.out.println("\n[ DB Credit is Empty! ]");
        } else {
            System.out.println("\n[ ORDERS ]");
            ICustomIterator iterator = createIterator();
            while (iterator.hasNext()) {
                Credit credit = (Credit) iterator.next();
                System.out.println("\nAmount: " + credit.getAmount());
                System.out.println("Coefficient: " + credit.getCoefficient() + "%");
                System.out.println("Start time: " + credit.getStartTime());
                System.out.println("End time: " + credit.getEndTime());
                System.out.println("Years: " + credit.getYears());
                System.out.println("[ Order ]");
                System.out.println("    Motorbike: " + credit.getOrder().getMotorbikeId());
                System.out.println("    Customer email: " + credit.getOrder().getCustomerEmail());
                System.out.println("    Cots: " + credit.getOrder().getTotalCost());
            }
        }
    }

    @Override
    public ICustomIterator createIterator() {
        return new DBCreditIterator(credits);
    }
}
