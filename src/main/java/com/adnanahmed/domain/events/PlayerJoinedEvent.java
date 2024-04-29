package com.adnanahmed.domain.events;

import com.adnanahmed.domain.Player;
import com.adnanahmed.eventstream.Event;

public class PlayerJoinedEvent implements Event {

    public Player player;

    public PlayerJoinedEvent(Player player) {
        this.player = player;
    }

    @Override
    public String getIdentifier() {
        return "playerjoined";
    }

    @Override
    public String serialize() {
        return "playerjoined";
    }
}
