package com.subscription.management;
// A Contract - promise a method called 'run'

@FunctionalInterface
public interface SubscriberAction {

// Operation to perform on a Subscriber
    void run(Subscriber subscriber);
}
