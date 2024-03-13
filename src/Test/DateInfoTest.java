package Test;
import paket.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateInfoTest {

    @Test
    public void testGetStartYear() {
        DateInfo dateInfo = new DateInfo(2023, 3, 6);
        assertEquals(2023, dateInfo.getStartYear());
    }

    @Test
    public void testGetEndMonth() {
        DateInfo dateInfo = new DateInfo(2023, 3, 6);
        assertEquals(6, dateInfo.getEndMonth());
    }
}

