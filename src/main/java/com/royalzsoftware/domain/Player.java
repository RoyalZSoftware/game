package com.royalzsoftware.domain;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.Subscriber;

public class Player {
    
    public static List<Player> players = new ArrayList<Player>();

    public final String username;
    public Subscriber subscriber;

    public Player(String username, Subscriber subscriber) {
        this.username = username;
        this.subscriber = subscriber;
        players.add(this);
    }
}
