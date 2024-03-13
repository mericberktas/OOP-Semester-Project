package paket;

import java.io.*;
import java.util.*;
import java.io.Serializable;
public class Distributor implements Serializable{
    private Hashtable<String, Journal> journals;
    private Vector<Subscriber> subscribers;
    private Hashtable<Subscriber, List<Subscription>> subscriberSubscriptions;

    public Distributor() {
        this.journals = new Hashtable<>();
        this.subscribers = new Vector<>();
        this.subscriberSubscriptions = new Hashtable<>();
    }

    public boolean addJournal(Journal journal) {
        if (journal != null) {
            String issn = journal.getIssn();

            journals.put(issn, journal);

            return true;
        }
        return false;
    }

    public Journal searchJournal(String issn) {
        return journals.get(issn);
    }

    public boolean addSubscriber(Subscriber subscriber) {
        if(subscriber != null){
            subscribers.add(subscriber);
            return true;
        }
       return false;
    }

    public Subscriber searchSubscriber(String name) {
        for(Subscriber subscriber : subscribers){
            if(subscriber.getName().equals(name))
                return subscriber;
        }
        return null;
    }

    public boolean addSubscription(String issn, String subscriberName, DateInfo dateInfo, PaymentInfo paymentInfo, int copies) {
        Journal journal = searchJournal(issn);
        if (journal == null) {
            System.out.println("Journal with ISSN " + issn + " not found.");
            return false;
        }

        Subscriber subscriber = searchSubscriber(subscriberName);
        if (subscriber == null) {
            System.out.println("Subscriber with name " + subscriberName + " not found.");
            return false;
        }

        Subscription subscription = new Subscription(dateInfo, paymentInfo, copies, journal, subscriber);

        if (!subscriberSubscriptions.containsKey(subscriber)) {
            subscriberSubscriptions.put(subscriber, new ArrayList<>());
        }
        subscriberSubscriptions.get(subscriber).add(subscription);

        return true;
    }

    public List<Subscription> getSubscriptions(Subscriber subscriber) {
        return subscriberSubscriptions.getOrDefault(subscriber, Collections.emptyList());
    }

    public void listSendingOrders(String issn, int month, int year) {
        System.out.println("Sending orders for the journal with the issn: " + issn);

        Journal journal = searchJournal(issn);
        if (journal != null) {
            for (Subscriber subscriber : subscribers) {
                List<Subscription> subscriptions = getSubscriptions(subscriber);

                for (Subscription subscription : subscriptions) {
                    if (subscription.getJournal().equals(journal) && subscription.datesInRange(year, month)) {
                        int startMonth = subscription.getDates().getStartMonth();
                        int endMonth = subscription.getDates().getEndMonth();
                        int frequency = journal.getFrequency();

                        if (canSend(startMonth, endMonth, frequency, month)) {
                            System.out.println("Will send " + subscription.getCopies() + " copies of " +
                                    journal.getName() + " to subscriber " + subscriber.getName() + " at "+month + "/" + year);
                        }
                    }
                }
            }
        } else {
            System.out.println("Journal with ISSN " + issn + " not found.");
        }
    }

    private boolean canSend(int startMonth, int endMonth, int frequency, int currentMonth) {
        int monthsSinceStart = (currentMonth - startMonth + 12) % 12;
        return monthsSinceStart % frequency == 0 && currentMonth <= endMonth;
    }


    public void listAllSendingOrders(int year, int month) {
        System.out.println("Sending Orders for " + month + "/" + year + ":");

        for (Subscriber subscriber : subscribers) {
            List<Subscription> subscriptions = getSubscriptions(subscriber);

            for (Subscription subscription : subscriptions) {
                Journal journal = subscription.getJournal();

                if (subscription.datesInRange(year, month)) {
                    int startMonth = subscription.getDates().getStartMonth();
                    int endMonth = subscription.getDates().getEndMonth();
                    int frequency = journal.getFrequency();

                    if (canSend(startMonth, endMonth, frequency, month)) {
                        System.out.println("Will send " + subscription.getCopies() + " copies of " +
                                journal.getName() + " to subscriber " + subscriber.getName()+ " at "+month + "/" + year);
                    }
                }
            }
        }
    }


    public void listIncompletePayments() {
        System.out.println("Incomplete Payments:");

        for (Subscriber subscriber : subscribers) {
            List<Subscription> subscriptions = getSubscriptions(subscriber);

            for (Subscription subscription : subscriptions) {
                double requiredPayment = subscription.getJournal().getIssuePrice();
                double receivedPayment = subscription.getPayment().getReceivedPayment();

                if (receivedPayment < requiredPayment) {
                    System.out.println("Subscriber " + subscriber.getName() + " has an incomplete payment for journal " +
                            subscription.getJournal().getName() + ". Required: " +
                            requiredPayment + ", Received: " + receivedPayment);
                }
            }
        }
    }


    public void listSubscriptionsBySubscriber(String subscriberName) {
        System.out.println("Subscriptions for subscriber " + subscriberName + ":");

        Subscriber subscriber = searchSubscriber(subscriberName);
        if (subscriber != null) {
            List<Subscription> subscriptions = getSubscriptions(subscriber);

            for (Subscription subscription : subscriptions) {
                System.out.println("Journal: " + subscription.getJournal().getName() +
                        ", Copies: " + subscription.getCopies());
            }
        } else {
            System.out.println("Subscriber with name " + subscriberName + " not found.");
        }
    }


    public void listSubscriptionsByIssn(String issn) {
        System.out.println("Subscriptions for journal with ISSN " + issn + ":");

        Journal journal = searchJournal(issn);
        if (journal != null) {
            for (Subscriber subscriber : subscribers) {
                List<Subscription> subscriptions = getSubscriptions(subscriber);

                for (Subscription subscription : subscriptions) {
                    if (subscription.getJournal().equals(journal)) {
                        System.out.println("Subscriber: " + subscriber.getName() +
                                ", Copies: " + subscription.getCopies());
                    }
                }
            }
        } else {
            System.out.println("Journal with ISSN " + issn + " not found.");
        }
    }


    public synchronized void saveState(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(subscribers);
            oos.writeObject(journals);
            oos.writeObject(subscriberSubscriptions);
            System.out.println("State saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void loadState(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            subscribers = (Vector<Subscriber>) ois.readObject();
            journals = (Hashtable<String, Journal>) ois.readObject();
            subscriberSubscriptions = (Hashtable<Subscriber, List<Subscription>>) ois.readObject();
            System.out.println("State loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading state: " + e.getMessage());
        }
    }


    public void report() {
        Thread reportThread = new Thread(() -> {
            System.out.println("Generating report...");

            for (Subscriber subscriber : subscribers) {
                List<Subscription> subscriptions = getSubscriptions(subscriber);

                for (Subscription subscription : subscriptions) {
                    DateInfo dates = subscription.getDates();
                    if (dates.getEndYear() == 2024 && dates.getEndMonth() < 6) {
                        System.out.println("Subscription to expire: " + subscription);
                    }
                }
            }



            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Report generated successfully.");
        });

        reportThread.start();

        try {
            reportThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Hashtable<String, Journal> getJournals() {
        return journals;
    }

    public Vector<Subscriber> getSubscribers() {
        return subscribers;
    }

    public Hashtable<Subscriber, List<Subscription>> getSubscriberSubscriptions() {
        return subscriberSubscriptions;
    }


}

