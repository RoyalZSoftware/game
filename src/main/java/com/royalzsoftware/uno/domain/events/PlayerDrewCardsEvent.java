package com.royalzsoftware.uno.domain.events;

import com.royalzsoftware.eventstream.Event;

public class PlayerDrewCardsEvent implements Event {
    private int count;

    public PlayerDrewCardsEvent(int count) {
        this.count = count;
    }

    @Override
    public String getIdentifier() {
        return "playerdrewcards";
    }
}
