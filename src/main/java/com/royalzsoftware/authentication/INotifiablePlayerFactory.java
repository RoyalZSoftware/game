package com.royalzsoftware.authentication;

import com.royalzsoftware.eventstream.Subscriber;

public interface INotifiablePlayerFactory {
    INotifiablePlayer create(String identifier, Subscriber subscriber);
}
