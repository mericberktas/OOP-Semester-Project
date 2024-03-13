package Test;
import paket.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CorporationTest {

    @Test
    public void testGetBillingInformation() {
        Corporation corporation = new Corporation("ABC Corp", "456 Business Ave", 9876, "BusinessBank", 6, 2024, 56789);

        String expected = "Billing month and year: 6/2024";
        assertEquals(expected, corporation.getBillingInformation());
    }

    @Test
    public void testToString() {
        Corporation corporation = new Corporation("ABC Corp", "456 Business Ave", 9876, "BusinessBank", 6, 2024, 56789);

        String expected = "Corporation{" +
                "bankCode=9876, bankName='BusinessBank', " +
                "issueMonth=6, issueYear=2024, accountNumber=56789}";
        assertEquals(expected, corporation.toString());
    }
}

