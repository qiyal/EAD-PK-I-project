package midka.motorbikes;

import midka.builders.MotorbikeBuilder;
import midka.motorbikes.components.Chassis;
import midka.motorbikes.components.Engine;
import midka.motorbikes.components.Transmission;

public class Motorbike implements Movable {
    private String modelCode;
    private String manufacturer;
    private Engine engine;
    private Transmission transmission;
    private Chassis chassis;
    private int maxSpeed;
    private int width;
    private int height;
    private int weight;
    private double fuelTankCapacity;
    private String classType;
    private boolean isLuggageCompartment;
    private boolean isComputerMonitor;
    private int price;

    public Motorbike() {}

    public Motorbike(MotorbikeBuilder builder) {
        this.modelCode = builder.modelCode;
        this.manufacturer = builder.manufacturer;
        this.engine = builder.engine;
        this.transmission = builder.transmission;
        this.chassis = builder.chassis;
        this.maxSpeed = builder.maxSpeed;
        this.width = builder.width;
        this.height = builder.height;
        this.weight = builder.weight;
        this.fuelTankCapacity = builder.fuelTankCapacity;
        this.classType = builder.classType;
        this.isLuggageCompartment = builder.isLuggageCompartment;
        this.isComputerMonitor = builder.isComputerMonitor;
        this.price = builder.price;
    }

    public String getModelCode() {
        return modelCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nModel code: " + modelCode +
                "\nManufacturer: " + manufacturer +
                "\n[ Engine ]" + engine +
                "\nTransmission: " + transmission +
                "\nChassis: " + chassis +
                "\nMaxSpeed: " + maxSpeed +
                "\nWidth: " + width +
                "\nHeight: " + height +
                "\nWeight: " + weight +
                "\nFuel tank capacity: " + fuelTankCapacity +
                "\nClass type: " + classType +
                "\nIs Luggage compartment: " + isLuggageCompartment +
                "\nIs Computer monitor: " + isComputerMonitor;
    }
}
