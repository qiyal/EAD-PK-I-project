package midka;

import java.time.LocalDateTime;
import java.util.Date;

public class Credit {
    private int amount;
    private double coefficient;
    private Order order;
    private Date startTime;
    private Date endTime;
    private int years = 5;

    public Credit(int amount, double coefficient, Order order) {
        this.amount = amount;
        this.coefficient = coefficient;
        this.order = order;
        startTime = new Date();
        endTime = new Date();
        endTime.setYear(new Date().getYear() + 5);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "\nAmount: " + (amount * coefficient) +
                "\nCoefficient: " + coefficient +
                "\n[ Order ]" + order.toString() +
                "\nStart time: " + startTime +
                "\nEnd Time: " + endTime +
                "\nYears: " + years;
    }
}
