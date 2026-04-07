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
    public Optional<Subscriber> findbyId(int id) {
        return subscribers.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
}
