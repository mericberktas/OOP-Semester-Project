package paket;
import java.io.Serializable;

public class Individual extends Subscriber implements Serializable{
    private String creditCardNumber;
    private int expireYear;
    private int expireMonth;
    private int CCV;

    public Individual(String name, String address, String creditCardNumber, int expireYear, int expireMonth, int CCV) {
        super(name, address);
        this.creditCardNumber = creditCardNumber;
        this.expireYear = expireYear;
        this.expireMonth = expireMonth;
        this.CCV = CCV;
    }

    @Override
    public String getBillingInformation() {
        return "Expire month and year: " + expireMonth +"/"+ expireYear;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", expireYear=" + expireYear +
                ", expireMonth=" + expireMonth +
                ", CCV=" + CCV +
                '}';
    }
}
