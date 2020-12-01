package midka.observers.listeners;

import midka.services.NotifierService;

public class AddMotorbikeListener implements EventListener {
    public String motorbikeName;
    public String email;
    private NotifierService notifierService = NotifierService.getInstance();

    public AddMotorbikeListener(String motorbikeName, String email) {
        this.motorbikeName = motorbikeName;
        this.email = email;
    }

    @Override
    public void update(String eventType) {
        if(motorbikeName.equals(eventType)) {
            String message = "We have received a new motorcycle: " + motorbikeName + ".";
            notifierService.sendMessage(email, message);
        }
    }
}
