package midka.files;

import midka.visitor.Visitor;

import java.util.Date;

public class Credit implements IFile {
    private int amount;
    private double coefficient;
    private Order order;
    private Date startTime;
    private Date endTime;
    private int years = 5;

    public Credit(int amount, double coefficient, Order order) {
        this.amount = (int)(amount * coefficient);
        this.coefficient = (coefficient - 1.0) * 100;
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

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getYears() {
        return years;
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
        return "\nAmount: " + amount +
                "\nCoefficient: " + coefficient +
                "\n[ Order ]" + order.toString() +
                "\nStart time: " + startTime +
                "\nEnd Time: " + endTime +
                "\nYears: " + years;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCredit(this);
    }
}
