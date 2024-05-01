package com.royalzsoftware.gameserver;

import java.util.Collection;
import java.util.HashMap;

import com.royalzsoftware.uno.Uno;

public class GameRepository {
    private HashMap<String, Uno> games = new HashMap<>();

    public Uno findGame(String identifier) {
        return this.games.get(identifier);
    }

    public Collection<Uno> getAll() {
        return games.values();
    }

    public String registerGame(Uno game) {
        String id = Integer.toString(this.games.size());

        this.games.put(id, game);

        return id;
    }
}
