package midka.visitor;

import midka.files.Credit;
import midka.files.Order;

import java.util.ArrayList;

public class JSONExportVisitor implements Visitor {

    public void doExportDbOrderAndDbCreditToJsonFormat(ArrayList<Order> orders, ArrayList<Credit> credits) {

    }

    @Override
    public String visitOrder(Order order) {
        return "{" + "\n" +
                    "\t\"customerEmail\": " + order.getCustomerEmail() + "," +  "\n" +
                    "\t\"motorbikeId\": " + order.getMotorbikeId() + "," + "\n" +
                    "\t\"cost\":" + order.getCost() + "," + "\n" +
                "}" + "\n";
    }

    @Override
    public String visitCredit(Credit credit) {
        return "{" + "\n" +
                "\t\"amount\": " + credit.getAmount() + "," +  "\n" +
                "\t\"coefficient\": " + credit.getCoefficient() + "," + "\n" +
                "\t\"order\":" + visitOrder(credit.getOrder()) + "," + "\n" +
                "\t\"startTime\":" + credit.getStartTime() + "," + "\n" +
                "\t\"endTime\":" + credit.getEndTime() + "," + "\n" +
                "\t\"years\":" + credit.getYears() + "," + "\n" +
                "}" + "\n";
    }
}
