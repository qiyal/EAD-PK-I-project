package midka.visitor;

import midka.files.Credit;
import midka.files.Order;
import midka.singleton.DBCredit;
import midka.singleton.DBOrder;

import java.util.ArrayList;

public class JSONExportVisitor implements Visitor {
    private String tab;

    public String doExportDbOrderAndDbCreditToJsonFormat() {
        ArrayList<Order> orders = DBOrder.getInstance().getOrders();
        ArrayList<Credit> credits = DBCredit.getInstance().getCredits();

        StringBuilder sb = new StringBuilder();
        tab = "\t\t";

        sb.append("{" + "\n");

        sb.append("\t\"orders\": " + "[" + "\n");
        for (int i = 0; i < orders.size(); ++i) {
            sb.append("\t\t").append(orders.get(i).accept(this));
            if(i < orders.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("\t],").append("\n");

        sb.append("\t\"credits\": " + "[" + "\n");
        for (int i = 0; i < credits.size(); ++i) {
            sb.append("\t\t").append(credits.get(i).accept(this));
            if(i < credits.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("\t]").append("\n");

        sb.append("}" + "\n");

        return sb.toString();
    }

    @Override
    public String visitOrder(Order order) {
        return "{" + "\n" +
                    tab + "\t\"customerEmail\": \"" + order.getCustomerEmail() + "\"," +  "\n" +
                    tab + "\t\"motorbikeId\": \"" + order.getMotorbikeId() + "\"," + "\n" +
                    tab + "\t\"cost\": " + order.getCost() + "\n" +
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
        str += tab + "\t\"startTime\": \"" + credit.getStartTime() + "\"," + "\n" +
                    tab + "\t\"endTime\": \"" + credit.getEndTime() + "\"," + "\n" +
                    tab + "\t\"years\": " + credit.getYears() + "\n" +
                tab + "}";
        return str;
    }
}
