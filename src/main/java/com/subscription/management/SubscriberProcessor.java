package com.subscription.management;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriberProcessor {

    // Filter list based on SubscriberFilter rule
    public List<Subscriber> findSubscribers(List<Subscriber> list, SubscriberFilter filter) {
        return list.stream()
                .filter(s -> filter.matches(s))
                .collect(Collectors.toList())
    }

    // Find matching subscribers and perform an action on them
public List<Subscriber> applyToMatching(List<Subscriber> list, SubscriberFilter filter, SubscriberAction action) {
        List<Subscriber> matches = findSubscribers(list, filter);
        matches.forEach(s -> action.run(s));
        return matches;
}
}
