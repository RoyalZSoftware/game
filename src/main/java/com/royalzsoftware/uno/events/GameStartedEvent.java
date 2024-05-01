package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.serialization.Serializable;

public class GameStartedEvent implements Event {

    @Override
    public String getIdentifier() {
        return "gamestarted";
    }

    @Override
    public Serializable getPayload() {
        return null;
    }
    
}
