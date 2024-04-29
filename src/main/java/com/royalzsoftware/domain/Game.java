package com.royalzsoftware.domain;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.domain.events.PlayerJoinedEvent;
import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;

public class Game {
    private EventBroker broker;

    private int round = 0;
    private List<Player> players = new ArrayList<>();

    public Game(EventBroker broker) {
        this.broker = broker;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        this.notifyGamePlayers(new PlayerJoinedEvent(player));
    }

    private void notifyGamePlayers(Event event) {
        this.broker.publish(event, players.stream().map(p -> p.subscriber).toList());
    }
}
