package midka.observers.listeners;

import midka.singleton.DBBike;

public class ChangePriceMotorbike implements EventListener {
    public String motorbikeName;
    public String email;
    private String message;
    private DBBike dbBike = DBBike.getInstance();

    public ChangePriceMotorbike(String motorbikeName, String email) {
        this.motorbikeName = motorbikeName;
        this.email = email;
    }

    @Override
    public void update(String eventType) {
        if(eventType.equals(motorbikeName)) {
            int newPrice = dbBike.getMotorBike(motorbikeName).getPrice();
            message = motorbikeName + "\nNew price: " + newPrice;
            System.out.println("\nSent Message to " + email + ".");
            System.out.println(message);
        }
    }
}
