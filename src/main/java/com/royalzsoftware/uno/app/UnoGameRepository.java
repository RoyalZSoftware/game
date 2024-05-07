package com.royalzsoftware.uno.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.royalzsoftware.uno.domain.Uno;
import com.royalzsoftware.uno.domain.UnoPlayer;
import com.royalzsoftware.uno.rpcendpoints.GameRepository;
import com.royalzsoftware.uno.rpcendpoints.Trackable;

public class UnoGameRepository implements GameRepository {
    private ArrayList<Trackable<Uno>> unos = new ArrayList<>();
    private HashMap<String, Trackable<Uno>> playerUnos = new HashMap<>();

    @Override
    public Trackable<Uno> addGame(Uno uno) {
        var trackableUno = new Trackable<Uno>(Integer.toString(this.unos.size()), uno);
        this.unos.add(trackableUno);
        return trackableUno;
    }

    public void addPlayerToGame(UnoPlayer player, Trackable<Uno> uno) {
        this.playerUnos.put(player.username, uno);
    }

    @Override
    public Trackable<Uno> getGameForPlayer(UnoPlayer player) {
        return this.playerUnos.get(player.username);
    }

    @Override
    public Trackable<Uno> getGameById(String id) {
        var first = this.unos.stream().filter(t -> t.getId().equalsIgnoreCase(id)).findFirst();
        if (!first.isPresent()) return null;
        return first.get();
    }

    @Override
    public List<Trackable<Uno>> getGames() {
        return this.unos;
    }
    
}
