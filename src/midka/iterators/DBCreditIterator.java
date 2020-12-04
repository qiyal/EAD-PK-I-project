package midka.iterators;

import midka.files.Credit;

import java.util.ArrayList;

public class DBCreditIterator implements ICustomIterator {
    private ArrayList<Credit> credits;
    private int position = 0;

    public DBCreditIterator(ArrayList<Credit> credits) {
        this.credits = credits;
    }

    @Override
    public boolean hasNext() {
        return position < credits.size();
    }

    @Override
    public Object next() {
        return credits.get(position++);
    }
}
