package midka.visitor;

import midka.files.Credit;
import midka.files.Order;
import midka.singleton.DBCredit;
import midka.singleton.DBOrder;
import midka.singleton.DBUser;
import midka.users.Admin;
import midka.users.Customer;

import java.util.ArrayList;

public class JSONExportVisitor implements Visitor {
    private String tab;

    public String doExportOrdersToJsonFormat() {
        ArrayList<Order> orders = DBOrder.getInstance().getOrders();
        return null;
    }

    public String doExportDbOrderAndDbCreditToJsonFormat() {
        ArrayList<Order> orders = DBOrder.getInstance().getOrders();
        ArrayList<Credit> credits = DBCredit.getInstance().getCredits();

        StringBuilder sb = new StringBuilder();
        tab = "\t\t";

        sb.append("{" + "\n");

        sb.append("\t\"orders:\" " + "[" + "\n");
        for (Order order : orders) {
            sb.append("\t\t").append(order.accept(this)).append(",").append("\n");
        }
        sb.append("\t],").append("\n");

        sb.append("\t\"credits:\" " + "[" + "\n");
        for (Credit credit : credits) {
            sb.append("\t\t").append(credit.accept(this)).append(",").append("\n");
        }
        sb.append("\t]").append("\n");

        sb.append("}" + "\n");

        return sb.toString();
    }

    @Override
    public String visitOrder(Order order) {
        return "{" + "\n" +
                    tab + "\t\"customerEmail\": " + order.getCustomerEmail() + "," +  "\n" +
                    tab + "\t\"motorbikeId\": " + order.getMotorbikeId() + "," + "\n" +
                    tab + "\t\"cost\": " + order.getCost() + "," + "\n" +
                tab + "}";
    }

    @Override
    public String visitCredit(Credit credit) {
        String str = "{" + "\n" +
                    tab + "\t\"amount\": " + credit.getAmount() + "," +  "\n" +
                    tab + "\t\"coefficient\": " + credit.getCoefficient() + "," + "\n" +
                    tab;
        tab = "\t\t\t";
        str += "\t\"order\": " + credit.getOrder().accept(this) + "," + "\n";

        tab = "\t\t";
        str += tab + "\t\"startTime\": " + credit.getStartTime() + "," + "\n" +
                    tab + "\t\"endTime\": " + credit.getEndTime() + "," + "\n" +
                    tab + "\t\"years\": " + credit.getYears() + "," + "\n" +
                tab + "}";
        return str;
    }
}
