package midka.files;

import midka.visitor.Visitor;

public class Order implements IFile {
    private String customerEmail;
    private String motorbikeId;
    private int cost = 0;

    public Order () {}

    public Order(int cost, String customerEmail, String motorbikeId) {
        this.cost = cost;
        this.customerEmail = customerEmail;
        this.motorbikeId = motorbikeId;
    }

    public void setTotalCost(int cost) {
        this.cost += cost;
    }

    public int getTotalCost() {
        return cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getMotorbikeId() {
        return motorbikeId;
    }

    public void setMotorbikeId(String motorbikeId) {
        this.motorbikeId = motorbikeId;
    }

    @Override
    public String toString() {
        return "\nCost: " + cost +
                "\nCustomer email: '" + customerEmail +
                "\nMotorbike name: " + motorbikeId;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitOrder(this);
    }
}
