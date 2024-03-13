package Test;
import paket.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class IndividualTest {

    @Test
    public void testGetBillingInformation() {
        Individual individual = new Individual("John Doe", "123 Main St", "1234-5678-9012-3456", 2025, 12, 123);

        String expected = "Expire month and year: 12/2025";
        assertEquals(expected, individual.getBillingInformation());
    }

    @Test
    public void testToString() {
        Individual individual = new Individual("John Doe", "123 Main St", "1234-5678-9012-3456", 2025, 12, 123);

        String expected = "Individual{" +
                "creditCardNumber='1234-5678-9012-3456', " +
                "expireYear=2025, expireMonth=12, CCV=123}";
        assertEquals(expected, individual.toString());
    }
}

