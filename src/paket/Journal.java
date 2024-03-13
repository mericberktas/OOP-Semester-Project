package paket;
import java.io.Serializable;

public class Journal implements Serializable{
    private String name;
    private String issn;
    private int frequency;
    private double issuePrice;

    public Journal(String name, String issn, int frequency, double issuePrice) {
        this.name = name;
        this.issn = issn;
        this.frequency = frequency;
        this.issuePrice = issuePrice;
    }

    public void addSubscription(Subscription subscription){
        if(subscription.getSubscriber() == null || subscription.getJournal() == null){
            return;
        }else{
            subscription.increaseCopies();
        }
    }

    public String getIssn() {
        return issn;
    }

    public String getName() {
        return name;
    }

    public double getIssuePrice() {
        return issuePrice;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", issn='" + issn + '\'' +
                ", frequency=" + frequency +
                ", issuePrice=" + issuePrice +
                '}';
    }

    public int getFrequency() {
        return frequency;
    }
}
