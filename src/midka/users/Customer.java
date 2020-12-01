package midka.users;

import midka.decorators.NotifierEnum;
import midka.strategies.CreditCard;

import java.util.HashSet;
import java.util.Set;

public class Customer extends User {
    private String email;
    private String password;
    private CreditCard card;
    private String telephoneNumber;
    private String facebookLogin;
    private Set<NotifierEnum> notifiers;

    public Customer() {}

    public Customer(String firstName, String lastName, String email, String password, CreditCard card) {
        super(firstName, lastName);
        this.email = email;
        this.password = password;
        this.card = card;
        this.notifiers = new HashSet<>(3);
        this.notifiers.add(NotifierEnum.EMAIL);
    }

    // SETTERS
    public void setCard(CreditCard card) {
        this.card = card;
    }

    public void setNotifiers(Set<NotifierEnum> notifiers) {
        this.notifiers = notifiers;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setFacebookLogin(String facebookLogin) {
        this.facebookLogin = facebookLogin;
    }

    // GETTERS
    public CreditCard getCard() {
        return card;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getFacebookLogin() {
        return facebookLogin;
    }

    public Set<NotifierEnum> getNotifiers() {
        return notifiers;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmail: " + email + card.toString();
    }
}
