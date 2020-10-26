package midka.motorbikes.components;

public class Engine {
    private double volume;
    private int cylinderNumber;
    private int maxPower;
    private String cooling;
    private String ignitionType;
    private double mileage = 0;
    private boolean started = false;

    public Engine(int volume, int cylinderNumber, int maxPower, String cooling, String ignitionType) {}

    public void on() {
        started = true;
    }

    public void off() {
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public void go(double mileage) {
        if (started) {
            this.mileage += mileage;
        } else {
            System.err.println("Cannot go(), you must start engine first!");
        }
    }

    @Override
    public String toString() {
        return "\nVolume: " + volume +
                "\nCylinder number: " + cylinderNumber +
                "\nMax power: " + maxPower +
                "\nCooling: " + cooling +
                "\nIgnition type: " + ignitionType +
                "\nMileage: " + mileage +
                "\nStarted: " + started;
    }
}
