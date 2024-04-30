package com.royalzsoftware.domain.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.identification.Identifiable;

public class PlayerJoinedEvent implements Event {

    public Identifiable player;

    public PlayerJoinedEvent(Identifiable player) {
        this.player = player;
    }

    @Override
    public String getIdentifier() {
        return "playerjoined" + this.player.getIdentifier();
    }
}
