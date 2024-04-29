package com.royalzsoftware.domain.usecases;

import com.royalzsoftware.domain.Game;
import com.royalzsoftware.eventstream.EventBroker;

public class CreateGame {
    private EventBroker broker;

    public CreateGame(EventBroker broker) {
        this.broker = broker;
    }

    public Game execute() {
        return new Game(this.broker);
    }
}
