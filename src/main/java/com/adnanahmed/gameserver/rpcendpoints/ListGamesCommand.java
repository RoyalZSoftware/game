package com.adnanahmed.gameserver.rpcendpoints;

import java.util.Collection;

import com.adnanahmed.domain.Game;
import com.adnanahmed.gameserver.GameRepository;
import com.adnanahmed.rpc.Command;
import com.adnanahmed.rpc.InvalidRequestException;
import com.adnanahmed.rpc.Request;
import com.adnanahmed.rpc.Response;

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
