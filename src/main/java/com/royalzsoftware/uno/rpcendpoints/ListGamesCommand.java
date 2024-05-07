package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.UnoPlayer;

public class ListGamesCommand extends AuthenticatedCommandBase {

    private GameRepository gameRepository;

    public ListGamesCommand(UserRepository repository, GameRepository gameRepository) {
        super(repository);
        this.gameRepository = gameRepository;
    }

    @Override
    protected Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        var games = gameRepository.getGames();

        return new Response(
            0,
            games
        );
    }

    @Override
    public String getIdentifier() {
        return "list";
    }
    
}
