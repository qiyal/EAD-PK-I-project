package midka.visitor;

import midka.files.Credit;
import midka.files.Order;

public interface Visitor {
    String visitOrder(Order order);
    String visitCredit(Credit credit);
}
