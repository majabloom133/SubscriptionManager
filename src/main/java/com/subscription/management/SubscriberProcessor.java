package com.subscription.management;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriberProcessor {

    // Filter list based on SubscriberFilter rule
    // Takes a list and a filter -> returns new list containing only those who passed filter
    public List<Subscriber> findSubscribers(List<Subscriber> list, SubscriberFilter filter) {
        return list.stream()
                // "Does the subscriber match?"
                .filter(s -> filter.matches(s))
                // Gather all "yes" answers into a new list
                .collect(Collectors.toList());
    }

    // Find matching subscribers and perform an action on them, returns list.
public List<Subscriber> applyToMatching(List<Subscriber> list, SubscriberFilter filter, SubscriberAction action) {
        // Find people using our other method
        List<Subscriber> matches = findSubscribers(list, filter);
        // Tell action to 'run' the task on every person in matches list
        matches.forEach(s -> action.run(s));
        return matches;
}
}
