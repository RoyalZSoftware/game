package com.royalzsoftware.uno.rpcendpoints;

import java.util.Optional;

import com.royalzsoftware.eventstream.Subscriber;
import com.royalzsoftware.uno.domain.UnoPlayer;

public interface UserRepository {
    public void register(UnoPlayer player);
    public void registerSubscriber(UnoPlayer player, Subscriber subscriber);
    public Optional<UnoPlayer> getPlayer(String identifier);
    public Subscriber getEventChannelForPlayer(UnoPlayer player);
}
