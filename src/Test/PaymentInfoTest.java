package Test;
import paket.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentInfoTest {

    @Test
    public void testIncreasePayment() {
        PaymentInfo paymentInfo = new PaymentInfo(0.1);
        paymentInfo.increasePayment(50);

        assertEquals(50, paymentInfo.getReceivedPayment(), 0.001);
    }

    @Test
    public void testGetReceivedPayment() {
        PaymentInfo paymentInfo = new PaymentInfo(0.1);
        paymentInfo.increasePayment(30);

        assertEquals(30, paymentInfo.getReceivedPayment(), 0.001);
    }
}

