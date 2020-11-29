package midka.singleton;

import midka.builders.MotorbikeBuilder;
import midka.iterators.DBBikeIterator;
import midka.iterators.ICustomIterableCollection;
import midka.iterators.ICustomIterator;
import midka.motorbikes.Motorbike;
import midka.motorbikes.components.Chassis;
import midka.motorbikes.components.Engine;
import midka.motorbikes.components.Transmission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBBike implements ICustomIterableCollection {
    private static DBBike instance;
    private Map<String, Pair> motorbikes = new HashMap<>();

    {
        Motorbike bike1 = new Motorbike(
                new MotorbikeBuilder().setModelCode("V-Rod Muscle VRSCF 1250")
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

        Motorbike bike2 = new Motorbike(
                new MotorbikeBuilder().setModelCode("Star Bolt R Spec XVS95CEGY/C")
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

        motorbikes.put(bike2.getModelCode(), new Pair(bike2, 1));
        motorbikes.put(bike1.getModelCode(), new Pair(bike1, 1));
    }

    private DBBike() {}

    public static DBBike getInstance() {
        if (instance == null)
            instance = new DBBike();
        return instance;
    }

    public void addMotorbike(Motorbike newMotorbike) {
        Pair pair = motorbikes.getOrDefault(newMotorbike.getModelCode(), new Pair(newMotorbike, 0));
        motorbikes.put(newMotorbike.getModelCode(), new Pair(newMotorbike, pair.count + 1));
    }

    public void deleteMotorbike(String motorbikeName) {
        if(motorbikes.containsKey(motorbikeName)) {
            Pair pair = motorbikes.get(motorbikeName);
            if (pair.count == 1)
                motorbikes.remove(motorbikeName);
            else
                motorbikes.put(motorbikeName, new Pair(pair.motorbike, pair.count - 1));
        } else {
            System.out.println("Error!!!");
        }
    }

    public void setNewPrice(String bikeName, int newPrice) {
        motorbikes.get(bikeName).motorbike.setPrice(newPrice);
    }

    public Motorbike getMotorBike(String motorbike) {
        if (motorbikes.containsKey(motorbike)) {
            return motorbikes.get(motorbike).motorbike;
        }
        System.out.println("Error!!!. " + motorbike + "Not exist");
        return null;
    }

    public ArrayList<String> getBikesCodeName() {
        ArrayList<String> arr = new ArrayList<>();

        for (String str : motorbikes.keySet()) {
            arr.add(str);
        }

        return arr;
    }

    public void showAllMotorBikes() {
        if (motorbikes.isEmpty())
            System.out.println("\nThere are not motorbikes in the Garage!!!");
        else {
            System.out.println("\n[ Motorbikes ]");
            ICustomIterator iterator = createIterator();
            int i = 0;

            while (iterator.hasNext()) {
                Pair pair = (Pair) iterator.next();
                System.out.println("\n" + (i++) + ") " + pair.motorbike.getModelCode());
                System.out.println("   Manufacturer: " + pair.motorbike.getManufacturer());
                System.out.println("   Price: " + pair.motorbike.getPrice());
                System.out.println("   Count: " + pair.count);
            }
        }
    }


    @Override
    public ICustomIterator createIterator() {
        return new DBBikeIterator(motorbikes);
    }
}
