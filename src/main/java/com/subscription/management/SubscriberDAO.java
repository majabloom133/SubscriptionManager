package com.subscription.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriberDAO {

    // Internal list, like a database
    private List<Subscriber> subscribers = new ArrayList<>();

    // Save new Subscriber to the list
    public void save(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    // Retrieve all subscribers
    public List<Subscriber> findAll() {
        return new ArrayList<>(subscribers);
    }

    // Find specific Subscriber by ID - using Stream
    public Optional<Subscriber> findById(int id) {
        return subscribers.stream()
                .filter(s -> s.getId() == id)
                // Returns an optional
                .findFirst();
    }


    // Find subscriber or create and save a new one if one is not found.  This uses Optional to decide what to do.
    public Subscriber findOrCreate(int id, String email) {
        return findById(id).orElseGet(() -> {
            // If empty - create new Free subscriber and save it
            Subscriber newSubscriber = new Subscriber(id, email, Plan.FREE, true, 0);
            save(newSubscriber);
            System.out.println("No subscriber found with ID " + id + ". Created a new one for " + email);
            return newSubscriber;
            });
        }
    }
