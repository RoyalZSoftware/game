package com.royalzsoftware.domain;

import com.royalzsoftware.eventstream.Subscriber;
import com.royalzsoftware.identification.Identifiable;

public class Player implements Identifiable {
    

    public final String username;
    private Subscriber subscriber;

    public Player(String username) {
        this.username = username;
        Identifiable.Register(this);
    }

    @Override
    public String getIdentifier() {
        return this.username;
    }

    @Override
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Subscriber getSubscriber() {
        return this.subscriber;
    }
}
