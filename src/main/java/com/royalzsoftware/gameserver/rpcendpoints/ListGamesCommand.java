package com.royalzsoftware.gameserver.rpcendpoints;

import java.util.Collection;

import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.gameserver.Trackable;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.Uno;
import com.royalzsoftware.uno.UnoPlayer;

public class ListGamesCommand extends AuthenticatedCommandBase {
    

    public ListGamesCommand(GameRepository gameRepository) {
        super(gameRepository);
    }

    @Override
    public Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        Collection<Trackable<Uno>> games = this.gameRepository.getAll();
        return new Response(0, games);
    }

    @Override
    public String getIdentifier() {
        return "listgames";
    }
}
