package com.royalzsoftware.uno.rpcendpoints;

import java.util.List;

import com.royalzsoftware.uno.domain.Uno;
import com.royalzsoftware.uno.domain.UnoPlayer;

public interface GameRepository {
    Trackable<Uno> addGame(Uno uno);
    void addPlayerToGame(UnoPlayer player, Trackable<Uno> uno);
    Trackable<Uno> getGameForPlayer(UnoPlayer player);
    Trackable<Uno> getGameById(String id);
    List<Trackable<Uno>> getGames();
}
