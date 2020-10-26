package midka.observers.listeners;

import midka.singleton.DBBike;

public class ChangePriceMotorbike implements EventListener {
    public String motorbikeName;
    private int oldPrice;
    public String email;
    private String message;
    private DBBike dbBike = DBBike.getInstance();

    public ChangePriceMotorbike(String motorbikeName, String email, int price) {
        this.motorbikeName = motorbikeName;
        this.email = email;
        this.oldPrice = price;
    }

    @Override
    public void update(String eventType) {
        if(eventType.equals(motorbikeName)) {
            int newPrice = dbBike.getMotorBike(motorbikeName).getPrice();
            message = "Price: " + oldPrice + "\nNew price: " + newPrice;
            System.out.println("Sent Message to " + email + ".");
            System.out.println(message);
            oldPrice = newPrice;
        }
    }
}
