package com.royalzsoftware.eventstream;

public interface Event {
    public String getIdentifier();
    public String serialize();
}
