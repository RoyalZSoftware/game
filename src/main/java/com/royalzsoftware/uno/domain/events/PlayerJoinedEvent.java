package com.royalzsoftware.uno.domain.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.uno.domain.UnoPlayer;

public class PlayerJoinedEvent implements Event {

    public UnoPlayer player;

    public PlayerJoinedEvent(UnoPlayer player) {
        this.player = player;
    }

    @Override
    public String getIdentifier() {
        return "joined";
    }
}
