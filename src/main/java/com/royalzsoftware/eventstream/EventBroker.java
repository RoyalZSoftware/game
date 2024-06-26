package com.royalzsoftware.eventstream;

import java.util.ArrayList;

public class EventBroker {
    
    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    public EventBroker() { }

    public void publish(Event event) {
        for (Subscriber subscriber : this.subscribers) {
            subscriber.eventReceived(event);
        }
    }

    public void publish(Event event, Subscriber subscriber) {
        subscriber.eventReceived(event);
    }

    public void publish(Event event, Iterable<Subscriber> subscribers) {
        for (Subscriber subscriber: subscribers) {
            publish(event, subscriber);
        }
    }

    public void subscribe(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }
}
