package com.adnanahmed.server.events;

import com.adnanahmed.domain.Player;
import com.adnanahmed.eventstream.Event;

public class JoinedEvent implements Event {

    private final Player player;
    
    public JoinedEvent(Player player) {
        this.player = player;
    }

    @Override
    public String getIdentifier() {
        // TODO Auto-generated method stub
        return "joined";
    }

    @Override
    public String getPayload() {
        return this.player.username;
    }
    
}
