package midka.singleton;

import midka.Credit;
import midka.Order;

import java.util.ArrayList;

public class DBCredit {
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
        for (Credit credit : credits) {
            System.out.println(credit);
        }
    }
}
