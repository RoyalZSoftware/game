package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;

public class CardPlayedEvent implements Event {

    @Override
    public String getIdentifier() {
        return "cardplayed";
    }
}
