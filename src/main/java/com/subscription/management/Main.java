package com.subscription.management;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Initialize data access + logic processor
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
        // Rule 3: Active and Expiring Subscriber (Combination)
    SubscriberFilter isActiveAndExpiring = s -> isActive.matches(s) && isExpiring.matches(s);

        // Rule 5: Paying Subscriber (NOT 'FREE')
    SubscriberFilter isPaying = s -> s.getPlan() != Plan.FREE;
        // Rule 6: Extend Subscription Action
    SubscriberAction extendAction = s -> s.setMonthsRemaining(s.getMonthsRemaining() + 12);
        // Rule 7: Deactivate Subscriber (turns off the account)
        SubscriberAction deactivateAction = s -> s.setActive(false);


        // --- PART 2: Running Scenarios ---

        // SCENARIO 4:
        System.out.println("--- Scenario 4: Extend Paying Expiring Subscribers ---");
        processor.applyToMatching(
                dao.findAll(),
                // Using rule 3 + 5
                s -> isActiveAndExpiring.matches(s) && isPaying.matches(s),
                extendAction
        );

        // SCENARIO 5: Deactivate Expired Free Users
        System.out.println("\n--- Scenario 5: Deactivate Expired Free Users ---");
        processor.applyToMatching(
                dao.findAll(),
                // Plan is FREE - AND time has run out
                s -> s.getPlan() == Plan.FREE && s.getMonthsRemaining() == 0,
                deactivateAction
        );

        // SCENARIO 6: Upgrade BASIC to PRO
        System.out.println("\n--- Scenario 6: Upgrading BASIC users to PRO ---");
        // Create an Action in the call
        SubscriberAction upgradeAction = s -> s.setPlan(Plan.PRO);
        processor.applyToMatching(
                dao.findAll(),
                // Filter: find everyone with Basic Plan
                s -> s.getPlan() == Plan.BASIC,
                // Action: Upgrade them!
                upgradeAction
        );

        // SCENARIO 7: Find or Create (Safety Net)
        System.out.println("\n--- Scenario 7: Find or Create ID 99 ---");
        // Creates a new subscriber since ID 99 doesn't exist
        dao.findOrCreate(99, "new_user@test.com");

        // FINAL REPORT
        System.out.println("\n--- Final Status of all Subscribers ---");
        // Using Method Reference to print each subscriber
        dao.findAll().forEach(System.out::println);
    }
}
