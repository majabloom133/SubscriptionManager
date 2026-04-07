package com.subscription.management;
// A Contract - that there is a method called matches, that responds true or false.

@FunctionalInterface
public interface SubscriberFilter {

    // A rule that returns true or false for a subscriber
boolean matches(Subscriber subscriber);
}
