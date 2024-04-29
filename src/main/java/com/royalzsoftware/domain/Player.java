package com.royalzsoftware.domain;

import com.royalzsoftware.eventstream.Subscriber;

public class Player {
    public final String username;
    public final Subscriber subscriber;

    public Player(String username, Subscriber subscriber) {
        this.username = username;
        this.subscriber = subscriber;
    }
}
