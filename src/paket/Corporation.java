package paket;
import java.io.Serializable;

public class Corporation extends Subscriber implements Serializable{
    private int bankCode;
    private String bankName;
    private int issueMonth;
    private int issueYear;
    private int accountNumber;

    public Corporation(String name, String address, int bankCode, String bankName, int issueMonth, int issueYear, int accountNumber) {
        super(name, address);
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.issueMonth = issueMonth;
        this.issueYear = issueYear;
        this.accountNumber = accountNumber;
    }

    @Override
    public String getBillingInformation() {
        return "Billing month and year: " + issueMonth + "/" + issueYear;
    }

    @Override
    public String toString() {
        return "Corporation{" +
                "bankCode=" + bankCode +
                ", bankName='" + bankName + '\'' +
                ", issueMonth=" + issueMonth +
                ", issueYear=" + issueYear +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
