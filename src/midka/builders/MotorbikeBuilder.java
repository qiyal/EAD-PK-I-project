package midka.builders;

import midka.motorbikes.components.Chassis;
import midka.motorbikes.components.Engine;
import midka.motorbikes.components.Transmission;

public class MotorbikeBuilder {
    public String modelCode;
    public String manufacturer;
    public Engine engine;
    public Transmission transmission;
    public Chassis chassis;
    public int maxSpeed;
    public int width;
    public int height;
    public int weight;
    public double fuelTankCapacity;
    public String classType;
    public boolean isLuggageCompartment;
    public boolean isComputerMonitor;
    public int price;

    public MotorbikeBuilder() { }

    public MotorbikeBuilder setModelCode(String modelCode) {
        this.modelCode = modelCode;
        return this;
    }

    public MotorbikeBuilder setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public MotorbikeBuilder setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public MotorbikeBuilder setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public MotorbikeBuilder setChassis(Chassis chassis) {
        this.chassis = chassis;
        return this;
    }

    public MotorbikeBuilder setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public MotorbikeBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public MotorbikeBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public MotorbikeBuilder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public MotorbikeBuilder setFuelTankCapacity(double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
        return this;
    }

    public MotorbikeBuilder setClassType(String classType) {
        this.classType = classType;
        return this;
    }

    public MotorbikeBuilder setLuggageCompartment(boolean luggageCompartment) {
        isLuggageCompartment = luggageCompartment;
        return this;
    }

    public MotorbikeBuilder setComputerMonitor(boolean computerMonitor) {
        isComputerMonitor = computerMonitor;
        return this;
    }

    public MotorbikeBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public MotorbikeBuilder getResult() {
        return this;
    }
}
