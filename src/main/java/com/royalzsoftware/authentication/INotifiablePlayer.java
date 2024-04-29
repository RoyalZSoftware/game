package com.royalzsoftware.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.royalzsoftware.eventstream.Subscriber;

public interface INotifiablePlayer {
    public static HashMap<String, INotifiablePlayer> notifiablePlayers = new HashMap<String, INotifiablePlayer>();
    String getIdentifier();
    Subscriber getSubscriber();
}
