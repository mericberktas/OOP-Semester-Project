package paket;
import java.io.Serializable;

public class Subscription implements Serializable{
    private final DateInfo dates;
    private PaymentInfo payment;
    private int copies;
    private final Journal journal;
    private final Subscriber subscriber;

    public Subscription(DateInfo dates, PaymentInfo payment, int copies, Journal journal, Subscriber subscriber) {
        this.dates = dates;
        this.payment = payment;
        this.copies = copies;
        this.journal = journal;
        this.subscriber = subscriber;
    }
        
    public void acceptPayment(double paymentAmount) {
        if (paymentAmount > 0) {
            this.payment.increasePayment(paymentAmount);
                System.out.println("Payment accepted: " + paymentAmount);
        } else {
                System.out.println("Invalid payment amount. Payment must be a positive value.");
        }
    }

    public boolean canSend(int issueMonth, int issueYear) {
        int startYear = dates.getStartYear();
        int startMonth = dates.getStartMonth();
        int endMonth = dates.getEndMonth();
        int frequency = journal.getFrequency();

        if (issueYear < startYear || issueYear > endMonth) {
            return false;
        }

        if (issueYear == startYear && issueMonth < startMonth) {
            return false;
        }

        int monthsSinceStart = (issueYear - startYear) * 12 + issueMonth - startMonth;
        return monthsSinceStart % frequency == 0 && issueMonth <= endMonth;
    }




    public Journal getJournal() {
        return journal;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void increaseCopies(){
        this.copies++;
    }

    public int getCopies() {
        return copies;
    }

    public PaymentInfo getPayment() {
        return payment;
    }

    public boolean datesInRange(int year, int month) {
        int startYear = dates.getStartYear();
        int startMonth = dates.getStartMonth();
        int endMonth = dates.getEndMonth();

        if (year > startYear && year < endMonth) {
            return true;
        } else if (year == startYear && month >= startMonth) {
            return true;
        } else return year == endMonth && month <= endMonth;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "dates=" + dates +
                ", payment=" + payment +
                ", copies=" + copies +
                ", journal=" + journal +
                ", subscriber=" + subscriber +
                '}';
    }

    public DateInfo getDates() {
        return dates;
    }
}



