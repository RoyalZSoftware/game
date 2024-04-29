package com.royalzsoftware.gameserver.rpcendpoints;

import java.util.Collection;

import com.royalzsoftware.domain.Game;
import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public class ListGamesCommand implements Command {
    
    private GameRepository gameRepository;

    public ListGamesCommand(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Response handle(Request request) throws InvalidRequestException {
        Collection<Game> games = this.gameRepository.getAll();
        return new Response(0, Integer.toString(games.size()));
    }

    @Override
    public String getIdentifier() {
        return "listgames";
    }
}
