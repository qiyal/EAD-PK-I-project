package midka;

import midka.strategies.CreditCard;

public class Customer extends User {
    private String email;
    private CreditCard card;

    public Customer() {}

    public Customer(String firstName, String lastName, String email, CreditCard card) {
        super(firstName, lastName);
        this.email = email;
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

    @Override
    public String toString() {
        return super.toString() + "\nEmail: " + email + card.toString();
    }
}
