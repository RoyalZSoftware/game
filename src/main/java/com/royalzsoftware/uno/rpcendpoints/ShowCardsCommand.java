package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.UnoPlayer;

public class ShowCardsCommand extends AuthenticatedCommandBase {

    private GameRepository gameRepository;
    public ShowCardsCommand(UserRepository repository, GameRepository gameRepository) {
        super(repository);
        this.gameRepository = gameRepository;
    }

    @Override
    protected Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        var game = this.gameRepository.getGameForPlayer(player);
        if (game == null) {
            throw new InvalidRequestException("Not in game.");
        }

        return new Response(0, player.getCards());
    }

    @Override
    public String getIdentifier() {
        return "showcards";
    }
    
}
