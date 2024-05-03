package com.royalzsoftware.identification;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.Subscriber;

public interface Identifiable {
    public static List<Identifiable> identifiables = new ArrayList<>();
    
    public static Identifiable Find(String identifier) {
        return identifiables.stream().filter(t -> t.getIdentifier().equalsIgnoreCase(identifier)).findFirst().get();
    }

    public static void Register(Identifiable authenticatable) {
        identifiables.add(authenticatable);
    }
    
    public static void Unregister(Identifiable identifiable) {
        identifiables.remove(identifiable);
    }

    String getIdentifier();
    void setSubscriber(Subscriber subscriber);
    void notifyAboutEvent(Event event);
}
