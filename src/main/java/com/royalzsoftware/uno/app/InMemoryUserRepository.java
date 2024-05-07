package com.royalzsoftware.uno.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.royalzsoftware.eventstream.Subscriber;
import com.royalzsoftware.uno.domain.UnoPlayer;
import com.royalzsoftware.uno.rpcendpoints.UserRepository;

public class InMemoryUserRepository implements UserRepository {

    private List<UnoPlayer> unoPlayers = new ArrayList<>();
    private HashMap<UnoPlayer, Subscriber> subscribers = new HashMap<>();

    @Override
    public void register(UnoPlayer player) {
        this.unoPlayers.add(player);
    }

    @Override
    public Optional<UnoPlayer> getPlayer(String identifier) {
        this.unoPlayers.stream().forEach(t -> System.out.println(t.username));
        return this.unoPlayers.stream().filter(t -> t.username.equalsIgnoreCase(identifier)).findFirst();
    }

    public void registerSubscriber(UnoPlayer player, Subscriber subscriber) {
        this.subscribers.put(player, subscriber);
    }

    @Override
    public Subscriber getEventChannelForPlayer(UnoPlayer player) {
        return this.subscribers.get(player);
    }
}
