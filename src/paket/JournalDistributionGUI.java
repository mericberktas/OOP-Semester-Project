package paket;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.io.Serializable;

public class JournalDistributionGUI extends JFrame implements Serializable{
    private Distributor distributor;

    public JournalDistributionGUI() {
        super("Journal Distribution System");
        distributor = new Distributor();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(15, 1));

        JButton createIndividualButton = new JButton("Create Individual");
        JButton createCorporationButton = new JButton("Create Corporation");
        JButton createJournalButton = new JButton("Create Journal");
        JButton createSubscriptionButton = new JButton("Create Subscription");
        JButton searchJournalButton = new JButton("Search Journal");
        JButton searchSubscriberButton = new JButton("Search Subscriber");
        JButton listSendingOrdersButton = new JButton("List Sending Orders");
        JButton listAllSendingOrdersButton = new JButton("List All Sending Orders");
        JButton listIncompletePaymentsButton = new JButton("List Incomplete Payments");
        JButton makePaymentButton = new JButton("Make Payment");
        JButton listSubscriptionsBySubscriberButton = new JButton("List Subscriptions By Subscriber");
        JButton listSubscriptionsByIssnButton = new JButton("List Subscriptions By ISSN");
        JButton saveStateButton = new JButton("Save State");
        JButton loadStateButton = new JButton("Load State");
        JButton reportButton = new JButton("Report");


        add(createIndividualButton);
        add(createCorporationButton);
        add(createJournalButton);
        add(createSubscriptionButton);
        add(searchJournalButton);
        add(searchSubscriberButton);
        add(listSendingOrdersButton);
        add(listAllSendingOrdersButton);
        add(listIncompletePaymentsButton);
        add(makePaymentButton);
        add(listSubscriptionsBySubscriberButton);
        add(listSubscriptionsByIssnButton);
        add(saveStateButton);
        add(loadStateButton);
        add(reportButton);

        createIndividualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createIndividual();
            }
        });

        createCorporationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCorporation();
            }
        });

        createJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createJournal();
            }
        });

        createSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSubscription();
            }
        });

        searchJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchJournal();
            }
        });

        searchSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSubscriber();
            }
        });

        listSendingOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listSendingOrders();
            }
        });

        listAllSendingOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllSendingOrders();
            }
        });

        listIncompletePaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIncompletePayments();
            }
        });

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makePayment();
            }
        });

        listSubscriptionsBySubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listSubscriptionsBySubscriber();
            }
        });

        listSubscriptionsByIssnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listSubscriptionsByIssn();
            }
        });

        saveStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveState();
            }
        });

        loadStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadState();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report();
            }
        });
    }

    private void createIndividual() {
        String name = JOptionPane.showInputDialog(this, "Enter Individual Name:");
        String address = JOptionPane.showInputDialog(this, "Enter Individual Address:");
        String creditCardNumber = JOptionPane.showInputDialog(this, "Enter Credit Card Number:");
        int expireYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Credit Card Expiry Year:"));
        int expireMonth = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Credit Card Expiry Month:"));
        int ccv = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Credit Card CCV:"));

        Individual individual = new Individual(name, address, creditCardNumber, expireYear, expireMonth, ccv);
        distributor.addSubscriber(individual);

        JOptionPane.showMessageDialog(this, "Individual created successfully!");
    }

    private void createCorporation() {
        String name = JOptionPane.showInputDialog(this, "Enter Corporation Name:");
        String address = JOptionPane.showInputDialog(this, "Enter Corporation Address:");
        int bankCode = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Bank Code:"));
        String bankName = JOptionPane.showInputDialog(this, "Enter Bank Name:");
        int issueMonth = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Issue Month:"));
        int issueYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Issue Year:"));
        int accountNumber = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Account Number:"));

        Corporation corporation = new Corporation(name, address, bankCode, bankName, issueMonth, issueYear, accountNumber);
        distributor.addSubscriber(corporation);

        JOptionPane.showMessageDialog(this, "Corporation created successfully!");
    }

    private void createJournal() {
        String name = JOptionPane.showInputDialog(this, "Enter Journal Name:");
        String issn = JOptionPane.showInputDialog(this, "Enter ISSN:");
        int frequency = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Frequency:"));
        double issuePrice = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Issue Price:"));

        Journal journal = new Journal(name, issn, frequency, issuePrice);
        distributor.addJournal(journal);

        JOptionPane.showMessageDialog(this, "Journal created successfully!");
    }

    private void createSubscription() {
        int startYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Start Year:"));
        int startMonth = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Start Month:"));
        int endMonth = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter End Month:"));

        double discountRatio = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Discount Ratio:"));

        String subscriberName = JOptionPane.showInputDialog(this, "Enter Subscriber Name:");
        String journalIssn = JOptionPane.showInputDialog(this, "Enter Journal ISSN:");

        DateInfo dateInfo = new DateInfo(startYear, startMonth, endMonth);
        PaymentInfo paymentInfo = new PaymentInfo(discountRatio);

        Journal journal = distributor.searchJournal(journalIssn);
        Subscriber subscriber = distributor.searchSubscriber(subscriberName);

        if (journal != null && subscriber != null) {
            Subscription subscription = new Subscription(dateInfo, paymentInfo, 1, journal, subscriber);
            distributor.addSubscription(journalIssn, subscriberName, dateInfo, paymentInfo, 1);

            JOptionPane.showMessageDialog(this, "Subscription created successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Journal or Subscriber not found!");
        }
    }

    private void searchJournal() {
        String issn = JOptionPane.showInputDialog(this, "Enter Journal ISSN:");
        Journal journal = distributor.searchJournal(issn);

        if (journal != null) {
            JOptionPane.showMessageDialog(this, "Journal found:\n" + journal.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Journal not found.");
        }
    }

    private void searchSubscriber() {
        String subscriberName = JOptionPane.showInputDialog(this, "Enter Subscriber Name:");
        Subscriber subscriber = distributor.searchSubscriber(subscriberName);

        if (subscriber != null) {
            JOptionPane.showMessageDialog(this, "Subscriber found:\n" + subscriber.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Subscriber not found.");
        }
    }

    private void listSendingOrders(){
        String issn = JOptionPane.showInputDialog(this,"Enter issn:");
        int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Year:"));
        int month = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Month:"));

        distributor.listSendingOrders(issn,month,year);
    }

    private void listAllSendingOrders() {
        int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Year:"));
        int month = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Month:"));

        distributor.listAllSendingOrders(year,month);
        JOptionPane.showMessageDialog(this, "All Sending Orders listed for " + month + "/" + year + ".");
    }

    private void listIncompletePayments() {
        distributor.listIncompletePayments();
        JOptionPane.showMessageDialog(this, "Incomplete Payments listed.");
    }

    private void makePayment() {
        Vector<Subscriber> subscribers = distributor.getSubscribers();
        Subscriber[] subscriberArray = new Subscriber[subscribers.size()];
        subscribers.copyInto(subscriberArray);

        Subscriber selectedSubscriber = (Subscriber) JOptionPane.showInputDialog(this,
                "Select a Subscriber to Make Payment:",
                "Make Payment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                subscriberArray,
                subscriberArray[0]);

        if (selectedSubscriber != null) {
            double paymentAmount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Payment Amount:"));

            List<Subscription> subscriptions = distributor.getSubscriptions(selectedSubscriber);
            Subscription selectedSubscription = (Subscription) JOptionPane.showInputDialog(this,
                    "Select a Subscription to Make Payment:",
                    "Make Payment",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    subscriptions.toArray(), (subscriptions).get(0));

            if (selectedSubscription != null) {
                selectedSubscription.acceptPayment(paymentAmount);
                JOptionPane.showMessageDialog(this, "Payment made successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No subscription selected.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No subscriber selected.");
        }
    }

    private void listSubscriptionsBySubscriber() {
        String subscriberName = JOptionPane.showInputDialog(this, "Enter Subscriber Name:");
        distributor.listSubscriptionsBySubscriber(subscriberName);
    }

    private void listSubscriptionsByIssn() {
        String issn = JOptionPane.showInputDialog(this, "Enter Journal ISSN:");
        distributor.listSubscriptionsByIssn(issn);
    }

    private void saveState() {
        String fileName = JOptionPane.showInputDialog(this, "Enter the file name:");
        distributor.saveState(fileName);
    }

    private void loadState() {
        String fileName = JOptionPane.showInputDialog(this, "Enter the file name:");
        distributor.loadState(fileName);
    }

    private void report() {
        distributor.report();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JournalDistributionGUI().setVisible(true);
            }
        });
    }
}

