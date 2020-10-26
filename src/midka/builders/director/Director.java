package midka.builders.director;

import midka.builders.MotorbikeBuilder;
import midka.motorbikes.Motorbike;
import midka.motorbikes.components.Chassis;
import midka.motorbikes.components.Engine;
import midka.motorbikes.components.Transmission;

public class Director {
    public Motorbike constructHarleyDavidson(MotorbikeBuilder builder) {
        return new Motorbike(builder.setModelCode("V-Rod Muscle VRSCF 1250")
                .setManufacturer("Harley-Davidson")
                .setEngine(new Engine(1247, 2, 122, "Liquid", "Electric starter"))
                .setTransmission(new Transmission(5, "AUTO"))
                .setChassis(new Chassis("Belt", true))
                .setMaxSpeed(150)
                .setWidth(2357)
                .setHeight(650)
                .setWeight(307)
                .setFuelTankCapacity(18.9)
                .setClassType("Cruise")
                .setLuggageCompartment(true)
                .setComputerMonitor(false)
                .setPrice(10000000)
                .getResult()
        );
    }

    public Motorbike constructYamaha(MotorbikeBuilder builder) {
        return new Motorbike(builder.setModelCode("Star Bolt R Spec XVS95CEGY/C")
                .setManufacturer("Yamaha")
                .setEngine(new Engine(942, 2, 54, "Air", "Injector"))
                .setTransmission(new Transmission(5, "AUTO"))
                .setChassis(new Chassis("Belt", false))
                .setMaxSpeed(200)
                .setWidth(2357)
                .setHeight(650)
                .setWeight(307)
                .setFuelTankCapacity(18.9)
                .setClassType("Cruise")
                .setLuggageCompartment(true)
                .setComputerMonitor(false)
                .setPrice(7000000)
                .getResult()
        );
    }
}
