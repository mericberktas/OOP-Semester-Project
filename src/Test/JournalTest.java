package Test;
import paket.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class JournalTest {

    @Test
    public void testAddSubscription() {
        Journal journal = new Journal("Tech Magazine", "ISSN123", 12, 25.99);
        Subscription subscription = new Subscription(new DateInfo(2023, 1, 12), new PaymentInfo(0.15), 1, journal, new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456));

        journal.addSubscription(subscription);
        assertEquals(1, subscription.getCopies());
    }

    @Test
    public void testToString() {
        Journal journal = new Journal("Tech Magazine", "ISSN123", 12, 25.99);

        String expected = "{name='Tech Magazine', issn='ISSN123', frequency=12, issuePrice=25.99}";
        assertEquals(expected, journal.toString());
    }
}
