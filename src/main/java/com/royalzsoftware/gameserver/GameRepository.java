package com.royalzsoftware.gameserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.royalzsoftware.uno.Uno;
import com.royalzsoftware.uno.UnoPlayer;

public class GameRepository {
    private ArrayList<Trackable<Uno>> games = new ArrayList<>();
    private HashMap<String, Uno> playerGames = new HashMap<>();

    public Uno findGame(String identifier) {
        return this.games.stream().filter(c -> c.getId() == identifier).findFirst().get().getItem();
    }

    public Uno findGameFor(UnoPlayer player) {
        return this.playerGames.get(player.getIdentifier());
    }

    public Collection<Trackable<Uno>> getAll() {
        return games;
    }

    public String registerGame(Uno game) {
        String id = Integer.toString(this.games.size());

        this.games.add(new Trackable<Uno>(id, game));

        return id;
    }

    public void addPlayerToGame(UnoPlayer player, Uno uno) {
        this.playerGames.put(player.getIdentifier(), uno);
    }

    public String getGameId(Uno game) {
        return this.games.stream().filter(t -> t.getItem().equals(game)).findFirst().get().getId();
    }
}
