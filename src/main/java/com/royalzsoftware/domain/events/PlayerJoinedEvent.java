package com.royalzsoftware.domain.events;

import com.royalzsoftware.authentication.Authenticatable;
import com.royalzsoftware.eventstream.Event;

public class PlayerJoinedEvent implements Event {

    public Authenticatable player;

    public PlayerJoinedEvent(Authenticatable player) {
        this.player = player;
    }

    @Override
    public String getIdentifier() {
        return "playerjoined" + this.player.getIdentifier();
    }
}
