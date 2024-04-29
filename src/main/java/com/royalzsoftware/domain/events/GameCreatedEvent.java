package com.royalzsoftware.domain.events;

import com.royalzsoftware.eventstream.Event;

public class GameCreatedEvent implements Event {

    @Override
    public String getIdentifier() {
        return "game_created";
    }
    
}
