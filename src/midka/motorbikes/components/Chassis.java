package midka.motorbikes.components;

public class Chassis {
    private String type;
    private boolean isABS;

    public Chassis(String type, boolean isABS) {
        this.type = type;
        this.isABS = isABS;
    }

    @Override
    public String toString() {
        return "[type] = " + type + " [ABS] = " + isABS;
    }
}
