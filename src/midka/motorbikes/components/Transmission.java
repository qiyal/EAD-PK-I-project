package midka.motorbikes.components;

public class Transmission {
    private int levelCount;
    private String Type;

    public Transmission(int levelCount, String type) {
        this.levelCount = levelCount;
        Type = type;
    }

    @Override
    public String toString() {
        return "[LevelCount] = " + levelCount +
                "[type] = " + Type;
    }
}
