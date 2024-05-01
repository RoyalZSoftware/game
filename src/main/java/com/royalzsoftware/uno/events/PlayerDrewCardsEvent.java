package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;

public class PlayerDrewCardsEvent implements Event {
    public PlayerDrewCardsEvent(int count) { }

    @Override
    public String getIdentifier() {
        return "playerdrewcards";
    }
}
