package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.serialization.NumberSerializer;
import com.royalzsoftware.serialization.Serializable;

public class PlayerDrewCardsEvent implements Event {
    private int count;

    public PlayerDrewCardsEvent(int count) {
        this.count = count;
    }

    @Override
    public String getIdentifier() {
        return "playerdrewcards";
    }

    @Override
    public Serializable getPayload() {
        //return new NumberSerializer(this.count);
        return null;
    }
}
