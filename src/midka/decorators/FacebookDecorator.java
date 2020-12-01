package midka.decorators;

public class FacebookDecorator extends NotifierDecorator {
    private String login;

    public FacebookDecorator() {}

    public FacebookDecorator(Notifier wrapper, String login) {
        super(wrapper);
        this.login = login;
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendToFacebook(message);
    }

    private void sendToFacebook(String message) {
        System.out.println("\nSent a message to Facebook account: " + login);
        System.out.println("Message: " + message);
    }
}
