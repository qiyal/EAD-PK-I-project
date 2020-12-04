package midka.visitor;

import midka.files.Credit;
import midka.files.Order;
import midka.users.Admin;
import midka.users.Customer;

public interface Visitor {
    String visitOrder(Order order);
    String visitCredit(Credit credit);
}
