package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.Uno;
import com.royalzsoftware.uno.UnoPlayer;

public class JoinGameCommand extends AuthenticatedCommandBase {
    public JoinGameCommand(GameRepository gameRepository) {
        super(gameRepository);
    }

    @Override
    public Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        var gameId = request.args.get("gameId");
        if (gameId == null) {
            throw new InvalidRequestException();
        }

        Uno game = this.gameRepository.findGame(gameId);

        if (game == null) {
            return new Response(1, "Game not found.");
        }

        this.gameRepository.addPlayerToGame(player, game);
        game.addPlayer(player);
        return new Response(0, "OK");
    }

    @Override
    public String getIdentifier() {
        return "join";
    }
    
}
