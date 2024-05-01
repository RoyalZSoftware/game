package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.serialization.Serializable;

public class TurnChangedEvent implements Event {
    @Override
    public String getIdentifier() {
        return "turnchanged";
    }

    @Override
    public Serializable getPayload() {
        return null;
    }
}
