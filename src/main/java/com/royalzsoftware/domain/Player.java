package com.royalzsoftware.domain;

import com.royalzsoftware.authentication.Authenticatable;
import com.royalzsoftware.eventstream.Subscriber;

public class Player implements Authenticatable {
    

    public final String username;
    private Subscriber subscriber;

    public Player(String username) {
        this.username = username;
        Authenticatable.Register(this);
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
