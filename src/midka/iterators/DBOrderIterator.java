package midka.iterators;

import midka.files.Order;

import java.util.ArrayList;

public class DBOrderIterator implements ICustomIterator {
    private ArrayList<Order> orders;
    private int position = 0;

    public DBOrderIterator(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean hasNext() {
        return position < orders.size();
    }

    @Override
    public Object next() {
        return orders.get(position++);
    }
}
