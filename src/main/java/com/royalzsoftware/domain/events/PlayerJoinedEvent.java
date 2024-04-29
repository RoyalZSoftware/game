package com.royalzsoftware.domain.events;

import com.royalzsoftware.domain.Player;
import com.royalzsoftware.eventstream.Event;

public class PlayerJoinedEvent implements Event {

    public Player player;

    public PlayerJoinedEvent(Player player) {
        this.player = player;
    }

    @Override
    public String getIdentifier() {
        return "playerjoined" + this.player.username;
    }
}
