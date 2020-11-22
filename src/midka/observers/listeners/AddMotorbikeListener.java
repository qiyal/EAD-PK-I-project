package midka.observers.listeners;

public class AddMotorbikeListener implements EventListener {
    public String motorbikeName;
    public String email;

    public AddMotorbikeListener(String motorbikeName, String email) {
        this.motorbikeName = motorbikeName;
        this.email = email;
    }

    @Override
    public void update(String eventType) {
        if(motorbikeName.equals(eventType)) {
            System.out.println("\nSent Message to " + email + ".");
            System.out.println("We have received a new motorcycle" + motorbikeName + ".");
        }
    }
}
