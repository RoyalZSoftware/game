package com.adnanahmed.eventstream;

public interface Event {
    public String getIdentifier();
    public String getPayload();
}
