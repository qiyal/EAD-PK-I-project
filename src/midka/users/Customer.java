package midka.users;

import midka.strategies.CreditCard;

public class Customer extends User {
    private String email;
    private String password;
    private CreditCard card;

    public Customer() {}

    public Customer(String firstName, String lastName, String email, String password, CreditCard card) {
        super(firstName, lastName);
        this.email = email;
        this.password = password;
        this.card = card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public CreditCard getCard() {
        return card;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmail: " + email + card.toString();
    }
}
