package midka.strategies;

import midka.motorbikes.Motorbike;

public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails(String motorbikeName, String customerName);
}
