package com.royalzsoftware.gameserver;

import java.util.Collection;
import java.util.HashMap;

import com.royalzsoftware.domain.Game;

public class GameRepository {
    private HashMap<String, Game> games = new HashMap<>();

    public GameRepository() {
    }

    public Game findGame(String identifier) {
        return this.games.get(identifier);
    }

    public Collection<Game> getAll() {
        return games.values();
    }

    public String registerGame(Game game) {
        String id = Integer.toString(this.games.size());

        this.games.put(id, game);

        return id;
    }
}
