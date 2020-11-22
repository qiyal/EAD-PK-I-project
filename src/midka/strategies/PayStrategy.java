package midka.strategies;

public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails(String motorbikeName, String customerName);
}
