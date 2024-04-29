package com.royalzsoftware.domain;

import java.util.ArrayList;

import com.royalzsoftware.domain.events.PlayerJoinedEvent;
import com.royalzsoftware.eventstream.EventBroker;

public class Game {
    private EventBroker broker;

    private int round;
    private ArrayList<Player> players = new ArrayList<>();

    public Game(EventBroker broker) {
        this.broker = broker;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        this.broker.publish(new PlayerJoinedEvent(player));
    }
}
