package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.serialization.Serializable;
import com.royalzsoftware.uno.UnoPlayer;

public class PlayerJoinedEvent implements Event {

    private UnoPlayer player;

    public PlayerJoinedEvent(UnoPlayer player) {
        this.player = player;
    }

    @Override
    public String getIdentifier() {
        return "joined";
    }

    @Override
    public Serializable getPayload() {
        return null; //new StringSerializer(player.username);
    }
    
}
