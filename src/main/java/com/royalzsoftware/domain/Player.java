package com.royalzsoftware.domain;

import com.royalzsoftware.authentication.INotifiablePlayer;
import com.royalzsoftware.eventstream.Subscriber;

public class Player implements INotifiablePlayer {
    

    public final String username;
    public Subscriber subscriber;

    public Player(String username, Subscriber subscriber) {
        this.username = username;
        this.subscriber = subscriber;
    }

    @Override
    public String getIdentifier() {
        return this.username;
    }

    @Override
    public Subscriber getSubscriber() {
        return this.subscriber;
    }
}
