package com.royalzsoftware.eventstream;

import com.royalzsoftware.serialization.Serializable;

public interface Event {
    public String getIdentifier();
    public Serializable getPayload();
}
