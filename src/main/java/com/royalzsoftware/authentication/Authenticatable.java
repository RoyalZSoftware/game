package com.royalzsoftware.authentication;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.Subscriber;

public interface Authenticatable {
    public static List<Authenticatable> authenticatables = new ArrayList<>();
    
    public static Authenticatable Find(String identifier) {
        return authenticatables.stream().filter(t -> t.getIdentifier().equalsIgnoreCase(identifier)).findFirst().get();
    }

    public static void Register(Authenticatable authenticatable) {
        authenticatables.add(authenticatable);
    }

    String getIdentifier();
    void setSubscriber(Subscriber subscriber);
    Subscriber getSubscriber();
}
