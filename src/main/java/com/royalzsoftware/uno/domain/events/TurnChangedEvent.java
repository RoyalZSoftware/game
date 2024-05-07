package com.royalzsoftware.uno.domain.events;

import com.royalzsoftware.eventstream.Event;

public class TurnChangedEvent implements Event {
    @Override
    public String getIdentifier() {
        return "turnchanged";
    }
}
