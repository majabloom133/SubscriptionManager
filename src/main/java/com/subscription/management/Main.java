package com.subscription.management;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Initialize data access and logic processor
        SubscriberDAO dao = new SubscriberDAO();
        SubscriberProcessor processor = new SubscriberProcessor();

        // Populate database with test cases
        dao.save(new Subscriber(1, "premium_user@test.com", Plan.PRO, true, 1));
        dao.save(new Subscriber(2, "free_user@test.com", Plan.FREE, true, 1));
        dao.save(new Subscriber(3, "inactive_user@test.com", Plan.BASIC, false, 1));



        // --- FOR PART 1: Defining Business Rules using Lambdas



        // Rule 1: Active Subscriber
    SubscriberFilter isActive = s -> s.isActive();

        // Rule 2: Expiring (0 or 1 month left)
    SubscriberFilter isExpiring = s -> s.getMonthsRemaining() <= 1;

        // Rule 5: Paying Subscriber (NOT 'FREE')
    SubscriberFilter isPaying = s -> s.getPlan() != Plan.FREE;

        // Rule 6: Extend Subscription Action
    SubscriberAction extendAction = s -> s.setMonthsRemaining(s.getMonthsRemaining() + 12);



        // --- PART 2: Running Scenarios ---



        System.out.println("--- Scenario 4: Extend Paying Expiring Subscribers ---");

        // Combine multiple rules into 1 filter inside method call
        processor.applyToMatching(
                dao.findAll(),
                s -> isActive.matches(s) && isPaying.matches(s) && isExpiring.matches(s),
                extendAction
        );

        // Display results to see if the months were increased
        dao.findAll().forEach(System.out::println);
    }
}
