package com.adnanahmed.domain.usecases;

import com.adnanahmed.domain.Game;
import com.adnanahmed.eventstream.EventBroker;

public class CreateGame {
    private EventBroker broker;

    public CreateGame(EventBroker broker) {
        this.broker = broker;
    }

    public Game execute() {
        return new Game(this.broker);
    }
}
