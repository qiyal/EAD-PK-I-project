package midka.iterators;

import midka.singleton.Pair;

import java.util.ArrayList;
import java.util.Map;

public class DBBikeIterator implements ICustomIterator {
    private ArrayList<Pair> bikes;
    private int position = 0;

    public DBBikeIterator(Map<String, Pair> bikes) {
        this.bikes = new ArrayList<>(bikes.values());
    }

    @Override
    public boolean hasNext() {
        return position < bikes.size();
    }

    @Override
    public Object next() {
        return bikes.get(position++);
    }
}
