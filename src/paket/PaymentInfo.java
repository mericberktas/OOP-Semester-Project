package paket;
import java.io.Serializable;

public class PaymentInfo implements Serializable{
    private final double discountRatio;
    private double receivedPayment = 0;

    public PaymentInfo(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    public void increasePayment(double amount){
        receivedPayment += amount;
    }

    public double getReceivedPayment() {
        return receivedPayment;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "discountRatio=" + discountRatio +
                ", receivedPayment=" + receivedPayment +
                '}';
    }
}
