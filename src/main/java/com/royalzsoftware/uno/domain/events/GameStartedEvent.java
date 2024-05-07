package com.royalzsoftware.uno.domain.events;

import com.royalzsoftware.eventstream.Event;

public class GameStartedEvent implements Event {

    @Override
    public String getIdentifier() {
        return "gamestarted";
    }
}
