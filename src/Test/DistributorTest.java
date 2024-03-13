package Test;
import paket.*;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import static org.junit.Assert.*;

public class DistributorTest {

    @Test
    public void testAddJournal() {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Magazine", "ISSN123", 12, 25.99);

        assertTrue(distributor.addJournal(journal));
        assertEquals(journal, distributor.searchJournal("ISSN123"));
    }

    @Test
    public void testSearchJournal() {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Magazine", "ISSN123", 12, 25.99);

        distributor.addJournal(journal);

        assertEquals(journal, distributor.searchJournal("ISSN123"));
        assertNull(distributor.searchJournal("ISSN"));
    }

    @Test
    public void testAddSubscriber() {
        Distributor distributor = new Distributor();
        Subscriber subscriber = new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456);

        assertTrue(distributor.addSubscriber(subscriber));
        assertEquals(subscriber, distributor.searchSubscriber("John"));
    }

    @Test
    public void testSearchSubscriber() {
        Distributor distributor = new Distributor();
        Subscriber subscriber = new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456);

        distributor.addSubscriber(subscriber);

        assertEquals(subscriber, distributor.searchSubscriber("John"));
        assertNull(distributor.searchSubscriber("Subscriber"));
    }


    @Test
    public void testListAllSendingOrders() {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Magazine", "123", 12, 25.99);
        Subscriber subscriber = new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456);

        distributor.addJournal(journal);
        distributor.addSubscriber(subscriber);

        DateInfo dateInfo = new DateInfo(2023, 1, 12);
        PaymentInfo paymentInfo = new PaymentInfo(0.15);
        distributor.addSubscription("123", "John", dateInfo, paymentInfo, 1);

        List<Subscription> subscriptions = distributor.getSubscriptions(subscriber);

        assertEquals(1, subscriptions.size());
        assertEquals(1, subscriptions.get(0).getCopies());
    }

    @Test
    public void testListIncompletePayments() {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Magazine", "123", 12, 25.99);
        Subscriber subscriber = new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456);

        distributor.addJournal(journal);
        distributor.addSubscriber(subscriber);

        DateInfo dateInfo = new DateInfo(2023, 1, 12);
        PaymentInfo paymentInfo = new PaymentInfo(0.15);
        distributor.addSubscription("123", "John", dateInfo, paymentInfo, 1);

        List<Subscription> subscriptions = distributor.getSubscriptions(subscriber);

        assertEquals(1, subscriptions.size());
        assertEquals(25.99, subscriptions.get(0).getJournal().getIssuePrice(), 0.001);

        distributor.listIncompletePayments();
    }

    @Test
    public void testListSubscriptionsBySubscriber() {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Magazine", "123", 12, 25.99);
        Subscriber subscriber = new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456);

        distributor.addJournal(journal);
        distributor.addSubscriber(subscriber);

        DateInfo dateInfo = new DateInfo(2023, 1, 12);
        PaymentInfo paymentInfo = new PaymentInfo(0.15);
        distributor.addSubscription("123", "John", dateInfo, paymentInfo, 1);

        List<Subscription> subscriptions = distributor.getSubscriptions(subscriber);

        assertEquals(1, subscriptions.size());
        assertEquals("Magazine", subscriptions.get(0).getJournal().getName());

        distributor.listSubscriptionsBySubscriber("John");
    }

    @Test
    public void testListSubscriptionsByIssn() {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Magazine", "123", 12, 25.99);
        Subscriber subscriber = new Individual("John", "123 Street", "1234-5678-9012-3456", 2024, 8, 456);

        distributor.addJournal(journal);
        distributor.addSubscriber(subscriber);

        DateInfo dateInfo = new DateInfo(2023, 1, 12);
        PaymentInfo paymentInfo = new PaymentInfo(0.15);
        distributor.addSubscription("123", "John", dateInfo, paymentInfo, 1);

        List<Subscription> subscriptions = distributor.getSubscriptions(subscriber);

        assertEquals(1, subscriptions.size());
        assertEquals("John", subscriptions.get(0).getSubscriber().getName());

        distributor.listSubscriptionsByIssn("123");
    }

}

