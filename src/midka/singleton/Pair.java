package midka.singleton;

import midka.motorbikes.Motorbike;
import midka.motorbikes.Movable;

public class Pair {
    public Motorbike motorbike;
    public int count;

    public Pair(Motorbike motorbike, int count) {
        this.motorbike = motorbike;
        this.count = count;
    }
}
