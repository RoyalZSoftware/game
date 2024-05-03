package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.uno.UnoPlayer;

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
