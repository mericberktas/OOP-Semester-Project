package Test;
import paket.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class SubscriptionTest {

    @Test
    public void testAcceptPayment() {
        Subscription subscription = new Subscription(new DateInfo(2023, 1, 12), new PaymentInfo(0.15), 1,
                new Journal("Tech Magazine", "ISSN123", 12, 25.99), new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456));

        subscription.acceptPayment(30);
        assertEquals(30, subscription.getPayment().getReceivedPayment(), 0.001);
    }

    @Test
    public void testCanSend() {
        Subscription subscription = new Subscription(new DateInfo(2023, 1, 12), new PaymentInfo(0.15), 1,
                new Journal("Tech Magazine", "ISSN123", 12, 25.99), new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456));

        assertFalse(subscription.canSend(1,2023));
    }
}

